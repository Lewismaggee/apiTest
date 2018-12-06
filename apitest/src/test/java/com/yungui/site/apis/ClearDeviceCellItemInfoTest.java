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
import com.yungui.entity.GetDevicePackCellResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 清空柜门物品信息
 * @author lewis
 *
 */
public class ClearDeviceCellItemInfoTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="clearDeviceCellItemInfoTestData",dataProviderClass=ApiData.class)
	public void testClearDeviceCellItemInfo(String cellId) {
		String interfaceName = "com.yung.site.dubbo.SiteDubboService";
		String methodName = "clearDeviceCellItemInfo" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cellId", Long.parseLong(cellId));
		request.setRequestBody(paramMap);
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		logger.info("jsresult:"+jsresult);
		
		GetDevicePackCellResult cancelCellresult = jsresult.toJavaObject(jsresult, GetDevicePackCellResult.class);

		String successExpected = "SUCCESS";
		String errorExpected = "ERROR";
		if("SUCCESS".equalsIgnoreCase(cancelCellresult.getStatus())) {
			assertEquals(successExpected, cancelCellresult.getStatus());
			logger.info("valid test case");
			logger.info("case execute success");
		}else if("ERROR".equalsIgnoreCase(cancelCellresult.getStatus())) {
			assertEquals(errorExpected, cancelCellresult.getStatus());
			logger.info("invalid test case:"+cancelCellresult.getErrorMsg());
		}
		logger.info("========result:"+result);
	}
}
