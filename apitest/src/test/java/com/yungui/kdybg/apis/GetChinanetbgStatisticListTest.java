package com.yungui.kdybg.apis;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.utils.DubboCallbackUtil;
import com.yungui.utils.HttpPostUtil;

/**
 * 包柜统计查询
 * @author lewis
 *
 */
public class GetChinanetbgStatisticListTest {
	private JSONObject callDubboRequest;
	private static Logger logger = LogManager.getLogger();
	
	@Test(dataProvider="getChinanetbgStatisticListTestData",dataProviderClass=ApiData.class)
	public void testGetChinanetbgStatisticList(String startrow,String pagesize) {
		HttpPostUtil postUtil = new HttpPostUtil();
		String url = "http://10.1.166.112:8081/consumer/callDubboRequest";
	    JSONObject callDubboRequest = new JSONObject();
	    String interfaceName = "com.yung.kdybg.service.ChinanetbgStatisticDataDubboService";
	    String methodName = "getChinanetbgStatisticList";
	    String address = "zookeeper://10.1.166.97:2181";
	    String version = "1.0.0";
	    DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("startrow",Integer.parseInt(startrow));
	    paramMap.put("pagesize",Integer.parseInt(pagesize));
	    request.setRequestBody(paramMap);
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    logger.info("******************"+result);
	}
}
