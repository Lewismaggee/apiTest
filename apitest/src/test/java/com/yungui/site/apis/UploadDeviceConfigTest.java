package com.yungui.site.apis;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.entity.HeartBeatResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 配置上送
 * @author lewis
 *
 */
public class UploadDeviceConfigTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="uploadDeviceConfigTestData",dataProviderClass=ApiData.class)
	public void testUploadDeviceConfig(String deviceCode,String cabinetSerialPort,String sizeType
			,String lockAddress,String groupCode,String code,String ypos,String xpos,String cellType
			,String groupAddress,String isUnion,String mainCell) {
		String interfaceName = "com.yung.device.dubbo.DeviceDubboService";
		String methodName = "uploadDeviceConfig" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "2.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deviceCode", deviceCode);
		paramMap.put("cabinetSerialPort", cabinetSerialPort);
		List devicecellList = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("sizeType", Integer.parseInt(sizeType));
		map.put("lockAddress", lockAddress);
		map.put("groupCode", groupCode);
		map.put("code", code);
		map.put("ypos", ypos);
		map.put("xpos", xpos);
		map.put("cellType", cellType);
		map.put("groupAddress", groupAddress);
		map.put("isUnion", Integer.parseInt(isUnion));
		map.put("mainCell", Integer.parseInt(mainCell));
		devicecellList.add(map);
		paramMap.put("deviceCellList", devicecellList);
		request.setRequestBody(paramMap);
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		logger.info("jsresult:"+jsresult);
		
		HeartBeatResult heartbeatresult = jsresult.toJavaObject(jsresult, HeartBeatResult.class);
		
		String successExpected = "SUCCESS";
		String errorExpected = "ERROR";
		if("SUCCESS".equalsIgnoreCase(heartbeatresult.getStatus())) {
			assertEquals(successExpected, heartbeatresult.getStatus());
			logger.info("valid test case");
			logger.info("case execute success");
		}else if("ERROR".equalsIgnoreCase(heartbeatresult.getStatus())) {
			assertEquals(errorExpected, heartbeatresult.getStatus());
			logger.info("invalid test case:"+heartbeatresult.getErrorMsg());
		}
		logger.info("========result:"+result);
	}
}
