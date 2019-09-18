package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.ShdippBizEntInfoDetailEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizPlatformDetailEntity;
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
public interface ShdippBizEntInfoDetailDao extends EpRepository<ShdippBizEntInfoDetailEntity, Long> {

    ShdippBizEntInfoDetailEntity findByEntIdAndStatus(String entId, String status);

    @Query(value = "select ptd from ShdippBizEntInfoDetailEntity ptd," +
            " ShdippBizEntInfoEntity pt," +
            "  ShdippBizUserEntity us" +
            " where us.id=?1 " +
            " and pt.lan = ?2" +
            " and us.id= pt.userId " +
            " and pt.id = ptd.entId " +
            " and ptd.status=?3 " +
            " order by ptd.id desc")
    List<ShdippBizEntInfoDetailEntity> getEnDetailByUserID(String id, String lan,String status);

    @Query(value = "select * from (select ptd.* from SHDIPP_BIZ_ENT_INFO_DETAIL ptd, " +
            "SHDIPP_BIZ_ENT_INFO pt " +
            " where   pt.lan = ?1  " +
            " and pt.data_Domain = ?2 " +
            " and ptd.status=?3  " +
            " and ptd.id not in (?4) " +
            " and pt.id = ptd.ent_Id  " +
            " order by ptd.update_Time desc)" +
            "  where  rownum< ?5 " , nativeQuery = true)
    List<ShdippBizEntInfoDetailEntity> queryByLanAndDataDomainAndStatusWithExcloud(String lan,
                                                                                   String dataDmoin,
                                                                                   String status,
                                                                                   List<String> ids,
                                                                                   long rownum);


    @Query(value = "select *(select ptd.* from SHDIPP_BIZ_ENT_INFO_DETAIL ptd, " +
            " SHDIPP_BIZ_ENT_INFO pt " +
            "  where   pt.lan = ?1  " +
            "  and pt.data_Domain = ?2 " +
            "  and ptd.status=?3 " +
            "  and pt.id = ptd.ent_Id  " +
            "  order by ptd.update_Time desc)" +
            "    where  rownum< ?4 " , nativeQuery = true)
    List<ShdippBizEntInfoDetailEntity> queryByLanAndDataDomainAndStatus(String lan,
                                                                                   String dataDmoin,
                                                                                   String status,
                                                                                   long rownum);

    @Query(value = "select ptd from ShdippBizEntInfoDetailEntity ptd where ptd.entId in(?1)")
    List<ShdippBizEntInfoDetailEntity> queryEntInfoByIdList(List<String> prdIdList);

    @Query(value = "select ptd from ShdippBizEntInfoDetailEntity ptd where ptd.id in(?1)")
    List<ShdippBizEntInfoDetailEntity> queryById(List<String> idList);
}
