package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.view.VEntRecInfoDetailEntity;
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
public interface VEntRecInfoDetailDao extends EpRepository<VEntRecInfoDetailEntity, Long> {

    @Query(value = "select ptd from VEntRecInfoDetailEntity ptd" +
            " where ptd.lan = ?1 " +
            " and ptd.dataDomain = ?2" +
            " and ptd.status = ?3 " +
            " and ptd.delFlag = nvl(?4,'1')" +
            " and rownum < ?5" +
            " order by ptd.id desc")
    List<VEntRecInfoDetailEntity> findByLanAndDataDomainAndStatusAndRownum(String lan, String dataDomain, String status, String delFlag, long rownum);

    VEntRecInfoDetailEntity findById(String ID);

    @Query(value = "select * from (select ptd.*,rownum num from V_ENT_REC_INFO_DETAIL ptd" +
            " where ptd.lan = ?1 " +
            " and ptd.data_Domain = ?2" +
            " and ptd.status = ?3" +
            " and ptd.del_Flag = nvl(?4,'1')" +
            "and category in(?6)"+
            " and rownum <= ?5" +
            " order by ptd.id desc) where num >=?7 ", nativeQuery = true)
    List<VEntRecInfoDetailEntity> findEnterpriseDetail(String lan, String dataDomain,
                                                       String status,
                                                       String delFlag,
                                                       long rownumEnd, List<String> category, long rownumStart);


}
