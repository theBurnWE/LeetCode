package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.ShdippBizProductDetailEntity;
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
public interface ShdippBizProductDetailDao extends EpRepository<ShdippBizProductDetailEntity, Long> {

    ShdippBizProductDetailEntity findByPrdIdAndStatus(String prdId, String status);

    @Query(value = "select * from(select ptd.*  from SHDIPP_BIZ_PRODUCT_DETAIL ptd, " +
            " SHDIPP_BIZ_PRODUCT pt " +
            " where   pt.lan = ?1  " +
            " and pt.data_Domain = ?2" +
            " and ptd.status=?3  " +
            " and ptd.id not in (?4) " +
            " and pt.id = ptd.prd_Id  " +
            " order by ptd.update_Time desc)" +
            " where   rownum< ?5 " , nativeQuery = true)
    List<ShdippBizProductDetailEntity> queryByLanAndDataDomainAndStatusWithExcloud(String lan,
                                                                                   String dataDmoin,
                                                                                   String status,
                                                                                   List<String> ids,
                                                                                   long rownum);

    @Query(value = "select * from (select ptd.* from SHDIPP_BIZ_PRODUCT_DETAIL ptd, " +
            " SHDIPP_BIZ_PRODUCT pt " +
            " where   pt.lan = ?1  " +
            " and pt.data_Domain = ?2 " +
            " and ptd.status=?3  " +
            " and pt.id = ptd.prd_Id  " +
            " order by ptd.update_Time desc)" +
            " where  rownum< ?4 " , nativeQuery = true)
    List<ShdippBizProductDetailEntity> queryByLanAndDataDomainAndStatus(String lan,
                                                                        String dataDmoin,
                                                                        String status,
                                                                        long rownum);

    @Query(value = "select ptd from ShdippBizProductDetailEntity ptd," +
            " ShdippBizProductEntity pt," +
            "  ShdippBizUserEntity us" +
            " where us.id=?1" +
            " and pt.lan = ?2 " +
            " and us.id= pt.userId " +
            " and pt.id = ptd.prdId " +
            " and ptd.status=?3 " +
            " order by ptd.id desc")
    List<ShdippBizProductDetailEntity> queryProductDetailByUserID(String id,String lan, String status);

    @Query(value = "select prd from ShdippBizProductDetailEntity prd," +
            " ShdippBizProductEntity pt" +
            " where pt.id = prd.prdId " +
            " and pt.lan = ?1 " +
            " and pt.dataDomain = ?2" +
            " and prd.status=?3 " +
            " and pt.id in (?4)" +
            " order by prd.updateTime desc")
    List<ShdippBizProductDetailEntity> queryByLanAndDataDomainAndStatus(String lan, String dataDmoin, String status, List<String> idList);

}
