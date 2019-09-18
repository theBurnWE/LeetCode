package com.shcepp.shdippsvr.oauth.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	private RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(15000)
			.setConnectTimeout(15000)
			.setConnectionRequestTimeout(15000)
			.build();
	private static HttpUtil instance = null;
	public static HttpUtil getInstance() {
		if (instance == null) {
			instance = new HttpUtil();
		}
		return instance;
	}
	/**
	 * 发送 post请求
	 * @param httpUrl 地址
	 */
	public String sendHttpPost(String httpUrl) throws Exception {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求（json）
	 * @param httpUrl 地址
	 * @param params 参数(格式:json)
	 */
	public String sendHttpPostJson(String httpUrl, String params) throws Exception {
		final String CONTENT_TYPE_TEXT_JSON = "application/json";
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try{
			httpClient = HttpClients.createDefault();
			String url = httpUrl;
			String js = params;
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
			StringEntity se = new StringEntity(js,"UTF-8");
//			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			se.setContentType(CONTENT_TYPE_TEXT_JSON);
			httpPost.setEntity(se);
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
			logger.info(responseContent);
		}finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				logger.error("error detai is ",e);
			}
		}
		return responseContent;
	}

	/**
	 * 发送 post请求
	 * @param httpUrl 地址
	 * @param params 参数(格式:key1=value1&key2=value2)
	 */
	public String sendHttpPost(String httpUrl, String params) throws Exception {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			//设置参数
			StringEntity stringEntity = new StringEntity(params, "UTF-8");
			stringEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			logger.error("error detai is ",e);
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求
	 * @param httpUrl 地址
	 * @param maps 参数
	 */
	public String sendHttpPost(String httpUrl, Map<String, String> maps) throws Exception {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		// 创建参数队列
		List<NameValuePair> nameValuePairs = new ArrayList<>();
		for (String key : maps.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		} catch (Exception e) {
			logger.error("error detai is ",e);
		}
		return sendHttpPost(httpPost);
	}


	/**
	 * 发送Post请求
	 * @param httpPost
	 * @return
	 */
	private String sendHttpPost(HttpPost httpPost) throws Exception {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				logger.error("error detai is ",e);
			}
		}
		return responseContent;
	}

	/**
	 * 发送 get请求
	 * @param httpUrl
	 */
	public String sendHttpGet(String httpUrl) {
		HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
		return sendHttpGet(httpGet);
	}

	/**
	 * 发送Get请求
	 * @param httpGet
	 * @return
	 */
	private String sendHttpGet(HttpGet httpGet) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpGet.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			logger.error("error detai is ",e);
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				logger.error("error detai is ",e);
			}
		}
		return responseContent;
	}

}

