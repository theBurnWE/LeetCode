//package com.shcepp.shdippsvr.business.entity.view;
//
//import com.shcepp.shdippsvr.business.entity.BaseEntity;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//
///**
// * @author BrunE
// * @date 2019-07-22 13:36
// **/
//@Entity
//@Table(name = "V_BATCH_ORDER_DETAIL")
//public class VBatchOrderDetailEntity extends BaseEntity {
//
//    //物流单未匹配
//    public static final String LOGISTICS_UNCHECK = "物流单未申报";
//    //物流单匹配
//    public static final String LOGISTICS_CHECKED = "物流单未申报";
//
//    //订单放行
//    public static final String LOGISTICS_RELEASE = "800";
//
//
//
//
//    private static final long serialVersionUID = -2586605757830241993L;
//    private String id;
//    private String ebcCode;
//    private String orderId;
//    private long batchApplyId;
//    private BigDecimal orderAmount;
//
//    @Id
//    @Column(name = "ID")
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Basic
//    @Column(name = "EBC_CODE")
//    public String getEbcCode() {
//        return ebcCode;
//    }
//
//    public void setEbcCode(String ebcCode) {
//        this.ebcCode = ebcCode;
//    }
//
//    @Basic
//    @Column(name = "ORDER_ID")
//    public String getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(String orderId) {
//        this.orderId = orderId;
//    }
//
//    @Basic
//    @Column(name = "BATCH_APPLY_ID")
//    public long getBatchApplyId() {
//        return batchApplyId;
//    }
//
//    public void setBatchApplyId(long batchApplyId) {
//        this.batchApplyId = batchApplyId;
//    }
//
//    @Basic
//    @Column(name = "ORDER_AMOUNT")
//    public BigDecimal getOrderAmount() {
//        return orderAmount;
//    }
//
//    public void setOrderAmount(BigDecimal orderAmount) {
//        this.orderAmount = orderAmount;
//    }
//}
