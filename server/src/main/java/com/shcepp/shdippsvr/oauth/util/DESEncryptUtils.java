package com.shcepp.shdippsvr.oauth.util;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.MessageDigest;

/**
 * <p>
 * ClassName: DESEncryptUtils
 * </p>
 * <p>
 * Description: DES加/解密工具类
 * </p>
 * <p>
 * Author: colick
 * </p>
 * <p>
 * Date: 2015-11-17
 * </p>
 */
public class DESEncryptUtils {

    // 密钥
    private final static String SECRETKEY = "mobilewinx@easipass@1234";

    // 向量
    private final static String IV = "01234567";

    // 加解密统一使用的编码方式
    private final static String ENCODING = "utf-8";

    /**
     * 3DES加密
     *
     * @param plainText 普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String plainText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(SECRETKEY.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(ENCODING));
        return Base64Utils.encode(encryptData);
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(SECRETKEY.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

        byte[] decryptData = cipher.doFinal(Base64Utils.decode(encryptText));

        return new String(decryptData, ENCODING);
    }

    /**
     * 非对称加密登陆密码
     * @param oriPass
     * @return
     */
    public static String oauthLoginEncode(String oriPass) {
        try {
            // use the MD5 algorithm - 160bit
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest using UTF-8 encoding
            byte buf[] = oriPass.getBytes("UTF-8");
            md.update(buf);
            byte raw[] = md.digest();

            // convert to base64 String
            String hash = (new Base64()).encodeBase64String(raw);
            return hash;
        }
        catch(Exception ex){return "";}
    }

    public static void main(String[] args) throws Exception {
        String en = DESEncryptUtils.encode("winxportal");
        System.out.println("加密后：" + en);
        String on = DESEncryptUtils.oauthLoginEncode("winxportal");
        System.out.println("加密后：" + on);
        String de = DESEncryptUtils.decode(en);
        System.out.println("解密后：" + de);

    }

}
