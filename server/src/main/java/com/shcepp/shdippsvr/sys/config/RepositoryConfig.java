package com.shcepp.shdippsvr.sys.config;

import com.shcepp.shdippsvr.sys.dao.EpRepositoryFactoryBean;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        repositoryFactoryBeanClass = EpRepositoryFactoryBean.class,//指定自己的工厂类
        basePackages = {"com.shcepp.shdippsvr.*.dao"})//设置dao（repo）所在位置
public class RepositoryConfig {
    private static final Logger logger = LoggerFactory.getLogger(RepositoryConfig.class);

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean(name = "entityManager")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean (name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .properties(getVendorProperties(dataSource))
                .packages("com.shcepp.shdippsvr.*.entity") //设置实体类所在位置
                .persistenceUnit("dataUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManager")
    PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }
}
