package com.yungui.apis;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.utils.HttpPostUtil;

/**
 * 包柜统计查询
 * @author lewis
 *
 */
public class GetChinanetbgStatisticListTest {
	private JSONObject callDubboRequest;
	
	@Test
	public void testUserQuery() {
		HttpPostUtil postUtil = new HttpPostUtil();
		String url = "http://10.1.166.112:8081/consumer/callDubboRequest";
	    JSONObject callDubboRequest = new JSONObject();
	    callDubboRequest.put("interfaceName","com.yung.kdybg.service.ChinanetbgStatisticDataDubboService");
	    callDubboRequest.put("method","getChinanetbgStatisticList");
	    callDubboRequest.put("address","zookeeper://10.1.166.97:2181");
	    callDubboRequest.put("version","1.0.0");
	    DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("startrow",1);
	    paramMap.put("pagesize",10);
//	    Map<String,Object> pMap = new HashMap<String,Object>();
//	    pMap.put("postmanTel", "");
//	    pMap.put("regions", 1005);
//	    paramMap.put("parms", pMap);
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
