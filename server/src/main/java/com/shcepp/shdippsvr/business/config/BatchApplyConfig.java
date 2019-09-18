package com.shcepp.shdippsvr.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by apple on 2017/8/1.
 */

@Component
public class BatchApplyConfig implements BaseConfig {

    private static final long serialVersionUID = 8756306125036699308L;
    //单笔审核模式的platCode
    @Value("${shdippsvrcache.SEND_BATCH_APPLY_RESULT_MAX_NUM:10}")
    private Long upfSendBatchApplyResultMaxNum;

    @Value("${shdippsvrcache.SEND_BATCH_APPLY_RESULT_URL}")
    private String upfSendBatchApplyResultUrl;

    @Value("${shdippsvrcache.SEND_BATCH_APPLY_RESULT_TRX_CODE:040801}")
    private String upfSendBatchApplyTrxCode;

    @Value("${shdippsvrcache.SEND_BATCH_APPLY_RESULT_VERSION:v1.0}")
    private String upfSendBatchApplyResultVersion;

    public Long getUpfSendBatchApplyResultMaxNum() {
        return upfSendBatchApplyResultMaxNum;
    }

    public void setUpfSendBatchApplyResultMaxNum(Long upfSendBatchApplyResultMaxNum) {
        this.upfSendBatchApplyResultMaxNum = upfSendBatchApplyResultMaxNum;
    }

    public String getUpfSendBatchApplyResultUrl() {
        return upfSendBatchApplyResultUrl;
    }

    public void setUpfSendBatchApplyResultUrl(String upfSendBatchApplyResultUrl) {
        this.upfSendBatchApplyResultUrl = upfSendBatchApplyResultUrl;
    }

    public String getUpfSendBatchApplyTrxCode() {
        return upfSendBatchApplyTrxCode;
    }

    public void setUpfSendBatchApplyTrxCode(String upfSendBatchApplyTrxCode) {
        this.upfSendBatchApplyTrxCode = upfSendBatchApplyTrxCode;
    }

    public String getUpfSendBatchApplyResultVersion() {
        return upfSendBatchApplyResultVersion;
    }

    public void setUpfSendBatchApplyResultVersion(String upfSendBatchApplyResultVersion) {
        this.upfSendBatchApplyResultVersion = upfSendBatchApplyResultVersion;
    }
}
