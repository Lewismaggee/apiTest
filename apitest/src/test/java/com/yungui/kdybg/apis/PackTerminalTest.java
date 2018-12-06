package com.yungui.kdybg.apis;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.entity.PackTerminalResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 占柜门
 * @author lewis
 *
 */
public class PackTerminalTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="packTerminalTestData",dataProviderClass=ApiData.class)
	public void testPackTerminal(String out_trade_no,String terminalcode) {
		String interfaceName = "com.yung.kdybg.service.TerminalPostmanPackDubboService";
		String methodName = "packTerminal" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("out_trade_no", out_trade_no);
		List<String> terminalCodes = new ArrayList();
		terminalCodes.add(terminalcode);
		paramMap.put("terminalCodes", terminalCodes);
		request.setRequestBody(paramMap);
		
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		PackTerminalResult packterminalresult = jsresult.toJavaObject(jsresult, PackTerminalResult.class);
		System.out.println("java 对象取值："+packterminalresult.getStatus());
		String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(packterminalresult.getStatus())) {
	    	assertEquals(successExpected, packterminalresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(packterminalresult.getStatus())) {
	    	assertEquals(errorExpected, packterminalresult.getStatus());
	    	logger.info("失败的用例结果："+packterminalresult.getErrorMsg());
	    }
		logger.info("========result:"+result);
	}
}
