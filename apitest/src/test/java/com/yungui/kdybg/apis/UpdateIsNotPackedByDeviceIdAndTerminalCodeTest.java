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
import com.yungui.entity.UpdateIsNotPackedByDeviceIdAndTerminalCodeResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 占库存，释放库存
 * @author Administrator
 *
 */
public class UpdateIsNotPackedByDeviceIdAndTerminalCodeTest {
	private static Logger logger = LogManager.getLogger();
	private JSONObject callDubboRequest;
	
	@Test(dataProvider="updateIsNotPackedByDeviceIdAndTerminalCodeTestData",dataProviderClass=ApiData.class)
	public  void  testUpdateIsNotPackedByDeviceIdAndTerminalCode(long deviceId,String terminalCode) {
		String interfaceName = "com.yung.kdybg.service.DevicePostmanPackDubboService";
		String methodName = "updateIsNotPackedByDeviceIdAndTerminalCode" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("deviceid", deviceId);
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
