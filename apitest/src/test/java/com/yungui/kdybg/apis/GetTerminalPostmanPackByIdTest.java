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
import com.yungui.entity.GetTerminalPostmanPackByIdResult;
import com.yungui.utils.DubboCallbackUtil;
/**
 * 根据id查询网点包柜列
 * @author lewis
 *
 */
public class GetTerminalPostmanPackByIdTest {
	private static Logger logger = LogManager.getLogger();
	@Test(dataProvider="getTerminalPostmanPackByIdTestData",dataProviderClass=ApiData.class)
	public void testGetTerminalPostmanPackById(String id) {
		String interfaceName = "com.yung.kdybg.service.TerminalPostmanPackDubboService";
		String methodName = "getTerminalPostmanPackById" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("id", Integer.parseInt(id));
	    request.setRequestBody(paramMap);
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    logger.info("++++++result:s"+result);
	    JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
	    GetTerminalPostmanPackByIdResult getterminalresult = jsresult.toJavaObject(jsresult, GetTerminalPostmanPackByIdResult.class);
	    String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(getterminalresult.getStatus())) {
	    	assertEquals(successExpected, getterminalresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(getterminalresult.getStatus())) {
	    	assertEquals(errorExpected, getterminalresult.getStatus());
	    	logger.info("失败的用例结果："+getterminalresult.getErrorMsg());
	    }
	}
}
