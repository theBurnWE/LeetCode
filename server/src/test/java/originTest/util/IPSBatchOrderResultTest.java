package originTest.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.BasicClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.alibaba.fastjson.JSON;



public class IPSBatchOrderResultTest
    {
        
        private static final String url = "http://localhost:9090/api/test/findall";//upftrans

        //开发环境
        private static final String batchApply = "http://localhost:9091/upfgateway/IPSKJZF/040300/v2.0";
        //测试环境
//        private static final String batchApply = "http://192.168.128.133:9091/upfgateway/IPSKJZFKJZF/040300/v2.0";
        //测试环境外网
//        private static final String batchApply = "http://localhost:18080/upfgateway/IPSKJZF/040300/v2.0";
        
        private static final String batchApply_test = "http://192.168.128.133:9090/upftrans/IPS/SETTLEMENT/BATCHORDERAPPLY";
        private static final String batchApply_test_out = "http://180.166.132.108/upftrans/IPS/SETTLEMENT/BATCHORDERAPPLY";
        private static final String xml_path = "E:\\工作文件夹\\环迅相关\\报文\\环迅测试报文.xml";


        private static final String platCode = "123123";
        
        public static void main(String[] args) throws Exception
            {
                do{

                 newUserRegisterMain(batchApply);
                }while (true);

            }
        
        private static void newUserRegisterMain(String url) throws Exception
            {
                
             String ss = TestCwsUtil.getSourceXml(xml_path);
                SchemeRegistry registry = new SchemeRegistry();
                registry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
                DefaultHttpClient httpClient = new DefaultHttpClient(new BasicClientConnectionManager(registry));
                HttpContext httpContext = new BasicHttpContext();
                HttpPost request = new HttpPost(url);
                request.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                List<NameValuePair> parameterList = new ArrayList<NameValuePair>();
                parameterList.add(new BasicNameValuePair("trxCode", "040300"));
                parameterList.add(new BasicNameValuePair("version", "v2.0"));
//                parameterList.add(new BasicNameValuePair("data", URLEncoder.encode(JSON.toJSONString(ss),"UTF-8")));
                parameterList.add(new BasicNameValuePair("data",ss));
                parameterList.add(new BasicNameValuePair("signMsg", TestCwsUtil.sign(JSON.toJSONString(ss), "1234", "MD5")));
                //parameterList.add(new BasicNameValuePair("notifyUrl", "www.baidu.com"));
                
                request.setEntity(new UrlEncodedFormEntity(parameterList, "UTF-8"));
                System.out.println();
                System.out.println("-----------------start-request------------" + new Date());
                HttpResponse response = httpClient.execute(request, httpContext);
                System.out.println("-----------------end-request------------" + new Date());
                System.out.println(response);
                System.out.println(getResponseBody(response));
            }
        
       
        
        private static String getResponseBody(HttpResponse postResponse) throws Exception
            {
                InputStream input = null;
                try
                    {
                        input = postResponse.getEntity().getContent();
                        Reader reader = new InputStreamReader(input, TestCwsUtil.DEFAULT_ENCODING);
                        StringBuilder b = new StringBuilder();
                        char[] c = new char[1024];
                        int len;
                        while (0 < (len = reader.read(c)))
                            {
                                b.append(c, 0, len);
                            }
                        return b.toString();
                    } catch (Exception e)
                    {
                        throw e;
                    } finally
                    {
                        TestCwsUtil.close(input);
                    }
            }
    }
