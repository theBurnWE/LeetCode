package com.shcepp.shdippsvr.business.aop;

import com.shcepp.shdippsvr.oauth.dto.OAuthUserInfo;
import com.shcepp.shdippsvr.sys.exception.ApiNoAuthException;
import com.shcepp.shdippsvr.sys.exception.NoOauthException;
import com.shcepp.shdippsvr.sys.service.BusinessInterceptor;

import com.shcepp.shdippsvr.sys.util.*;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Merjiezo on 2017/6/29.
 * 使用切面编程增加加/验签功能和认证功能
 */
@Component
@Aspect
public class BusinessAspact {
    
    private final LambdaLogger logger = LambdaLoggerFactory.getLogger(getClass());
    @Resource(name = "businessInterceptor")
    private BusinessInterceptor businessInterceptor;

    private String oauthSwitch = "1";

    
    @Pointcut("within(@org.springframework.stereotype.Controller *) && " +
//            "@annotation(requestMapping) && " +
            "execution(* springfox.documentation..*.*(..))")
    public void swagger() {
    }
    
//    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && " +
//            "execution(* com.shcepp.shdippsvr.sys.controller.ApiController.*(..))")
//    public void LoginOrOut() {
//    }
//
//    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && " +
//            "execution(* com.shcepp.shdippsvr.business.controller.UserController.*(..))")
//    public void UserReg() {
//    }
//
//    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && " +
//            "execution(* com.shcepp.shdippsvr.business.controller.QuartzSchedulerController.*(..))")
//    public void Quartz() {
//    }


    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void TestController() {
    }
    
    
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) &&" +
            "!swagger() &&" +
//            "!LoginOrOut() && " +
//            "!UserReg() && " +
//            "!Quartz() && " +
            "execution(* *WithAuth(..))")

    public void controller() {
    }
    
    @Around("controller()")
    public Object cusAround(ProceedingJoinPoint point) throws Throwable {
        logger.debug("---------PointCut Start--------");
//        if(true){
//            return point.proceed();
//        }
        //获取request
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        
        showParams(request);
        
        String requestUri = request.getRequestURI();
        logger.info("--------requestUri:" + requestUri);
        
        String reqUrl = request.getServletPath();
        //切点中获取requestBody中的值
        Object[] objects = point.getArgs();
        Map requestBody = new LinkedHashMap();
        for(Object object : objects) {
            if(object instanceof HashMap) {//对应requestBody
                requestBody = (HashMap) object;
                logger.info("requestBody:" + JsonUtil.beanToJson(requestBody));
            }
        }

//------------------------------------------------------------------------Ouath Start
        try {
            // OAuth 验证
            OAuthUserInfo oui = businessInterceptor.handleOAuth(request);
            //获取成功，并且有权限，根据Controller层情况注入用户信息
            if(objects.length > 0 && objects[objects.length - 1] != null && objects[objects.length - 1].getClass() == OAuthUserInfo.class) {
                OAuthUserInfo obj = (OAuthUserInfo) point.getArgs()[objects.length - 1];
                obj.info = oui.info;
                obj.permission = oui.permission;
                obj.flag = oui.flag;
                obj.errorCode = oui.errorCode;
                obj.errorInfo = oui.errorInfo;
            }
        }
        catch(NoOauthException ne) {
            return ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F,
                    Constants.CLIENT_ERROR_900.ERROR_CODE904,
                    Constants.CLIENT_ERROR_900.ERROR_INFO904,
                    null);
        }
        catch(ApiNoAuthException anae) {
            return ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F,
                    Constants.CLIENT_ERROR_900.ERROR_CODE906,
                    Constants.CLIENT_ERROR_900.ERROR_INFO906,
                    null);
        }
//------------------------------------------------------------------------Ouath End
        return point.proceed();
    }
    
    private void showParams(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            
            String[] paramValues = request.getParameterValues(paramName);
            if(paramValues.length == 1) {
                String paramValue = paramValues[0];
                if(paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        
        Set<Map.Entry<String, String>> set = map.entrySet();
        logger.info("--------requestParameters:");
        for(Map.Entry entry : set) {
            logger.info(entry.getKey() + ":" + entry.getValue());
        }
        logger.info("----------------");
    }
    
    
    @Before("controller()")
    public void ecsBefore() throws Throwable {
//        logger.debug("testBefore");
    }
    
    @After("controller()")
    public void ecsAfter() throws Throwable {
//        logger.debug("testAfter");
    }
    
    @AfterReturning(returning = "rvt", pointcut = "within(@org.springframework.web.bind.annotation.RestController *) &&" +
            "!swagger() &&" +
            "!TestController() &&" +
//            "!LoginOrOut()" +
//            " || " +
            "within(@org.springframework.stereotype.Controller *) && " +
            "execution(* *(..))")
    public Object afterExec(JoinPoint joinPoint, Object rvt) {
        logger.info("AfterReturning");
        logger.info("------------result:" + JsonUtil.beanToJson(rvt));
        return rvt;
        
    }
    
}
