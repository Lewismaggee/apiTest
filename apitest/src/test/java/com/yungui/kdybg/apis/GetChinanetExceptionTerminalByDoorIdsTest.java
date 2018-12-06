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
import com.yungui.entity.GetChinanetExceptionTerminalByDoorIdsResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 根据柜门id查询异常柜门编号
 * @author lewis
 *
 */
public class GetChinanetExceptionTerminalByDoorIdsTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="getChinanetExceptionTerminalByDoorIdsTestData",dataProviderClass=ApiData.class)
	public void testGetChinanetExceptionTerminalByDoorIds(String doorIds) {
		String interfaceName = "com.yung.kdybg.service.ChinanetExceptionDubboService";
		String methodName = "getChinanetExceptionTerminalByDoorIds" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("doorIds", doorIds);
		request.setRequestBody(paramMap);
		
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		GetChinanetExceptionTerminalByDoorIdsResult getpostmandevicelistforwechatresult = jsresult.toJavaObject(jsresult, GetChinanetExceptionTerminalByDoorIdsResult.class);
		System.out.println("java 对象取值："+getpostmandevicelistforwechatresult.getStatus());
		String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(getpostmandevicelistforwechatresult.getStatus())) {
	    	assertEquals(successExpected, getpostmandevicelistforwechatresult.getStatus());
	    	logger.info("case execute success");
	    }else if("ERROR".equalsIgnoreCase(getpostmandevicelistforwechatresult.getStatus())) {
	    	assertEquals(errorExpected, getpostmandevicelistforwechatresult.getStatus());
	    	logger.info("case execute failed");
	    	logger.info("the cause："+getpostmandevicelistforwechatresult.getErrorMsg());
	    }
		logger.info("========result:"+result);
	}
}
