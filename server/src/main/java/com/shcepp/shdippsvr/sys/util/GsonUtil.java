package com.shcepp.shdippsvr.sys.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 初始化gson 类型注册
 * 
 * @author xk
 * 
 */
public class GsonUtil {

	private static final Logger logger = LoggerFactory.getLogger(GsonUtil.class);

	private static Gson gson;

	static {
		if (gson == null) {
			// gson = new
			// GsonBuilder().setDateFormat("yyyy-MM-ddHH:mm:ss").disableHtmlEscaping().create();
			DateTypeAdapter dateTypeAdapter = new DateTypeAdapter();
			gson = new GsonBuilder().registerTypeAdapter(Date.class,
					dateTypeAdapter).registerTypeAdapter(Timestamp.class,
					dateTypeAdapter).registerTypeAdapter(java.sql.Date.class,
					dateTypeAdapter).create();
		}
	}

	private GsonUtil() {
	}

	public static Gson getGson() {
		return gson;
	}

}
