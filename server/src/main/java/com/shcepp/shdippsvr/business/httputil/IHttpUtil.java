package com.shcepp.shdippsvr.business.httputil;

import com.shcepp.shdippsvr.business.bean.http.RequestParamBean;

public interface IHttpUtil {

    Object formatRequestData(RequestParamBean o);

    String sendRequest(RequestParamBean o) throws Exception;

    //在发送方法中有封装的类
    Object sendRequestFormated(RequestParamBean o) throws Exception;

}
