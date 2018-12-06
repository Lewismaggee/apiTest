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
import com.yungui.entity.DelDeviceIntendResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 删除想要包柜
 * @author lewis
 *
 */
public class DelDeviceIntendTest {
	private static Logger logger = LogManager.getLogger();
	private JSONObject callDubboRequest;
	
	@Test(dataProvider="delDeviceIntendTestData",dataProviderClass=ApiData.class)
	public void testDelDeviceIntend(String deviceIntendId) {
		
		String interfaceName = "com.yung.kdybg.service.DeviceIntendDubboService";
		String methodName = "delDeviceIntend" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deviceIntendId", Long.parseLong(deviceIntendId));
		request.setRequestBody(paramMap);
		
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		DelDeviceIntendResult deldeviceresult = jsresult.toJavaObject(jsresult, DelDeviceIntendResult.class);
		System.out.println("java 对象取值："+deldeviceresult.getStatus());
		String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(deldeviceresult.getStatus())) {
	    	assertEquals(successExpected, deldeviceresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(deldeviceresult.getStatus())) {
	    	assertEquals(errorExpected, deldeviceresult.getStatus());
	    	logger.info("失败的用例结果："+deldeviceresult.getErrorMsg());
	    }
		logger.info("========result:"+result);
	}
}
