package com.shcepp.shdippsvr.sys.service;

import com.shcepp.shdippsvr.oauth.dto.OAuthUserInfo;
import com.shcepp.shdippsvr.sys.util.Constants;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by LH on 2017/4/7.
 */
public class RedisUtil {
    
    final static int CAPTCHA_EXPIRE_SEC = 300;
    private static RedisUtil redisUtil;

    private static Logger log = LoggerFactory.getLogger(RedisUtil.class);

    private static String app_Code = Constants.APP_CODE;

    private JedisCluster jedisCluster;

    @Value("${spring.redis.cluster.nodes}")
    private String redisNodes;


    public static void setEx(String key, String value, int second) {
        redisUtil.jedisCluster.setex(app_Code + "_" + key, second, value);
    }

    public static void set(String key, String value) {
        redisUtil.jedisCluster.set(app_Code + "_" + key, value);
    }

    public static void hmset(String key, Map map) {
        redisUtil.jedisCluster.hmset(app_Code + "_" + key, map);
    }

    public static String get(String key) {
        return redisUtil.jedisCluster.get(app_Code + "_" + key);
    }

    public static String hmget(String key , String field) {
        List<String> list = redisUtil.jedisCluster.hmget(app_Code + "_" + key , field);
        return list.size() > 0 ? list.get(0) : null;
    }

    public static Map<String, String> hgetAll(String key) {
        return redisUtil.jedisCluster.hgetAll(app_Code + "_" + key );
    }

    public static void remove(String key) {
        redisUtil.jedisCluster.del(app_Code + "_" + key);
    }

    public static void expireAt (String key, int second) {
        redisUtil.jedisCluster.expireAt(key, second);
    }

    /***
     * 设置内容
     * @param token
     * @param oui
     */
    public static void setToken (String token, OAuthUserInfo oui) {
        String userId = oui.info.attrs.get("Userid");
        RedisUtil.setEx(token, userId, Constants.REDIS_OUT_TIME_30);
        RedisUtil.setEx(Constants.REDIS_USERID + userId, JsonUtil.beanToJson(oui), Constants.REDIS_OUT_TIME_30);
    }

    public static String getToken (String token) {
        String userId = get(token);
        if (null != userId) {
            return get(Constants.REDIS_USERID + userId);
        }
        return null;
    }
    
    public static void setCaptcha(String captchaKey, String captcha) {
        RedisUtil.setEx(captchaKey, captcha, CAPTCHA_EXPIRE_SEC);
    }
    
    public static void removeCaptcha(String captchaKey) {
        RedisUtil.remove(captchaKey);
    }
    
    public static String getCaptcha(String captchaKey) {
        String captcha = get(captchaKey);
        if(StringUtils.isBlank(captcha)) {
            return "";
        }
        else {
            return captcha;
        }
    }

    /**
     * 删除OAuth
     * @param token
     * @return
     */
    public static void removeToken (String token) {
        remove(token);
    }

    /**
     * 初始化
     */
    public void init() {
        try{
            log.info("------------------redisNodes:"+redisNodes);
            HashSet haps = new HashSet();
            String[] ipArray = redisNodes.split(",");
            for(String s : ipArray){
                String[] portArray = s.split(":");
                HostAndPort hap = new HostAndPort(portArray[0], Integer.parseInt(portArray[1]));
                haps.add(hap);
            }
            GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
            genericObjectPoolConfig.setMaxIdle(100);
            genericObjectPoolConfig.setMaxTotal(1000);
            genericObjectPoolConfig.setMinIdle(8);
            genericObjectPoolConfig.setMaxWaitMillis(-1);
            redisUtil = this;
            redisUtil.jedisCluster = new JedisCluster(haps, 300000, 6, genericObjectPoolConfig);
            log.info("redisUtil初始化...");

        }catch (Exception e){
            log.error("---------redis初始化错误");
            log.error(e.getMessage());
        }
    }

    public static String getConfig(String key){
        return hmget("CommConfigCache",key);
    }

    public static String getDefaultConfig(String key , String defaultVale) {
        String value = getConfig(key);
        return null != value ? value : defaultVale;
    }


}
