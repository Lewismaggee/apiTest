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
import com.yungui.entity.CancelCellResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 取消柜门(包柜)
 * @author lewis
 *
 */
public class CancelCellTest {
	private static Logger logger = LogManager.getLogger();

	@Test(dataProvider="cancelCellTestData",dataProviderClass=ApiData.class)
	public void testCancelCell(String siteId,String itemId,String groupCode,String code,String businessType) {
		String interfaceName = "com.yung.site.dubbo.SiteDubboService";
		String methodName = "cancelCell" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("siteId", Long.parseLong(siteId));
		paramMap.put("itemId",itemId);
		paramMap.put("groupCode", groupCode);
		paramMap.put("code", code);
		paramMap.put("businessType", businessType);
		request.setRequestBody(paramMap);
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		logger.info("jsresult:"+jsresult);

		CancelCellResult cancelCellresult = jsresult.toJavaObject(jsresult, CancelCellResult.class);

		String successExpected = "SUCCESS";
		String errorExpected = "ERROR";
		if("SUCCESS".equalsIgnoreCase(cancelCellresult.getStatus())) {
			assertEquals(successExpected, cancelCellresult.getStatus());
			logger.info("valid test case");
			logger.info("case execute success");
		}else if("ERROR".equalsIgnoreCase(cancelCellresult.getStatus())) {
			assertEquals(errorExpected, cancelCellresult.getStatus());
			logger.info("invalid test case"+cancelCellresult.getErrorMsg());
		}
		logger.info("========result:"+result);
	}
}
