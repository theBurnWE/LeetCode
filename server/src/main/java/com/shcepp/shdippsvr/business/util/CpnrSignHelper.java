package com.shcepp.shdippsvr.business.util;

import com.shcepp.shdippsvr.business.exception.BizCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * Singhelper
 *
 * @author BrunE
 * @date 2017-08-04 14:04
 **/
public class CpnrSignHelper {



    private static final Logger logger = LoggerFactory.getLogger(CpnrSignHelper.class);

    // 测试商户的私钥证书包含了公钥和私钥的二进制证书
    public static String USER_PRIVATE_KEY_PATH = PropertiesUtil.get("USER.PRIVATE.KEY.PATH");
    // 测试商户的公钥证书
    public static String USER_PUBLIC_KEY_PATH = PropertiesUtil.get("USER.PUBLIC.KEY.PATH");
    // 付汇的公钥证书
    public static String PUBLIC_KEY_PATH = PropertiesUtil.get("PUBLIC.KEY.PATH");
    // 保护密码
    public static String KEYSTORE_PASSWORD = PropertiesUtil.get("KEYSTORE.PASSWORD");
    // 别名
    public static String KEYSTORE_ALIAS = PropertiesUtil.get("KEYSTORE.ALIAS");
    // 编码格式
    public static String CHAREST = PropertiesUtil.get("XML.CHAREST");

    /**
     *
     * @param xmlStr
     * @return
     */
    public static String signXmlStr(String xmlStr, String platCode) {
       // PrivateKey privateKey = getPrivateKey(USER_PRIVATE_KEY_PATH,
         //      KEYSTORE_PASSWORD,
           //     KEYSTORE_ALIAS);
        PrivateKey privateKey = getPrivateKey(PropertiesUtil.get(platCode +".USER.PRIVATE.KEY.PATH"),
                                              PropertiesUtil.get(platCode +".KEYSTORE.PASSWORD"),
                                              PropertiesUtil.get(platCode +".KEYSTORE.ALIAS"));
        if (null != privateKey) {
            SignUtil sg = new SignUtil();
            // 加签
            String reStr = sg.signXml(xmlStr, privateKey);

            if (null == reStr) {
                logger.info("sign is fial");
                throw new BizCheckException("sign is fial");
            }
            return reStr;
        } else {
            logger.info("privateKey is null please check your config.properties");
            throw new BizCheckException("privateKey is null please check your config.properties");
        }

    }

    public static boolean checkXmlStr(String xmlStr, String merchantId) throws Exception {
       // PublicKey publicKey = getPublicKey(SignHelper.PUBLIC_KEY_PATH);// 本地测试的时候使用USER_PUBLIC_KEY_PATH，生产和对接的时候使用PUBLIC_KEY_PATH
    	 String platCode = PropertiesUtil.get("MERCHANTID."+merchantId);
     	PublicKey publicKey = getPublicKey(PropertiesUtil.get(platCode +".USER.PUBLIC.KEY.PATH"));
        if (null != publicKey) {
            SignUtil signUtil = new SignUtil();

            boolean reB = signUtil.verifyXml(xmlStr, publicKey);

            return reB;
        } else {
            logger.info("public is null please check you *.pfx file");
            throw new BizCheckException("public is null please check you *.pfx file");
        }
    }

    /**
     * 证书操作类，对证书进行读取操作cer文件中的公钥
     *
     * @param path
     * @return
     * @throws Exception
     */
    private static PublicKey getPublicKey(String path) throws Exception {
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            InputStream in = new FileInputStream(path);
            Certificate cate = factory.generateCertificate(in);
            return cate.getPublicKey();
        } catch (Exception e) {
            logger.info(e
                    + "can not find publicKey file please check attribute:PUBLIC.KEY.PATH in your config.properties ");
            throw new BizCheckException(e
                    + "can not find publicKey file please check attribute:PUBLIC.KEY.PATH in your config.properties ");
        }
    }

    /**
     * 私钥操作类，读取私钥加密文件中的私钥
     *
     * @param filePath,,alias
     * @return
     * @throws Exception
     */
    private static PrivateKey getPrivateKey(String filePath,
                                            String pwd,
                                            String alias) {

        try {
            //String proPath = System.getProperty("user.dir");
            //File directory = new File("");//设定为当前文件夹
            //System.out.println(directory.getCanonicalPath());//获取标准的路径
            //System.out.println(directory.getAbsolutePath());//获取绝对路径

            KeyStore ks = KeyStore.getInstance("PKCS12");
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(filePath);
            } catch (Exception e) {
                // TODO: handle exception
                 logger.info("filepath for privateKey is wrong, please check attribute USER.PRIVATE.KEY.PATH in your config.properties");
                throw new BizCheckException("filepath for privateKey is wrong, please check attribute USER.PRIVATE.KEY.PATH in your config.properties");
            }

            // If the keystore password is empty(""), then we have to set
            // to null, otherwise it won't work!!!

            char[] nPassword = null;
            if ((null == pwd) || pwd.trim().equals("")) {
                nPassword = null;
            } else {
                nPassword = pwd.toCharArray();
            }

            try {
                ks.load(fis, nPassword);
            } catch (Exception e) {
                logger.info("password for privateKey is wrong, please check attribute KEYSTORE_PASSWORD in your config.properties");
                throw new BizCheckException("password for privateKey is wrong, please check attribute KEYSTORE_PASSWORD in your config.properties");
            }

            fis.close();

            PrivateKey prikey = (PrivateKey) ks.getKey(alias, nPassword);
            if (null == prikey) {
                logger.info("alias for privateKey is wrong, please check attribute KEYSTORE.ALIAS in your config.properties");
                throw new BizCheckException("alias for privateKey is wrong, please check attribute KEYSTORE.ALIAS in your config.properties");
            } else {
                return prikey;
            }

        } catch (Exception e) {
            throw new BizCheckException(e);
        }
    }

}
