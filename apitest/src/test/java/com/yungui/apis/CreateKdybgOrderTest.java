package com.yungui.apis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.utils.DubboCallbackUtil;
import com.yungui.utils.HttpPostUtil;
import org.apache.logging.log4j.Logger;

/**
 * 创建订单 占库存
 * @author lewis
 *
 */
public class CreateKdybgOrderTest {
	private Logger logger;
	private JSONObject callDubboRequest;
	
	@Test(dataProvider="createKdybgOrderData",dataProviderClass=ApiData.class)
	public void testCreateKdybgOrder(long deviceid) {
//		HttpPostUtil postUtil = new HttpPostUtil();
//		String url = "http://10.1.166.112:8081/consumer/callDubboRequest";
//		JSONObject callDubboRequest = new JSONObject();
		String interfaceName = "com.yung.kdybg.service.DevicePostmanPackDubboService";
		String methodName = "createKdybgOrder" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		
//		callDubboRequest.put("interfaceName","com.yung.kdybg.service.DevicePostmanPackDubboService");
//	    callDubboRequest.put("method","createKdybgOrder");
//	    callDubboRequest.put("address","zookeeper://10.1.166.97:2181");
//	    callDubboRequest.put("version","1.0.0");
		
	    DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("wechat","oRIQ3wBa2gNzbs4uoU8Ov2V675l0");
	    paramMap.put("renewals", 0);
	    paramMap.put("userid", 586443L);
	    paramMap.put("deviceid", deviceid);
	    paramMap.put("remind", 0);
	    paramMap.put("packedNum", 1);
	    paramMap.put("payType", 0);
	    paramMap.put("month", 1);
	    paramMap.put("totalFee", 0.01);
	    paramMap.put("userAccount", "13817564282");
	    List<String> terminalcodes = new ArrayList();
	    terminalcodes.add("B11-20");
	    paramMap.put("terminalCodes", terminalcodes);
	    paramMap.put("startTime", new Date());
	    paramMap.put("requestTime", new Date());
	    request.setRequestBody(paramMap);
	    
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    
	    System.out.println("========result:"+result);
	    
	    
//	    callDubboRequest.put("dubboRequest",request);
//	    
//	    JSONObject jsonobject = postUtil.httpPostWithJson(callDubboRequest, url);
//	    System.out.println(jsonobject.get("result"));
	}
}
