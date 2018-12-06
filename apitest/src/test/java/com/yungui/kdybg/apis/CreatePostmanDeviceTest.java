package com.yungui.kdybg.apis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.yung.common.DubboRequest;
import com.yungui.dataprovider.ApiData;
import com.yungui.utils.DubboCallbackUtil;

/**
 * 新增包柜网点
 * @author lewis
 *
 */
public class CreatePostmanDeviceTest {
	private static Logger logger = LogManager.getLogger();
	@Test(dataProvider="createPostmanDeviceTestData",dataProviderClass = ApiData.class)
	public void testCreatePostmanDevice(long id,long deviceid,int maxpacknum,int packnum
			,int intendnum,BigDecimal packprice,String operatername,int operatorid,String userate
			,String devicename,String devicecode,String deviceno,String addres,String detailaddress
			,int generation,int ischarge,int dcnums,int regionid,String organname,String organcode
			,String province,int provinceid,String city,int cityid,String area,int areaid,BigDecimal packprice1
			,BigDecimal packprice3,BigDecimal packprice6,BigDecimal packprice12) {
		String interfaceName = "com.yung.kdybg.service.DevicePostmanPackDubboService";
		String methodName = "createPostmanDevice" ;
		String address = "zookeeper://10.1.166.97:2181";
		String version = "1.0.0";
		
		DubboRequest request = new DubboRequest();
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("id",id);
	    paramMap.put("deviceId",deviceid);
	    paramMap.put("maxpacknum",maxpacknum);
	    paramMap.put("packnum",packnum);
	    paramMap.put("intendnum",intendnum);
	    paramMap.put("packprice",packprice);
	    paramMap.put("operatername",operatername);
	    paramMap.put("operatorid",operatorid);
	    paramMap.put("operatedate",new Date());
	    paramMap.put("userate",userate);
	    paramMap.put("devicename",devicename);
	    paramMap.put("devicecode",devicecode);
	    paramMap.put("deviceno",deviceno);
	    paramMap.put("address",addres);
	    paramMap.put("detailAddress",detailaddress);
	    paramMap.put("generation",generation);
	    paramMap.put("ischarge",ischarge);
	    paramMap.put("dc_nums",dcnums);
	    paramMap.put("region_id",regionid);
	    paramMap.put("organName",organname);
	    paramMap.put("organCode",organcode);
	    paramMap.put("province",province);
	    paramMap.put("provinceId",provinceid);
	    paramMap.put("city",city);
	    paramMap.put("cityId",cityid);
	    paramMap.put("area",area);
	    paramMap.put("areaId",areaid);
	    paramMap.put("packprice1",packprice1);
	    paramMap.put("packprice3",packprice3);
	    paramMap.put("packprice6",packprice6);
	    paramMap.put("packprice12",packprice12);
	    request.setRequestBody(paramMap);
	    
	    Object result = DubboCallbackUtil.invoke(interfaceName, methodName, request, address, version);
	    logger.info("========result:"+result);
	}
}
