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
import com.yungui.entity.GetAllPostmanDevicePackResult;
import com.yungui.utils.DubboCallbackUtil;
/**
 * 根据条件查询所有包柜网点
 * @author lewis
 *
 */
public class GetAllPostmanDevicePackTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="getAllPostmanDevicePackTestData",dataProviderClass=ApiData.class)
	public void testGetAllPostmanDevicePack(String deviceno,String devicecode,String devicename,String regions
			,String generation,String packprice,String monthyear,String ischarge) {
		String interfaceName = "com.yung.kdybg.service.DevicePostmanPackDubboService";
		String methodName = "getAllPostmanDevicePack" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deviceno", deviceno);
		paramMap.put("devicecode", devicecode);
		paramMap.put("devicename", devicename);
		paramMap.put("regions", regions);
		paramMap.put("generation", Integer.parseInt(generation));
		paramMap.put("packprice", Double.parseDouble(packprice));
		paramMap.put("monthyear", monthyear);
		paramMap.put("ischarge", ischarge);
		request.setRequestBody(paramMap);
		
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		
		GetAllPostmanDevicePackResult getpostmandevicelistforwechatresult = jsresult.toJavaObject(jsresult, GetAllPostmanDevicePackResult.class);
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
