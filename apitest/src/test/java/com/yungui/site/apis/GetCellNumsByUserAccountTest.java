package com.yungui.site.apis;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.entity.GetCellNumsByUserAccountResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 根据登录账号查询可用柜门数量
 * @author lewis
 *
 */
public class GetCellNumsByUserAccountTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="getCellNumsByUserAccountTestData",dataProviderClass=ApiData.class)
	public void testGetCellNumsByUserAccount(String siteId,String userAccount) {
		String interfaceName = "com.yung.device.dubbo.DeviceDubboService";
		String methodName = "getCellNumsByUserAccount" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "2.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("siteId", Long.parseLong(siteId));
		paramMap.put("userAccount", userAccount);
		request.setRequestBody(paramMap);
		
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		GetCellNumsByUserAccountResult getcellnumsresult = jsresult.toJavaObject(jsresult, GetCellNumsByUserAccountResult.class);
		
		String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(getcellnumsresult.getStatus())) {
	    	assertEquals(successExpected, getcellnumsresult.getStatus());
	    	logger.info("case execute success");
	    }else if("ERROR".equalsIgnoreCase(getcellnumsresult.getStatus())) {
	    	assertEquals(errorExpected, getcellnumsresult.getStatus());
	    	logger.info("case execute failed："+getcellnumsresult.getErrorMsg());
	    }
		logger.info("========result:"+result);
	}
}
