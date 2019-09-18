package com.shcepp.shdippsvr.sys.util;

import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TemplateUtil {
    private static LambdaLogger logger = LambdaLoggerFactory.getLogger(TemplateUtil.class);
    private static Map<String ,String> templateMap = new HashMap();

    private static String encoding = "UTF-8";

    
    public static String getShortMessageContent(String captcha, String captchaType) {
        switch(captchaType){
            case Constants.CAPTCHA_RESET_TYPE:
                return getActualContent(getTemplateString("mobilereset"), captcha);
            case Constants.CAPTCHA_REG_TYPE:
            default:
                return getActualContent(getTemplateString("mobile"), captcha);
        }

    }
    
    public static String getEmailContent(String captcha, String captchaType) {
        switch(captchaType){
            case Constants.CAPTCHA_RESET_TYPE:
                return getActualContent(getTemplateString("emailreset"), captcha);
            case Constants.CAPTCHA_REG_TYPE:
            default:
                return getActualContent(getTemplateString("email"), captcha);
        }
    }
    
    private static String getActualContent(String content, String captcha) {
        return content.replace("{captcha}", captcha);
    }
    
    private static String getTemplate(String templatePrefix) {
        
        String templateFileFullPath = templatePrefix + "template.txt";
        logger.debug("获取模板文件: {}", () -> templateFileFullPath);
        
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            
            InputStream stream = TemplateUtil.class.getClassLoader().getResourceAsStream(templateFileFullPath);
            inputStreamReader = new InputStreamReader(stream, encoding); // 建立一个输入流对象reader
            bufferedReader = new BufferedReader(inputStreamReader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            StringBuffer stringBuffer = new StringBuffer();
            String line = bufferedReader.readLine().trim();
            while(line != null) {
                stringBuffer.append(line);
                line = bufferedReader.readLine(); // 一次读入一行数据
            }
            
            return stringBuffer.toString();
        }
        catch(UnsupportedEncodingException e) {
            logger.error("The OS does not support {}", () -> encoding);
            return "";
        }
        
        catch(FileNotFoundException e) {
            logger.error("模板文件 {} 不存在", () -> templateFileFullPath, () -> e);
            return "";
        }
        catch(IOException e) {
            logger.error("读取模板文件 {} 发生错误: ", () -> templateFileFullPath, () -> e);
            return "";
        }
        catch(Exception ex) {
            logger.error("获取文件信息时发生错误", () -> ex);
            return "";
        }
        finally {
            try {
                if(null != bufferedReader) {
                    bufferedReader.close();
                }
                if(null != inputStreamReader) {
                    inputStreamReader.close();
                }
            }
            catch(Exception ex) {
                logger.error("获取文件信息时发生错误", () -> ex);
            }
        }
    }
    
    
    private static String getTemplateString(String prefix) {
        if (templateMap.containsKey("prefix")) {
            return templateMap.get(prefix);
        }
        else{
            String tmp = getTemplate(prefix);
            if (!StringUtils.isEmpty(tmp)) {
                templateMap.put(prefix, tmp);
            }
            return tmp;
        }
     
    }

    
}
