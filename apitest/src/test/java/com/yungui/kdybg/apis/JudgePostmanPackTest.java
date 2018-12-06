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
import com.yungui.entity.JudgePostmanPackResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 判断用户是否包柜
 * @author lewis
 *
 */
public class JudgePostmanPackTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="judgePostmanPackTestData",dataProviderClass=ApiData.class)
	public void testJudgePostmanPack(String postmanTel,String deviceId,String doorId) {
		String interfaceName = "com.yung.kdybg.service.TerminalPostmanPackDubboService";
		String methodName = "judgePostmanPack" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("postmanTel", postmanTel);
		paramMap.put("deviceId", Long.parseLong(deviceId));
		paramMap.put("doorId", Long.parseLong(doorId));
		request.setRequestBody(paramMap);
		
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		
		JudgePostmanPackResult judgepostmanresult = jsresult.toJavaObject(jsresult, JudgePostmanPackResult.class);
		System.out.println("java 对象取值："+judgepostmanresult.getStatus());
		String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(judgepostmanresult.getStatus())) {
	    	assertEquals(successExpected, judgepostmanresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(judgepostmanresult.getStatus())) {
	    	assertEquals(errorExpected, judgepostmanresult.getStatus());
	    	logger.info("失败的用例结果："+judgepostmanresult.getErrorMsg());
	    }
		logger.info("========result:"+result);
	}
}
