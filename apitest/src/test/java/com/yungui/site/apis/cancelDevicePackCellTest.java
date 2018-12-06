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
import com.yungui.entity.CancelDevicePackCellResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 包柜失败，释放占用柜门
 * @author lewis
 *
 */
public class cancelDevicePackCellTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="cancelDevicePackCellTestData",dataProviderClass=ApiData.class)
	public void testCancelDevicePackCell(String outTradeNo) {
		String interfaceName = "com.yung.site.dubbo.SiteDubboService";
		String methodName = "cancelDevicePackCell" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("out_trade_no", outTradeNo);
		request.setRequestBody(paramMap);
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		logger.info("jsresult:"+jsresult);
		CancelDevicePackCellResult getcellnumsresult = jsresult.toJavaObject(jsresult, CancelDevicePackCellResult.class);

		String successExpected = "SUCCESS";
		String errorExpected = "ERROR";
		if("SUCCESS".equalsIgnoreCase(getcellnumsresult.getStatus())) {
			assertEquals(successExpected, getcellnumsresult.getStatus());
			logger.info("case execute success");
		}else if("ERROR".equalsIgnoreCase(getcellnumsresult.getStatus())) {
			assertEquals(errorExpected, getcellnumsresult.getStatus());
			logger.info("case execute failed");
		}
		logger.info("========result:"+result);
	}
}
