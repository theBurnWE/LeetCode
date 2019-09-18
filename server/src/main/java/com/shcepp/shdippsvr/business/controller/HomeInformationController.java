package com.shcepp.shdippsvr.business.controller;

import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseDetailReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseDetailResPojo;
import com.shcepp.shdippsvr.business.service.BaseService;
import com.shcepp.shdippsvr.sys.controller.BaseController;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by shcepp on 2019/9/12.
 */
@RestController
@RequestMapping("home/")
public class HomeInformationController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(HomeInformationController.class);
    @PostMapping(value = "/information")
    public ApiResult queryHomeInformation() {
        ApiResult apiResult;
        try{
            /**请求wordpress首页资讯接口*/
             String data=sendPost("http://www.dtradex.com/news/syzx.php");
            apiResult=new ApiResult(BaseService.FLAG_T,BaseService.BR_SUCCESS,BaseService.BR_SUCCESS_MESSAGE, data);
        }catch (Exception e){
            apiResult=new ApiResult(BaseService.FLAG_F,BaseService.BR_OTHER_ERROR,e.getMessage(),null);
        }

        return apiResult;
    }
    @PostMapping(value = "/copyright")
    public ApiResult queryCopyright() {
        ApiResult apiResult;
        try{
            /**请求wordpress版权接口*/
            String data=sendPost("http://www.dtradex.com/news/bq.php");
            apiResult=new ApiResult(BaseService.FLAG_T,BaseService.BR_SUCCESS,BaseService.BR_SUCCESS_MESSAGE, data);
        }catch (Exception e){
            apiResult=new ApiResult(BaseService.FLAG_F,BaseService.BR_OTHER_ERROR,e.getMessage(),null);
        }

        return apiResult;
    }

    public static String sendPost(String url) {

        OutputStreamWriter out = null;

        BufferedReader in = null;

        String result = "";        try {

            URL realUrl = new URL(url);

            HttpURLConnection conn = null;            // 打开和URL之间的连接

            conn = (HttpURLConnection) realUrl.openConnection();            // 发送POST请求必须设置如下两行

            conn.setDoOutput(true);

            conn.setDoInput(true);

            conn.setRequestMethod("POST");    // POST方法



            // 设置通用的请求属性

            conn.setRequestProperty("accept", "*/*");

            conn.setRequestProperty("connection", "Keep-Alive");

            conn.setRequestProperty("user-agent",                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            conn.connect();            // 获取URLConnection对象对应的输出流

            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");            // 发送请求参数

            //out.write(param);            // flush输出流的缓冲

            out.flush();            // 定义BufferedReader输入流来读取URL的响应

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;            while ((line = in.readLine()) != null) {

                result += line;

            }

        } catch (Exception e) {

            System.out.println("发送 POST 请求出现异常！" + e);

            e.printStackTrace();

        }        //使用finally块来关闭输出流、输入流

        finally {            try {                if (out != null) {

            out.close();

        }                if (in != null) {

            in.close();

        }

        } catch (IOException ex) {

            ex.printStackTrace();

        }

        }        return result;

    }
}
