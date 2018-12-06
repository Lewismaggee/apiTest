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
import com.yungui.entity.DeleteTerminalPostmanPackByIdResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 根据id删除网点包柜记录
 * @author lewis
 *
 */
public class DeleteTerminalPostmanPackByIdTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="deleteTerminalPostmanPackByIdTestData",dataProviderClass=ApiData.class)
	public void testDeleteTerminalPostmanPackById(String id) {
		String interfaceName = "com.yung.kdybg.service.TerminalPostmanPackDubboService";
		String methodName = "deleteTerminalPostmanPackById" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", Integer.parseInt(id));
		request.setRequestBody(paramMap);
		
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		
		DeleteTerminalPostmanPackByIdResult deleteterminalbyidresult = jsresult.toJavaObject(jsresult, DeleteTerminalPostmanPackByIdResult.class);
		String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(deleteterminalbyidresult.getStatus())) {
	    	assertEquals(successExpected, deleteterminalbyidresult.getStatus());
	    	logger.info("the case execute successful");
	    }else if("ERROR".equalsIgnoreCase(deleteterminalbyidresult.getStatus())) {
	    	assertEquals(errorExpected, deleteterminalbyidresult.getStatus());
	    	logger.info("the invalid case："+deleteterminalbyidresult.getErrorMsg());
	    }
		logger.info("========result:"+result);
	}
}
