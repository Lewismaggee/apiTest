package com.yungui.api;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yungui.dataprovider.ApiData;
import com.yungui.entity.Authorization;
import com.yungui.entity.SearchBookError;
import com.yungui.entity.SearchBookResult;
import com.yungui.utils.HttpPostUtil;

public class SearchBookApiTest {
	private HttpPostUtil postUtil;
	private Authorization authorization;
	
	@DataProvider(name = "searchBookData")
	public Object[][] getData(){
		return new Object[][] {
			{"liudao",null,null,null,null,5},
			{"liudao","3",null,null,null,1},
			{"liudao",null,"Thinking",null,null,4},
			{"liudao",null,null,null,"100.00",1}
		};
	}
	
	@BeforeClass
	public void doLogin() {
		Gson gson = new GsonBuilder().create();
		postUtil = new HttpPostUtil();
		String url = "http://localhost:8080/pink_api/login";
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("user", "liudao"));
		params.add(new BasicNameValuePair("pwd", "123456"));
		String responseStr = postUtil.doPost(url, params, null);
		System.out.println(responseStr);
		authorization = gson.fromJson(responseStr, Authorization.class);
	}
	
	@Test(dataProvider="searchBookData",dataProviderClass=ApiData.class)
	public void testSearchBook(String user,String id,String name,String author,String price,int count) {
		Gson gson = new GsonBuilder().create();
		String url = "http://localhost:8080/pink_api/sbook";
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("user", user));
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("author", author));
		params.add(new BasicNameValuePair("price", price));
		params.add(new BasicNameValuePair("user", user));
//		params.add(new BasicNameValuePair("id", "1"));
		Header header = new BasicHeader("Authorization", "Bearer "+authorization.getToken());
		List<Header> headers = new ArrayList<>();
		headers.add(header);
		String responseStr = postUtil.doPost(url, params, headers);
		System.out.println(responseStr);
		SearchBookResult result = gson.fromJson(responseStr, SearchBookResult.class);
		assertEquals(result.getCount(), count);
	}
	
	@Test
	public void testSearchBookWithoutUser() {
		Gson gson = new GsonBuilder().create();
		SearchBookError error = null;
		String url = "http://localhost:8080/pink_api/sbook";
		Header header = new BasicHeader("Authorization", "Bearer "+authorization.getToken());
		List<Header> headers = new ArrayList<>();
		headers.add(header);
		String responseStr = postUtil.doPost(url, null, headers);
		error = gson.fromJson(responseStr, SearchBookError.class);
		assertEquals(error.getErrorCode(), "103");
	}
}
