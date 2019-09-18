package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.view.VPlatformInfoDetailEntity;
import com.shcepp.shdippsvr.sys.dao.EpRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 首页推荐内容的企业信息的内容
 *
 * @author BrunE
 * @date 2019-07-18 20:49
 **/
@Repository
public interface VPlatformInfoDetailDao extends EpRepository<VPlatformInfoDetailEntity, Long> {

    @Query(value = "select ptd from VPlatformInfoDetailEntity ptd" +
            " where ptd.lan = ?1 " +
            " and ptd.dataDomain = ?2" +
            " and ptd.status = ?3" +
            " and ptd.delFlag = nvl(?4,'1')" +
            " and rownum < ?5" +
            " order by ptd.id desc")
    List<VPlatformInfoDetailEntity> findByLanAndDataDomainAndStatusAndRownum(String lan,
                                                                             String dataDomain,
                                                                             String status,
                                                                             String delFlag,
                                                                             long rownum);

    VPlatformInfoDetailEntity findById(String ID);


//    @Query(value = "select * from (select ptd.*,rownum num from V_PLATFORM_INFO_DETAIL ptd" +
//            " where ptd.lan = ?1 " +
//            " and ptd.data_Domain = ?2" +
//            " and ptd.status = ?3" +
//            " and ptd.del_Flag = nvl(?4,'1')" +
//            "and category in(?6)"+
//            " and rownum <= ?5" +
//            " order by ptd.id desc) where num >=?7 ", nativeQuery = true)
//    List<VPlatformInfoDetailEntity> findPlatformDetail(String lan, String dataDomain,
//                                                                             String status,
//                                                                             String delFlag,
//                                                                             long rownumEnd,List<String> category,long rownumStart);

    @Query(value = "select * from V_PLATFORM_INFO_DETAIL ptd" +
            " where ptd.id in(?1) " +
            " order by ptd.id desc ", nativeQuery = true)
    List<VPlatformInfoDetailEntity> findPlatformDetail(List<String> id);


    @Query(value = "select ptd.* from V_PLATFORM_INFO_DETAIL ptd" +
            " where ptd.lan = ?1 " +
            " and ptd.data_Domain = ?2" +
            " and ptd.status = ?3" +
            " and ptd.del_Flag = nvl(?4,'1')" +
            "and category =?6"+
            " and rownum <= ?5" +
            " order by ptd.bizre_POS_ID asc ", nativeQuery = true)
    List<VPlatformInfoDetailEntity> findPlatform(String lan, String dataDomain,
                                                       String status,
                                                       String delFlag,
                                                       long rownumEnd,List<String> category,long rownumStart);
}
