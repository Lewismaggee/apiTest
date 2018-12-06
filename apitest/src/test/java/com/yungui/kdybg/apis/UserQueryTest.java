package com.yungui.kdybg.apis;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.entity.UserQueryResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 根据用户id查询用户
 * @author lewis
 *
 */
public class UserQueryTest {
	private static Logger logger = LogManager.getLogger();
	private JSONObject callDubboRequest;
	
	@Test(dataProvider="userQueryTestData",dataProviderClass=ApiData.class)
	public void testUserQuery(int userid) {
		String interfaceName = "com.yung.userquery.dubbo.UserQueryDubboService";
	    String methodName = "getUserByUserId";
	    String address = "zookeeper://10.1.166.97:2181";
	    String version = "1.0.0";
	    DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("userId",Integer.valueOf(userid));
	    request.setRequestBody(paramMap);
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
//		HttpPostUtil postUtil = new HttpPostUtil();
//		String url = "http://10.1.166.112:8081/consumer/callDubboRequest";
//	    JSONObject callDubboRequest = new JSONObject();
//	    callDubboRequest.put("interfaceName","com.yung.userquery.dubbo.UserQueryDubboService");
//	    callDubboRequest.put("method","getUserByUserId");
//	    callDubboRequest.put("address","zookeeper://10.1.166.97:2181");
//	    callDubboRequest.put("version","1.0.0");
//	    DubboRequest request = new DubboRequest();
//	    Map<String, Object> paramMap = new HashMap<String, Object>();
//	    paramMap.put("userId",74);
//	    request.setRequestBody(paramMap);
//	    callDubboRequest.put("dubboRequest",request);
	    JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
	    UserQueryResult userqueryresult = jsresult.toJavaObject(jsresult, UserQueryResult.class);
	    logger.info("============="+jsresult);
	    System.out.println("java 对象取值："+userqueryresult.getStatus());
	    String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(userqueryresult.getStatus())) {
	    	assertEquals(successExpected, userqueryresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(userqueryresult.getStatus())) {
	    	assertEquals(errorExpected, userqueryresult.getStatus());
	    	logger.info("失败的用例结果："+userqueryresult.getErrorMsg());
	    }
	}
}
