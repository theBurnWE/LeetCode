package com.shcepp.shdippsvr.business.service.impl;

import com.shcepp.shdippsvr.business.config.SMConfig;
import com.shcepp.shdippsvr.business.exception.MessageException;
import com.shcepp.shdippsvr.business.service.SMService;
import com.shcepp.shdippsvr.oauth.util.HttpUtil;
import com.shcepp.shdippsvr.sys.util.Constants;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SMServiceImpl implements SMService {
    private final LambdaLogger logger = LambdaLoggerFactory.getLogger(getClass());
    
    @Override
    public void sendMessage(String mobilePhoneNo, String content) throws MessageException {
        //发送短消息
        logger.info("Processing sendMessage, param mobilePhoneNo: {} and content: {}", () -> mobilePhoneNo, () -> content);
        //生成发送实体
        
        Map<String, String> maps = new HashMap<>();
        maps.put("action", "send");
        maps.put("userid", SMConfig.SM_USER_ID);
        maps.put("account", SMConfig.SM_ACCOUNT);
        maps.put("password", SMConfig.SM_PASSWORD);
        maps.put("mobile", mobilePhoneNo);
        maps.put("content", content);
        maps.put("sendTime", "");
        maps.put("extno", "");
        
        logger.debug("Sending short message, entity: {}", () -> maps);
        boolean sendSuccess = true;
        if("1".equals(SMConfig.SM_ACTRUAL_SEND)) {
            //生成httpclient
            try {
                String resopnse = HttpUtil.getInstance().sendHttpPost(SMConfig.SM_URL, maps);
                //解析返回的xml
                logger.debug("Processing response: {}", () -> resopnse);
                if(resopnse.contains("<message>ok</message>")) {
                    sendSuccess = true;
                }
                else {
                    sendSuccess = false;
                }
            }
            catch(Exception ex) {
                logger.error("Send Short Message Error", () -> ex);
            }
        }
        else {
            logger.debug("不进行实际发送，短消息内容: {}", () -> content);
            throw new MessageException(Constants.USER_REG_ERROR.ERROR_CODE007,
                    String.format("%s 短信消息为: %s ", Constants.USER_REG_ERROR.ERROR_INFO007, content));
            
        }
        
        //成功则return，失败则throw exception
        if(sendSuccess) {
            return;
        }
        else {
            throw new MessageException(Constants.USER_REG_ERROR.ERROR_CODE001, Constants.USER_REG_ERROR.ERROR_INFO001);
        }
    }
}
