package com.shcepp.shdippsvr.sys.service;

import com.shcepp.shdippsvr.oauth.dto.OAuthUserInfo;
import com.shcepp.shdippsvr.oauth.service.AppOAuthService;
import com.shcepp.shdippsvr.sys.exception.ApiNoAuthException;
import com.shcepp.shdippsvr.sys.exception.NoOauthException;
import com.shcepp.shdippsvr.sys.exception.TrafficException;
import com.shcepp.shdippsvr.sys.util.Constants;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by Merjiezo on 2017/6/29.
 */
@Service("businessInterceptor")
public class BusinessInterceptor {

    @Resource(name="appOAuthService")
    public AppOAuthService service;
    private final LambdaLogger logger = LambdaLoggerFactory.getLogger(getClass());
    

    //OAuth
    private boolean useAntPath = true;
    private boolean lowerComparison ;
    private UrlMatcher urlMatcher = new AntUrlPathMatcher();

    public void setLowerComparison(boolean lowerComparison) {
        this.lowerComparison = lowerComparison;
    }
    
    //参数可以精确到月
    private static final int[] DEFAULT_TIME = { 1, 60, 3600, 86400, 2592000 };

    public OAuthUserInfo handleOAuth(HttpServletRequest request) throws NoOauthException {

        // 获取应用的Code
        String appCode = this.getAppcode(request);
        // 用户请求的refresh_token
        String newrt = request.getHeader("token");
        if (newrt == null) {
            newrt = request.getParameter("token");
        }
        if (newrt == null) {
            newrt = String.valueOf(request.getAttribute("token"));
        }
        logger.info("-------------token-----------------:"+newrt);
        request.setAttribute("refresh_token", newrt);
        OAuthUserInfo oui = null;

        //无token，拦截
        if (StringUtils.isEmpty(newrt)) {
            throw new NoOauthException("noOauth");
        } else {
            if (null == oui) {
                String userInfo = RedisUtil.getToken(newrt);
                if (null != userInfo) {
                    oui = JsonUtil.jsonToBean(userInfo, OAuthUserInfo.class);
                } else {
                    oui = service.getUserInfo(newrt,appCode);
                    if (null != oui) {
                        if (!"T".equals(oui.flag)) {
                            logger.error(JsonUtil.beanToJson(oui));
                            throw new NoOauthException("noOauth");
                        } else {
                         
                            RedisUtil.setToken(newrt, oui);
                        }
                    } else {
                        logger.error("Oauth服务器返回为空！");
                        throw new NoOauthException("noOauth");
                    }
                }
            }
        }
        if (!match(request ,newrt, oui)) {
            throw new ApiNoAuthException("ApiNoAuth");
        }
        return oui;
    }

    

    private String getAppcode(HttpServletRequest httpRequest) {
        String appCode = httpRequest.getParameter("app_code");
        if (StringUtils.isEmpty(appCode)) {
            appCode = Constants.APP_CODE;
        }
        return appCode;
    }

    private boolean match(HttpServletRequest request, String token, OAuthUserInfo oui) {
        boolean matched = false;
        matched = true;
        if (null != oui  && null != oui.info && null != oui.info.permits
                && oui.info.permits.size() > 0) {

            String url;
            String reqUrl = request.getServletPath();
            // logger.info("match method reqUrl="+reqUrl);
            if (!(request.getQueryString() == null || "null".equals(request
                    .getQueryString()))) {
                reqUrl = reqUrl + "?" + request.getQueryString();
                if (reqUrl.indexOf("token=") != -1) {
                    reqUrl = reqUrl.substring(0,
                            reqUrl.indexOf("token=") - 1);
                }
            }
            if (lowerComparison) {
                reqUrl = reqUrl.toLowerCase();
            }
            matched = true;

//            for (OAuthUserInfo.UserInfo.UmPermission key : oui.info.permits) {
//                url = key.url;
//                // 兼容旧接口
//                url = url.replace(".do", "/");
//                if (lowerComparison) {
//                    url = url.toLowerCase();
//                }
//
//                if (useAntPath ? urlMatcher.pathMatchesUrl(url, reqUrl)
//                        : urlMatcher.pathMatchesUrl(Pattern.compile(url),reqUrl)) {
//                    matched = true;
//                    break;
//                }
//            }
        }
        return matched;

    }

    /***
     * 节流阀算法
     * @param flowRule     节流配置的规则
     * @param originTime   初始时间
     * @param userFlowTime 用户的行为
     * @return Boolean     是否使其限流
     */
    private String match(String flowRule, long originTime, String userFlowTime) throws Exception {
        //初始值和规则
        StringBuffer nowUserFlow = new StringBuffer("");
        long newTime = System.currentTimeMillis() / 1000;
        String[] rules = userFlowTime.split("#"),
                flowRules = flowRule.split("#");

        if (rules.length == flowRules.length) {

            for (int i = 0, j = rules.length; i < j; i++) {
                String[] singleUserFlow = rules[i].split("\\|", 2);
                String[] singleRules = flowRules[i].split("\\|", 2);
                int second = DEFAULT_TIME[i] * Integer.parseInt(singleRules[0]);

                if (!"*".equals(singleRules[1])) {
                    long singleUserTime = Long.parseLong(singleUserFlow[0]);
                    int singleUserFrequency = Integer.parseInt(singleUserFlow[1]);

                    //判断时间过了没
                    if (singleUserTime + second >= newTime) {

                        if (singleUserFrequency + 1 <= Integer.parseInt(singleRules[1])) {
                            singleUserFrequency += 1;
                            nowUserFlow.append(singleUserTime).append("|").append(singleUserFrequency).append("#");
                        } else {
                            //超流
                            throw new TrafficException("traffic");
                        }
                    } else {
                        nowUserFlow.append(newTime).append("|").append("1#");
                    }


                } else {
                    //如果为*号，则为此
                    nowUserFlow.append(newTime).append("|").append("1#");
                }

            }
        } else {
            throw new Exception("保存的参数个数和配置表不一致");
        }
        nowUserFlow.append("#").append(originTime);
        return nowUserFlow.toString();
    }
    /***
     * 设置初始节流阀参数
     * @param key 存入Redis的主键
     * @param flowRule 规则匹配
     */
    private void setUserFrequency(String key, String flowRule) {
        String[] rules = flowRule.split("#");
        int length = rules.length ,
                time_out = Integer.valueOf(RedisUtil.getConfig("TrafficTimeOutTime"));
        long nowTime = System.currentTimeMillis() / 1000,
                outUnixTime = nowTime + time_out;

        StringBuffer val = new StringBuffer();

        while (length > 0) {
            val.append(nowTime).append("|1#");
            length--;
        }
        val.append("#").append(outUnixTime);

        RedisUtil.setEx(key, val.toString(), time_out);
    }
}
