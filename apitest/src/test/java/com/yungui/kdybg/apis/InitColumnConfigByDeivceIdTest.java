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
import com.yungui.entity.InitColumnConfigByDeviceIdForNettyResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * netty配置上送包柜列配置处理
 * @author lewis
 *
 */
public class InitColumnConfigByDeivceIdTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="initColumnConfigByDeviceIdForNettyTestData",dataProviderClass=ApiData.class)
	public void testInitColumnConfigByDeviceId(String deviceid) {
		String interfaceName = "com.yung.kdybg.service.PostmanPackConfigDubboService";
		String methodName = "initColumnConfigByDeviceIdForNettyUploadDeviceConfig" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("deviceId", Long.parseLong(deviceid));
	    
	    request.setRequestBody(paramMap);
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    logger.info("++++++result:s"+result);
	    JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
	    InitColumnConfigByDeviceIdForNettyResult initColumnresult 
	    	= jsresult.toJavaObject(jsresult, InitColumnConfigByDeviceIdForNettyResult.class);
	    String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(initColumnresult.getStatus())) {
	    	assertEquals(successExpected, initColumnresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(initColumnresult.getStatus())) {
	    	assertEquals(errorExpected, initColumnresult.getStatus());
	    	logger.info("失败的用例结果："+initColumnresult.getErrorMsg());
	    }
	}
}
