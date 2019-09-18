package com.shcepp.shdippsvr.sys.config;

import com.shcepp.shdippsvr.business.service.FileService;
import com.shcepp.shdippsvr.sys.filer.UrlRewriteFilter;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器的注入类
 *
 * @author BrunE
 * @date 2018-04-02 20:43
 **/
@Configuration
public class ResourceConfig extends WebMvcConfigurerAdapter {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ResourceConfig.class);

    @Bean   //把我们的拦截器注入为bean
    public FilterRegistrationBean getMyInterceptor() {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setFilter(getResourceBean());
        registration.addUrlPatterns("/" + FileService.FILE_RESOURCE_PATTEN + "/*");
        registration.setName("resourceFilter");
        registration.setOrder(1);
        return registration;

    }

    @Bean(name = "myUserFilter")
    public UrlRewriteFilter getResourceBean() {
        UrlRewriteFilter bean = new UrlRewriteFilter();
        return new UrlRewriteFilter();
    }
}
