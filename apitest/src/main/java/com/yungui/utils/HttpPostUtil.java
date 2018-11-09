package com.yungui.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

public class HttpPostUtil {
	private CloseableHttpClient httpClient;
	private CookieStore cookieStore;
	public HttpPostUtil() {
		cookieStore = new BasicCookieStore();
		httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	}
	
	public String doPost(String url , List<NameValuePair> params, List<Header> headers) {
		HttpPost post = new HttpPost(url);
		String responseStr = null;
		if(params!=null) {
			try {
				post.setEntity(new UrlEncodedFormEntity(params));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if(headers != null) {
			Header[] h = new Header[headers.size()];
			post.setHeaders(headers.toArray(h));
		}
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			responseStr = EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseStr;
	}
	
	public static JSONObject httpPostWithJson(JSONObject jsonObj, String url) {
	    String responseTxt = "";
	    HttpPost post = null;
	    try {
	      HttpClient httpClient = HttpClients.createDefault();
	      // 设置超时时间，单位是秒
	      RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(3000)
	          .setConnectTimeout(3000)
	          .setConnectionRequestTimeout(3000).build();
	      post = new HttpPost(url);
	      // 构造消息头
	      post.setHeader("Content-type", "application/json; charset=utf-8");
	      post.setHeader("Connection", "Close");
	      // 构建消息实体
	      StringEntity entity = new StringEntity(jsonObj.toString(), Charset.forName("UTF-8"));
	      entity.setContentEncoding("UTF-8");
	      // 发送Json格式的数据请求
	      entity.setContentType("application/json");
	      post.setEntity(entity);
	      HttpResponse response = httpClient.execute(post);
	      // 检验返回码
	      int statusCode = response.getStatusLine().getStatusCode();
//	      System.out.println("================response:"+response.toString());
	      if (statusCode == HttpStatus.SC_OK) {
	        responseTxt = EntityUtils.toString(response.getEntity(),Charset.forName("UTF-8"));
	      }
	    } catch (Exception e) {
	      responseTxt = e.toString();
	    } finally {
	      if (post != null) {
	        try {
	          post.releaseConnection();
	          Thread.sleep(500);
	        } catch (InterruptedException e) {
	          responseTxt = e.toString();
	        }
	      }
	    }
	    return JSONObject.parseObject(responseTxt);
	  }
	
//	public String doPost(String url,JSONObject jsonParam) {
//		
//	}
	
}
