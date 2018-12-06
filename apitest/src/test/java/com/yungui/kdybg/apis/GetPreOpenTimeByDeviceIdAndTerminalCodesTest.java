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
import com.yungui.entity.GetPreOpenTimeByDeviceIdAndTerminalCodesResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 根据网点id,列编号集合 获取预计到期时间
 * @author lewis
 *
 */
public class GetPreOpenTimeByDeviceIdAndTerminalCodesTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider = "getPreOpenTimeByDeviceIdAndTerminalCodesTestData",dataProviderClass = ApiData.class)
	public void testGetPreOpenTime(long deviceid,List<String> terminalCodes) {
		String interfaceName = "com.yung.kdybg.service.TerminalPostmanPackDubboService";
		String methodName = "getPreOpenTimeByDeviceIdAndTerminalCodes" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		
		DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("deviceId",1040L);
	    List<String> terminalCode = new ArrayList();
	    terminalCode.add("B01-10");
	    terminalCode.add("B11-20");
	    terminalCode.add("C01-10");
	    terminalCode.add("C11-20");
//	    terminalCode.add("D01-10");
//	    terminalCode.add("D11-20");
	    
	    paramMap.put("terminalCodes", terminalCode);
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
	    GetPreOpenTimeByDeviceIdAndTerminalCodesResult getpreopentimeresult 
	    	= jsresult.toJavaObject(jsresult, GetPreOpenTimeByDeviceIdAndTerminalCodesResult.class);
	    logger.info("========result:"+jsresult);
	    String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(getpreopentimeresult.getStatus())) {
	    	assertEquals(successExpected, getpreopentimeresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(getpreopentimeresult.getStatus())) {
	    	assertEquals(errorExpected, getpreopentimeresult.getStatus());
	    	logger.info("失败的用例结果："+getpreopentimeresult.getErrorMsg());
	    }
	    
	}
}
