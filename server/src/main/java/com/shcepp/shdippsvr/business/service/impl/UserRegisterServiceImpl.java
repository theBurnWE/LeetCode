package com.shcepp.shdippsvr.business.service.impl;

import com.shcepp.shdippsvr.business.config.UserConfig;
import com.shcepp.shdippsvr.business.dao.*;
import com.shcepp.shdippsvr.business.entity.ShdippBizUserEntity;
import com.shcepp.shdippsvr.business.entity.UmBizAuditEntity;
import com.shcepp.shdippsvr.business.entity.UmOrgEntity;
import com.shcepp.shdippsvr.business.entity.UmUserEntity;
import com.shcepp.shdippsvr.business.exception.MessageException;
import com.shcepp.shdippsvr.business.service.CaptchaService;
import com.shcepp.shdippsvr.business.service.UserRegisterService;
import com.shcepp.shdippsvr.oauth.dto.OAuthUserInfo;
import com.shcepp.shdippsvr.sys.service.BaseService;
import com.shcepp.shdippsvr.sys.util.Constants;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.util.Date;

/**
 * @description:
 * @author: zkmao
 * @date: 2018/8/24
 */

@Service
public class UserRegisterServiceImpl extends BaseService implements UserRegisterService {
    private final LambdaLogger logger = LambdaLoggerFactory.getLogger(getClass());
    
    @Autowired
    CaptchaService captchaService;
    
    @Autowired
    UmUserDao umUserDao;
    @Autowired
    UmOrgDao umOrgDao;
    @Autowired
    UmRoleDao umRoleDao;
    @Autowired
    UmUserRoleDao umUserRoleDao;
    @Autowired
    UmBizAuditDao umBizAuditDao;
    @Autowired
    OauthBizAppUserDao oauthBizAppUserDao;
    @Autowired
    ShdippBizUserDao shdippBizUserDao;
    
    /**
     * 发送验证码
     *
     * @param id
     * @param idType
     * @throws MessageException
     */
    @Override
    public void sendCaptcha(String id, String idType, String ip) throws MessageException {
        sendCaptchaByType(id, idType, ip, Constants.CAPTCHA_REG_TYPE);
    }
    
    /**
     * 发送重置密码验证码
     *
     * @param id
     * @param idType
     * @throws MessageException
     */
    @Override
    public void sendResetPwdCaptcha(String id, String idType, String ip) throws MessageException {
        sendCaptchaByType(id, idType, ip, Constants.CAPTCHA_RESET_TYPE);
    }
    
    private void sendCaptchaByType(String id, String idType, String ip, String captchaType) {
        //先进行ip校验，是否重复发送
        if(captchaService.checkPeriod(ip, id, idType, captchaType)) {
            
            //生成并保存短消息
            String captcha = captchaService.generateAndStoreCaptcha(id, idType, captchaType);
            //设置发送间隔点，用于后续校验
            captchaService.setPeriodPoint(ip, id, idType, captchaType);
            //发送验证码
            switch(idType) {
                case "1":
                    captchaService.sendCaptchaViaSM(id, captcha, captchaType);
                    
                    break;
                case "2":
                    captchaService.sendCaptchaViaEmail(id, captcha, captchaType);
                    break;
                default:
                    throw new MessageException(
                            Constants.USER_REG_ERROR.ERROR_CODE003,
                            Constants.USER_REG_ERROR.ERROR_INFO003);
            }
        }
        else {
            throw new MessageException(
                    Constants.USER_REG_ERROR.ERROR_CODE002,
                    Constants.USER_REG_ERROR.ERROR_INFO002);
        }
    }
    
    @Override
    public void resetPwd(String id, String idType, String newPwd) throws MessageException {
        try {
            //获取用户信息
            UmUserEntity user;
            switch(idType) {
                case "1":
                    user = umUserDao.findByMobile(id);
                    break;
                case "2":
                    user = umUserDao.findByMail(id);
                    break;
                default:
                    user = umUserDao.findByMobile(id);
                    break;
            }
            if(user == null) {
                throw new Exception("用户不存在");
            }
            //检查新密码符合规范
            if(!pwdCheck(newPwd)) {
                throw new Exception("新密码格式错误");
            }
            //对密码进行加密
            
            String tmpNewPwd = "";
            tmpNewPwd = md5Pass(newPwd);
            //更新密码
            user.setLoginPass(tmpNewPwd);
            //保存用户信息
            user.setModifyDate(new Date());
            umUserDao.save(user);
            
            //生成审计对象
            UmBizAuditEntity auditEntity = new UmBizAuditEntity();
            auditEntity.setLoginId(user.getLoginId());
            auditEntity.setOperId(user.getId());
            auditEntity.setAuditOperation("Modify");
            auditEntity.setAuditPermission("UserAdd");
            auditEntity.setOperType("USER");
            auditEntity.setFlag("1");
            auditEntity.setRecordTime(new Date());
            auditEntity.setDescription("重置密码");
            auditEntity.setDataDescription1("");
            auditEntity.setDataDescription2("");
            
            //保存审计
            umBizAuditDao.saveAndFlush(auditEntity);
            
        }
        catch(Exception ex) {
            logger.error("error occurs while encrypting origin passwords: ", ex);
            throw new MessageException(Constants.USER_REG_ERROR.ERROR_CODE012, Constants.USER_REG_ERROR.ERROR_INFO012);
        }
    }
    
    @Override
    public ShdippBizUserEntity queryUser(long uummUserId) {
        logger.info("query userinfo by uumm userId: {}" , uummUserId);
        return shdippBizUserDao.findFirstByUserId(uummUserId);
    }
    
    @Override
    public void DealWithUserInfo(OAuthUserInfo oui) {
        //先尝试获取用户id，如果不存在，则从uumm中抓取，获取后保存更新attrs
        String shdippUserId = MapUtils.getString(oui.info.attrs, "ShdippUserid", "");
        if (StringUtils.isEmpty(shdippUserId)){
            //获得uumm用户的id
            long uummUserId = MapUtils.getLong(oui.info.attrs, "Userid", 0L);
            if (uummUserId != 0) {
                //根据uumm用户id获得站点用户id
                ShdippBizUserEntity bizUserEntity = shdippBizUserDao.findFirstByUserId(uummUserId);
                if (bizUserEntity != null) {
                    shdippUserId = bizUserEntity.getId();
                    oui.info.attrs.put("ShdippUserid", shdippUserId);
                }
            }
        }
    }
    
    /**
     * 检查用户id是否存在
     *
     * @param id
     * @param idType
     * @return
     * @throws MessageException
     */
    @Override
    public int checkUserExists(String id, String idType) {
        switch(idType) {
            case "0":
                return umUserDao.existsByLoginId(id) ? 1 : 0;
            case "1":
                return umUserDao.existsByMobile(id) ? 1 : 0;
            case "2":
                return umUserDao.existsByMail(id) ? 1 : 0;
            default:
                return 0;
        }
    }
    
    @Transactional
    @Override
    public boolean registerUser(String loginId, String mobile, String mail, String password) throws MessageException {
        try {
            //判断登录名是否已经注册
            if(checkUserExists(loginId, "0") != 0) {
                throw new MessageException(
                        Constants.USER_REG_ERROR.ERROR_CODE000,
                        Constants.USER_REG_ERROR.ERROR_INFO000);
            }
            //判断用户名正则
            if(!loginIdCheck(loginId)) {
                throw new MessageException(
                        Constants.USER_REG_ERROR.ERROR_CODE014,
                        Constants.USER_REG_ERROR.ERROR_INFO014);
            }
            //如果有手机号，则判断手机号是否已绑定
            if(!StringUtils.isBlank(mobile) && checkUserExists(mobile, "1") != 0) {
                throw new MessageException(
                        Constants.USER_REG_ERROR.ERROR_CODE009,
                        Constants.USER_REG_ERROR.ERROR_INFO009);
            }
            //如果有邮箱，则判断邮箱是否已绑定
            if(!StringUtils.isBlank(mail) && checkUserExists(mail, "2") != 0) {
                throw new MessageException(
                        Constants.USER_REG_ERROR.ERROR_CODE010,
                        Constants.USER_REG_ERROR.ERROR_INFO010);
            }
            
            //校验密码 长度大于6位且包含字母与数字
            if(pwdCheck(password)) {
                //加密密码 MD5
                String pwd = md5Pass(password);
                //查询默认组织的id 组织机构代码 SC_PORTAL
                UmOrgEntity org = umOrgDao.findFirstByOrgCode(UserConfig.DEFAULT_ORG_CODE);
                if(org == null) {
                    throw new Exception("默认组织不存在");
                }
                //生成用户对象,赋值组织id
                UmUserEntity umUser = new UmUserEntity();
                
                umUser.setLoginId(loginId);
                umUser.setName("用户");
                umUser.setUserLevel("2");
                umUser.setFlag("0");
                umUser.setUserType("0");
                umUser.setBodyType("person");
                umUser.setCreateDate(new Date());
                umUser.setLoginPass(pwd);
                umUser.setOrgId(org.getId());
                umUser.setSex("1");
                umUser.setMobile(mobile);
                umUser.setMail(mail);
                
                //保存用户
                umUserDao.saveAndFlush(umUser);
                
                //生成审计对象
                UmBizAuditEntity auditEntity = new UmBizAuditEntity();
                auditEntity.setLoginId("sys");
                auditEntity.setOperId(umUser.getId());
                auditEntity.setAuditOperation("Insert");
                auditEntity.setAuditPermission("UserAdd");
                auditEntity.setOperType("USER");
                auditEntity.setFlag("1");
                auditEntity.setRecordTime(new Date());
                auditEntity.setDescription("用户自助注册");
                auditEntity.setDataDescription1("");
                auditEntity.setDataDescription2(getDesc(umUser, org));
                
                //保存审计
                umBizAuditDao.saveAndFlush(auditEntity);

//                //保存用户角色关联
//                UmRoleEntity role =  umRoleDao.findFirstByRoleCode("Default-App-User-SHDIPP_USER");
//                UmUserRoleEntity umUserRoleEntity = new UmUserRoleEntity();
//                umUserRoleEntity.setUserId(umUser.getId());
//                umUserRoleEntity.setRoleId(role.getId());
//                umUserRoleDao.saveAndFlush(umUserRoleEntity);
//
//                //保存用户应用关联
//                OauthBizAppUserEntity obau = new OauthBizAppUserEntity();
//                obau.setAppCode("SHDIPP");
//                obau.setLoginId(umUser.getLoginId());
//                obau.setCreateDate(new Date());
//
//                oauthBizAppUserDao.saveAndFlush(obau);
            
            }
            else {
                throw new MessageException(
                        Constants.USER_REG_ERROR.ERROR_CODE006,
                        Constants.USER_REG_ERROR.ERROR_INFO006);
            }
            return true;
        }
        catch(MessageException scex) {
            throw scex;
        }
        catch(Exception ex) {
            logger.error("用户注册错误:", () -> ex);
            throw new MessageException(
                    Constants.USER_REG_ERROR.ERROR_CODE005,
                    Constants.USER_REG_ERROR.ERROR_INFO005);
        }
    }
    
    @Override
    public void changePwd(String loginId, String oldPwd, String newPwd) throws MessageException {
        //获取用户信息
        UmUserEntity user = umUserDao.findByLoginId(loginId);
        //对密码进行加密
        String tmpOldPwd = "";
        String tmpNewPwd = "";
        try {
            
            //检查新密码符合规范
            if(!pwdCheck(newPwd)) {
                throw new Exception("新密码格式错误");
            }
            tmpOldPwd = md5Pass(oldPwd);
            tmpNewPwd = md5Pass(newPwd);
            
            //校验老密码
            if(!tmpOldPwd.equals(user.getLoginPass())) {
                throw new Exception("旧密码错误");
            }
            //更新密码
            user.setLoginPass(tmpNewPwd);
            
            //保存用户信息
            user.setModifyDate(new Date());
            umUserDao.save(user);
            
            //生成审计对象
            UmBizAuditEntity auditEntity = new UmBizAuditEntity();
            auditEntity.setLoginId(user.getLoginId());
            auditEntity.setOperId(user.getId());
            auditEntity.setAuditOperation("Modify");
            auditEntity.setAuditPermission("UserAdd");
            auditEntity.setOperType("USER");
            auditEntity.setFlag("1");
            auditEntity.setRecordTime(new Date());
            auditEntity.setDescription("修改密码");
            auditEntity.setDataDescription1("");
            auditEntity.setDataDescription2("");
            
            //保存审计
            umBizAuditDao.saveAndFlush(auditEntity);
            
        }
        catch(Exception ex) {
            logger.error("error occurs while encrypting origin passwords: ", ex);
            throw new MessageException(Constants.USER_REG_ERROR.ERROR_CODE012, Constants.USER_REG_ERROR.ERROR_INFO012);
        }
    }
    
    
    private synchronized String md5Pass(String oriPass) throws Exception {
        // use the MD5 algorithm - 160bit
        MessageDigest md = MessageDigest.getInstance("MD5");
        
        // digest using UTF-8 encoding
        byte[] buf = oriPass.getBytes("UTF-8");
        md.update(buf);
        byte[] raw = md.digest();
        
        // convert to base64 String
        String hash = (new BASE64Encoder()).encode(raw);
        return hash;
    }
    

    
    private boolean pwdCheck(String password) {
        if(!StringUtils.isBlank(password)) {
            return password.matches(new String("^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?!([^(0-9a-zA-Z)])+$)^.{6,16}$"));
        }
        else {
            return false;
        }
    }
    private boolean loginIdCheck(String loginId) {
        if(!StringUtils.isBlank(loginId)) {
            return loginId.matches(new String("^[0-9A-z][0-9A-z\\-_]{2,18}[0-9A-z]$"));
        }
        else {
            return false;
        }
    }
    
    public String getDesc(UmUserEntity umUser, UmOrgEntity org) {
        
        String ret = "{登录名}：" + umUser.getLoginId() + "；{用户姓名}：" + umUser.getName()
                + "；{电子邮箱}：；{地址}：；{电话}：；{手机}：" + umUser.getMobile() + "；{组织名称}："
                + org.getName() + "[" + org.getOrgCode()
                + "]；{用户级别}：普通用户；{状态}：" + "有效；{备注}：跨境应用门户自助注册；"
                + "{用户类型}：手机用户；{服务商来源}：；" + "{集团类型}：个人；{性别}：男；";
        return ret;
    }
}
