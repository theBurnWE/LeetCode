package com.shcepp.shdippsvr.sys.util;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局捕获traffic异常
 * 到一个单独的跨域json接口处理
 * Created by Merjiezo on 2017/4/19.
 */
public class ErrorExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(ErrorExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {

        if (ex != null && !"".equals(ex.toString())) {

            if ("noOauth".equals(ex.getMessage())) {
                logger.info("用户主动被踢出");
                setCrossDomain(request, response);
            }

            if ("traffic".equals(ex.getMessage())) {
                logger.info("用户：" + request.getAttribute("UserloginId") + "被限流");
                setCrossDomain(request, response);
            } else {
                logger.error("error happen ",ex);
            }
        }
        return super.doResolveException(request, response, handler, ex);
    }

    /**
     * 设置跨域json接口http头
     * @param request
     * @param response
     */
    private void setCrossDomain (HttpServletRequest request, HttpServletResponse response) {
        //设置跨域Restful头
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "1800");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, No-Cache, X-Requested-With, Pragma, Cache-Control, Content-Type, eptoken");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
    }

}
