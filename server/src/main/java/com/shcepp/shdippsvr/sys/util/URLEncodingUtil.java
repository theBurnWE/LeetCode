package com.shcepp.shdippsvr.sys.util;

import com.shcepp.shdippsvr.business.exception.MessageException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncodingUtil {
	public final static String charset = "UTF-8";

	public static String encodeData(String data) throws UnsupportedEncodingException {
		return URLEncoder.encode(data, charset);
	}

	public static String decodeData(String data) throws MessageException {
		try {
			return URLDecoder.decode(data, charset);
		} catch (Exception e) {
			throw new MessageException("999999", "EData URLDecoder解码出错,EData : " + data + ";charset : " + charset);
		}
	}

}
