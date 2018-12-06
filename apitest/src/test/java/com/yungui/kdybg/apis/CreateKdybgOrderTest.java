package com.yungui.kdybg.apis;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.entity.CreateKdybgOrderResult;
import com.yungui.entity.UpdateIsNotPackedByDeviceIdAndTerminalCodeResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 创建订单 占库存
 * @author lewis
 *
 */
public class CreateKdybgOrderTest {
	private static Logger logger = LogManager.getLogger();
	String address = "zookeeper://10.1.166.97:2181";
	String version = "1.0.0";
	DubboRequest request = new DubboRequest();
	@Test(priority=1,dataProvider="createKdybgOrderTestData",dataProviderClass=ApiData.class)
	public void testCreateKdybgOrder(String id,String wechat,String userid,String deviceid,String useraccount,String terminalcodes) {
//		HttpPostUtil postUtil = new HttpPostUtil();
//		String url = "http://10.1.166.112:8081/consumer/callDubboRequest";
//		JSONObject callDubboRequest = new JSONObject();
		String interfaceName = "com.yung.kdybg.service.DevicePostmanPackDubboService";
		String methodName = "createKdybgOrder" ;
		
		
//		callDubboRequest.put("interfaceName","com.yung.kdybg.service.DevicePostmanPackDubboService");
//	    callDubboRequest.put("method","createKdybgOrder");
//	    callDubboRequest.put("address","zookeeper://10.1.166.97:2181");
//	    callDubboRequest.put("version","1.0.0");
		
	    
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("wechat",wechat);
	    paramMap.put("renewals", 0);
	    paramMap.put("userid", Long.parseLong(userid));
	    paramMap.put("deviceid", Long.parseLong(deviceid));
	    paramMap.put("remind", 0);
	    paramMap.put("packedNum", 1);
	    paramMap.put("payType", 0);
	    paramMap.put("packDays", 30);
	    paramMap.put("month", 1);
	    paramMap.put("totalFee", 0.01);
	    paramMap.put("userAccount", useraccount);
	    List<String> terminalCodes = new ArrayList();
	    terminalCodes.add(terminalcodes);
	    paramMap.put("terminalCodes", terminalCodes);
	    /*List<Integer> ids = new ArrayList();
	    ids.add(Integer.parseInt(id));
	    paramMap.put("ids", ids);*/
	    paramMap.put("startTime", new Date());
	    paramMap.put("requestTime", new Date());
	    request.setRequestBody(paramMap);
	    
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
	    CreateKdybgOrderResult createkdybgresult = jsresult.toJavaObject(jsresult, CreateKdybgOrderResult.class);
	    System.out.println("java 对象取值："+createkdybgresult.getStatus());
	    String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(createkdybgresult.getStatus())) {
	    	assertEquals(successExpected, createkdybgresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(createkdybgresult.getStatus())) {
	    	assertEquals(errorExpected, createkdybgresult.getStatus());
	    	logger.info("失败的用例结果："+createkdybgresult.getErrorMsg());
	    }
	    
//	    callDubboRequest.put("dubboRequest",request);
//	    
//	    JSONObject jsonobject = postUtil.httpPostWithJson(callDubboRequest, url);
//	    System.out.println(jsonobject.get("result"));
	}
	
	/**
	 * 释放库存
	 * @param deviceId
	 * @param terminalCode
	 */
	
	@Test(priority = 0,dataProvider="updateIsNotPackedByDeviceIdAndTerminalCodeTestData",dataProviderClass=ApiData.class)
	public  void  testUpdateIsNotPackedByDeviceIdAndTerminalCode(String deviceId,String terminalCode) {
		String interfaceName = "com.yung.kdybg.service.DevicePostmanPackDubboService";
		String methodName = "updateIsNotPackedByDeviceIdAndTerminalCode" ;
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("deviceid", Long.parseLong(deviceId));
	    paramMap.put("terminalCode", terminalCode);
	    request.setRequestBody(paramMap);
	    
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		UpdateIsNotPackedByDeviceIdAndTerminalCodeResult updateresult = jsresult.toJavaObject(jsresult, UpdateIsNotPackedByDeviceIdAndTerminalCodeResult.class);
		System.out.println("java 对象取值："+updateresult.getStatus());
		String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(updateresult.getStatus())) {
	    	assertEquals(successExpected, updateresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(updateresult.getStatus())) {
	    	assertEquals(errorExpected, updateresult.getStatus());
	    	logger.info("失败的用例结果："+updateresult.getErrorMsg());
	    }
		logger.info("========result:"+result);
		
	}
}
