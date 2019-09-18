package com.shcepp.shdippsvr.business.dao;

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
public interface ShdippBizPlatformInfoDao extends EpRepository<ShdippBizPlatformInfoEntity, Long> {
    @Query(value = "select name from SHDIPP_BIZ_PLATFORM_INFO t1,SHDIPP_BIZ_PLATFORM_DETAIL t2 where t1.ID=t2.PLATFORM_ID " +
            "and t1.USER_ID=?1 " +
            "and t1.STATUS=?2 " +
            "and t1.DEL_FLAG=nvl(?3,'1')"  , nativeQuery = true)
    /**获取平台名称*/
    String findPlatformName(String userId,String status,String delFlag);

    List<ShdippBizPlatformInfoEntity> findByUserIdAndLanAndDataDomainAndDelFlag(String userId,String lan,String dataDomain,String delFlag);


    @Query(value = "SELECT * FROM (SELECT P.ID" +
            " FROM SHDIPP_BIZ_PLATFORM_INFO p, SHDIPP_BIZ_PLATFORM_DETAIL d" +
            " WHERE P.ID = D.PLATFORM_ID" +
            " AND P.DEL_FLAG = '1'" +
            " AND P.DATA_DOMAIN = ?1" +
            " AND D.CATEGORY LIKE %?4%" +
            " AND P.LAN = ?2" +
            " AND D.STATUS = '1'" +
            " ORDER BY p.update_time DESC) WHERE ROWNUM <= ?3"  , nativeQuery = true)
    List<String> queryNewlyPlatformIdByCategory(String dataDomain, String lan, int top, String category);

    @Query(value = "select * from SHDIPP_BIZ_PLATFORM_INFO t1 where t1.ID=?1"  , nativeQuery = true)
    ShdippBizPlatformInfoEntity findById(String id);


}
