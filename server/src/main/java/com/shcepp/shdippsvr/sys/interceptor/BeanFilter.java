package com.shcepp.shdippsvr.sys.interceptor;

import com.shcepp.shdippsvr.business.exception.MessageException;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import com.shcepp.shdippsvr.sys.util.URLEncodingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 对bean进行转换的拦截器
 *
 * @author BrunE
 * @date 2018-04-02 19:59
 **/
//@Component
//@ComponentScan.Filter
public class BeanFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(BeanFilter.class);

    private static Map<String, String> getRequest(ServletRequest request) throws IOException, MessageException {

        Map<String, String> returnMap = new HashMap<String, String>();

        if (request.getParameter("eData") != null) {
            returnMap.put("eData", request.getParameter("eData"));
            returnMap.put("signMsg", request.getParameter("signMsg"));
            returnMap.put("version", request.getParameter("version"));
            returnMap.put("trxCode", request.getParameter("trxCode"));
        } else {
            String content;//= contentBuffer.toString();

            ServletInputStream sin = ((HttpServletRequest) request).getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte buffer[] = new byte[4096];
            for (int n = 0; -1 != (n = sin.read(buffer)); ) {
                output.write(buffer, 0, n);
            }
            byte[] reqdata = output.toByteArray();

            content = new String(reqdata, "UTF-8");
            content = URLEncodingUtil.decodeData(content);
            Map<String, String> map = new HashMap<String, String>();
            if (content == null) {
                return null;
            } else {
                String s1[] = content.split("&");

                for (String mid : s1) {
                    String s2[] = mid.split("=");
                    map.put(s2[0], s2[1]);
                }
                returnMap = map;
            }
        }

        return returnMap;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //对edata的内容进行拦截，进行decode的操作
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String methodName = ((HttpServletRequest) request).getServletPath();
        String edata = "";
        try {
            Map<String, String> returnMap = getRequest(request);
            edata = returnMap.get("eData");
            String signMsg = returnMap.get("signMsg");
            if (StringUtils.isBlank(edata)) {
                edata = "";
            }
            edata = URLEncodingUtil.decodeData(edata);
            Map<String, Object> parameterMap = new HashMap<String, Object>(request.getParameterMap());
            ParameterRequestWrapper
                    requestWrapper =
                    new ParameterRequestWrapper((HttpServletRequest) request, parameterMap);
            chain.doFilter(requestWrapper, response);
            return;
        } catch (Exception e) {
            logger.error("error detai is ", e);
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
