package com.shcepp.shdippsvr.business.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author BrunE
 * @date 2019-08-21 17:23
 **/
@Entity
@Table(name = "SHDIPP_BIZ_USER_FAVORITE")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

public class ShdippBizUserFavoriteEntity {

    public static final long ITEM_TYPE_1 = 1;       //平台
    public static final long ITEM_TYPE_2 = 2;       //企业
    public static final long ITEM_TYPE_3 = 3;       //产品
    public static final long ITEM_TYPE_4 = 4;       //服务

    private String userFavoriteId;
    private String userId;
    private long itemType;
    private String favoriteItemId;
    private Date createTime;

    @Id
    @Column(name = "USER_FAVORITE_ID")
    public String getUserFavoriteId() {
        return userFavoriteId;
    }

    public void setUserFavoriteId(String userFavoriteId) {
        this.userFavoriteId = userFavoriteId;
    }

    @Basic
    @Column(name = "USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
    @Column(name = "FAVORITE_ITEM_ID")
    public String getFavoriteItemId() {
        return favoriteItemId;
    }

    public void setFavoriteItemId(String favoriteItemId) {
        this.favoriteItemId = favoriteItemId;
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
