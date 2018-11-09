package com.yungui.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yungui.entity.Authorization;

public class TestDemo {
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().create();
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		HttpPost post = new HttpPost("http://localhost:8080/pink_api/login");
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("user", "liudao"));
		params.add(new BasicNameValuePair("pwd", "123456"));
		try {
			post.setEntity(new UrlEncodedFormEntity(params));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Authorization auth = new Authorization();
		
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			String respStr = EntityUtils.toString(entity);
			auth = gson.fromJson(respStr, Authorization.class);
			List<Cookie> cookies = cookieStore.getCookies();
			if(cookies.size()>0) {
				for(int i = 0 ; i<cookies.size() ; i++) {
					System.out.println(cookies.get(i));
				}
			}
			System.out.println(respStr);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		HttpPost post2 = new HttpPost("http://localhost:8080/pink_api/sbook");
		List<NameValuePair> params2 = new ArrayList<>();
		params2.add(new BasicNameValuePair("user", "liudao"));
		try {
			post2.setEntity(new UrlEncodedFormEntity(params2));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Header header2 = new BasicHeader("Authorization", "Bearer "+auth.getToken());
		post2.addHeader(header2);
		CloseableHttpResponse response2 = null;
		
		try {
			response2 = httpClient.execute(post2);
			HttpEntity entity2 = response2.getEntity();
			String respStr2 = EntityUtils.toString(entity2);
			System.out.println(respStr2);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
