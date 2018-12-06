package com.yungui.kdybg.apis;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 根据网点id查询包柜网点
 * @author lewis
 *
 */
public class GetByDeviceIdTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="",dataProviderClass=ApiData.class)
	public void testGetByDeviceId(String deviceId) {
		String interfaceName = "com.yung.kdybg.service.DevicePostmanPackDubboService";
		String methodName = "getByDeviceId" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deviceid", Long.parseLong(deviceId));
		request.setRequestBody(paramMap);
		
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		
	}
}
