package com.shcepp.shdippsvr.business.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shcepp.shdippsvr.business.bean.ProductDetailDesBean;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticSearchQueryResultBean;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticsearchPagination;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ShdippElasticSearchBean;
import com.shcepp.shdippsvr.business.bean.elasticsearch.pojo.CompanyItemPojo;
import com.shcepp.shdippsvr.business.bean.elasticsearch.pojo.PlatformItemPojo;
import com.shcepp.shdippsvr.business.bean.elasticsearch.pojo.ProductItemPojo;
import com.shcepp.shdippsvr.business.enums.BaseEnums;
import com.shcepp.shdippsvr.business.enums.EnterpriseCategoryType;
import com.shcepp.shdippsvr.business.enums.PlatformCategoryType;
import com.shcepp.shdippsvr.business.enums.ProductCategoryType;
import com.shcepp.shdippsvr.business.service.elasticsearch.ElasticsearchService;
import com.shcepp.shdippsvr.business.util.DateUtils;
import com.shcepp.shdippsvr.sys.controller.BaseController;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zkmao
 * @description
 * @date 2019/9/6 20:32
 */
@RestController
@RequestMapping("/es")
public class EsTestController extends BaseController {

    private final LambdaLogger logger = LambdaLoggerFactory.getLogger(getClass());

    @Autowired
    ElasticsearchService elasticsearchService;

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public boolean uploadProduct(String value) {

        JSONArray json = JSON.parseArray(value);
        for (Object o : json) {
            ProductItemPojo product = JsonUtil.jsonToBean(JsonUtil.beanToJson(o), ProductItemPojo.class);
            List<BaseEnums> list = new ArrayList<>();
            String categories = product.getCategory();
            for (String str : categories.split(",")) {
                list.add(ProductCategoryType.getEnumsByCode(str));
            }
            ShdippElasticSearchBean bean;
            bean = ShdippElasticSearchBean.Builder()
                                          .attrList(list)
                                          .createDate(new Date())
                                          .magicObject(product)
                                          .region(product.getRegion())
                                          .build();
            elasticsearchService.saveOrUpdate(bean);
        }
        return true;

    }

    @RequestMapping(value = "/platform", method = RequestMethod.POST)
    public boolean uploadPlatform(String value) {
        JSONArray json = JSON.parseArray(value);
        for (Object o : json) {
            PlatformItemPojo platform = JsonUtil.jsonToBean(JsonUtil.beanToJson(o), PlatformItemPojo.class);

            ShdippElasticSearchBean bean;
            List<BaseEnums> list = new ArrayList<>();
            String categories = platform.getCategory();
            for (String str : categories.split(",")) {
                list.add(PlatformCategoryType.getEnumsByCode(str));
            }
            bean = ShdippElasticSearchBean.Builder()
                                          .attrList(list)
                                          .createDate(new Date())
                                          .magicObject(platform)
                                          .build();

            elasticsearchService.saveOrUpdate(bean);
        }
        return true;

    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public boolean uploadCompany(String value) {

        JSONArray json = JSON.parseArray(value);
        for (Object o : json) {
            CompanyItemPojo company = JsonUtil.jsonToBean(JsonUtil.beanToJson(o), CompanyItemPojo.class);
            ShdippElasticSearchBean bean;
            List<BaseEnums> list = new ArrayList<>();
            String categories = company.getCategory();
            for (String str : categories.split(",")) {
                list.add(EnterpriseCategoryType.getEnumsByCode(str));
            }
            bean = ShdippElasticSearchBean.Builder()
                                          .attrList(list)
                                          .createDate(new Date())
                                          .magicObject(company)
                                          .build();
            elasticsearchService.saveOrUpdate(bean);
        }
        return true;

    }

    @RequestMapping(value = "/prdDetail", method = RequestMethod.POST)
    public boolean uploadPrdDetail(String value) {

        JSONArray json = JSON.parseArray(value);
        for (Object o : json) {
            ProductDetailDesBean prdDetail = JsonUtil.jsonToBean(JsonUtil.beanToJson(o), ProductDetailDesBean.class);
            ShdippElasticSearchBean bean;

            bean = ShdippElasticSearchBean.Builder()
                                          .createDate(new Date())
                                          .magicObject(prdDetail)
                                          .build();
            elasticsearchService.saveOrUpdate(bean);
        }
        return true;

    }

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public Object getAll() {
        ShdippElasticSearchBean bean;

        bean = ShdippElasticSearchBean.Builder()

                                      .build();
        ElasticsearchPagination page = new ElasticsearchPagination();
        page.setPageSize(1000);
        page.setCurrentPage(0);
        page = elasticsearchService.queryEsByBean(bean, page);

        page.getContentList().forEach(f -> {

//            logger.info(JsonUtil.beanToJson(f));
            formatBean(((ElasticSearchQueryResultBean) f));
        });
        return null;

    }

    private void formatBean(ElasticSearchQueryResultBean bean) {
        StringBuilder sb = new StringBuilder();
        String ss = new String();
        sb.append("PUT shdipp-rtxt/_doc/");
        sb.append(bean.getId());
        sb.append("\r\n");
        sb.append("{");
        sb.append("\r\n");
        sb.append(" \"attributes\" : \"");
        sb.append(bean.getAttrStr());
        sb.append("\",\r\n");

        sb.append("\"id\" : \"");
        sb.append(bean.getId());
        sb.append("\",\r\n");

        sb.append(" \"isDelete\" : ");
        sb.append(bean.getIsDelete());
        sb.append(",\r\n");

        sb.append(" \"message\" : \"");
        sb.append(bean.getMessage());
        sb.append("\",\r\n");

        sb.append(" \"orMessage\" : \"\"\"");
        sb.append(bean.getOrMessage());
        sb.append("\"\"\",\r\n");

        if (StringUtils.isNotEmptyWithNUllCheckStr(bean.getUserId())) {

            sb.append(" \"userId\" : \"");
            sb.append(bean.getUserId());
            sb.append("\",\r\n");
        }

        sb.append(" \"region\" : \"");
        sb.append(bean.getRegion());
        sb.append("\",\r\n");

        if (null != (bean.getCreateTime())) {
            sb.append(" \"createTime\" : \"");
            sb.append(DateUtils.formatDate(DateUtils.dateTimePattern, bean.getCreateTime()));
            sb.append("\",\r\n");
        } else {
            sb.append(" \"createTime\" : \"");
            sb.append(DateUtils.formatDate(DateUtils.dateTimePattern, new Date()));
            sb.append("\",\r\n");
        }

        if (null != (bean.getUpdateTime())) {
            sb.append(" \"updateTime\" : \"");
            sb.append(DateUtils.formatDate(DateUtils.dateTimePattern, bean.getUpdateTime()));
            sb.append("\",\r\n");
        }
        ss = sb.substring(0, sb.length() - 3);
        ss = ss + ("}");

        System.out.println();

        System.out.println(ss);
        System.out.println();
    }

}
