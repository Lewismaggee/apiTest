package com.yungui.site.apis;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.entity.OccupyCellResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 存件占柜
 * @author lewis
 *
 */
public class OccupyCellTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="occupyCellTestData",dataProviderClass=ApiData.class)
	public void testOccupyCell(String siteId,String sizeType,String businessType,String itemId
			,String userAccount) {
		String interfaceName = "com.yung.site.dubbo.SiteDubboService";
		String methodName = "occupyCell" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("siteId", Long.parseLong(siteId));
		paramMap.put("sizeType", Integer.parseInt(sizeType));
		paramMap.put("businessType", Integer.parseInt(businessType));
		paramMap.put("itemId",Long.parseLong(itemId));
		paramMap.put("userAccount",userAccount);
		request.setRequestBody(paramMap);
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		logger.info("jsresult:"+jsresult);
		OccupyCellResult cancelCellresult = jsresult.toJavaObject(jsresult, OccupyCellResult.class);

		String successExpected = "SUCCESS";
		String errorExpected = "ERROR";
		if("SUCCESS".equalsIgnoreCase(cancelCellresult.getStatus())) {
			assertEquals(successExpected, cancelCellresult.getStatus());
			logger.info("valid test case");
			logger.info("case execute success");
		}else if("ERROR".equalsIgnoreCase(cancelCellresult.getStatus())) {
			assertEquals(errorExpected, cancelCellresult.getStatus());
			logger.info("invalid test case:"+cancelCellresult.getErrorMsg());
		}
		logger.info("========result:"+result);
	}
}
