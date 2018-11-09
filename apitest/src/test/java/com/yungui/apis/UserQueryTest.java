package com.yungui.apis;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.entity.UserQueryResult;
import com.yungui.utils.HttpPostUtil;
import static org.testng.Assert.assertEquals;

/**
 * ”√ªß≤È—Ø
 * @author lewis
 *
 */
public class UserQueryTest {
	private JSONObject callDubboRequest;
	
	@Test
	public void testUserQuery() {
		HttpPostUtil postUtil = new HttpPostUtil();
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
	    JSONObject jsonobject = postUtil.httpPostWithJson(callDubboRequest, url);
	    JSONObject result = (JSONObject) jsonobject.get("result");
	    System.out.println("******************"+result.toJSONString());
//	    UserQueryResult uqresult = jsonobject.toJavaObject(result, UserQueryResult.class);
//	    System.out.println("===========result:"+uqresult.getUsers());
	}
}
