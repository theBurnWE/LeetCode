package com.shcepp.shdippsvr.business.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "BASE_IP_IN_LOG")
public class BaseIpInLogEntity extends BaseEntity implements Serializable {
    
    /**
     *
     */
    
    private static final long serialVersionUID = 2876026556903409462L;
    
    
    private String id;
    
    private String ip;
    
    private String spt;
    
    private Date createTime;
    
    @GenericGenerator(name = "generator", strategy = "assigned")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false)
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name = "IP")
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    @Column(name = "SPT")
    public String getSpt() {
        return spt;
    }
    
    public void setSpt(String spt) {
        this.spt = spt;
    }
    
    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    
}
