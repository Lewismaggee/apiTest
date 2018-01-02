package com.yungui.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpGetUtil {
	private CloseableHttpClient httpClient;
	private CookieStore cookieStore;
	
	public HttpGetUtil() {
		cookieStore = new BasicCookieStore();
		httpClient = HttpClients.custom().build();
	}
	
	public String doGet(String url) {
		HttpGet get = new HttpGet(url);
		String responseStr = null;
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			responseStr = EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseStr;
	}
	
	public static void main(String[] args) {
		String url = "http://www.baidu.com";
		HttpGetUtil getUtil = new HttpGetUtil();
		String resStr = getUtil.doGet(url);
		System.out.println(resStr);
	}
}
