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
import com.yungui.entity.GetTerminalPostmanPackResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 查询终端包柜信息和该快递员在该终端是否有包柜信息
 * @author lewis
 *
 */
public class GetTerminalPostmanPackTest {
	private static Logger logger = LogManager.getLogger();
	private String interfaceName = "com.yung.kdybg.service.TerminalPostmanPackDubboService";
	private String methodName = "getTerminalPostmanPack" ;
	private String address = "zookeeper://10.1.166.97:2181";
	private String version = "1.0.0";
	@Test(dataProvider="getTerminalPostmanPackTestData",dataProviderClass=ApiData.class)
	public void testGetTerminalPostmanPack(String deviceCode) {
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deviceCode", deviceCode);
		request.setRequestBody(paramMap);
		
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		
		GetTerminalPostmanPackResult getpostmanpackresult = jsresult.toJavaObject(jsresult, GetTerminalPostmanPackResult.class);
		String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(getpostmanpackresult.getStatus())) {
	    	assertEquals(successExpected, getpostmanpackresult.getStatus());
	    	logger.info("the case execute successful");
	    }else if("ERROR".equalsIgnoreCase(getpostmanpackresult.getStatus())) {
	    	assertEquals(errorExpected, getpostmanpackresult.getStatus());
	    	logger.info("the invalid case："+getpostmanpackresult.getErrorMsg());
	    }
		logger.info("========result:"+result);
	}
	
	/**
	 * 查询该快递员在该终端是否有包柜信息
	 * @param deviceId
	 * @param postmanTel
	 * @param doorId
	 */
	@Test(dataProvider="getTerminalPostmanPackbytelTestData",dataProviderClass=ApiData.class)
	public void testGetTerminalPostmanPack(String deviceId,String postmanTel,String doorId) {
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deviceId", Long.parseLong(deviceId));
		paramMap.put("postmanTel", postmanTel);
		paramMap.put("doorId", Long.parseLong(doorId));
		request.setRequestBody(paramMap);
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		GetTerminalPostmanPackResult getpostmanpackresult = jsresult.toJavaObject(jsresult, GetTerminalPostmanPackResult.class);
		String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(getpostmanpackresult.getStatus())) {
	    	assertEquals(successExpected, getpostmanpackresult.getStatus());
	    	logger.info("the case execute successful");
	    }else if("ERROR".equalsIgnoreCase(getpostmanpackresult.getStatus())) {
	    	assertEquals(errorExpected, getpostmanpackresult.getStatus());
	    	logger.info("the invalid case："+getpostmanpackresult.getErrorMsg());
	    }
		logger.info("========result:"+result);
	}
}
