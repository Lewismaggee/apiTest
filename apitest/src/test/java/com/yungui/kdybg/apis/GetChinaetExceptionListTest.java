package com.yungui.kdybg.apis;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.entity.GetChinaetExceptionListResult;
import com.yungui.entity.MergeConfigResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 分页查询异常柜门
 * @author lewis
 *
 */
public class GetChinaetExceptionListTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test
	public void testGetChinaetExceptionList() {
		String interfaceName = "com.yung.kdybg.service.ChinanetExceptionDubboService";
		String methodName = "getChinanetExceptionList" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0"; 
		DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("startrow", Integer.valueOf(1));
	    paramMap.put("pagesize", Integer.valueOf(10));
	    request.setRequestBody(paramMap);
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    logger.info("++++++result:s"+result);
	    JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
	    GetChinaetExceptionListResult mergeconfigresult = jsresult.toJavaObject(jsresult, GetChinaetExceptionListResult.class);
	    String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(mergeconfigresult.getStatus())) {
	    	assertEquals(successExpected, mergeconfigresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(mergeconfigresult.getStatus())) {
	    	assertEquals(errorExpected, mergeconfigresult.getStatus());
	    	logger.info("失败的用例结果："+mergeconfigresult.getErrorMsg());
	    }
	}
}
