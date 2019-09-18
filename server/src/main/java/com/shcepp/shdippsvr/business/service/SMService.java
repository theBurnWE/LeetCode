package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.exception.MessageException;

public interface SMService {
    /**
     * 发送消息
     *
     * @param mobilePhoneNo 手机号
     * @param content       内容
     * @throws MessageException
     */
    void sendMessage(String mobilePhoneNo, String content) throws MessageException;
}
