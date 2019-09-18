package com.shcepp.shdippsvr;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.shcepp.shdippsvr.business.bean.pojo.SysBizAuditPojo;
import com.shcepp.shdippsvr.business.entity.ShdippSysAttachmentEntity;
import com.shcepp.shdippsvr.business.service.SysBizAuditService;
import com.shcepp.shdippsvr.sys.service.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

import   com.shcepp.shdippsvr.business.enums.*;

//声明SpringBoot应用
@SpringBootApplication
//增加对定时任务的支持
@EnableScheduling
//声明扫描范围
@ComponentScan(basePackages = "com.shcepp.*")
//启动apollo
@EnableApolloConfig
public class Application extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    private SysBizAuditService sysBizAuditService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        //解决返回串中文乱码问题
        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return builder.additionalMessageConverters(m).build();
    }

    @Bean(initMethod = "init")
    public RedisUtil redisUtil() {
        return new RedisUtil();
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        test();

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                                             SerializerFeature.WriteDateUseDateFormat,
                                             SerializerFeature.DisableCircularReferenceDetect);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastConverter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(fastConverter);
    }

    //用以测试ES进行存取/查询测试
    private void test() {
        SysBizAuditPojo pojo = new SysBizAuditPojo();
        ShdippSysAttachmentEntity en = new ShdippSysAttachmentEntity();
        en.setFileName("123");
        pojo.setAuditBeanNew(en);
        en.setFileName("1234");
        pojo.setAuditBeanOld(en);
//        pojo.setAuditStrNew("wwwww");
//        pojo.setAuditStrOld("wwwww");
        pojo.setAudtPermissionType(AudtPermissionType.APT_PRODUCT);
        pojo.setOperType(AudtOptionType.AOT_Delete);
        pojo.setOperMemberType(OperMemberType.OMT_ADMIN);
        pojo.setDiscrttion("我的测试");
        pojo.setOperIp("王炳辉");
        pojo.setLoginId("王炳辉");

        sysBizAuditService.SaveAudit(pojo);
    }

}
