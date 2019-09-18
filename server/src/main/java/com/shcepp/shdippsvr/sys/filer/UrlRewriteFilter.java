package com.shcepp.shdippsvr.sys.filer;

import com.shcepp.shdippsvr.business.dao.ShdippSysAttachmentDao;
import com.shcepp.shdippsvr.business.entity.ShdippSysAttachmentEntity;
import com.shcepp.shdippsvr.business.enums.FileType;
import com.shcepp.shdippsvr.business.service.BaseService;
import com.shcepp.shdippsvr.business.service.FileService;
import com.shcepp.shdippsvr.business.util.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.shcepp.shdippsvr.business.enums.FileType.FT_102;
import static com.shcepp.shdippsvr.business.enums.FileType.FT_202;

/**
 * 路由重构的拦截器，用以在获取到文件存储的时候指向指定的文件服务
 */

@ComponentScan
public class UrlRewriteFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(UrlRewriteFilter.class);

    @Autowired
    private ShdippSysAttachmentDao shdippSysAttachmentDao;

    @Override
    public void init(FilterConfig config) throws ServletException {
        logger.info("收到文件读取请求");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String requestURI = request.getRequestURI();
        String[] uri = requestURI.split(BaseService.URL_SPLIT_CHARACTER);
        if (requestURI.contains(FileService.FILE_PATTEN)) {
            try {

            chain.doFilter(req, res);
            }catch (Exception ex){
                logger.error("wbherror ----------------");
            }
        } else {
            StringBuilder urlStr = new StringBuilder();
            ShdippSysAttachmentEntity entity;
            String targetUrl;
            String midId;
            String id;
            String fileType="";
            try {
                midId = uri[uri.length - 1];
                id = midId.split(FileService.FILE_URL_PATTEN)[1];
                fileType = midId.split(FileService.FILE_URL_PATTEN)[0];
            } catch (Exception ex) {
                logger.info("资源加载模块的ID解析错误，请求的URI为：{}解析的错误信息为：", uri, ex);
                id = "0";
            }
            entity = shdippSysAttachmentDao.findById(id);
            //在未查询到数据/在数据解析异常的时候填补数据
            if (null == entity) {
                urlStr.append("404.html");
                targetUrl = urlStr.toString().replaceAll("\\\\", BaseService.URL_SPLIT_CHARACTER);

            } else {
                urlStr.append(FileService.FILE_PATTEN);
                urlStr.append(BaseService.URL_SPLIT_CHARACTER);
                urlStr.append(entity.getUrl());

                urlStr.append(FileUploadUtil.getFilePattenKeyWord(fileType));

                urlStr.append(entity.getSwitchFileName());
                logger.info("请求的URL为:{},资源目标为：{}", uri, urlStr);

                targetUrl = urlStr.toString().replaceAll("\\\\", BaseService.URL_SPLIT_CHARACTER);

                logger.info("重新编码后的请求的URL为:{},资源目标为：{}", uri, targetUrl);
            }

            request.getRequestDispatcher(targetUrl).forward(request, response);

        }
    }

    @Override
    public void destroy() {
        logger.info("资源读取结束");
    }


    /**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Map getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }
}
