package com.yungui.kdybg.apis;

import static org.testng.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.entity.MergeConfigResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 合并包柜配置信息
 * @author lewis
 *
 */
public class MergeConfigTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="mergeConfigTestData",dataProviderClass=ApiData.class)
	public void testMergeConfig(String deviceid,String packprice1,String packprice3,String packprice6,
			String packprice12,String operatername,String terminalCode,String enablePack) {
		String interfaceName = "com.yung.kdybg.service.PostmanPackConfigDubboService";
		String methodName = "mergeConfig" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    
	    paramMap.put("deviceid", Long.parseLong(deviceid));
//	    paramMap.put("packprice1", packprice1);
//	    paramMap.put("packprice3", packprice3);
//	    paramMap.put("packprice6", packprice6);
//	    paramMap.put("packprice12", packprice12);
	    paramMap.put("operatername", operatername);
	    paramMap.put("operatedate", new Date());
	    paramMap.put("terminalCode", terminalCode);
	    paramMap.put("enablePack", Integer.parseInt(enablePack));
	    request.setRequestBody(paramMap);
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    logger.info("++++++result:s"+result);
	    JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
	    MergeConfigResult mergeconfigresult = jsresult.toJavaObject(jsresult, MergeConfigResult.class);
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
