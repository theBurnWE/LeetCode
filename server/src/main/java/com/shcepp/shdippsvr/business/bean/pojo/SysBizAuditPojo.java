package com.shcepp.shdippsvr.business.bean.pojo;

import com.shcepp.shdippsvr.business.entity.BaseEntity;
import com.shcepp.shdippsvr.business.enums.AudtOptionType;
import com.shcepp.shdippsvr.business.enums.AudtPermissionType;
import com.shcepp.shdippsvr.business.enums.OperMemberType;
import com.shcepp.shdippsvr.sys.util.JsonUtil;

import javax.validation.constraints.NotNull;

/**
 * 审计类
 *
 * @author BrunE
 * @date 2019-09-17 16:32
 **/
public class SysBizAuditPojo<T extends BaseEntity> {

    /// <summary>
    /// 审计BEAN
    /// </summary>
    private T auditBeanNew = null;
    /// <summary>
    /// 审计BEAN2
    /// </summary>
    private T auditBeanOld = null;
    /// <summary>
    /// 审计Str
    /// </summary>
    private String auditStrNew = null;
    /// <summary>
    /// 审计Str2
    /// </summary>
    private String auditStrOld  =null;

    //操作对象类型
    @NotNull(message = "操作对象类型[audtPermissionType]不能为空")
    private AudtPermissionType audtPermissionType;
    //操作类型
    @NotNull(message = "操作类型[operType]不能为空")
    private AudtOptionType operType;
    //操作权限
    @NotNull(message = "操作权限[operMemberType]不能为空")
    private OperMemberType operMemberType;
    //IP
    @NotNull(message = "IP[operIp]不能为空")
    private String operIp;
    //登陆名
    @NotNull(message = "登陆名[loginId]不能为空")
    private String loginId;


    //对象ID
    @NotNull(message = "操作对象ID[operId]不能为空")
    private String operId;


    @NotNull(message = "描述[discrttion]不能为空")
    private String discrttion;
    //操作是否成功，默认成功
    private String flag = BaseEntity.AUDIT_SUCCESS;



    private String spt1;
    private String spt2;
    private String spt3;

    public T getAuditBeanNew() {
        return auditBeanNew;
    }

    public void setAuditBeanNew(T auditBeanNew) {
        this.auditBeanNew = (T) JsonUtil.formatJson2Object(JsonUtil.beanToJson(auditBeanNew), auditBeanNew.getClass());
    }

    public T getAuditBeanOld() {
        return auditBeanOld;
    }

    public void setAuditBeanOld(T auditBeanOld) {

        this.auditBeanOld = (T) JsonUtil.formatJson2Object(JsonUtil.beanToJson(auditBeanOld), auditBeanOld.getClass());
    }

    public String getAuditStrNew() {
        return auditStrNew;
    }

    public void setAuditStrNew(String auditStrNew) {
        this.auditStrNew = auditStrNew;
    }

    public String getAuditStrOld() {
        return auditStrOld;
    }

    public void setAuditStrOld(String auditStrOld) {
        this.auditStrOld = auditStrOld;
    }

    public AudtPermissionType getAudtPermissionType() {
        return audtPermissionType;
    }

    public void setAudtPermissionType(AudtPermissionType audtPermissionType) {
        this.audtPermissionType = audtPermissionType;
    }

    public AudtOptionType getOperType() {
        return operType;
    }

    public void setOperType(AudtOptionType operType) {
        this.operType = operType;
    }

    public OperMemberType getOperMemberType() {
        return operMemberType;
    }

    public void setOperMemberType(OperMemberType operMemberType) {
        this.operMemberType = operMemberType;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDiscrttion() {
        return discrttion;
    }

    public void setDiscrttion(String discrttion) {
        this.discrttion = discrttion;
    }

    public String getSpt1() {
        return spt1;
    }

    public void setSpt1(String spt1) {
        this.spt1 = spt1;
    }

    public String getSpt2() {
        return spt2;
    }

    public void setSpt2(String spt2) {
        this.spt2 = spt2;
    }

    public String getSpt3() {
        return spt3;
    }

    public void setSpt3(String spt3) {
        this.spt3 = spt3;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }
}
