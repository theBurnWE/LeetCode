package com.shcepp.shdippsvr.business.entity;



import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @description:
 * @author: zkmao
 * @date: 2018/9/4
 */
@Entity
            //PP.ID,
//         D.PRODUCT_NAME,
//         ED.NAME AS COMPANY_NAME,
//         ED.OFFICIAL_WEBSITE,
//         D.SUMMARY,
//         D.IMAGE1,
//         L.LIKE_COUNT,
//         D.UPDATE_TIME
public class ProductEntity extends BaseEntity{
    private static final long serialVersionUID = 5060838166807585506L;
    private String id;
    private String productName;
    private String companyName;
    private String officialWebSite;
    private String summary;
    private String image1;
    private Long likeCount;
    private String updateTime;
    
    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Basic
    @Column(name = "PRODUCT_NAME")
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    @Basic
    @Column(name = "COMPANY_NAME")
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    @Basic
    @Column(name = "OFFICIAL_WEBSITE")
    public String getOfficialWebSite() {
        return officialWebSite;
    }
    
    public void setOfficialWebSite(String officialWebSite) {
        this.officialWebSite = officialWebSite;
    }
    
    @Basic
    @Column(name = "SUMMARY")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "IMAGE1")
    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    @Basic
    @Column(name = "LIKE_COUNT")
    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }
    @Basic
    @Column(name = "UPDATE_TIME")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}
