package com.shcepp.shdippsvr.sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 基础服务类
 * @author: mlzhang
 * @date: 2016/10/12 13:51
 * @version: V1.0
 */

public abstract class BaseService {

    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
    @Autowired
    public RestTemplate restTemplate;
}
