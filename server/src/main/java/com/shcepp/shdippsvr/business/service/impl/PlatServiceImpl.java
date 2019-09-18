package com.shcepp.shdippsvr.business.service.impl;

import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesResDetailPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesResPojo;
import com.shcepp.shdippsvr.business.config.HomePageConfig;
import com.shcepp.shdippsvr.business.dao.ShdippBizEntInfoDetailDao;
import com.shcepp.shdippsvr.business.dao.ShdippBizPlatformDetailDao;
import com.shcepp.shdippsvr.business.dao.ShdippBizRecommendationDao;
import com.shcepp.shdippsvr.business.dao.VPlatformInfoDetailDao;
import com.shcepp.shdippsvr.business.entity.ShdippBizEntInfoDetailEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizPlatformDetailEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizRecommendationEntity;
import com.shcepp.shdippsvr.business.entity.view.VPlatformInfoDetailEntity;
import com.shcepp.shdippsvr.business.service.FileService;
import com.shcepp.shdippsvr.business.service.PlatService;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于平台纬度业务逻辑的实现
 *
 * @author BrunE
 * @date 2019-07-18 14:10
 **/

@Service
@Transactional
public class PlatServiceImpl implements PlatService {

    private static final Logger logger = LoggerFactory.getLogger(PlatServiceImpl.class);

    @Autowired
    private VPlatformInfoDetailDao vPlatformInfoDetailDao;
    @Autowired
    private FileService fileService;

    @Autowired
    private ShdippBizEntInfoDetailDao shdippBizEntInfoDetailDao;

    @Autowired
    private ShdippBizRecommendationDao shdippBizRecommendationDao;
    @Autowired
    private ShdippBizPlatformDetailDao shdippBizPlatformDetailDao;

    @Override
    public List<RecommendedResourcesResPojo> queryHomePagePlatRecommendedResources(RecommendedResourcesReqPojo pojo) {

        List<String> idList = new ArrayList<>();
        //推荐位
        List<ShdippBizRecommendationEntity> recommendationList;
        //顺位排序
        List<ShdippBizPlatformDetailEntity> platformDetailEntities;

        recommendationList = shdippBizRecommendationDao.queryByRecommTypeAndLanAndDataDomainAndStatus(pojo.getModuleType(),
                                                                                                      pojo.getLanCode(),
                                                                                                      pojo.getDominCode(),
                                                                                                      pojo.getRecommendationStatus(),
                                                                                                      HomePageConfig.homepagePlatSize);
        recommendationList.stream().forEach(f -> {
            idList.add(f.getRecommId());
        });
        if (idList.size() == 0) {
            platformDetailEntities = shdippBizPlatformDetailDao.queryByLanAndDataDomainAndStatus(pojo.getLanCode(),
                                                                                                 pojo.getDominCode(),
                                                                                                 pojo.getRecommendationStatus(),

                                                                                                 HomePageConfig.homepagePlatSize);
        } else {
            platformDetailEntities = shdippBizPlatformDetailDao.queryByLanAndDataDomainAndStatusWithExcloud(pojo.getLanCode(),
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
                                                                        List<ShdippBizPlatformDetailEntity> platformDetailEntities) {
        int size = new Long(HomePageConfig.homepagePlatSize).intValue();
        String[] midIds = new String[size];
        String[] ids;

        List<RecommendedResourcesResPojo> pojoList = new ArrayList<>();
        int j = 0;
        RecommendedResourcesResPojo resPojo = new RecommendedResourcesResPojo();
        //对推荐位进行排序
        for (ShdippBizRecommendationEntity entity : recommendationList) {
            if (entity.getPosId() < size - 1) {
                midIds[((int) entity.getPosId())] = entity.getRecommId();
                logger.debug("使用ID为{}的推荐平台数据进行展示", entity.getId());
            }
        }
        //对自然顺序进行排序
        for (int i = 0; i < midIds.length; i++) {

            if (!StringUtils.isNotEmptyWithNUllCheckStr(midIds[i])) {
                //判断数据是否充足
                //在充足的情况下再进行插入操作
                if (j < platformDetailEntities.size()) {
                    logger.debug("使用ID为{}的平台数据进行展示,下标为：{}", platformDetailEntities.get(j).getId(), j);

                    midIds[i] = platformDetailEntities.get(j).getId();
                    j++;
                }

            }
        }
        ids = StringUtils.removeNull(midIds);
        //获取展示内容
        for (int i = 0; i < ids.length; i++) {
            if (StringUtils.isNotEmptyWithNUllCheckStr(ids[i])) {
                resPojo = getHomePagePlatRecommendedResources(ids[i], status, (long) i, BaseURL);
                if (null != resPojo) {
                    pojoList.add(resPojo);
                }
            }
        }

        return pojoList;
    }

    @Override
    public RecommendedResourcesResPojo getHomePagePlatRecommendedResources(String id, String status, long sort, String baseUrl) {
        VPlatformInfoDetailEntity entity = vPlatformInfoDetailDao.findById(id);
        List<ShdippBizEntInfoDetailEntity> entityList;
        //进行关联查询，获取对应的企业信息
        if (null == entity) {
            logger.info("根据ID{}无法获取到对应数据", id);
            return null;
        }
        entityList = shdippBizEntInfoDetailDao.getEnDetailByUserID(entity.getUserId(),entity.getLan() ,status);
        return formatHomPageBean(entity, baseUrl, sort, entityList);
    }

    @Override
    public RecommendedResourcesResPojo formatHomPageBean(VPlatformInfoDetailEntity entity,
                                                         String baseUrl,
                                                         long sort,
                                                         List<ShdippBizEntInfoDetailEntity> entityList) {
        RecommendedResourcesResPojo resPojo = new RecommendedResourcesResPojo();

        List<RecommendedResourcesResDetailPojo> detailPojoList;
        RecommendedResourcesResDetailPojo resourceDetailPojo;

        resPojo.setTransversePicUrlPicUrl(fileService.getFileUrl(baseUrl, entity.getImage1(), null));
        resPojo.setPortraitPicUrl(fileService.getFileUrl(baseUrl, entity.getImage2(), null));
        resPojo.setLogoUrl(fileService.getFileUrl(baseUrl, entity.getLogo(), null));
        resPojo.setName(entity.getName());
        resPojo.setJumpLink(entity.getWebsite());
        resPojo.setAbbreviation(StringUtils.getLegalProfile(entity.getProfile(), HomePageConfig.pageAbbreviationLength));
        resPojo.setId(entity.getId());
        resPojo.setDescribe(StringUtils.getLegalProfile(entity.getProfile(), HomePageConfig.pageAbbreviationLength));
        resPojo.setAbbreviation(entity.getProfile());

        //业务类型--平台
        resPojo.setBusinessType( entity.getCategory());

        //所属模块
        resPojo.setModuleType(entity.getCategory());
        resPojo.setSort(sort);

        if (null != entityList) {
            detailPojoList = new ArrayList<>();
            for (ShdippBizEntInfoDetailEntity detailEntity : entityList) {
                resourceDetailPojo = new RecommendedResourcesResDetailPojo();
                resourceDetailPojo.setDisName(detailEntity.getName());
                resourceDetailPojo.setDisAbbreviation(detailEntity.getProfile());
                resourceDetailPojo.setDisUrl(detailEntity.getOfficialWebsite());
                resourceDetailPojo.setDisId(detailEntity.getId());
                detailPojoList.add(resourceDetailPojo);
            }
            resPojo.setDisList(detailPojoList);
        }
        resPojo.setUserId(entity.getUserId());

        return resPojo;
    }
}
