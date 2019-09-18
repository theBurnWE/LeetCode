package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.AppUserEntity;


import java.util.List;

/**
 * @description:
 * @author: zkmao
 * @date: 2018/8/24
 */
public interface OauthBizAppCustomDao {
    /**
     * 查询应用列表
     *
     * @param loginId 登录id
     * @return
     */
    List<AppUserEntity> findAppByLoginId(String loginId);
}
