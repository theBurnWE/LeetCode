package com.shcepp.shdippsvr.business.bean.pojo;

import com.shcepp.shdippsvr.business.bean.BaseBean;

import java.util.List;
import java.util.Map;

/**
 * @author zkmao
 * @description
 * @date 2019/9/3 11:35
 */
public class ProductInAllPojo extends BaseBean{
    
    private static final long serialVersionUID = -8065350245463997391L;
    
    public List<ProductCategoryPojo> categories;
    public ProductRanksPojo ranks;
}
