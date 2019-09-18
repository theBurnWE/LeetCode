package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.exception.MessageException;

public interface MailService {
    void sendMessage(String subject, String email, String content) throws MessageException;
}
