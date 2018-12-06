package com.yungui.site.apis;

import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * 查询包柜柜门
 * @author lewis
 *
 */
public class GetDevicePackCellTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="getDevicePackCellTestData",dataProviderClass=ApiData.class)
	public void testGetDevicePackCell(String cellId,String siteId,String deviceId,String postmanId
			,String postmantel,String expireDate,String outTradeNo) {
		String interfaceName = "com.yung.site.dubbo.SiteDubboService";
		String methodName = "getDevicePackCell" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cellId", Long.parseLong(cellId));
		paramMap.put("siteId", Long.parseLong(siteId));
		paramMap.put("deviceId", Long.parseLong(deviceId));
		paramMap.put("postmanId", Long.parseLong(postmanId));
		paramMap.put("postmanTel", postmantel);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			paramMap.put("expireDate", sdf.parse(expireDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		paramMap.put("outTradeNo", outTradeNo);
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
