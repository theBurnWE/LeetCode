package com.shcepp.shdippsvr.sys.util;

import java.io.Serializable;

/**
 * Created by mlzhang on 2017/7/7.
 */
public class ApiResult implements Serializable {

    private static final long serialVersionUID = -1093927950574276476L;
    private String returnCode;
    private String returnInfo;
    private String flag;
    private Object data;
    public ApiResult() {
    }

    public ApiResult(String flag ) {

        this.flag = flag;

    }

    public ApiResult(String flag, String returnCode, String returnInfo, Object data) {
        this.returnCode = returnCode;
        this.returnInfo = returnInfo;
        this.flag = flag;
        this.data = data;
    }

    public ApiResult(String flag, String returnCode, String returnInfo) {
        this.returnCode = returnCode;
        this.returnInfo = returnInfo;
        this.flag = flag;
    }

    public static ApiResult newInstance(String flag, String returnCode, String returnInfo, Object data) {
        return new ApiResult(flag, returnCode, returnInfo, data);
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(String returnInfo) {
        this.returnInfo = returnInfo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
