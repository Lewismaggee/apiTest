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
import com.yungui.entity.GetPostmanDeviceListForWechatResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 查询可包柜网点
 * @author lewis
 *
 */
public class GetPostmanDeviceListForWechatTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="getPostmanDeviceListForWechatTestData",dataProviderClass=ApiData.class)
	public void testGetPostmanDeviceListForWechat(String startRow,String pageSize,String deviceno
			,String devicecode,String devicename,String regions,String generation,String packprice
			,String monthyear,String ischarge) {
		String interfaceName = "com.yung.kdybg.service.DevicePostmanPackDubboService";
		String methodName = "getPostmanDeviceListForWechat" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startRow", Integer.parseInt(startRow));
		paramMap.put("pageSize", Integer.parseInt(pageSize));
		paramMap.put("deviceno", deviceno);
		paramMap.put("devicecode", devicecode);
		paramMap.put("devicename", devicename);
		paramMap.put("regions", regions);
		paramMap.put("generation", Integer.parseInt(generation));
		paramMap.put("packprice", Double.parseDouble(packprice));
		paramMap.put("monthyear", monthyear);
		paramMap.put("ischarge", ischarge);
		Map<String,Object> searchDto = new HashMap();
		searchDto.put("", "");
		paramMap.put("searchDto", searchDto);
		request.setRequestBody(paramMap);
		
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		
		GetPostmanDeviceListForWechatResult getpostmandevicelistforwechatresult = jsresult.toJavaObject(jsresult, GetPostmanDeviceListForWechatResult.class);
		System.out.println("java 对象取值："+getpostmandevicelistforwechatresult.getStatus());
		String successExpected = "SUCCESS";
	    String errorExpected = "ERROR";
	    if("SUCCESS".equalsIgnoreCase(getpostmandevicelistforwechatresult.getStatus())) {
	    	assertEquals(successExpected, getpostmandevicelistforwechatresult.getStatus());
	    	logger.info("case execute success");
	    }else if("ERROR".equalsIgnoreCase(getpostmandevicelistforwechatresult.getStatus())) {
	    	assertEquals(errorExpected, getpostmandevicelistforwechatresult.getStatus());
	    	logger.info("case execute failed");
	    	logger.info("the cause："+getpostmandevicelistforwechatresult.getErrorMsg());
	    }
		logger.info("========result:"+result);
	}
}
