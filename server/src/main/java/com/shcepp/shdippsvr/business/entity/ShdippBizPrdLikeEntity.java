package com.shcepp.shdippsvr.business.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author BrunE
 * @date 2019-08-21 17:23
 **/
@Entity
@Table(name = "SHDIPP_BIZ_PRD_LIKE")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

public class ShdippBizPrdLikeEntity {

    private String prdId;
    private int likeCount;
    private Date updateTime;

    @Id
    @Column(name = "PRD_ID")
    public String getPrdId() {
        return prdId;
    }

    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }

    @Basic
    @Column(name = "LIKE_COUNT")
    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    @Basic
    @Column(name = "UPDATE_TIME")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
