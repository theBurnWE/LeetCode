package com.shcepp.shdippsvr.sys.config;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);
    @Value("${dbClient.password}")
    private String password;

    @Value("${dbClient.publicKey:shcepp}")
    private String publicKey;

    @Value("${dbClient.decrypt:false}")
    private boolean decrypt;

    @Bean(name = "dataSource")
    @Primary
    @Qualifier("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db")
    public DataSource dataSource() throws Exception {
        logger.info("-------------------- dataSource初始化 ---------------------");
        // return new DruidDataSource();
        return createDataSource(publicKey, password);
    }

    private DruidDataSource createDataSource(String publicKey, String password) throws Exception {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        if (decrypt) {
            dataSource.setPassword(ConfigTools.decrypt(publicKey, password));
        } else {
            dataSource.setPassword(password);
        }
        return dataSource;
    }
}
