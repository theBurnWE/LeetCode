package com.shcepp.shdippsvr.business.dao.impl;

import com.shcepp.shdippsvr.business.dao.ShdippBizProductCustomDao;
import com.shcepp.shdippsvr.business.entity.ProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author zkmao
 * @description
 * @date 2019/9/5 19:31
 */
public class ShdippBizProductDaoImpl implements ShdippBizProductCustomDao{
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public int countProductByCategory(String dataDomain, String lan, String category) {
        Query query = entityManager.createNativeQuery( "SELECT count(*) as count" +
                " FROM SHDIPP_BIZ_PRODUCT p, SHDIPP_BIZ_PRODUCT_DETAIL d" +
                " WHERE P.ID = D.PRD_ID" +
                " AND P.DEL_FLAG = '1'" +
                " AND P.DATA_DOMAIN = ?1" +
                " AND D.CATEGORY LIKE ?2" +
                " AND P.LAN = ?3" +
                " AND D.STATUS = '1'");
        query.setParameter(1, dataDomain);
        query.setParameter(2, "%" + category + "%");
        query.setParameter(3, lan);
        BigDecimal bd = (BigDecimal)query.getSingleResult();
        return bd.intValue();
    }

    @Override
    public List<String> queryNewlyProductIdByCategory(String dataDomain, String lan, int top, String category) {
        Query query = entityManager.createNativeQuery( "SELECT P.ID" +
                " FROM SHDIPP_BIZ_PRODUCT p, SHDIPP_BIZ_PRODUCT_DETAIL d" +
                " WHERE P.ID = D.PRD_ID" +
                " AND P.DEL_FLAG = '1'" +
                " AND P.DATA_DOMAIN = ?1" +
                " AND D.CATEGORY LIKE ?2" +
                " AND P.LAN = ?3" +
                " AND D.STATUS = '1'" +
                " AND ROWNUM <= ?4" +
                " ORDER BY p.update_time DESC");
        query.setParameter(1, dataDomain);
        query.setParameter(2, "%" + category + "%");
        query.setParameter(3, lan);
        query.setParameter(4, top);

        List<String> list = query.getResultList();
        return list;
    }

    @Override
    public List<ProductEntity> queryProductByIdList(List<String> prdIdList) {
//        String ids = "";
//        for(String str : prdIdList){
//            ids += str + ",";
//        }
//        ids = ids.substring(0,ids.length() - 1);
        Query query = entityManager.createNativeQuery("SELECT P.ID," +
                "D.PRODUCT_NAME," +
                "ED.NAME AS COMPANY_NAME," +
                "ED.OFFICIAL_WEBSITE," +
                "D.SUMMARY," +
                "D.IMAGE1," +
                "L.LIKE_COUNT," +
                "D.UPDATE_TIME" +
                "  FROM SHDIPP_BIZ_PRODUCT p," +
                " SHDIPP_BIZ_PRODUCT_DETAIL d," +
                " SHDIPP_BIZ_ENT_INFO e," +
                " SHDIPP_BIZ_ENT_INFO_DETAIL ed," +
                " SHDIPP_BIZ_PRD_LIKE l," +
                " SHDIPP_BIZ_USER u" +
                " WHERE P.ID = D.PRD_ID" +
                " AND P.USER_ID = E.USER_ID" +
                " AND p.user_id = u.Id" +
                " AND e.user_id = u.id" +
                " AND P.DEL_FLAG = '1'" +
                " AND P.DATA_DOMAIN = '01'" +
//                " AND P.LAN = 'CN'" +
                " AND D.STATUS = '1'" +
                " AND E.ID = ED.ENT_ID" +
                " AND ED.STATUS = '1'" +
                " AND E.DATA_DOMAIN = P.DATA_DOMAIN" +
                " AND E.LAN = P.LAN" +
                " AND E.DEL_FLAG = '1'" +
                " AND P.ID = L.PRD_ID(+)" +
                " AND P.ID IN (?1)",ProductEntity.class);
        query.setParameter(1, prdIdList);
        List<ProductEntity> list = query.getResultList();
        return list;
    }

    @Override
    public List<ProductEntity> queryRankByLikes(String dataDomain, String lan, int top) {
        Query query = entityManager.createNativeQuery( "SELECT P.ID," +
                "D.PRODUCT_NAME," +
                "ED.NAME AS COMPANY_NAME," +
                "ED.OFFICIAL_WEBSITE," +
                "D.SUMMARY," +
                "D.IMAGE1," +
                "L.LIKE_COUNT," +
                "D.UPDATE_TIME" +
                " FROM SHDIPP_BIZ_PRODUCT p," +
                " SHDIPP_BIZ_PRODUCT_DETAIL d," +
                " SHDIPP_BIZ_ENT_INFO e," +
                " SHDIPP_BIZ_ENT_INFO_DETAIL ed," +
                " SHDIPP_BIZ_PRD_LIKE l," +
                " SHDIPP_BIZ_USER u" +
                " WHERE P.ID = D.PRD_ID" +
                " AND P.USER_ID = E.USER_ID" +
                " AND p.user_id = u.Id" +
                " AND e.user_id = u.id" +
                " AND P.DEL_FLAG = '1'" +
                " AND P.DATA_DOMAIN = ?1" +
                " AND P.LAN = ?2" +
                " AND E.ID = ED.ENT_ID" +
                " AND ED.STATUS = '1'" +
                " AND E.DATA_DOMAIN = P.DATA_DOMAIN" +
                " AND E.LAN = P.LAN" +
                " AND E.DEL_FLAG = '1'" +
                " AND P.ID = L.PRD_ID(+)" +
                " AND ROWNUM <= ?3" +
                " ORDER BY L.LIKE_COUNT DESC", ProductEntity.class);
        query.setParameter(1, dataDomain);
        query.setParameter(2, lan);
        query.setParameter(3, top);
        List<ProductEntity> list = query.getResultList();
        return list;
    }

    @Override
    public List<ProductEntity> queryRankByFavs(String dataDomain, String lan, int top) {
        Query query = entityManager.createNativeQuery("SELECT P.ID," +
                "D.PRODUCT_NAME," +
                "ED.NAME AS COMPANY_NAME," +
                "ED.OFFICIAL_WEBSITE," +
                "D.SUMMARY," +
                "D.IMAGE1," +
                "L.LIKE_COUNT," +
                "D.UPDATE_TIME" +
//            "         F1.Fav_COUNT" +
                " FROM SHDIPP_BIZ_PRODUCT p," +
                "SHDIPP_BIZ_PRODUCT_DETAIL d," +
                "SHDIPP_BIZ_ENT_INFO e," +
                "SHDIPP_BIZ_ENT_INFO_DETAIL ed," +
                "SHDIPP_BIZ_PRD_LIKE l," +
                "SHDIPP_BIZ_USER u," +
                "(SELECT COUNT (*) AS FAV_COUNT, F.FAVORITE_ITEM_ID, F.ITEM_TYPE" +
                " FROM SHDIPP_BIZ_USER_FAVORITE f" +
                " GROUP BY F.FAVORITE_ITEM_ID, F.ITEM_TYPE" +
                " HAVING item_type = '3') F1" +
                " WHERE P.ID = D.PRD_ID" +
                " AND P.USER_ID = E.USER_ID" +
                " AND p.user_id = u.Id" +
                " AND e.user_id = u.id" +
                " AND P.DEL_FLAG = '1'" +
                " AND P.DATA_DOMAIN = ?1" +
                " AND P.LAN = ?2" +
                " AND D.STATUS = '1'" +
                " AND E.ID = ED.ENT_ID" +
                " AND ED.STATUS = '1'" +
                " AND E.DATA_DOMAIN = P.DATA_DOMAIN" +
                " AND E.LAN = P.LAN" +
                " AND E.DEL_FLAG = '1'" +
                " AND P.ID = L.PRD_ID(+)" +
                " AND F1.FAVORITE_ITEM_ID = P.ID" +
                " AND ROWNUM <= ?3" +
                " ORDER BY FAV_COUNT DESC", ProductEntity.class);
        query.setParameter(1, dataDomain);
        query.setParameter(2, lan);
        query.setParameter(3, top);
        List<ProductEntity> list = query.getResultList();
        return list;
    }

    @Override
    public List<ProductEntity> queryRankByRecommendation(String dataDomain, String lan, int top) {
        Query query = entityManager.createNativeQuery("SELECT P.ID," +
                "D.PRODUCT_NAME," +
                "ED.NAME AS COMPANY_NAME," +
                "ED.OFFICIAL_WEBSITE," +
                "D.SUMMARY," +
                "D.IMAGE1," +
                "L.LIKE_COUNT," +
                "D.UPDATE_TIME" +
                " FROM SHDIPP_BIZ_PRODUCT P," +
                "SHDIPP_BIZ_PRODUCT_DETAIL d," +
                "SHDIPP_BIZ_ENT_INFO E," +
                "SHDIPP_BIZ_ENT_INFO_DETAIL ed," +
                "SHDIPP_BIZ_PRD_LIKE l," +
                "SHDIPP_BIZ_RECOMMENDATION R," +
                "SHDIPP_BIZ_USER u" +
                " WHERE P.ID = D.PRD_ID" +
                " AND P.USER_ID = E.USER_ID" +
                " AND p.user_id = u.ID" +
                " AND e.user_id = u.ID" +
                " AND P.DEL_FLAG = '1'" +
                " AND P.DATA_DOMAIN = ?1" +
                " AND P.LAN = ?2" +
                " AND E.ID = ED.ENT_ID" +
                " AND ED.STATUS = '1'" +
                " AND E.DATA_DOMAIN = P.DATA_DOMAIN" +
                " AND E.LAN = P.LAN" +
                " AND E.DEL_FLAG = '1'" +
                " AND P.ID = L.PRD_ID(+)" +
                " AND R.ITEM_CATEGORY = '03'" +
                " AND R.RECOMM_TYPE = 'P0'" +
                " AND R.RECOMM_ID = p.ID" +
                " AND R.STATUS = '1'" +
                " AND ROWNUM <= ?3" +
                " ORDER BY R.POS_ID", ProductEntity.class);
        query.setParameter(1, dataDomain);
        query.setParameter(2, lan);
        query.setParameter(3, top);
        List<ProductEntity> list = query.getResultList();
        return list;
    }

    @Override
    public List<ProductEntity> queryRankByNewly(String dataDomain, String lan, int top) {
            Query query = entityManager.createNativeQuery("SELECT P.ID," +
                    "D.PRODUCT_NAME," +
                    "ED.NAME AS COMPANY_NAME," +
                    "ED.OFFICIAL_WEBSITE," +
                    "D.SUMMARY," +
                    "D.IMAGE1," +
                    "L.LIKE_COUNT," +
                    "D.UPDATE_TIME" +
                    " FROM SHDIPP_BIZ_PRODUCT P," +
                    " SHDIPP_BIZ_PRODUCT_DETAIL d," +
                    " SHDIPP_BIZ_ENT_INFO E," +
                    " SHDIPP_BIZ_ENT_INFO_DETAIL ed," +
                    " SHDIPP_BIZ_PRD_LIKE l," +
                    " SHDIPP_BIZ_USER u" +
                    " WHERE P.ID = D.PRD_ID" +
                    " AND P.USER_ID = E.USER_ID" +
                    " AND p.user_id = u.ID" +
                    " AND e.user_id = u.ID" +
                    " AND P.DEL_FLAG = '1'" +
                    " AND P.DATA_DOMAIN = ?1" +
                    " AND P.LAN = ?2" +
                    " AND E.ID = ED.ENT_ID" +
                    " AND ED.STATUS = '1'" +
                    " AND E.DATA_DOMAIN = P.DATA_DOMAIN" +
                    " AND E.LAN = P.LAN" +
                    " AND E.DEL_FLAG = '1'" +
                    " AND P.ID = L.PRD_ID(+)" +
                    " AND ROWNUM <= ?3" +
                    " ORDER BY P.UPDATE_TIME DESC", ProductEntity.class);
            query.setParameter(1, dataDomain);
            query.setParameter(2, lan);
            query.setParameter(3, top);
            List<ProductEntity> list = query.getResultList();
            return list;
    }
}
