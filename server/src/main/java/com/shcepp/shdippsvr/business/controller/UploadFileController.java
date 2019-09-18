package com.shcepp.shdippsvr.business.controller;

import com.shcepp.shdippsvr.business.bean.pojo.FileUploadReqPojo;
import com.shcepp.shdippsvr.business.dao.ShdippSysAttachmentDao;
import com.shcepp.shdippsvr.business.service.BaseService;
import com.shcepp.shdippsvr.business.service.FileService;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("resource/")
public class UploadFileController {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileController.class);
    @Autowired
    private FileService fileService;
    @Autowired
    private ShdippSysAttachmentDao shdippSysAttachmentDao;

    @PostMapping("/SingleFile")
    public ApiResult uploadFile(@RequestHeader(required = false) String epToken,
                                        @RequestParam("fileName") MultipartFile file,
                                        FileUploadReqPojo pojo) {
        logger.info("开始上传文件");
        logger.info("开始根据上传参数上传文件, 上传参数为 : {} ",
                    JsonUtil.beanToJson(pojo));
        //认证的部分已经在filter中完成
        //在此处只执行查询任务
        ApiResult result;
        try {
            result = fileService.uploadFile(file, pojo);
        } catch (ValidationException ver) {
            logger.error("validate req fial error message is ", ver);
            result = new ApiResult();
            //在参数校验失败的时候
            result.setReturnCode(ver.getMessage());
            result.setReturnInfo(BaseService.BR_OTHER_ERROR);
        } catch (Exception err) {
            logger.error("unexpect  error message is ", err);
            result = new ApiResult();
            result.setReturnCode(BaseService.BR_OTHER_ERROR);
            result.setReturnInfo(BaseService.BR_OTHER_ERROR_MESSAGE);
        }
        return result;
    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/fileresource")
//    public void getFile(
//            HttpServletRequest request,
//            HttpServletResponse response) {
//        try {
//            String pickey = ((String) request.getAttribute("resourceKey"));
//
//            response.setContentType("image/jpeg");
//            FileInputStream is = fileService.readPicTopage(pickey);
//
//            if (is != null) {
//                int i = is.available(); // 得到文件大小
//                byte data[] = new byte[i];
//                is.read(data); // 读数据
//                is.close();
//                response.setContentType("image/jpeg"); // 设置返回的文件类型
//                OutputStream toClient; // 得到向客户端输出二进制数据的对象
//                toClient = response.getOutputStream();
//
//                toClient.write(data); // 输出数据
//
//                toClient.close();
//            }
//        } catch (Exception e1) {
//            logger.error("读取图片的时候发生异常，异常信息为：", e1);
//        }
//
//    }

}
