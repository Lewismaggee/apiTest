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
import com.yungui.entity.GetPostmanPackListResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 根据快递员查询包柜列表
 * @author lewis
 *
 */
public class GetPostmanPackListTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="getPostmanPackListTestData",dataProviderClass=ApiData.class)
	public void testGetPostmanPackList(String postmanTel) {
		String interfaceName = "com.yung.kdybg.service.TerminalPostmanPackDubboService";
		String methodName = "getPostmanPackList" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("postmanTel", postmanTel);
	    
	    request.setRequestBody(paramMap);
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    logger.info("++++++result:s"+result);
	    JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
	    GetPostmanPackListResult getpostmanpackresult = jsresult.toJavaObject(jsresult, GetPostmanPackListResult.class);
	    String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(getpostmanpackresult.getStatus())) {
	    	assertEquals(successExpected, getpostmanpackresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(getpostmanpackresult.getStatus())) {
	    	assertEquals(errorExpected, getpostmanpackresult.getStatus());
	    	logger.info("失败的用例结果："+getpostmanpackresult.getErrorMsg());
	    }
	}
}
