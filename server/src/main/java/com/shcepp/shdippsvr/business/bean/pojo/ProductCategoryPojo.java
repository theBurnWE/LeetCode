package com.shcepp.shdippsvr.business.bean.pojo;

import com.shcepp.shdippsvr.business.bean.BaseBean;

import java.util.List;

/**
 * @author zkmao
 * @description
 * @date 2019/9/3 11:31
 */
public class ProductCategoryPojo extends BaseBean {
    private static final long serialVersionUID = 8520983617319893726L;
    public String category;
    public List<ProductCardItemPojo> list;
    public PagePojo page_info;
    
}
