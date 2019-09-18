package com.shcepp.shdippsvr.business.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author BrunE
 * @date 2019-08-21 17:23
 **/
@Entity
@Table(name = "SHDIPP_BIZ_USER_LIKE")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

public class ShdippBizUserLikeEntity {

    private String userLikeId;
    private long itemType;
    private String likeItemId;
    private Date createTime;

    @Id
    @Column(name = "USER_LIKE_ID")
    public String getUserLikeId() {
        return userLikeId;
    }

    public void setUserLikeId(String userLikeId) {
        this.userLikeId = userLikeId;
    }

    @Basic
    @Column(name = "ITEM_TYPE")
    public long getItemType() {
        return itemType;
    }

    public void setItemType(long itemType) {
        this.itemType = itemType;
    }

    @Basic
    @Column(name = "LIKE_ITEM_ID")
    public String getLikeItemId() {
        return likeItemId;
    }

    public void setLikeItemId(String likeItemId) {
        this.likeItemId = likeItemId;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
