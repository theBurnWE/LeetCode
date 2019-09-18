package com.shcepp.shdippsvr.business.controller;

import com.shcepp.shdippsvr.business.bean.pojo.ProductCategoryPojo;
import com.shcepp.shdippsvr.business.bean.pojo.ProductConditionPojo;
import com.shcepp.shdippsvr.business.bean.pojo.ProductRanksPojo;
import com.shcepp.shdippsvr.business.bean.pojo.resources.ProductInfoReqPojo;
import com.shcepp.shdippsvr.business.exception.MessageException;
import com.shcepp.shdippsvr.business.service.BaseService;
import com.shcepp.shdippsvr.business.service.ProductService;
import com.shcepp.shdippsvr.oauth.dto.OAuthUserInfo;
import com.shcepp.shdippsvr.oauth.service.AppOAuthService;
import com.shcepp.shdippsvr.sys.controller.BaseController;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import com.shcepp.shdippsvr.sys.util.Constants;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zkmao
 * @description
 * @date 2019/8/22 9:46
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    private final LambdaLogger logger = LambdaLoggerFactory.getLogger(getClass());

    @Autowired
    ProductService productService;
    @Autowired
    AppOAuthService appOAuthService;
    /**
     * 首页产品详情展示
     * @return
     */
    @RequestMapping(value = "/showInfo", method = RequestMethod.POST)
    public ApiResult showInfo(@RequestHeader(required = false) String token,ProductInfoReqPojo productInfoReqPojo) {
        ApiResult result = new ApiResult();

        try {
            //检查是否有token，有则获取用户信息，获得userId
            String userId = "";
            if(!StringUtils.isEmpty(token)) {
                OAuthUserInfo oui = appOAuthService.getUserInfo(token, Constants.APP_CODE);
                if(oui != null) {
                    userId = MapUtils.getString(oui.info.attrs, "ShdippUserid", "");
                }
            }
            //查询产品详情
            result = productService.queryProductInfo(productInfoReqPojo,userId);
        } catch(Exception e) {
            logger.error("Show Product Information error :",e);
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, BaseService.BR_OTHER_ERROR, "查询产品详情信息错误。", null);
        }
        return result;
    }
    /**
     * 拼装查询条件
     *
     * @param site
     * @param lan
     * @param category
     * @param from
     * @param to
     * @param region
     * @param keywords
     * @param page
     * @return
     */

    private ProductConditionPojo buildCondition(String site, String lan,
                                                String category, String from, String to,
                                                String region, String keywords, int page, String userId, String baseUrl) {
        ProductConditionPojo ret = new ProductConditionPojo();
        ret.site = site;
        ret.lan = lan;
        ret.category = category;
        ret.userId = userId;
        if (!StringUtils.isEmpty(from)) {
            ret.from = from;
        }
        if (!StringUtils.isEmpty(to)) {
            ret.to = to;
        }
//        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //必须捕获异常
//        try {
//            if (!StringUtils.isEmpty(from)) {
//                ret.from = sDateFormat.parse(from);
//            }
//            if (!StringUtils.isEmpty(to)) {
//                ret.to = sDateFormat.parse(to);
//            }
//        }
//        catch(ParseException px) {
//            logger.error("query product parameters are invalid,", px);
//        }

        ret.region = region;
        ret.keywords = keywords;
        ret.page = page;
        if(!StringUtils.isEmpty((from)) ||
                !StringUtils.isEmpty((to)) ||
                !StringUtils.isEmpty((region)) ||
                !StringUtils.isEmpty((keywords)) ||
                (page > 1)) {
            ret.esSearchFlag = "1";
        }
        ret.baseUrl = baseUrl;

        return ret;
    }

    /**
     * 返回产品首页内容
     *
     * @param site 站点
     * @param lan  语言
     * @return
     */
    @RequestMapping(value = "/cata", method = RequestMethod.POST)
    public ApiResult retrieveCataProductList(@RequestHeader(required = false) String token, String site, String lan,
                                             String category, String from, String to, String region, String keywords, int page, String baseUrl) {
        logger.info("Processing retrieveCataProductList, params are token:{}, site: {}, lan: {}, category: {}, from: {}, to: {}, region: {}, keywords: {}, page: {}",
                token, site, lan, category, from, to, region, keywords, page);

        ApiResult result = null;

        try {
            //检查是否有token，有则获取用户信息，获得userId
            String userId = "";
            if(!StringUtils.isEmpty(token)) {
                OAuthUserInfo oui = appOAuthService.getUserInfo(token, Constants.APP_CODE);
                if(oui != null) {
                    userId = MapUtils.getString(oui.info.attrs, "ShdippUserid", "");
                }
            }

            ProductConditionPojo productConditionPojo = buildCondition(site, lan, category, from, to, region, keywords, page, userId, baseUrl);

            ProductCategoryPojo ret = productService.retrieveProductByCategory(productConditionPojo);

            //返回信息
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "调用成功", ret);

        }
        catch(MessageException ex) {

            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, ex.getMsgCode(), ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 返回产品排行
     *
     * @param site 站点
     * @param lan  语言
     * @return
     */
    @RequestMapping(value = "/ranks", method = RequestMethod.POST)
    public ApiResult retrieveProductRanks(@RequestHeader(required = false) String token, String site, String lan, String baseUrl) {
        logger.info("Processing retrieveProductRanks, params are token:{}, site: {}, lan: {}",
                token, site, lan);

        ApiResult result = null;

        try {
            //检查是否有token，有则获取用户信息，获得userId
            String userId = "";
            if(!StringUtils.isEmpty(token)) {
                OAuthUserInfo oui = appOAuthService.getUserInfo(token, Constants.APP_CODE);
                if(oui != null) {
                    userId = MapUtils.getString(oui.info.attrs, "ShdippUserid", "");
                }
            }

            ProductConditionPojo productConditionPojo = buildCondition(site, lan, "", "", "", "", "", 0, userId, baseUrl);

            ProductRanksPojo ret = productService.retrieveProductRanks(productConditionPojo);

            //返回信息
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "调用成功", ret);

        }
        catch(MessageException ex) {

            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, ex.getMsgCode(), ex.getMessage(), null);
        }
        return result;
    }


    /**
     * 点赞产品
     *
     * @param prdId 产品id
     * @return
     */
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public ApiResult productLike(String prdId) {
        logger.info("Processing productLike, prdId: {}", prdId);

        ApiResult result = null;

        try {
            productService.productLike(prdId);

            //返回信息
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "调用成功", null);

        }
        catch(MessageException ex) {

            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, ex.getMsgCode(), ex.getMessage(), null);
        }
        return result;
    }

    /**
     * 点赞产品
     *
     * @param prdId 产品id
     * @return
     */
    @RequestMapping(value = "/favorite", method = RequestMethod.POST)
    public ApiResult productFav(@RequestHeader(required = false) String token, String prdId, String favFlag) {
        logger.info("Processing productfav, prdId: {}", prdId);

        ApiResult result = null;

        try {
            //检查是否有token，有则获取用户信息，获得userId
            String userId = "";
            if(!StringUtils.isEmpty(token)) {
                OAuthUserInfo oui = appOAuthService.getUserInfo(token, Constants.APP_CODE);
                if(oui != null) {
                    userId = MapUtils.getString(oui.info.attrs, "ShdippUserid", "");
                    productService.productFav(userId, prdId, favFlag);
                }
                else {
                    throw new MessageException("未登录用户不能收藏产品");
                }
            }


            //返回信息
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "调用成功", null);

        }
        catch(MessageException ex) {

            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, ex.getMsgCode(), ex.getMessage(), null);
        }
        return result;
    }

}
