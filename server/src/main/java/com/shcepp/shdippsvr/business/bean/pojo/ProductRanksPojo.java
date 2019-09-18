package com.shcepp.shdippsvr.business.bean.pojo;

import com.shcepp.shdippsvr.business.bean.BaseBean;

import java.util.List;

/**
 * @author zkmao
 * @description
 * @date 2019/9/3 11:37
 */
public class ProductRanksPojo extends BaseBean{
    private static final long serialVersionUID = -2557067477726741233L;
    public List<ProductCardItemPojo> likes;
    public List<ProductCardItemPojo> favorites;
    public List<ProductCardItemPojo> recommendations;
    public List<ProductCardItemPojo> newly;
    
}
