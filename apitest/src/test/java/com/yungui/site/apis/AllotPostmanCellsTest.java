package com.yungui.site.apis;

import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.entity.GetDevicePackCellResult;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 分配包柜列
 * @author lewis
 *
 */
public class AllotPostmanCellsTest {
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="alltoPostmanCellsTestData",dataProviderClass=ApiData.class)
	public void testAllotPostmanCells(String siteId,String rowNum,String expireDate
			,String userAccount,String postmanId,String outTradeNo,String terminalCodes) {
		String interfaceName = "com.yung.site.dubbo.SiteDubboService";
		String methodName = "allotPostmanCells" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		DubboRequest request = new DubboRequest();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("siteId", Long.parseLong(siteId));
		paramMap.put("rowNum", Integer.parseInt(rowNum));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			paramMap.put("expireDate", sdf.parse(expireDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		paramMap.put("userAccount", userAccount);
		paramMap.put("postmanId", Long.parseLong(postmanId));
		paramMap.put("outTradeNo", outTradeNo);
		List<String> terminalcodes = new ArrayList<>();
		terminalcodes.add(terminalCodes);
		paramMap.put("terminalCodes", terminalcodes);
		request.setRequestBody(paramMap);
		//device_pack_cell
		Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
		JSONObject jsresult = (JSONObject) JSONObject.toJSON(result);
		logger.info("jsresult:"+jsresult);
		GetDevicePackCellResult cancelCellresult = jsresult.toJavaObject(jsresult, GetDevicePackCellResult.class);
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
