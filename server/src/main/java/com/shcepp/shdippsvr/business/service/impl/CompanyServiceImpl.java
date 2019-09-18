package com.shcepp.shdippsvr.business.service.impl;

import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesResDetailPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesResPojo;
import com.shcepp.shdippsvr.business.bean.pojo.resources.CompanyInfoReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.resources.CompanyInfoRspPojo;
import com.shcepp.shdippsvr.business.config.HomePageConfig;
import com.shcepp.shdippsvr.business.dao.*;
import com.shcepp.shdippsvr.business.entity.ShdippBizEntInfoDetailEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizEntInfoEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizPlatformDetailEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizRecommendationEntity;
import com.shcepp.shdippsvr.business.entity.view.VEntRecInfoDetailEntity;
import com.shcepp.shdippsvr.business.service.BaseService;
import com.shcepp.shdippsvr.business.service.CompanyService;
import com.shcepp.shdippsvr.business.service.FileService;
import com.shcepp.shdippsvr.oauth.dto.OAuthUserInfo;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import com.shcepp.shdippsvr.sys.util.SnowflakeIdWorkerUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.shcepp.shdippsvr.business.entity.ShdippBizEntInfoDetailEntity.STATUS_1;
import static com.shcepp.shdippsvr.business.entity.ShdippBizEntInfoEntity.*;

/**
 * 基于企业纬度业务逻辑的实现
 *
 * @author BrunE
 * @date 2019-07-18 14:10
 **/

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private VEntRecInfoDetailDao vEntRecInfoDetailDao;
    @Autowired
    private ShdippBizEntInfoDao shdippBizEntInfoDao;
    @Autowired
    private ShdippBizEntInfoDetailDao shdippBizEntInfoDetailDao;
    @Autowired
    private FileService fileService;

    @Autowired
    private ShdippBizPlatformDetailDao shdippBizPlatformDetailDao;

    @Autowired
    private ShdippBizRecommendationDao shdippBizRecommendationDao;

    @Override
    public List<RecommendedResourcesResPojo> queryHomePageCompanyRecommendedResources(RecommendedResourcesReqPojo pojo) {

        List<String> idList = new ArrayList<>();
        //推荐位
        List<ShdippBizRecommendationEntity> recommendationList;
        //顺位排序
        List<ShdippBizEntInfoDetailEntity> companyDetailEntities;

        recommendationList = shdippBizRecommendationDao.queryByRecommTypeAndLanAndDataDomainAndStatus(pojo.getModuleType(),
                                                                                                      pojo.getLanCode(),
                                                                                                      pojo.getDominCode(),
                                                                                                      pojo.getRecommendationStatus(),
                                                                                                      HomePageConfig.homepagePlatSize);
        recommendationList.stream().forEach(f -> {
            idList.add(f.getRecommId());
        });

        if (idList.size() == 0) {

            companyDetailEntities = shdippBizEntInfoDetailDao.queryByLanAndDataDomainAndStatus(pojo.getLanCode(),
                                                                                               pojo.getDominCode(),
                                                                                               pojo.getRecommendationStatus(),
                                                                                               HomePageConfig.homepagePlatSize);
        } else {

            companyDetailEntities = shdippBizEntInfoDetailDao.queryByLanAndDataDomainAndStatusWithExcloud(pojo.getLanCode(),
                                                                                                          pojo.getDominCode(),
                                                                                                          pojo.getRecommendationStatus(),
                                                                                                          idList,
                                                                                                          HomePageConfig.homepagePlatSize);
        }

        List<RecommendedResourcesResPojo> platHomePageResourceResList = new ArrayList<>();

        platHomePageResourceResList = sortAndFormatPlatRecommend(pojo.getRecommendationStatus(),
                                                                 pojo.getBaseUrl(),
                                                                 recommendationList,
                                                                 companyDetailEntities);

        return platHomePageResourceResList;
    }

    @Override
    public List<RecommendedResourcesResPojo> sortAndFormatPlatRecommend(String status,
                                                                        String BaseURL,
                                                                        List<ShdippBizRecommendationEntity> recommendationList,
                                                                        List<ShdippBizEntInfoDetailEntity> companyDetailEntities) {
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
                if (j < companyDetailEntities.size()) {
                    logger.debug("使用ID为{}的平台数据进行展示,下标为：{}", companyDetailEntities.get(j).getId(), j);

                    midIds[i] = companyDetailEntities.get(j).getId();
                    j++;
                }

            }
        }
        ids = StringUtils.removeNull(midIds);

        //获取展示内容
        for (int i = 0; i < ids.length; i++) {

            resPojo = queryHomePageCompanyRecommendedResources(ids[i], status, ((long) i), BaseURL);
            if (null != resPojo) {
                pojoList.add(resPojo);
            }
        }

        return pojoList;
    }

    @Override
    public RecommendedResourcesResPojo queryHomePageCompanyRecommendedResources(String id, String status, long sort, String baseUrl) {
        List<ShdippBizPlatformDetailEntity> entityList;
        VEntRecInfoDetailEntity entity = vEntRecInfoDetailDao.findById(id);

        //进行关联查询，获取对应的企业信息
        if (null == entity) {
            logger.info("根据ID{}无法获取到对应数据", id);
            return null;
        }
        //进行关联查询，获取对应的平台数据
        entityList = shdippBizPlatformDetailDao.getPlatDetailByUserID(entity.getUserId(), entity.getLan(), status);

        return formatHomPageBean(entity, baseUrl, sort, entityList);
    }

    @Override
    public RecommendedResourcesResPojo formatHomPageBean(VEntRecInfoDetailEntity entity,
                                                         String baseUrl,
                                                         long sort,
                                                         List<ShdippBizPlatformDetailEntity> entityList) {
        RecommendedResourcesResPojo resPojo = new RecommendedResourcesResPojo();
        List<RecommendedResourcesResDetailPojo> detailPojoList;
        RecommendedResourcesResDetailPojo resourceDetailPojo;

        resPojo.setTransversePicUrlPicUrl(fileService.getFileUrl(baseUrl, entity.getImage1(), null));
        resPojo.setPortraitPicUrl(fileService.getFileUrl(baseUrl, entity.getImage2(), null));
        resPojo.setLogoUrl(fileService.getFileUrl(baseUrl, entity.getLogo(), null));
        resPojo.setName(entity.getName());
        resPojo.setJumpLink(entity.getOfficialWebsite());
        resPojo.setId(entity.getId());
        resPojo.setDescribe(StringUtils.getLegalProfile(entity.getProfile(), HomePageConfig.pageAbbreviationLength));
        resPojo.setAbbreviation(StringUtils.getLegalProfile(entity.getProfile(), HomePageConfig.pageAbbreviationLength));
        resPojo.setModuleType(entity.getCategory());

        resPojo.setBusinessType(entity.getCategory());

        resPojo.setSort(sort);
        if (null != entityList) {
            detailPojoList = new ArrayList<>();
            for (ShdippBizPlatformDetailEntity detailEntity : entityList) {
                resourceDetailPojo = new RecommendedResourcesResDetailPojo();
                //平台名称
                resourceDetailPojo.setDisName(detailEntity.getName());
                //平台描述
                resourceDetailPojo.setDisAbbreviation(detailEntity.getProfile());
                //平台网址
                resourceDetailPojo.setDisUrl(detailEntity.getWebsite());
                //平台ID
                resourceDetailPojo.setDisId(detailEntity.getId());
                detailPojoList.add(resourceDetailPojo);
            }
            resPojo.setDisList(detailPojoList);
        }
        resPojo.setUserId(entity.getUserId());

        return resPojo;
    }

    @Override
    public ApiResult entryCompanyInfo(CompanyInfoReqPojo companyInfoReqPojo, OAuthUserInfo oui) {
        ApiResult result = new ApiResult();
        //用户信息
        //测试阶段注销！！！！！！！！！！！！
//        String userId = oui.info.attrs.get("UserloginId");
//        String operator = oui.info.attrs.get("Orgname");
        String userId = "userID_32ysh45387";
        String operator = "operateName";
        String lan = companyInfoReqPojo.getLan();
        String name = companyInfoReqPojo.getName();
        String usci = companyInfoReqPojo.getUsci();
        String officialWebsite = companyInfoReqPojo.getOfficialWebsite();
        String tele = companyInfoReqPojo.getTele();
        String[] categoryArr = companyInfoReqPojo.getCategoryArr();
        String scale = companyInfoReqPojo.getScale();
        String profile = companyInfoReqPojo.getProfile();
        String logo = companyInfoReqPojo.getLogo();
        String image1 = companyInfoReqPojo.getImage1();
        String image2 = companyInfoReqPojo.getImage2();
        String media = companyInfoReqPojo.getMedia();
        StringBuffer category = new StringBuffer();
        for (String cat : categoryArr) {
            category.append(cat + ",");
        }
        ShdippBizEntInfoEntity entInfo = null;
        ShdippBizEntInfoDetailEntity entInfoDetail = null;
        long entInfoID = SnowflakeIdWorkerUtil.nextId();
        long entInfoDetailID = SnowflakeIdWorkerUtil.nextId();
        //需要修改的
        entInfo = shdippBizEntInfoDao.findByUserIdAndLanAndDataDomainAndDelFlag(userId, "CN", "1", "1");
        if (null == entInfo) {
            //数据库新增企业信息数据
            entInfo = new ShdippBizEntInfoEntity();
            entInfo.setId(String.valueOf(entInfoID));
            entInfo.setUserId(userId);
            entInfo.setLan(lan);
            entInfo.setStatus(STATUS_2);
            entInfo.setDelFlag(DEL_FLAG_1);
            entInfo.setDataDomain(DATA_DOMAIN_01);
            entInfo.setRecommendationPrd(null);
            entInfo.setCreateTime(new Date());
            entInfo.setUpdateTime(new Date());
            entInfo.setCreator(operator);
            entInfo.setLastOperator(operator);
        } else {
            //修改原先的企业信息
            entInfo.setLan(lan);
            entInfo.setStatus(STATUS_2);
            entInfo.setUpdateTime(new Date());
            entInfo.setLastOperator(operator);
            //修改信息 则在子表内新增修改数据
            //entInfoDetail = shdippBizEntInfoDetailDao.findByEntIdAndStatus(entInfo.getId(), STATUS_1);
        }
        //无论是首次输入企业信息，还是修改信息，都将新增一条子表信息等待审核
        //在管理员审核后，将原先的信息删除
        //企业信息明细表
        entInfoDetail = new ShdippBizEntInfoDetailEntity();
        entInfoDetail.setId(String.valueOf(entInfoDetailID));
        entInfoDetail.setEntId(String.valueOf(entInfoID));
        entInfoDetail.setCreateTime(new Date());
        entInfoDetail.setCreator(operator);
        entInfoDetail.setName(name);
        entInfoDetail.setUsci(usci);
        entInfoDetail.setOfficialWebsite(officialWebsite);
        entInfoDetail.setTele(tele);
        entInfoDetail.setCategory(category.toString());
        entInfoDetail.setScale(scale);
        entInfoDetail.setProfile(profile);
        entInfoDetail.setLogo(logo);
        entInfoDetail.setImage1(image1);
        entInfoDetail.setImage2(image2);
        entInfoDetail.setMedia(media);
        entInfoDetail.setStatus(STATUS_2);
        entInfoDetail.setUpdateTime(new Date());
        entInfoDetail.setLastOperator(operator);
        try {
            shdippBizEntInfoDao.saveAndFlush(entInfo);
            shdippBizEntInfoDetailDao.saveAndFlush(entInfoDetail);
            result.setFlag(BaseService.FLAG_T);
            result.setReturnCode(BaseService.BR_SUCCESS);
            result.setReturnInfo(BaseService.BR_SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error("unexpect error message is ", e);
            result.setFlag(BaseService.FLAG_F);
            result.setReturnCode(BaseService.BR_OTHER_ERROR);
            result.setReturnInfo(BaseService.BR_OTHER_ERROR_MESSAGE);
        }
        return result;
    }

    @Override
    public ApiResult queryCompanyInfo(String userId, String urlFirst) {
        ApiResult result = new ApiResult();
        CompanyInfoRspPojo companyInfoRspPojo = null;
        ShdippBizEntInfoEntity entInfo = new ShdippBizEntInfoEntity();
        ShdippBizEntInfoDetailEntity entInfoDetail = new ShdippBizEntInfoDetailEntity();
        String urlMedian = FileService.FILE_RESOURCE_PATTEN;
        entInfo = shdippBizEntInfoDao.findByUserIdAndLanAndDataDomainAndDelFlag(userId, "CN", "1", "1");
        if (null == entInfo) {
            logger.info("USER : {} has not entried company info.", userId);
            result.setFlag(BaseService.FLAG_F);
            result.setReturnCode(BaseService.BR_OTHER_ERROR);
            result.setReturnInfo("无该企业信息");
        } else {
            entInfoDetail = shdippBizEntInfoDetailDao.findByEntIdAndStatus(entInfo.getId(), STATUS_1);
            companyInfoRspPojo = new CompanyInfoRspPojo();
            companyInfoRspPojo.setLan(entInfo.getLan());
            companyInfoRspPojo.setName(entInfoDetail.getName());
            companyInfoRspPojo.setUsci(entInfoDetail.getUsci());
            companyInfoRspPojo.setOfficialWebsite(entInfoDetail.getOfficialWebsite());
            companyInfoRspPojo.setTele(entInfoDetail.getTele());
            String[] categoryArr = entInfoDetail.getCategory().split(",");
            companyInfoRspPojo.setCategory(categoryArr);
            companyInfoRspPojo.setScale(entInfoDetail.getScale());
            companyInfoRspPojo.setProfile(entInfoDetail.getProfile());
            //传入文件接口相关图片视频等地址信息
            companyInfoRspPojo.setLogo(fileService.getFileUrl(urlFirst, entInfoDetail.getLogo(), null));
            companyInfoRspPojo.setImage1(fileService.getFileUrl(urlFirst, entInfoDetail.getImage1(), null));
            companyInfoRspPojo.setImage2(fileService.getFileUrl(urlFirst, entInfoDetail.getImage2(), null));
            companyInfoRspPojo.setMedia(fileService.getFileUrl(urlFirst, entInfoDetail.getMedia(), null));
            companyInfoRspPojo.setStatus(entInfoDetail.getStatus());
            result.setFlag(BaseService.FLAG_T);
            result.setReturnCode(BaseService.BR_SUCCESS);
            result.setReturnInfo("企业信息");
            result.setData(companyInfoRspPojo);
        }
        return result;
    }
}
