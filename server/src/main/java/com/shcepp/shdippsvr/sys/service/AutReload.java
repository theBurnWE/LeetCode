package com.shcepp.shdippsvr.sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.*;
//import net.easipay.pepp.cbt.config.Starter;
//import net.easipay.pepp.cbt.config.entity.CeppCommonPropertiesDao;
//import net.easipay.pepp.cbt.config.entity.CommonDataSource;
//import net.easipay.pepp.cbt.config.PropertiesReloader;
//import net.easipay.pepp.cbt.config.listener.ListenerCenter;

/**
 * 装载自动刷新类
 *
 * @author BrunE
 * @date 2018-04-11 16:56
 **/
//@Configuration
public class AutReload implements BeanDefinitionRegistryPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(AutReload.class);
    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        logger.info("Invoke Metho postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    private void registerBean(BeanDefinitionRegistry registry, String name, Class<?> beanClass){
        AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(beanClass);

        ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(abd);
        abd.setScope(scopeMetadata.getScopeName());
        // 可以自动生成name
        String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(abd, registry));

        AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);

        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName);
        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
    }
}
