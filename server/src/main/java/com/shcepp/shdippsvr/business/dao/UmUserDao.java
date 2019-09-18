package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.UmUserEntity;
import com.shcepp.shdippsvr.sys.dao.EpRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by EasonXu on 2018/4/10.
 */
@Repository
@Transactional
public interface UmUserDao extends EpRepository<UmUserEntity, Long>, OauthBizAppCustomDao {
    boolean existsByMail(String mail);
    
    boolean existsByMobile(String mobile);
    
    boolean existsByLoginId(String loginId);
    
    UmUserEntity findByLoginId(String loginId);
    
    UmUserEntity findByMail(String mail);
    
    UmUserEntity findByMobile(String mobile);
    
    @Modifying
    @Query(value = "update um_user set u.login_pass = ?1 where login_id = ?2", nativeQuery = true)
    int updatePassword(String newPwd, String loginId);
}


