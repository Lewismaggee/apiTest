package com.yungui.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yungui.dataprovider.ApiData;
import com.yungui.utils.HttpPostUtil;

public class RegisterAuthentication {
	private HttpPostUtil postUtil;
	
	
	
	@Test(dataProvider="registerAuthentication",dataProviderClass=ApiData.class)
	public void registerAndAuthentication(String image,String back,String handopenid,String idnumber
			,String realname,String expcompanyid,String cityid,String provinceid,String unionid
			,String password,String tel) {
		postUtil = new HttpPostUtil();
		Gson gson = new GsonBuilder().create();
		String url = "http://10.1.166.214:8080/EboxCI/wechat/v2/registerAndAuthentication";
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("image", image));
		params.add(new BasicNameValuePair("back", back));
		params.add(new BasicNameValuePair("handopenid", handopenid));
		params.add(new BasicNameValuePair("idnumber", idnumber));
		params.add(new BasicNameValuePair("realname", realname));
		params.add(new BasicNameValuePair("expcompanyid", expcompanyid));
		params.add(new BasicNameValuePair("cityId", cityid));
		params.add(new BasicNameValuePair("proveinceId", provinceid));
		params.add(new BasicNameValuePair("unionid", unionid));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("tel", tel));
//		params.add(new BasicNameValuePair("id", "1"));
//		Header header = new BasicHeader("Authorization", "Bearer "+authorization.getToken());
//		List<Header> headers = new ArrayList<>();
//		headers.add(header);
		String responseStr = postUtil.doPost(url, params, null);
		System.out.println("----------------------"+responseStr);
//		SearchBookResult result = gson.fromJson(responseStr, SearchBookResult.class);
//		assertEquals(result.getCount(), count);
	}
}
