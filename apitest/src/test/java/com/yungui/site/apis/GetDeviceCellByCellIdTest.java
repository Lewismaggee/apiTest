package com.yungui.site.apis;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.entity.HeartBeatResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 查询网点柜门信息
 * @author lewis
 *
 */
public class GetDeviceCellByCellIdTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="getDeviceCellByCellIdTestData",dataProviderClass=ApiData.class)
	public void testGetDeviceCellByCellId(String cellId) {
		String interfaceName = "com.yung.site.dubbo.SiteDubboService";
		String methodName = "getDeviceCellByCellId" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cellId", Long.parseLong(cellId));
		request.setRequestBody(paramMap);
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		logger.info("jsresult:"+jsresult);
		
		HeartBeatResult heartbeatresult = jsresult.toJavaObject(jsresult, HeartBeatResult.class);

		String successExpected = "SUCCESS";
		String errorExpected = "ERROR";
		if("SUCCESS".equalsIgnoreCase(heartbeatresult.getStatus())) {
			assertEquals(successExpected, heartbeatresult.getStatus());
			logger.info("valid test case");
			logger.info("case execute success");
		}else if("ERROR".equalsIgnoreCase(heartbeatresult.getStatus())) {
			assertEquals(errorExpected, heartbeatresult.getStatus());
			logger.info("invalid test case:"+heartbeatresult.getErrorMsg());
		}
		logger.info("========result:"+result);
	}
}
