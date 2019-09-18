package com.shcepp.shdippsvr.business.dao.impl;

import com.shcepp.shdippsvr.business.dao.OauthBizAppCustomDao;
import com.shcepp.shdippsvr.business.entity.AppUserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @description:
 * @author: zkmao
 * @date: 2018/8/24
 */
public class OauthBizAppDaoImpl implements OauthBizAppCustomDao {
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<AppUserEntity> findAppByLoginId(String loginId) {
        Query query = entityManager.createNativeQuery("SELECT UA.ID,UA.LOGIN_ID,A.APP_CODE,A.APP_NAME,A.DOMAIN,A.WEB_URL,A.ICON_URL,A.ORDERNO,A.APPTYPE  FROM OAUTH_BIZ_APP_USER UA ,OAUTH_BIZ_APP A WHERE A.APP_CODE = UA.APP_CODE AND UA.LOGIN_ID = ?", AppUserEntity.class);
        query.setParameter(1, loginId);
        List<AppUserEntity> list = query.getResultList();
        return list;
    }
}
