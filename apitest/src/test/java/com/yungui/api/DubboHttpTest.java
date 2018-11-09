package com.yungui.api;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;

public class DubboHttpTest {
	private JSONObject callDubboRequest;
	
	@Test
	public void testDubboHttp() {
//	    String url = "http://127.0.0.1:8081/consumer/callDubboRequest";
		String url = "http://10.1.166.112:8081/consumer/callDubboRequest";
	    JSONObject callDubboRequest = new JSONObject();
	    callDubboRequest.put("interfaceName","com.yung.userquery.dubbo.UserQueryDubboService");
	    callDubboRequest.put("method","getUserByUserId");
	    callDubboRequest.put("address","zookeeper://10.1.166.97:2181");
	    callDubboRequest.put("version","1.0.0");
	    DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("userId",74);
	    request.setRequestBody(paramMap);
	    callDubboRequest.put("dubboRequest",request);
	    System.out.println("callDubboRequest:" + callDubboRequest.toJSONString());
	    System.out.println(httpPostWithJson(callDubboRequest,url).get("result"));
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
	      System.out.println("================response:"+response.toString());
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
}
