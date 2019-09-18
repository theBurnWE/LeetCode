package com.shcepp.shdippsvr.sys.task;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

/**
 * @Description: 基础Task抽象类
 * @author: mlzhang
 * @date: 2016/10/17 16:58
 * @version: V1.0
 */
public abstract class BaseTask {

    protected abstract void run(String jobName,String jobGroup);
}
