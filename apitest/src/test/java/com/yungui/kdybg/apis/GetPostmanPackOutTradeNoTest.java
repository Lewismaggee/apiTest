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
import com.yungui.entity.GetPostmanPackOutTradeNoResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 按订单号查询包柜列列表
 * @author lewis
 *
 */
public class GetPostmanPackOutTradeNoTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="getPostmanPackOutTradeNoTestData",dataProviderClass=ApiData.class)
	public void testGetPostmanPackOutTradeNo(String out_trade_no) {
		String interfaceName = "com.yung.kdybg.service.TerminalPostmanPackDubboService";
		String methodName = "getPostmanPack_Out_trade_no" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("out_trade_no", out_trade_no);
		request.setRequestBody(paramMap);
		
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		
		GetPostmanPackOutTradeNoResult getpostmanouttradenoresult = jsresult.toJavaObject(jsresult, GetPostmanPackOutTradeNoResult.class);
		System.out.println("java 对象取值："+getpostmanouttradenoresult.getStatus());
		String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(getpostmanouttradenoresult.getStatus())) {
	    	assertEquals(successExpected, getpostmanouttradenoresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(getpostmanouttradenoresult.getStatus())) {
	    	assertEquals(errorExpected, getpostmanouttradenoresult.getStatus());
	    	logger.info("失败的用例结果："+getpostmanouttradenoresult.getErrorMsg());
	    }
		logger.info("========result:"+result);
	}
}
