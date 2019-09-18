package com.shcepp.shdippsvr.business.service.impl;

import com.shcepp.shdippsvr.business.bean.pojo.SysBizAuditPojo;
import com.shcepp.shdippsvr.business.dao.ShdippSysAuditDao;
import com.shcepp.shdippsvr.business.entity.ShdippSysAuditEntity;
import com.shcepp.shdippsvr.business.service.SysBizAuditService;
import com.shcepp.shdippsvr.business.util.BeanValidator;
import com.shcepp.shdippsvr.sys.service.BaseService;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import com.shcepp.shdippsvr.sys.util.SnowflakeIdWorkerUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

/**
 * @description:
 * @author: zkmao
 * @date: 2018/8/24
 */

@Service
public class SysBizAuditServiceImpl extends BaseService implements SysBizAuditService {

    private final LambdaLogger logger = LambdaLoggerFactory.getLogger(getClass());

    @Autowired
    private ShdippSysAuditDao shdippSysAuditDao;

    @Override
    public boolean SaveAudit(SysBizAuditPojo objItem) {

        boolean blnReturn = false;        //返回值
        ShdippSysAuditEntity objSave = new ShdippSysAuditEntity();
        try {

            if (objItem == null) {
                logger.error("单个保存审计信息错误:没有审计信息,传入数据为：{}", JsonUtil.beanToJson(objItem));
            } else {
                //操作前的值
                if (null != objItem.getAuditBeanOld()) {
                    objSave.setDataDescription1(objItem.getAuditBeanOld().toAuditString());
                } else {
                    objSave.setDataDescription1(objItem.getAuditStrOld());
                }
                //操作后的值
                if (null != objItem.getAuditBeanNew()) {
                    objSave.setDataDescription2(objItem.getAuditBeanNew().toAuditString());
                } else {
                    objSave.setDataDescription2(objItem.getAuditStrNew());
                }
                if (null != objItem.getOperId()) {
                    objSave.setOperId(objItem.getOperId());
                } else {
                    objSave.setOperId(objSave.getId());
                }
                try {
                    BeanValidator.validate(objItem);
                    if (StringUtils.isEmptyWithNUllCheckStr(objSave.getDataDescription1())) {
                        logger.error("操作前数据 AuditBeanNew/AuditStrNew为空");
                        throw new ValidationException("请给操作前数据 AuditBeanNew/AuditStrNew 描述赋值");
                    }
                    if (StringUtils.isEmptyWithNUllCheckStr(objSave.getDataDescription2())) {
                        logger.error("操作后数据  AuditBeanOld/AuditStrOld 为空");

                        throw new ValidationException("请给操作后数据 AuditBeanOld/AuditStrOld 描述赋值");
                    }

                    if (StringUtils.isEmptyWithNUllCheckStr(objSave.getOperId())) {
                        logger.error("操作数据的id为空");
                        throw new ValidationException("请给操作后数据 AuditBeanOld/AuditStrOld 描述赋值");
                    }
                } catch (ValidationException ver) {
                    logger.error("单个保存审计信息错误,被审计的信息为：{}", ver, JsonUtil.beanToJson(objItem));
                    return blnReturn;
                }

                objSave.setAuditOperation(objItem.getOperType().getValue());
                objSave.setAuditPermission(objItem.getOperMemberType().getValue());
                objSave.setOperType(objItem.getAudtPermissionType().getValue());
                objSave.setFlag(objItem.getFlag());
                objSave.setLoginId(objItem.getLoginId());
                objSave.setClientIp(objItem.getOperIp());
                objSave.setDescription(objItem.getDiscrttion());
                objSave.setSp1(objItem.getSpt1());
                objSave.setSp2(objItem.getSpt2());
                objSave.setSp3(objItem.getSpt3());
                objSave.setId(SnowflakeIdWorkerUtil.nextIdStr());
                shdippSysAuditDao.save(objSave);
                logger.info("审计数据入库成功");
                logger.debug("入库的审计数据为：{}", JsonUtil.beanToJson(objSave));
                blnReturn = true;
            }

        } catch (Exception ex) {
            logger.error("单个保存审计信息错误,被审计的信息为：{}", ex, JsonUtil.beanToJson(objItem));
        }
        return blnReturn;

    }
}
