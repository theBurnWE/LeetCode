package com.shcepp.shdippsvr.business.service.impl;

import com.shcepp.shdippsvr.business.config.MailConfig;
import com.shcepp.shdippsvr.business.config.SMConfig;
import com.shcepp.shdippsvr.business.exception.MessageException;
import com.shcepp.shdippsvr.business.service.MailService;
import com.shcepp.shdippsvr.sys.util.Constants;
import com.shcepp.shdippsvr.sys.util.mail.EmailSender;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    private final LambdaLogger logger = LambdaLoggerFactory.getLogger(getClass());
    
    @Override
    public void sendMessage(String subject, String email, String content) throws MessageException {
        //发送短消息
        logger.info("Processing sendMessage, param email: {} and content: {}", () -> email, () -> content);
        //生成发送实体
        boolean sendSuccess = true;
        if("1".equals(SMConfig.SM_ACTRUAL_SEND)) {
            EmailSender es = new EmailSender(email, MailConfig.MAIL_USER, MailConfig.MAIL_SERVER,
                    MailConfig.MAIL_USER, "", subject, content);
            sendSuccess = es.sendMail();
        }
        else {
            logger.debug("不进行实际发送，邮件标题: {} 邮件内容: {}", () -> subject, () -> content);
            throw new MessageException(Constants.USER_REG_ERROR.ERROR_CODE008,
                    String.format("%s 邮件内容为: %s ", Constants.USER_REG_ERROR.ERROR_INFO008, content));
            
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
