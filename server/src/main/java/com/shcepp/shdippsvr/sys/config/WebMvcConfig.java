package com.shcepp.shdippsvr.sys.config;

import com.shcepp.shdippsvr.business.service.FileService;
import com.shcepp.shdippsvr.business.util.FileUploadUtil;
import com.shcepp.shdippsvr.sys.filer.UrlRewriteFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 静态文件加载
 *
 * @author BrunE
 * @date 2019-08-30 18:49
 **/

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(UrlRewriteFilter.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        String path = "file:" + FileUploadUtil.getFilePrePatten();
        path = path + java.io.File.separator;
        String filter = "/" + FileService.FILE_RESOURCE_PATTEN + "/" + FileService.FILE_PATTEN + "/**";
        logger.debug("拦截规则为：{},静态资源地址配置为：", filter, path);
        //静态文件拦截地址
        //静态文件放置地址
        //会将filert包含的内容切割之后，至path指定的地方加载对应的资源
        registry.addResourceHandler(filter).addResourceLocations(path);
        super.addResourceHandlers(registry);
    }

}
