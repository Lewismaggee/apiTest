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
import com.yungui.entity.GetDeviceIntendListResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 分页查询用户想要的包柜列表
 * @author lewis
 *
 */
public class GetDeviceIntendListTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="getDeviceIntendListTestData",dataProviderClass=ApiData.class)
	public void testGetDeviceIntendList(String startrow,String pagesize) {
		String interfaceName = "com.yung.kdybg.service.DeviceIntendDubboService";
		String methodName = "getDeviceIntendList" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("startrow", Integer.parseInt(startrow));
	    paramMap.put("pagesize", Integer.parseInt(pagesize));
	    
	    request.setRequestBody(paramMap);
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    logger.info("++++++result:s"+result);
	    
	    JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
	    GetDeviceIntendListResult getresultintendlistresult 
	    	= jsresult.toJavaObject(jsresult, GetDeviceIntendListResult.class);
	    String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(getresultintendlistresult.getStatus())) {
	    	assertEquals(successExpected, getresultintendlistresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(getresultintendlistresult.getStatus())) {
	    	assertEquals(errorExpected, getresultintendlistresult.getStatus());
	    	logger.info("失败的用例结果："+getresultintendlistresult.getErrorMsg());
	    }
	}
}
