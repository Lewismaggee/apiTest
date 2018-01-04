package com.yungui.soap;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
public class HttpClientSoap {
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://localhost:8080/axis2/services/calcService");
//		post.addHeader("Content-Type", "application/soap+xml;charset=utf-8");
		String msg = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ser=\"http://service.yungui.com\">\r\n" + 
				"   <soap:Header/>\r\n" + 
				"   <soap:Body>\r\n" + 
				"      <ser:plus>\r\n" + 
				"         <ser:x>20</ser:x>\r\n" + 
				"         <ser:y>30</ser:y>\r\n" + 
				"      </ser:plus>\r\n" + 
				"   </soap:Body>\r\n" + 
				"</soap:Envelope>";
		StringEntity xmlEntity = 
				new StringEntity(msg,ContentType.create("application/soap+xml", "utf-8"));
		post.setEntity(xmlEntity);
		CloseableHttpResponse response = httpClient.execute(post);
		HttpEntity entity = response.getEntity();
		String responseStr = EntityUtils.toString(entity);
		System.out.println(responseStr);
		response.close();
		httpClient.close();
	}
}
