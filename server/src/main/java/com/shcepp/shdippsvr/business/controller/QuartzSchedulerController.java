package com.shcepp.shdippsvr.business.controller;

import com.shcepp.shdippsvr.business.bean.pojo.ProductConditionPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesReqPojo;
import com.shcepp.shdippsvr.business.enums.DomainType;
import com.shcepp.shdippsvr.business.enums.LanType;
import com.shcepp.shdippsvr.business.service.ProductService;
import com.shcepp.shdippsvr.sys.controller.BaseController;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 定时任务调度器
 * @author: Burn~E
 * @date: 2019/05/06 11:06
 * @version: V1.0
 */
@RestController
@RequestMapping("cache")
public class QuartzSchedulerController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(QuartzSchedulerController.class);

    @Autowired
    private ProductService productService;

    /**
     * 给到框架使用的定时任务调度请求
     *
     * @param request http请求
     * @return 响应报文
     */
    @RequestMapping(value = "productRanks/load")
    public void doOrderCheck(HttpServletRequest request, RecommendedResourcesReqPojo recommendedResourcesPojo) {
        ProductConditionPojo pojo;
        logger.info("开始刷新产品排名");
        for (DomainType domainType : DomainType.values()) {
            for (LanType lanType : LanType.values()) {
                pojo = new ProductConditionPojo();
                pojo.site = domainType.getCode();
                pojo.lan = lanType.getCode();
                logger.info("开始加载参数为{}的产品排名", JsonUtil.beanToJson(pojo));
                productService.storeProductRanksToCache(pojo);
                logger.info("参数为{}的产品排名加载完成", JsonUtil.beanToJson(pojo));

            }
        }
        logger.info("产品排名刷新结束");
    }

}
