package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.dao.ShdippBizUserDao;
import com.shcepp.shdippsvr.business.entity.ShdippBizUserEntity;
import com.shcepp.shdippsvr.business.exception.MessageException;
import com.shcepp.shdippsvr.oauth.dto.OAuthUserInfo;

public interface UserRegisterService {
    /**
     * 发送验证码
     *
     * @param id     登录id
     * @param idType id类型
     * @param ip     请求ip
     * @throws MessageException
     */
    void sendCaptcha(String id, String idType, String ip) throws MessageException;

    
    /**
     * 检查用户是否存在
     *
     * @param id     登录id
     * @param idType id类型
     * @return
     * @throws MessageException
     */
    int checkUserExists(String id, String idType);
    
    /**
     * 注册用户
     *
     * @param loginId 登录名
     * @param mobile 手机号
     * @param mail 邮箱
     * @param password 密码
     * @return
     * @throws MessageException
     */
    boolean registerUser(String loginId, String mobile, String mail, String password) throws MessageException;
    
    /**
     * 修改密码
     *
     * @param loginId 用户登录名
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @throws MessageException
     */
    void changePwd(String loginId, String oldPwd, String newPwd) throws MessageException;
    
    /**
     * 发送重置密码验证码
     *
     * @param id     登录id
     * @param idType id类型
     * @param ip     请求ip
     * @throws MessageException
     */
    void sendResetPwdCaptcha(String id, String idType, String ip) throws MessageException;
    
    /**
     * 重置密码
     *
     * @param id     登录id
     * @param idType id类型
     * @param newPwd 新密码
     * @throws MessageException
     */
    void resetPwd(String id, String idType, String newPwd) throws MessageException;
    
    /**
     * 根据uumm用户id获得站点用户信息
     * @param uummUserId
     * @return
     */
    ShdippBizUserEntity queryUser(long uummUserId);
    
    /**
     * 处理用户信息
     * @param oui
     */
    void DealWithUserInfo(OAuthUserInfo oui);
}
