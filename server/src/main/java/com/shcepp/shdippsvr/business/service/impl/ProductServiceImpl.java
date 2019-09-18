package com.shcepp.shdippsvr.business.service.impl;

import com.shcepp.shdippsvr.business.bean.ProductDetailDesBean;
import com.shcepp.shdippsvr.business.bean.ProductRecommendBean;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticSearchQueryResultBean;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticsearchPagination;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ShdippElasticSearchBean;
import com.shcepp.shdippsvr.business.bean.pojo.*;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesResDetailPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesResPojo;
import com.shcepp.shdippsvr.business.bean.pojo.resources.*;
import com.shcepp.shdippsvr.business.config.HomePageConfig;
import com.shcepp.shdippsvr.business.dao.*;
import com.shcepp.shdippsvr.business.entity.*;
import com.shcepp.shdippsvr.business.entity.elasticsearch.ElasticSearchEntity;
import com.shcepp.shdippsvr.business.entity.view.VProductInfoDetailEntity;
import com.shcepp.shdippsvr.business.enums.*;
import com.shcepp.shdippsvr.business.exception.MessageException;
import com.shcepp.shdippsvr.business.service.BaseService;
import com.shcepp.shdippsvr.business.service.FileService;
import com.shcepp.shdippsvr.business.service.InteractionService;
import com.shcepp.shdippsvr.business.service.ProductService;
import com.shcepp.shdippsvr.business.service.elasticsearch.ElasticsearchService;
import com.shcepp.shdippsvr.business.service.elasticsearch.impl.ElasticsearchServiceImpl;
import com.shcepp.shdippsvr.business.util.DateUtils;
import com.shcepp.shdippsvr.sys.service.RedisUtil;
import com.shcepp.shdippsvr.sys.util.*;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.shcepp.shdippsvr.business.entity.ShdippBizContactEntity.DEL_FLAG_1;
import static com.shcepp.shdippsvr.business.entity.ShdippBizProductDetailEntity.STATUS_1;
import static com.shcepp.shdippsvr.business.service.FileService.PIC_FILE_URL_PATTEN_O;
import static com.shcepp.shdippsvr.business.service.FileService.PIC_FILE_URL_PATTEN_T;

/**
 * 基于产品纬度业务逻辑的实现
 *
 * @author BrunE
 * @date 2019-07-18 14:10
 **/

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final LambdaLogger logger = LambdaLoggerFactory.getLogger(getClass());
    @Autowired
    ProductService productService;
    @Autowired
    InteractionService interactionService;
    @Autowired
    ShdippBizProductDao shdippBizProductDao;
    @Autowired
    ElasticsearchService elasticsearchService;
    @Autowired
    ShdippBizUserLikeDao shdippBizUserLikeDao;
    @Autowired
    ShdippBizUserFavoriteDao shdippBizUserFavoriteDao;
    @Autowired
    FileService fileService;

    //产品推荐位
    Map<String, ModuleType> prdRecommendations = new HashMap<String, ModuleType>() {

        private static final long serialVersionUID = 3924912787998678653L;

        {
            put("01", ModuleType.MT_P1);
            put("02", ModuleType.MT_P2);
            put("03", ModuleType.MT_P3);
            put("04", ModuleType.MT_P4);
            put("05", ModuleType.MT_P5);
            put("06", ModuleType.MT_P6);
        }
    };

    @Autowired
    private VProductInfoDetailDao vProductInfoDetailDao;


    @Autowired
    private ShdippBizRecommendationDao shdippBizRecommendationDao;
    @Autowired
    private ShdippBizProductDetailDao shdippBizProductDetailDao;
    @Autowired
    private ShdippBizContactDao shdippBizContactDao;
    @Autowired
    private ShdippBizEntInfoDao shdippBizEntInfoDao;
    @Autowired
    private ShdippBizEntInfoDetailDao shdippBizEntInfoDetailDao;
    @Autowired
    private ElasticsearchServiceImpl elasticsearchServiceImpl;
    @Autowired
    private ShdippBizPlatformInfoDao shdippBizPlatformInfoDao;
    @Autowired
    private ShdippBizPlatformDetailDao shdippBizPlatformDetailDao;
    @Autowired
    private ShdippBizPrdLikeDao shdippBizPrdLikeDao;

    //产品单页大小
    private int maxPos = 7;

    @Override
    public List<RecommendedResourcesResPojo> queryHomePageProductRecommendedResources(RecommendedResourcesReqPojo pojo) {

        List<String> idList = new ArrayList<>();
        //推荐位
        List<ShdippBizRecommendationEntity> recommendationList;
        //顺位排序
        List<ShdippBizProductDetailEntity> platformDetailEntities;

        recommendationList = shdippBizRecommendationDao.queryByRecommTypeAndLanAndDataDomainAndStatus(pojo.getModuleType(),
                pojo.getLanCode(),
                pojo.getDominCode(),
                pojo.getRecommendationStatus(),
                HomePageConfig.homepagePlatSize);
        recommendationList.stream().forEach(f -> {
            idList.add(f.getRecommId());
        });

        if(idList.size() == 0) {
            platformDetailEntities = shdippBizProductDetailDao.queryByLanAndDataDomainAndStatus(pojo.getLanCode(),
                    pojo.getDominCode(),
                    pojo.getRecommendationStatus(),
                    HomePageConfig.homepagePlatSize);
        }
        else {
            platformDetailEntities = shdippBizProductDetailDao.queryByLanAndDataDomainAndStatusWithExcloud(pojo.getLanCode(),
                    pojo.getDominCode(),
                    pojo.getRecommendationStatus(),
                    idList,
                    HomePageConfig.homepagePlatSize);

        }

        List<RecommendedResourcesResPojo> platHomePageResourceResList = new ArrayList<>();

        platHomePageResourceResList = sortAndFormatPlatRecommend(pojo.getRecommendationStatus(),
                pojo.getBaseUrl(),
                recommendationList,
                platformDetailEntities);

        return platHomePageResourceResList;
    }

    @Override
    public List<RecommendedResourcesResPojo> sortAndFormatPlatRecommend(String status,
                                                                        String BaseURL,
                                                                        List<ShdippBizRecommendationEntity> recommendationList,
                                                                        List<ShdippBizProductDetailEntity> productDetailEntities) {
        int size = new Long(HomePageConfig.homepagePlatSize).intValue();
        String[] midIds = new String[size];
        String[] ids;

        List<RecommendedResourcesResPojo> pojoList = new ArrayList<>();
        int j = 0;
        RecommendedResourcesResPojo resPojo = new RecommendedResourcesResPojo();
        //对推荐位进行排序
        for(ShdippBizRecommendationEntity entity : recommendationList) {
            if(entity.getPosId() < size - 1) {
                midIds[((int) entity.getPosId())] = entity.getRecommId();
                logger.debug("使用ID为{}的推荐平台数据进行展示", entity.getId());
            }
        }
        //对自然顺序进行排序
        for(int i = 0; i < midIds.length; i++) {
            if(!StringUtils.isNotEmptyWithNUllCheckStr(midIds[i])) {
                //判断数据是否充足
                //在充足的情况下再进行插入操作
                if(j < productDetailEntities.size()) {
                    logger.debug("使用ID为{}的平台数据进行展示,下标为：{}", productDetailEntities.get(j).getId(), j);

                    midIds[i] = productDetailEntities.get(j).getId();
                    j++;
                }
            }
        }

        ids = StringUtils.removeNull(midIds);
        //获取展示内容
        for(int i = 0; i < ids.length; i++) {
            if(StringUtils.isNotEmptyWithNUllCheckStr(ids[i])) {
                resPojo = getHomePageProductRecommendedResources(ids[i], status, ((long) i), BaseURL);
                if(null != resPojo) {
                    pojoList.add(resPojo);
                }
            }
        }

        return pojoList;
    }

    @Override
    public RecommendedResourcesResPojo getHomePageProductRecommendedResources(String id, String status, long sort, String baseUrl) {
        VProductInfoDetailEntity entity = vProductInfoDetailDao.findById(id);
        List<ShdippBizProductDetailEntity> entityList;
        //进行关联查询，获取对应的企业信息
        if(null == entity) {
            logger.info("根据ID{}无法获取到对应数据", id);
            return null;
        }
        entityList = shdippBizProductDetailDao.queryProductDetailByUserID(entity.getUserId(), entity.getLan(), status);
        return formatHomPageBean(entity, baseUrl, sort, entityList);
    }

    @Override
    public RecommendedResourcesResPojo formatHomPageBean(VProductInfoDetailEntity entity,
                                                         String baseURL,
                                                         long sort,
                                                         List<ShdippBizProductDetailEntity> entityList) {
        RecommendedResourcesResPojo resPojo = new RecommendedResourcesResPojo();
        List<RecommendedResourcesResDetailPojo> detailPojoList = new ArrayList<>();
        RecommendedResourcesResDetailPojo resourceDetailPojo;
        //横图
        resPojo.setTransversePicUrlPicUrl(fileService.getFileUrl(baseURL, entity.getImage1(), null));
        //竖图
        resPojo.setPortraitPicUrl(fileService.getFileUrl(baseURL, entity.getImage2(), null));
        //产品名称
        resPojo.setName(entity.getProductName());
        //排序
        resPojo.setSort(sort);
        //明细ID
        resPojo.setId(entity.getId());
        //业务类型
        resPojo.setModuleType( entity.getCategory());
        resPojo.setBusinessType(entity.getCategory());
        resPojo.setAbbreviation(StringUtils.getLegalProfile(entity.getSummary(), HomePageConfig.pageAbbreviationLength));

//        resourceDetailPojo = new RecommendedResourcesResDetailPojo();
//        resourceDetailPojo.setDisName(entity.getProductName());
//        resourceDetailPojo.setDisAbbreviation(entity.getSummary());
//        resourceDetailPojo.setDisUrl(entity.getOfficialWebsite());
//        resourceDetailPojo.setDisId(entity.getId());
//        detailPojoList.add(resourceDetailPojo);
//        resPojo.setDisList(detailPojoList);
//        resPojo.setUserId(entity.getUserId());

        return resPojo;
    }

    private String getCategoryRecommType(String category) {
        return prdRecommendations.get(category).getCode();
    }

    @Override
    public ProductCategoryPojo retrieveProductByCategory(ProductConditionPojo condition) {
        logger.info("query product info by category ,only fetch one page, conditions are condition: {}", condition);
//        condition.category = ProductCategoryType. ProductCategoryType.getEnumsByValue(condition.category)
        ProductCategoryPojo ret = new ProductCategoryPojo();
        try {
            ret.page_info = new PagePojo();
            ret.category = condition.category;
            ret.list = new ArrayList<>();
            List<ProductEntity> resultList = new ArrayList<>();
            List<String> finalList = null;
            //判断是否使用es查询
            if("1".equals(condition.esSearchFlag)) {
                //拼接查询条件
                ShdippElasticSearchBean.Builder builder = ShdippElasticSearchBean.Builder();
                ShdippElasticSearchBean bean = new ShdippElasticSearchBean();

                List<BaseEnums> list = new ArrayList<>();
                //增加查询条件
                //类别，根据企业、产品、平台，有所不同
                list.add(ProductCategoryType.getEnumsByCode(condition.category));
                //语言
                list.add(LanType.getEnumsByCode(condition.lan));
                //站点
                list.add(DomainType.getEnumsByCode(condition.site));

                builder.attrList(list);

                //时间
                if(!StringUtils.isEmpty(condition.from)) {
                    builder.createDateStartTime(condition.from);
                }
                if(!StringUtils.isEmpty(condition.to)) {
                    builder.createDateEndTime(condition.to);
                }
                //地区
                if(!StringUtils.isEmpty(condition.region)) {
                    builder.region(condition.region);
                }
                //默认查询未删除数据
                builder.isDelete(false);


                //关键字
                if(!StringUtils.isEmpty(condition.keywords)) {
                    builder.queryKey(condition.keywords);
                }
                builder.sortKey(ElasticSearchEntity.UPDATE_DATE);
                bean = builder.build();
                //es暂时不提供分页，所以需要后端进行假分页，获取id列表
                ElasticsearchPagination page = new ElasticsearchPagination();
                page.setPageSize(maxPos);
                page.setCurrentPage(condition.page - 1);
                ElasticsearchPagination pageRet = elasticsearchService.queryEsByBean(bean, page);

                List<ElasticSearchQueryResultBean> result = pageRet.getContentList();

                if(result != null) {
                    //获得查询列表的ID List
                    finalList = new ArrayList<>();
                    for(ElasticSearchQueryResultBean item : result) {
                        finalList.add(item.getId());
                    }
                    ret.page_info.current = pageRet.getCurrentPage() + 1;
                    ret.page_info.max = (int) (pageRet.getTotalSize() / pageRet.getPageSize()) + 1;
                }
                else {
                    ret.page_info.current = 1;
                    ret.page_info.max = 1;
                }
            }
            else {
                //查询推荐位
                List<ShdippBizRecommendationEntity> prdRecommList = shdippBizRecommendationDao.queryByRecommTypeAndLanAndDataDomainAndStatus(
                        getCategoryRecommType(condition.category),
                        condition.lan,
                        condition.site,
                        "1",
                        maxPos);

                //查询最新的7条
                List<String> prdIdList = shdippBizProductDao.queryNewlyProductIdByCategory(condition.site, condition.lan, maxPos, condition.category);

                //处理推荐位
                finalList = BuildCategoryIdList(prdRecommList, prdIdList);
                int count = shdippBizProductDao.countProductByCategory(condition.site, condition.lan, condition.category);
                ret.page_info.current = 1;
                ret.page_info.max =  (count / maxPos) + 1;
            }


            int order = 1;
            if(finalList != null && finalList.size() > 0) {
                //根据id获取产品全信息
                    resultList = shdippBizProductDao.queryProductByIdList(finalList);
                for(String prdId : finalList) {
                    for(ProductEntity item : resultList) {
                        if(prdId.equals(item.getId())) {
                            ProductCardItemPojo nItem = buildProductItem(item, order++);
                            ret.list.add(nItem);
                            break;
                        }
                    }
                }
                //处理图片路径
                DealWithImg(ret.list, condition.baseUrl);
                //按类别 处理收藏值
                if(!StringUtils.isEmpty(condition.userId)) {
                    //获取收藏列表
                    Set<String> favList = interactionService.queryUserFavPrdId(condition.userId);
                    DealWithFavoriteItem(ret.list, favList);
                }
            }
        }
        catch(Exception ex) {
            logger.error("error occurs while query products: ", ex);
            throw new MessageException(Constants.PRODUCT_ERROR.ERROR_CODE000, Constants.PRODUCT_ERROR.ERROR_INFO000);
        }
        return ret;
    }

    @Override
    public ProductRanksPojo retrieveProductRanks(ProductConditionPojo condition) {

        logger.info("query product info ranks, conditions are condition: {}", condition);
        //查询排名
        logger.info("fetch ProductRanksPojo from redis");
        String rankStr = RedisUtil.get("ProductRanks" + condition.site + condition.lan);


        ProductRanksPojo ranks = JsonUtil.jsonToBean(rankStr, ProductRanksPojo.class);
        try {
            if(ranks == null || ranks.newly.size() == 0) {
                ranks = storeProductRanksToCache(condition);
            }
            else {
                //按类别 处理收藏值
                if(!StringUtils.isEmpty(condition.userId)) {
                    //获取收藏列表
                    Set<String> favList = interactionService.queryUserFavPrdId(condition.userId);
                    DealWithFavoriteItem(ranks.favorites, favList);
                    DealWithFavoriteItem(ranks.newly, favList);
                    DealWithFavoriteItem(ranks.likes, favList);
                    DealWithFavoriteItem(ranks.recommendations, favList);
                }
            }
            //处理图片路径

                DealWithImg(ranks.favorites, condition.baseUrl);
            DealWithImg(ranks.newly, condition.baseUrl);
            DealWithImg(ranks.likes, condition.baseUrl);
            DealWithImg(ranks.recommendations, condition.baseUrl);
        }
        catch(Exception ex) {
            logger.error("error occurs while query ranks: ", ex);
            throw new MessageException(Constants.PRODUCT_ERROR.ERROR_CODE001, Constants.PRODUCT_ERROR.ERROR_INF0001);
        }
        //返回信息

        return ranks;
    }

    @Override
    public ProductRanksPojo storeProductRanksToCache(ProductConditionPojo condition) {

        ProductRanksPojo ret = new ProductRanksPojo();
        try {

            //收藏榜
            List<ProductEntity> list = shdippBizProductDao.queryRankByFavs(condition.site, condition.lan, 5);
            ret.favorites = new ArrayList<>();
            int order = 1;
            for(ProductEntity item : list) {
                ProductCardItemPojo nItem = buildProductItem(item, order++);
                ret.favorites.add(nItem);
            }

            //点赞榜
            list = shdippBizProductDao.queryRankByLikes(condition.site, condition.lan, 5);
            ret.likes = new ArrayList<>();
            order = 1;
            for(ProductEntity item : list) {
                ProductCardItemPojo nItem = buildProductItem(item, order++);
                ret.likes.add(nItem);
            }

            //推荐榜
            list = shdippBizProductDao.queryRankByRecommendation(condition.site, condition.lan, 3);
            ret.recommendations = new ArrayList<>();
            order = 1;
            for(ProductEntity item : list) {
                ProductCardItemPojo nItem = buildProductItem(item, order++);
                ret.recommendations.add(nItem);
            }
            //最近
            list = shdippBizProductDao.queryRankByNewly(condition.site, condition.lan, 3);
            ret.newly = new ArrayList<>();
            order = 1;
            for(ProductEntity item : list) {
                ProductCardItemPojo nItem = buildProductItem(item, order++);
                ret.newly.add(nItem);
            }
        }
        catch(Exception ex) {
            logger.error("error occurs while caching ranks: ", ex);
            throw new MessageException(Constants.PRODUCT_ERROR.ERROR_CODE004, Constants.PRODUCT_ERROR.ERROR_INF0004);
        }

        logger.info("save ProductRanks to redis");
        RedisUtil.set("ProductRanks" + condition.site + condition.lan, JsonUtil.beanToJson(ret));

        return ret;
    }

    @Override
    public void productLike(String prdId) {
        try {
            ShdippBizUserLikeEntity entity = new ShdippBizUserLikeEntity();
            entity.setUserLikeId(SnowflakeIdWorkerUtil.nextIdStr());
            entity.setItemType(3);
            entity.setLikeItemId(prdId);
            shdippBizUserLikeDao.saveAndFlush(entity);
        }
        catch(Exception ex) {
            logger.error("error occurs while add userlike entity: ", ex);
            throw new MessageException(Constants.PRODUCT_ERROR.ERROR_CODE002, Constants.PRODUCT_ERROR.ERROR_INF0002);
        }
    }

    @Override
    public void productFav(String userId, String prdId, String favFlag) {
        //判断收藏标志
        try {
            ShdippBizUserFavoriteEntity entity = shdippBizUserFavoriteDao.findByUserIdAndAndItemTypeAndAndFavoriteItemId(userId, 3, prdId);
            if ("1".equals(favFlag)){
                if (entity == null){
                    entity = new ShdippBizUserFavoriteEntity();
                    entity.setFavoriteItemId(prdId);
                    entity.setItemType(3);
                    entity.setUserFavoriteId(SnowflakeIdWorkerUtil.nextIdStr());
                    entity.setUserId(userId);
                    shdippBizUserFavoriteDao.saveAndFlush(entity);
                }
            }
            else {
                shdippBizUserFavoriteDao.delete(entity);
            }
        }
        catch(Exception ex) {
            logger.error("error occurs while deal with favorite: ", ex);
            throw new MessageException(Constants.PRODUCT_ERROR.ERROR_CODE003, Constants.PRODUCT_ERROR.ERROR_INF0003);
        }
    }

    /**
     * 处理收藏
     *
     * @param prdList 产品列表
     * @param favList 收藏列表
     */
    private void DealWithFavoriteItem(List<ProductCardItemPojo> prdList, Set<String> favList) {

        for(ProductCardItemPojo item : prdList) {
            //判断是否收藏
            item.favorite = favList.contains(item.prdId) ? "1" : "0";
        }
    }

    /**
     * 处理地址
     *
     * @param prdList 产品列表
     * @param baseUrl 收藏列表
     */
    private void DealWithImg(List<ProductCardItemPojo> prdList, String baseUrl) {

        for(ProductCardItemPojo item : prdList) {
            item.img = fileService.getFileUrl(baseUrl, item.img, PIC_FILE_URL_PATTEN_O);
            item.img_s = fileService.getFileUrl(baseUrl, item.img_s, PIC_FILE_URL_PATTEN_T);
        }
    }

    /**
     * 处理推荐位
     *
     * @param prdRecommList
     * @param prdIdList
     * @return
     */
    private List<String> BuildCategoryIdList(List<ShdippBizRecommendationEntity> prdRecommList, List<String> prdIdList) {

        for(ShdippBizRecommendationEntity item : prdRecommList) {
            //去除重复的id
            if(prdIdList.contains(item.getRecommId())) {
                prdIdList.remove(prdIdList.indexOf(item.getRecommId()));
            }
            //推荐位序号如果小于等于列表长度，则直接插入在对应位置
            if(item.getPosId() <= prdIdList.size()) {
                prdIdList.add((int) item.getPosId() - 1, item.getRecommId());
            }
            //否则插入在尾部
            else {
                prdIdList.add(item.getRecommId());
            }
        }
        return prdIdList;
    }

    private ProductCardItemPojo buildProductItem(ProductEntity item, int order) {
        ProductCardItemPojo nItem = new ProductCardItemPojo();
        nItem.prdId = item.getId();
        nItem.product_name = item.getProductName();
        nItem.company_name = item.getCompanyName();
        nItem.company_url =  item.getOfficialWebSite().toLowerCase().startsWith("http")? item.getOfficialWebSite(): "http://" + item.getOfficialWebSite();
        nItem.img = item.getImage1();
        nItem.img_s = item.getImage1();
        nItem.like = item.getLikeCount();
        nItem.mail = Constants.ADMIN_EMAIL;
        nItem.order = order;
        nItem.summary = item.getSummary();
        nItem.puton_time = item.getUpdateTime();
        nItem.url = item.getId();
        return nItem;
    }

    @Override
    public ApiResult queryProductInfo(ProductInfoReqPojo productInfoReqPojo,String userId) {

        logger.info("query product's detail .");
        String url = productInfoReqPojo.getUrl();
        String lan = productInfoReqPojo.getLan();
        String site = productInfoReqPojo.getSite();
        ApiResult result = new ApiResult();
        ProductInfoRspPojo productInfoRspPojo = new ProductInfoRspPojo();
        ShdippBizUserEntity user = new ShdippBizUserEntity();
        ShdippBizContactEntity contact = new ShdippBizContactEntity();
        ShdippBizProductEntity product = new ShdippBizProductEntity();
        ShdippBizProductDetailEntity productDetail = new ShdippBizProductDetailEntity();
        ShdippBizEntInfoEntity entInfo = new ShdippBizEntInfoEntity();
        ShdippBizEntInfoDetailEntity entInfoDetail = new ShdippBizEntInfoDetailEntity();
        List<ShdippBizPlatformInfoEntity> platformList = new ArrayList<ShdippBizPlatformInfoEntity>();
        List<ShdippBizPlatformDetailEntity> platformDetailList = new ArrayList<ShdippBizPlatformDetailEntity>();
        ShdippBizPrdLikeEntity prdLike = new ShdippBizPrdLikeEntity();
        ShdippBizUserFavoriteEntity userFavorite = new ShdippBizUserFavoriteEntity();

        GuessCompanyInfoRspPojo guessCompanyInfoRspPojo = new GuessCompanyInfoRspPojo();
        List<GuessPlatformInfoRspPojo> guessPlatformInfoRspPojoList = new ArrayList<GuessPlatformInfoRspPojo>();
        List<GuessProductInfoRspPojo> guessProductInfoRspPojoList = new ArrayList<GuessProductInfoRspPojo>();

        String summary = null;
        String profile = null;
        int likeCount = 0;
        List<ProductImageRspPojo> imageList = new ArrayList<ProductImageRspPojo>();
        try{
            //通过产品信息主表ID查询相关信息
            product = shdippBizProductDao.findById(productInfoReqPojo.getProductId());
            prdLike = shdippBizPrdLikeDao.findByPrdId(productInfoReqPojo.getProductId());
            if(StringUtils.isNotEmptyWithNUllCheckStr(userId)){
                userFavorite = shdippBizUserFavoriteDao.findByUserIdAndAndItemTypeAndAndFavoriteItemId(userId,ShdippBizUserFavoriteEntity.ITEM_TYPE_3,productInfoReqPojo.getProductId());
                if(null != userFavorite){
                    productInfoRspPojo.setFavoriteFlag("T");
                }else {
                    productInfoRspPojo.setFavoriteFlag("F");
                }
            }else {
                productInfoRspPojo.setFavoriteFlag("F");
            }
            //user = shdippBizUserDao.findById(product.getUserId());
            contact = shdippBizContactDao.findTopByUserIdAndDelFlagOrderByDispOrderAsc(product.getUserId(), DEL_FLAG_1);
            productDetail = shdippBizProductDetailDao.findByPrdIdAndStatus(productInfoReqPojo.getProductId(),STATUS_1);
            entInfo = shdippBizEntInfoDao.findByUserIdAndLanAndDataDomainAndDelFlag(product.getUserId(),lan,site,DEL_FLAG_1);
            entInfoDetail = shdippBizEntInfoDetailDao.findByEntIdAndStatus(entInfo.getId(),STATUS_1);

            //判断是否使用es查询
            //拼接查询条件
            ShdippElasticSearchBean.Builder builder = ShdippElasticSearchBean.Builder();
            ShdippElasticSearchBean bean = new ShdippElasticSearchBean();

            //查询单条信息时，需要传入id,此id为产品子表的id号
            builder.idVal(productDetail.getId());

            bean = builder.build();
            //获取id列表
            ElasticSearchQueryResultBean resultBean = elasticsearchService.queryEsById(bean);
            if(null != resultBean){
                ProductDetailDesBean desBean = JsonUtil.jsonToBean(resultBean.getOrMessage(),ProductDetailDesBean.class);
                summary = desBean.getDescribe();
            }

            //产品详情相关信息
            productInfoRspPojo.setProductName(productDetail.getProductName());
            //产品点赞
            if(null != prdLike){
                likeCount = prdLike.getLikeCount();
            }
            productInfoRspPojo.setLikeCount(likeCount);
            String[] categoryArr = productDetail.getCategory().split(",");
            productInfoRspPojo.setCategoryArr(categoryArr);

            if(StringUtils.isNotEmptyWithNUllCheckStr(productDetail.getImage1())){
                ProductImageRspPojo image = new ProductImageRspPojo();
                String IUrl = fileService.getFileUrl(url, productDetail.getImage1(), null);
                if(StringUtils.isNotEmptyWithNUllCheckStr(IUrl) && IUrl.contains(FileService.VID_FILE_URL_PATTEN)){
                    image.setIsMedia("M");
                }else{
                    image.setIsMedia("I");
                }
                image.setImage(IUrl);
                imageList.add(image);

            }
            if(StringUtils.isNotEmptyWithNUllCheckStr(productDetail.getImage2())){
                ProductImageRspPojo image = new ProductImageRspPojo();
                String IUrl = fileService.getFileUrl(url, productDetail.getImage2(), null);
                if(StringUtils.isNotEmptyWithNUllCheckStr(IUrl) && IUrl.contains(FileService.VID_FILE_URL_PATTEN)){
                    image.setIsMedia("M");
                }else{
                    image.setIsMedia("I");
                }
                image.setImage(IUrl);
                imageList.add(image);
            }

            productInfoRspPojo.setImages(imageList);
            productInfoRspPojo.setPutonTime(DateUtils.getDate(productDetail.getPutonTime()));
            productInfoRspPojo.setRegion(productDetail.getRegion());
            productInfoRspPojo.setCopyrightNumber(productDetail.getCopyrightNumber());
            productInfoRspPojo.setCompanyName(entInfoDetail.getName());
            productInfoRspPojo.setSummary(null == summary ? productDetail.getSummary() : summary);
            productInfoRspPojo.setName(contact.getName());
            productInfoRspPojo.setTele(contact.getTele());
            productInfoRspPojo.setEmail(contact.getEmail());
            productInfoRspPojo.setOfficeAddr(contact.getOfficeAddr());
            //猜你喜欢相关信息
            //企业
            guessCompanyInfoRspPojo.setCompanyName(entInfoDetail.getName());
            if(StringUtils.isNotEmptyWithNUllCheckStr(entInfoDetail.getProfile())){
                profile = entInfoDetail.getProfile();
                if(profile.length() > 200){
                    profile = profile.substring(0,200);
                }
                guessCompanyInfoRspPojo.setCompangProfile(profile);
            }
            guessCompanyInfoRspPojo.setCompanyLogoUrl(fileService.getFileUrl(url, entInfoDetail.getLogo(), PIC_FILE_URL_PATTEN_O));
            productInfoRspPojo.setGuessCompanyInfo(guessCompanyInfoRspPojo);
            //平台
            platformList = shdippBizPlatformInfoDao.findByUserIdAndLanAndDataDomainAndDelFlag(product.getUserId(), lan, site, DEL_FLAG_1);
            if (null != platformList && platformList.size() > 0) {
                int i = 1;
                for(ShdippBizPlatformInfoEntity platform : platformList){
                    platformDetailList =
                            shdippBizPlatformDetailDao.findByPlatformIdAndDataDomainAndStatusAndDelFlag(platform.getId(),
                                    site,
                                    ShdippBizPlatformDetailEntity.STATUS_1,
                                    ShdippBizEntInfoEntity.DEL_FLAG_1);
                    for(ShdippBizPlatformDetailEntity platformDetail : platformDetailList) {
                        GuessPlatformInfoRspPojo guessPlatformInfoRspPojo = new GuessPlatformInfoRspPojo();
                        guessPlatformInfoRspPojo.setOrder(String.valueOf(i));
                        guessPlatformInfoRspPojo.setPlatformName(platformDetail.getName());
                        guessPlatformInfoRspPojo.setPlatformProfile(platformDetail.getProfile());
                        guessPlatformInfoRspPojo.setPlatformLogoUrl(fileService.getFileUrl(url, platformDetail.getLogo(), PIC_FILE_URL_PATTEN_O));

                        guessPlatformInfoRspPojoList.add(guessPlatformInfoRspPojo);
                        ++i;
                    }
                }


                productInfoRspPojo.setGuessPlatformInfoList(guessPlatformInfoRspPojoList);
            }

            //推荐产品
            ProductRecommendBean recommendProductBean = JsonUtil.jsonToBean(entInfo.getRecommendationPrd(), ProductRecommendBean.class);
            List<String> productIdList = new ArrayList<String>();
            if (null != recommendProductBean) {
                if (StringUtils.isNotEmptyWithNUllCheckStr(recommendProductBean.getProductOneId())) {
                    String productId = recommendProductBean.getProductOneId();
                    productIdList.add(productId);
                }
                if (StringUtils.isNotEmptyWithNUllCheckStr(recommendProductBean.getProductTwoId())) {
                    String productId = recommendProductBean.getProductTwoId();
                    productIdList.add(productId);
                }
                if (StringUtils.isNotEmptyWithNUllCheckStr(recommendProductBean.getProductThreeId())) {
                    String productId = recommendProductBean.getProductThreeId();
                    productIdList.add(productId);
                }
                if (StringUtils.isNotEmptyWithNUllCheckStr(recommendProductBean.getProductFourId())) {
                    String productId = recommendProductBean.getProductFourId();
                    productIdList.add(productId);
                }
            }
            if(productIdList.size() > 0) {
                List<ShdippBizProductDetailEntity>
                        productDetailList =
                        shdippBizProductDetailDao.queryByLanAndDataDomainAndStatus(lan, site, ShdippBizProductDetailEntity.STATUS_1, productIdList);
                int i = 1;
                for(ShdippBizProductDetailEntity prdDetail : productDetailList) {
                    GuessProductInfoRspPojo productInfo = new GuessProductInfoRspPojo();
                    productInfo.setOrder(String.valueOf(i));
                    productInfo.setProductName(prdDetail.getProductName());
                    productInfo.setProductSummary(prdDetail.getSummary());
                    productInfo.setProductImageUrl(fileService.getFileUrl(url, prdDetail.getImage1(), PIC_FILE_URL_PATTEN_O));

                    guessProductInfoRspPojoList.add(productInfo);
                    ++i;
                }
            }
            productInfoRspPojo.setGuessProductInfoList(guessProductInfoRspPojoList);
            result.setFlag(BaseService.FLAG_T);
            result.setReturnCode(BaseService.BR_SUCCESS);
            result.setReturnInfo(BaseService.BR_SUCCESS_MESSAGE);
            result.setData(productInfoRspPojo);
        }
        catch(Exception e) {
            logger.error("Query Product Info error:", e);
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, BaseService.BR_OTHER_ERROR, "查询产品详情信息错误。", null);
        }
        return result;
    }
}

