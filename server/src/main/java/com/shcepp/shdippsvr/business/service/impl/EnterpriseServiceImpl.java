package com.shcepp.shdippsvr.business.service.impl;

import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticSearchQueryResultBean;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ElasticsearchPagination;
import com.shcepp.shdippsvr.business.bean.elasticsearch.ShdippElasticSearchBean;
import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseCategoryPojo;
import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseDetailReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseDetailResPojo;
import com.shcepp.shdippsvr.business.dao.*;
import com.shcepp.shdippsvr.business.entity.ShdippBizEntInfoDetailEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizEntInfoEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizRecommendationEntity;
import com.shcepp.shdippsvr.business.entity.view.VPlatformInfoDetailEntity;
import com.shcepp.shdippsvr.business.enums.*;
import com.shcepp.shdippsvr.business.service.EnterpriseService;
import com.shcepp.shdippsvr.business.service.FileService;
import com.shcepp.shdippsvr.business.service.elasticsearch.ElasticsearchService;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Creatd by gxd on 2019/9/3.
 */
@Service
@Transactional
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    private VEntRecInfoDetailDao vEntRecInfoDetailDao;
    @Autowired
    private FileService fileService;
    @Autowired
    private ShdippBizPlatformInfoDao shdippBizPlatformInfoDao;
    @Autowired
    private ShdippBizRecommendationDao shdippBizRecommendationDao;
    @Autowired
    private ShdippBizEntInfoDao shdippBizEntInfoDao;
    @Autowired
    private ShdippBizEntInfoDetailDao shdippBizEntInfoDetailDao;
    @Autowired
    ElasticsearchService elasticsearchService;
    //企业推荐位
    Map<String, ModuleType> enterpriseRecommendations = new HashMap<String, ModuleType>() {

        private static final long serialVersionUID = 3924912787998678653L;

        {
            put("01", ModuleType.ET_E1);
            put("02", ModuleType.ET_E2);
            put("03", ModuleType.ET_E3);
            put("04", ModuleType.ET_E4);
            put("05", ModuleType.ET_E5);
            put("06", ModuleType.ET_E6);
        }
    };

    @Override
    public List<PlatformEnterpriseDetailResPojo> queryEnterpriseDetail(PlatformEnterpriseDetailReqPojo platformEnterpriseDetailReqPojo) {
        String baseUrl​ = platformEnterpriseDetailReqPojo.getBaseUrl();
        List<PlatformEnterpriseDetailResPojo> platformEnterpriseDetailResPojoList = new ArrayList<>();
        //拼接查询条件
        ShdippElasticSearchBean.Builder builder = ShdippElasticSearchBean.Builder();
        ShdippElasticSearchBean bean = new ShdippElasticSearchBean();
        List<BaseEnums> list = new ArrayList<>();
        List<BaseEnums> fuzzylist = new ArrayList<>();
        //增加查询条件
        //类别，根据企业、产品、平台，有所不同
        String[] category=platformEnterpriseDetailReqPojo.getCategory().split(",");
        for(int i=0;i<category.length;i++){
            fuzzylist.add(EnterpriseCategoryType.getEnumsByCode(category[i]));
        }
        builder.fuzzyAttrList(fuzzylist);
        //语言
        list.add(LanType.getEnumsByCode(platformEnterpriseDetailReqPojo.getLan()));
        //站点
        list.add(DomainType.getEnumsByCode(platformEnterpriseDetailReqPojo.getDataDomain()));
        //状态
        //list.add(StatusType.getEnumsByCode(VPlatformInfoDetailEntity.STATUS_TRUE));
        builder.attrList(list);

        //默认查询未删除数据
        builder.isDelete(false);

        //关键字
        if (!StringUtils.isEmpty(platformEnterpriseDetailReqPojo.getKeyWord())) {
            builder.queryKey(platformEnterpriseDetailReqPojo.getKeyWord());
        }

        bean = builder.build();
        ElasticsearchPagination elasticsearchPagination = new ElasticsearchPagination();
        int currentPage = (int) platformEnterpriseDetailReqPojo.getMaxRowCounts() / 1;
        elasticsearchPagination.setCurrentPage(currentPage);
        elasticsearchPagination.setPageSize(1);
        //es查询
        ElasticsearchPagination result = elasticsearchService.queryEsByBean(bean, elasticsearchPagination);
        //获得查询列表的ID List
        List<String> finalList = null;
        //判断数据是否为空
        if (result.getContentList() != null) {
            finalList = new ArrayList<>();
            for (int idx = 0; idx < result.getContentList().size(); idx++) {
                finalList.add(((ElasticSearchQueryResultBean) result.getContentList().get(idx)).getId());
            }
            //List<VEntRecInfoDetailEntity> vPlatformInfoDetailList = vEntRecInfoDetailDao.findEnterpriseDetail(lan, dataDomain, VPlatformInfoDetailEntity.STATUS_TRUE, "", rownumEnd, category, rownumStart);
            List<ShdippBizEntInfoDetailEntity> ShdippBizEntInfoDetailList=shdippBizEntInfoDetailDao.queryById(finalList);
            /**组装数据返回给前端*/
            if (ShdippBizEntInfoDetailList.size() > 0) {
                for (ShdippBizEntInfoDetailEntity shdippBizEntInfoDetailEntity : ShdippBizEntInfoDetailList) {
                    PlatformEnterpriseDetailResPojo platformEnterpriseDetailResPojo = new PlatformEnterpriseDetailResPojo();
                    //h获取userId
                    ShdippBizEntInfoEntity shdippBizEntInfoEntity=shdippBizEntInfoDao.findById(shdippBizEntInfoDetailEntity.getEntId());
                    String userId=shdippBizEntInfoEntity.getUserId();
                    /**根据userId获取平台名称*/
                    String platformName = shdippBizPlatformInfoDao.findPlatformName(userId, VPlatformInfoDetailEntity.STATUS_TRUE, "");
                    platformEnterpriseDetailResPojo.setName(platformName);
                    platformEnterpriseDetailResPojo.setCategory(shdippBizEntInfoDetailEntity.getCategory());
                    platformEnterpriseDetailResPojo.setBusinessName(shdippBizEntInfoDetailEntity.getName());
                    platformEnterpriseDetailResPojo.setProfile(shdippBizEntInfoDetailEntity.getProfile());
                    platformEnterpriseDetailResPojo.setWebsite(shdippBizEntInfoDetailEntity.getOfficialWebsite());
                    /**获取图片及logo地址*/
                    platformEnterpriseDetailResPojo.setLogo(fileService.getFileUrl(baseUrl​, shdippBizEntInfoDetailEntity.getLogo(),FileService.PIC_FILE_URL_PATTEN_O));
                    platformEnterpriseDetailResPojo.setImage1(fileService.getFileUrl(baseUrl​, shdippBizEntInfoDetailEntity.getImage1(),FileService.PIC_FILE_URL_PATTEN_O));
                    if(ShdippBizEntInfoDetailList.size() <1){
                        //隐藏查看更多
                        platformEnterpriseDetailResPojo.setTotalCounts(0);
                    }else{
                        platformEnterpriseDetailResPojo.setTotalCounts(ShdippBizEntInfoDetailList.size()+platformEnterpriseDetailReqPojo.getMaxRowCounts());
                    }
                    platformEnterpriseDetailResPojoList.add(platformEnterpriseDetailResPojo);
                }
            } else {
                PlatformEnterpriseDetailResPojo platformEnterpriseDetailResPojo = new PlatformEnterpriseDetailResPojo();
                /**隐藏页面查看更多*/
                platformEnterpriseDetailResPojo.setTotalCounts(0);
                platformEnterpriseDetailResPojoList.add(platformEnterpriseDetailResPojo);
            }
            return platformEnterpriseDetailResPojoList;
        }else{
            PlatformEnterpriseDetailResPojo platformEnterpriseDetailResPojo =new PlatformEnterpriseDetailResPojo();
            /**隐藏页面查看更多*/
            platformEnterpriseDetailResPojo.setTotalCounts(0);
            platformEnterpriseDetailResPojoList.add(platformEnterpriseDetailResPojo);
            return platformEnterpriseDetailResPojoList;
        }
    }
    @Override
    public PlatformEnterpriseCategoryPojo queryEnterprise(PlatformEnterpriseDetailReqPojo platformEnterpriseDetailReqPojo) {
        List<String> finalList = null;
        List<ShdippBizEntInfoDetailEntity> resultList = null;
        PlatformEnterpriseCategoryPojo platformEnterpriseCategoryPojo =new PlatformEnterpriseCategoryPojo();
        platformEnterpriseCategoryPojo.list=new ArrayList<>();
        platformEnterpriseCategoryPojo.category = platformEnterpriseDetailReqPojo.getCategory();
        //查询推荐位
        List<ShdippBizRecommendationEntity> prdRecommList = new ArrayList<>();

        prdRecommList = shdippBizRecommendationDao.queryByRecommTypeAndLanAndDataDomainAndStatus(
                getCategoryRecommType(platformEnterpriseDetailReqPojo.getCategory()),
                platformEnterpriseDetailReqPojo.getLan(),
                platformEnterpriseDetailReqPojo.getDataDomain(),
                "1",
                7);

        //查询最新的7条
        List<String> prdIdList = shdippBizEntInfoDao.queryNewlyEntInfoIdByCategory(platformEnterpriseDetailReqPojo.getDataDomain(), platformEnterpriseDetailReqPojo.getLan(), 7, platformEnterpriseDetailReqPojo.getCategory());

        //处理推荐位
        finalList = BuildCategoryIdList(prdRecommList,prdIdList);
 if(finalList != null && finalList.size() > 0){
        resultList = shdippBizEntInfoDetailDao.queryEntInfoByIdList(finalList);
        int order = 0;
        /**组装返回数据*/
            for (String prdId : finalList) {
                for (ShdippBizEntInfoDetailEntity item : resultList) {
                    if (prdId.equals(item.getEntId())) {
                        PlatformEnterpriseDetailResPojo platformEnterpriseDetailResPojo = new PlatformEnterpriseDetailResPojo();
                        platformEnterpriseDetailResPojo.setName(item.getName());
                        platformEnterpriseDetailResPojo.setImage1(fileService.getFileUrl(platformEnterpriseDetailReqPojo.getBaseUrl(),item.getImage1(),FileService.PIC_FILE_URL_PATTEN_O));
                        platformEnterpriseDetailResPojo.setImage2(fileService.getFileUrl(platformEnterpriseDetailReqPojo.getBaseUrl(),item.getImage2(),FileService.PIC_FILE_URL_PATTEN_O));
                        platformEnterpriseDetailResPojo.setProfile(item.getProfile());
                        platformEnterpriseDetailResPojo.setWebsite(item.getOfficialWebsite());
                        platformEnterpriseDetailResPojo.setOrder(order++);
                        platformEnterpriseDetailResPojo.setCategory(item.getCategory());
                        platformEnterpriseCategoryPojo.list.add(platformEnterpriseDetailResPojo);
                        break;
                    }
                }
            }
        return platformEnterpriseCategoryPojo;
 }else{
     return platformEnterpriseCategoryPojo;
 }
    }

    /**
     * 处理推荐位
     * @param prdRecommList
     * @param prdIdList
     * @return
     */
    private List<String> BuildCategoryIdList(List<ShdippBizRecommendationEntity> prdRecommList, List<String> prdIdList) {

        int maxPos = 7;

        for(ShdippBizRecommendationEntity item : prdRecommList){
            if (prdIdList.contains(item.getRecommId())){
                prdIdList.remove(prdIdList.indexOf(item.getRecommId()));
            }
            if (item.getPosId() <= prdIdList.size()) {
                prdIdList.add((int)item.getPosId() - 1,item.getRecommId());
            }
            else {
                prdIdList.add(item.getRecommId());
            }
        }


        return prdIdList;
    }
    private String getCategoryRecommType(String category) {
        return enterpriseRecommendations.get(category).getCode();
    }
}
