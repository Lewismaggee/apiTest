package com.yungui.kdybg.apis;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.entity.SynchroDataToChinanetExceptionResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 异常柜门同步数据
 * @author lewis
 *
 */
public class SynchroDataToChinanetExceptionTest {
	private static Logger logger = LogManager.getLogger();
	@Test
	public void testSynchroDataToChinanetException() {
		String interfaceName = "com.yung.kdybg.service.ChinanetExceptionDubboService";
		String methodName = "synchroDataToChinanetException" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("", "");
	    request.setRequestBody(paramMap);
	    
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    logger.info("++++++result:s"+result);
	    JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
	    SynchroDataToChinanetExceptionResult synchrdataresult = jsresult.toJavaObject(jsresult, SynchroDataToChinanetExceptionResult.class);
	    String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(synchrdataresult.getStatus())) {
	    	assertEquals(successExpected, synchrdataresult.getStatus());
	    	logger.info("用例执行成功");
	    }else if("ERROR".equalsIgnoreCase(synchrdataresult.getStatus())) {
	    	assertEquals(errorExpected, synchrdataresult.getStatus());
	    	logger.info("失败的用例结果："+synchrdataresult.getErrorMsg());
	    }
	}
}
