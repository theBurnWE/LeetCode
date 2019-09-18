package com.shcepp.shdippsvr.sys.config;


import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile("test")
public class SwaggerConfig {
    
    
    // 定义分隔符
    private static final String splitor = ";";
    
    
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage("com.shcepp.shdippsvr.sys.controller"+splitor+"com.shcepp.shdippsvr.business.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("EDI客户端接口调用API")
                .description("如有调用问题请发送邮箱：mlzhang@shcepp.com")
                .contact(new Contact("EP-mlzhang", "http://www.singlewindow.sh.cn", "mlzhang@shcepp.com"))
                .version("1.0")
                .build();
    }
    
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }
    
    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(splitor)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }
    
    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

}
