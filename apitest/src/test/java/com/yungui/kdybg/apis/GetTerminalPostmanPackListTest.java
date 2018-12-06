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
import com.yungui.entity.GetTerminalPostmanPackListResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 按条件查询网点包柜列列表
 * @author Administrator
 *
 */
public class GetTerminalPostmanPackListTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="getTerminalPostmanPackListTestData",dataProviderClass=ApiData.class)
	public void testGetTerminalPostmanPackList(String startrow,String pagesize,String deviceId) {
		String interfaceName = "com.yung.kdybg.service.TerminalPostmanPackDubboService";
		String methodName = "getTerminalPostmanPackList" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("startrow", Integer.parseInt(startrow));
	    paramMap.put("pagesize", Integer.parseInt(pagesize));
	    paramMap.put("deviceId", Long.parseLong(deviceId));
	    request.setRequestBody(paramMap);
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    logger.info("++++++result:s"+result);
	    JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
	    GetTerminalPostmanPackListResult getterminalpostmanpacklsitresult = jsresult.toJavaObject(jsresult, GetTerminalPostmanPackListResult.class);
	    String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(getterminalpostmanpacklsitresult.getStatus())) {
	    	assertEquals(successExpected, getterminalpostmanpacklsitresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(getterminalpostmanpacklsitresult.getStatus())) {
	    	assertEquals(errorExpected, getterminalpostmanpacklsitresult.getStatus());
	    	logger.info("失败的用例结果："+getterminalpostmanpacklsitresult.getErrorMsg());
	    }
	}
}
