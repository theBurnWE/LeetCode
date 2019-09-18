package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.ShdippBizEntInfoDetailEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizPlatformDetailEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizPlatformInfoEntity;
import com.shcepp.shdippsvr.sys.dao.EpRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author BrunE
 * @date 2019-07-18 20:49
 **/
@Repository
@Transactional
public interface ShdippBizPlatformDetailDao extends EpRepository<ShdippBizPlatformDetailEntity, Long> {

    @Query(value = "select ptd from ShdippBizPlatformDetailEntity ptd," +
            " ShdippBizPlatformInfoEntity pt," +
            "  ShdippBizUserEntity us" +
            " where us.id=?1" +
            " and pt.lan = ?2 " +
            " and us.id= pt.userId " +
            " and pt.id = ptd.platformId " +
            " and ptd.status=?3 " +
            " order by ptd.id desc")
    List<ShdippBizPlatformDetailEntity> getPlatDetailByUserID(String id,String lan, String status);

    @Query(value = "select * from (select ptd.* from SHDIPP_BIZ_PLATFORM_DETAIL ptd, " +
            "  SHDIPP_BIZ_PLATFORM_INFO pt " +
            " where pt.lan = ?1" +
            " and pt.data_Domain = ?2" +
            " and ptd.STATUS=?3  " +
            " and pt.id = ptd.PLATFORM_ID  " +
            " order by ptd.update_Time desc)" +
            " where  rownum< ?4 " , nativeQuery = true)
    List<ShdippBizPlatformDetailEntity> queryByLanAndDataDomainAndStatus(String lan,
                                                                         String dataDmoin,
                                                                         String status,
                                                                         long rownum);

    @Query(value = "select * from (select ptd.* from SHDIPP_BIZ_PLATFORM_DETAIL ptd, " +
            " SHDIPP_BIZ_PLATFORM_INFO pt " +
            " where  pt.lan = ?1  " +
            " and pt.DATA_DOMAIN = ?2 " +
            " and ptd.STATUS=?3  " +
            " and ptd.id not in (?4) " +
            " and pt.id = ptd.PLATFORM_ID  " +
            " order by ptd.UPDATE_TIME desc)" +
            " where  rownum< ?5 "  , nativeQuery = true)
    List<ShdippBizPlatformDetailEntity> queryByLanAndDataDomainAndStatusWithExcloud(String lan,
                                                                         String dataDmoin,
                                                                         String status,
                                                                         List<String> ids,
                                                                         long rownum);

    List<ShdippBizPlatformDetailEntity> findByPlatformIdAndDataDomainAndStatusAndDelFlag(String platformId,String dataDomain,String status,String delFlag);


    @Query(value = "select ptd from ShdippBizPlatformDetailEntity ptd where ptd.platformId in(?1)")
    List<ShdippBizPlatformDetailEntity> queryPlatformByIdList(List<String> prdIdList);

    @Query(value = "select ptd from ShdippBizPlatformDetailEntity ptd where ptd.id in(?1)")
    List<ShdippBizPlatformDetailEntity> queryById(List<String> idList);
}
