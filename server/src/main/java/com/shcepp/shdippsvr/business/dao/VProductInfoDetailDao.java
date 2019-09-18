package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.view.VProductInfoDetailEntity;
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
public interface VProductInfoDetailDao extends EpRepository<VProductInfoDetailEntity, Long> {

    @Query(value = "select ptd from VProductInfoDetailEntity ptd" +
            " where ptd.lan = ?1 " +
            " and ptd.dataDomain = ?2" +
            " and ptd.status = ?3 " +
            " and ptd.delFlag = nvl(?4,'1')" +
            " and rownum < ?5" +
            " order by ptd.prdId asc")
    List<VProductInfoDetailEntity> findByLanAndDataDomainAndStatusAndRownum(String lan,
                                                                            String dataDomain,
                                                                            String status,
                                                                            String delFlag,
                                                                            long rownum);

    VProductInfoDetailEntity findById(String id);

}
