package com.shcepp.shdippsvr.sys.filer;

import javax.servlet.*;
import java.io.IOException;

/**
 * 认证系统的拦截器
 * 在没有拦截的时候
 */
public class AuthFilter implements Filter {

    //初始化拦截器
    public void init(FilterConfig config) throws ServletException {
        //
    }

    //执行拦截器的功能
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws ServletException, IOException {
    }

    public void destroy() {
        //结束拦截器
    }

}
