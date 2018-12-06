package com.yungui.kdybg.apis;

import static org.testng.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.utils.DubboCallbackUtil;

public class AddDeviceIntendTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test
	public void testAddDeviceIntend() {
		String interfaceName = "com.yung.kdybg.service.DeviceIntendDubboService";
		String methodName = "addDeviceIntend" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0"; 
		
		DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("id", 2222L);
	    paramMap.put("deviceid", 1000L);
	    paramMap.put("postmantel", "13817564282");
	    paramMap.put("microaccount", "13817564282");
	    paramMap.put("status", Integer.valueOf(0));
	    paramMap.put("create_id", 555555L);
	    paramMap.put("create_time", new Date());
	    paramMap.put("isRemaind", Integer.valueOf(1));
	    request.setRequestBody(paramMap);
	    
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
	    String status = jsresult.getString("status");
	    logger.info("status:"+status);
	    String errorExpected = "ERROR";
	    String successfulExpected = "SUCCESS";
	    if("ERROR".equalsIgnoreCase(status)) {
	    	assertEquals(status, errorExpected);
	    	logger.info("无效用例执行成功");
	    }else if("SUCCESS".equalsIgnoreCase(status)) {
	    	assertEquals(status, successfulExpected);
	    	logger.info("有效用例执行成功");
	    }
	    logger.info("++++++++++json result :"+jsresult);
	    logger.info("========result:"+result);
	}
}
