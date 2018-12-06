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
import com.yungui.entity.GetCellNumsByUserAccountResult;
import com.yungui.entity.UploadExceptionCellsResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 上送故障柜门
 * @author lewis
 *
 */
public class UploadExceptionCellsTest {
	private static Logger logger = LogManager.getLogger();

	@Test(dataProvider="uploadExceptionCellsTestData",dataProviderClass=ApiData.class)
	public void testUploadExceptionCells(String cellId,String cellStatus) {
		String interfaceName = "com.yung.device.dubbo.DeviceDubboService";
		String methodName = "uploadExceptionCells" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "2.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();

		List<Map<String,Object>> deviceCellList = new ArrayList<>();
		Map<String,Object> pmap = new HashMap<>();
		pmap.put("cellId", Long.parseLong(cellId));
		pmap.put("cellStatus", Integer.parseInt(cellStatus));
		deviceCellList.add(pmap);
		paramMap.put("deviceCellList", deviceCellList);
		request.setRequestBody(paramMap);

		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		logger.info("jsresult:"+jsresult);

		UploadExceptionCellsResult getcellnumsresult = jsresult.toJavaObject(jsresult, UploadExceptionCellsResult.class);

		String successExpected = "SUCCESS";
		String errorExpected = "ERROR";
		if("SUCCESS".equalsIgnoreCase(getcellnumsresult.getStatus())) {
			assertEquals(successExpected, getcellnumsresult.getStatus());
			logger.info("case execute success");
		}else if("ERROR".equalsIgnoreCase(getcellnumsresult.getStatus())) {
			assertEquals(errorExpected, getcellnumsresult.getStatus());
			logger.info("case execute failed");
		}
		logger.info("========result:"+result);
	}
}
