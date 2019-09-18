package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.ShdippBizRecommendationEntity;
import com.shcepp.shdippsvr.sys.dao.EpRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 查询首页推荐的内容
 *
 * @author BrunE
 * @date 2019-07-18 20:49
 **/
@Repository
@Transactional
public interface ShdippBizRecommendationDao extends EpRepository<ShdippBizRecommendationEntity, Long> {

    /**
     * 首页的首页排序逻辑为生效的排序肯定是从N开始递增
     * @param recommType
     * @param lan
     * @param dataDmoin
     * @param status
     * @param rownum
     * @return
     */
    @Query(value = "SELECT * FROM (select ptd.* from SHDIPP_BIZ_RECOMMENDATION ptd " +
            " where ptd.recomm_Type=?1" +
            " and ptd.lan = ?2" +
            " and ptd.data_Domain = ?3" +
            " and ptd.status= ?4" +
            "  order by ptd.pos_Id asc)   " +
            "  WHERE  rownum<= ?5" , nativeQuery = true )
    List<ShdippBizRecommendationEntity> queryByRecommTypeAndLanAndDataDomainAndStatus(String recommType,
                                                                                      String lan,
                                                                                      String dataDmoin,
                                                                                      String status,
                                                                                      long rownum);
}
