package com.shcepp.shdippsvr.business.bean.pojo;

import com.shcepp.shdippsvr.business.bean.BaseBean;

/**
 * 页面产品卡片信息的bean
 *
 * @author zkmao
 * @date 2019-07-18 18:38
 **/
public class ProductCardItemPojo extends BaseBean {

    private static final long serialVersionUID = -6382389295765714047L;
    
    //产品id
    public String prdId;
    //显示顺序编号
    public int order;
    //产品名称
    public String product_name;
    //企业名称（产品权利人）
    public String company_name;
    //企业官网地址
    public String company_url;
    //产品摘要
    public String summary;
    //点赞数
    public Long like;
    //是否被收藏（登录账户有效）
    // 1:已收藏，0:未收藏
    public String favorite;
    //咨询联系方式，默认为管理员邮箱
    public String mail;
    //产品展示图
    public String img;
    //产品展示缩略图
    public String img_s;
    //产品详情url
    public String url;
    //发布时间
    public String puton_time;
}
