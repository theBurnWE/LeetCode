package com.shcepp.shdippsvr.business.bean;

import java.io.Serializable;

/**
 * 基础bean
 *
 * @author BrunE
 * @date 2019-07-18 11:18
 **/
public abstract class BaseEspojoBean implements Serializable {

    private static final long serialVersionUID = 2754121046250564685L;
    public abstract String toEsString();
}
