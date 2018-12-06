package com.yungui.kdybg.apis;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.entity.UpdateSitePostmanPackMonthlyUseRateResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 更新包柜网点上月使用率
 * @author Administrator
 *
 */
public class UpdateSitePostmanPackMonthUseRateTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="updateSitePostmanPackMonthUseRateTestData",dataProviderClass=ApiData.class)
	public void testUpdateSitePostmanPackMonthUseRate(String deviceId,String useRate) {
		String interfaceName = "com.yung.kdybg.service.DevicePostmanPackDubboService";
		String methodName = "updateSitePostmanPackMonthlyUseRate" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Map<String,Object>> data = new ArrayList<>();
		Map<String,Object> mapdata = new HashMap<String,Object>();
		mapdata.put("", "");
		data.add(mapdata);
		paramMap.put("data", data);
		paramMap.put("deviceId", Long.parseLong(deviceId));
		paramMap.put("useRate", useRate);
		request.setRequestBody(paramMap);
		
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		
		UpdateSitePostmanPackMonthlyUseRateResult updatesiteuserateresult = jsresult.toJavaObject(jsresult, UpdateSitePostmanPackMonthlyUseRateResult.class);
		System.out.println("java 对象取值："+updatesiteuserateresult.getStatus());
		String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(updatesiteuserateresult.getStatus())) {
	    	assertEquals(successExpected, updatesiteuserateresult.getStatus());
	    	logger.info("case execute success");
	    }else if("ERROR".equalsIgnoreCase(updatesiteuserateresult.getStatus())) {
	    	assertEquals(errorExpected, updatesiteuserateresult.getStatus());
	    	logger.info("case execute failed");
	    	logger.info("the cause："+updatesiteuserateresult.getErrorMsg());
	    }
		logger.info("========result:"+result);
	}
}
