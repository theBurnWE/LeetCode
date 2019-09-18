package com.shcepp.shdippsvr.sys.config;

import com.shcepp.shdippsvr.sys.exception.EasiAopException;
import com.shcepp.shdippsvr.sys.exception.EasiControllerException;
import com.shcepp.shdippsvr.sys.exception.EasiServiceException;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import com.shcepp.shdippsvr.sys.util.Constants;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

/**
 * @author mlzhang
 */
@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class GlobalRestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public ApiResult restExceptionHandler(Exception e) {
        ApiResult result;
        if (e instanceof EasiAopException) {//切面异常
            logger.info(e.getMessage());
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F,
                                           e.getMessage(),
                                           Constants.showInfo(e.getMessage()),
                                           null);
        } else if (e instanceof EasiControllerException) {
            logger.info(e.getMessage());
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F,
                    ((EasiControllerException) e).getStatus(),
                    Constants.showInfo(((EasiControllerException) e).getStatus()),
                    null);
        } else if (e instanceof EasiServiceException) {
            logger.info(e.getMessage());
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F,
                    ((EasiServiceException) e).getStatus(),
//                    ((EasiServiceException) e).getStatus().equals(Constants.CLIENT_ERROR_000.ERROR_CODE006)? e.getMessage() :
                            Constants.showInfo(((EasiServiceException) e).getStatus()),
                    null);
        } else if (e instanceof RestClientException) {
            logger.info(e.getMessage());
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F,
                    Constants.CLIENT_ERROR_900.ERROR_CODE903,
                    "服务器异常",
                    null);
        } else {
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F,
                    Constants.CLIENT_ERROR_900.ERROR_CODE903,
                    e.toString(),
                    null);
        }
        logger.error("error message is {} ", JsonUtil.beanToJson(result));
        return result;
    }
}
