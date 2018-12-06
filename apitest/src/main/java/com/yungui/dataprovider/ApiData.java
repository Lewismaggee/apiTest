package com.yungui.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.yungui.utils.CsvUtil;

public class ApiData {
	@DataProvider(name="searchBookData")
	public static Object[][] sBookData(){
		return new Object[][] {
			{"liudao",null,null,null,null,5},
			{"liudao","3",null,null,null,1},
			{"liudao",null,"Thinking",null,null,4},
			{"liudao",null,null,null,"100.00",1}
		};
	}
	
	@DataProvider(name="plusTestData")
	public static Object[][] calcData(){
		return CsvUtil.readCsv("plusTestData.csv");
	}
	
	@DataProvider(name="createKdybgOrderTestData")
	public static Object[][] createKdybgOrderData(){
		/*return new Object[][] {
			{1035L},
			{1041L}
		};*/
		
		return CsvUtil.readCsv("createKdybgOrderTestData.csv");
	}
	
	@DataProvider(name="delDeviceIntendTestData")
	public static Object[][] delDeviceIntendData(){
		/*return new Object[][] {
			{1035L},
			{1041L}
		};*/
		return CsvUtil.readCsv("delDeviceIntendTestData.csv");
	}
	
	@DataProvider(name="cancelCellTestData")
	public static Object[][] cancelCellData(){
		return CsvUtil.readCsv("cancelCellTestData.csv");
	}
	
	@DataProvider(name="occupyCellTestData")
	public static Object[][] occupyCellData(){
		return CsvUtil.readCsv("occupyCellTestData.csv");
	}
	
	@DataProvider(name="heartBeatTestData")
	public static Object[][] heartBeatData(){
		return CsvUtil.readCsv("heartBeatTestData.csv");
	}
	
	@DataProvider(name="getDevicePackCellTestData")
	public static Object[][] getDevicePackCellData(){
		return CsvUtil.readCsv("getDevicePackCellTestData.csv");
	}
	
	@DataProvider(name="getSiteSetBySiteIdTestData")
	public static Object[][] getSiteSetBySiteIdData(){
		return CsvUtil.readCsv("getSiteSetBySiteIdTestData.csv");
	}
	
	@DataProvider(name="getDisableCellsByDeviceIdTestData")
	public static Object[][] getDisableCellsByDeviceIdData(){
		return CsvUtil.readCsv("getDisableCellsByDeviceIdTestData.csv");
	}
	
	@DataProvider(name="releaseCellTestData")
	public static Object[][] releaseCellData(){
		return CsvUtil.readCsv("releaseCellTestData.csv");
	}
	
	@DataProvider(name="getSiteExpressPricesTestData")
	public static Object[][] getSiteExpressPricesData(){
		return CsvUtil.readCsv("getSiteExpressPricesTestData.csv");
	}
	
	@DataProvider(name="clearDeviceCellItemInfoTestData")
	public static Object[][] clearDeviceCellItemInfoData(){
		return CsvUtil.readCsv("clearDeviceCellItemInfoTestData.csv");
	}
	
	@DataProvider(name="getChinanetbgStatisticListTestData")
	public static Object[][] getChinanetbgStatisticList(){
		/*return new Object[][] {
			{1,20}
		};*/
		return CsvUtil.readCsv("getChinanetbgStatisticListTestData.csv");
	}
	
	@DataProvider(name="packTerminalTestData")
	public static Object[][] packTerminalData(){
		return CsvUtil.readCsv("packTerminalTestData.csv");
	}
	
	@DataProvider(name="updateIsNotPackedByDeviceIdAndTerminalCodeTestData")
	public static Object[][] updateIsNotPackedByDeviceIdAndTerminalCodeData(){
		/*return new Object[][] {
			{2974L,"B11-20"},
			{1041L,"B01-10"}
		};*/
		
		return CsvUtil.readCsv("updateIsNotPackedByDeviceIdAndTerminalCodeTestData.csv");
	}
	
	@DataProvider(name="mergeConfigTestData")
	public static Object[][] mergeConfigData(){
		/*return new Object[][] {
			{1000L,1.0,2.2,3.3,4.4,"admin","B01-10",1}
		};*/
		
		return CsvUtil.readCsv("mergeConfigTestData.csv");
	}
	
	@DataProvider(name="getTerminalPostmanPackListTestData")
	public static Object[][] getTerminalPostmanPackListData(){
		return CsvUtil.readCsv("getTerminalPostmanPackListTestData.csv");
	}
	
	@DataProvider(name="uploadDeviceConfigTestData")
	public static Object[][] uploadDeviceConfigData(){
		return CsvUtil.readCsv("uploadDeviceConfigTestData.csv");
	}
	
	@DataProvider(name="breakDeviceByDeviceCodeTestData")
	public static Object[][] breakDeviceByDeviceCodeData(){
		return CsvUtil.readCsv("breakDeviceByDeviceCodeTestData.csv");
	}
	
	@DataProvider(name="onlineDeviceByDeviceCodeTestData")
	public static Object[][] onlineDeviceByDeviceCodeData(){
		return CsvUtil.readCsv("onlineDeviceByDeviceCodeTestData.csv");
	}
	
	@DataProvider(name="getDeviceCellByCellIdTestData")
	public static Object[][] getDeviceCellByCellIdData(){
		return CsvUtil.readCsv("getDeviceCellByCellIdTestData.csv");
	}
	
	@DataProvider(name="userQueryTestData")
	public static Object[][] userQueryData(){
		return new Object[][] {
			{74}
		};
	}
	
	
	@DataProvider(name="initColumnConfigByDeviceIdForNettyTestData")
	public static Object[][] initColumnConfigByDeviceIdForNettyData(){
		/*return new Object[][] {
			{1000L},
			{1010L},
			{1020L}
		};*/
		return CsvUtil.readCsv("initColumnConfigByDeviceIdForNettyTestData.csv");
	}
	
	@DataProvider(name="getTerminalPostmanPackByIdTestData")
	public static Object[][] getTerminalPostmanPackByIdData(){
		/*return new Object[][] {
			{1971},
			{1973},
			{1975}
		};*/
		return CsvUtil.readCsv("getTerminalPostmanPackByIdTestData.csv");
	}
	
	@DataProvider(name="getDeviceIntendListTestData")
	public static Object[][] getDeviceIntendListTestData(){
		return CsvUtil.readCsv("getDeviceIntendListTestData.csv");
	}
	
	@DataProvider(name="cancelDevicePackCellTestData")
	public static Object[][] cancelDevicePackCellData(){
		return CsvUtil.readCsv("cancelDevicePackCellTestData.csv");
	}
	
	@DataProvider(name="alltoPostmanCellsTestData")
	public static Object[][] alltoPostmanCellsData(){
		return CsvUtil.readCsv("alltoPostmanCellsTestData.csv");
	}
	
	@DataProvider(name="getCellNumsByUserAccountTestData")
	public static Object[][] getCellNumsByUserAccountData(){
		return CsvUtil.readCsv("getCellNumsByUserAccountTestData.csv");
	}
	
	@DataProvider(name="uploadExceptionCellsTestData")
	public static Object[][] uploadExceptionCellsData(){
		return CsvUtil.readCsv("uploadExceptionCellsTestData.csv");
	}
	
	@DataProvider(name="judgePostmanPackTestData")
	public static Object[][] judgePostmanPackTestData(){
		return CsvUtil.readCsv("judgePostmanPackTestData.csv");
	}
	
	@DataProvider(name="getPostmanPackOutTradeNoTestData")
	public static Object[][] getPostmanPackOutTradeNoData(){
		return CsvUtil.readCsv("getPostmanPackOutTradeNoTestData.csv");
	}
	
	@DataProvider(name="updateSitePostmanPackMonthUseRateTestData")
	public static Object[][] updateSitePostmanPackMonthUseRateData(){
		return CsvUtil.readCsv("updateSitePostmanPackMonthUseRateTestData.csv");
	}
	
	@DataProvider(name="getTerminalPostmanPackTestData")
	public static Object[][] getTerminalPostmanPackData(){
		return CsvUtil.readCsv("getTerminalPostmanPackTestData.csv");
	}
	
	@DataProvider(name="getTerminalPostmanPackbytelTestData")
	public static Object[][] getTerminalPostmanPackbytelData(){
		return CsvUtil.readCsv("getTerminalPostmanPackbytelTestData.csv");
	}
	
	@DataProvider(name="deleteTerminalPostmanPackByIdTestData")
	public static Object[][] deleteTerminalPostmanPackByIdData(){
		return CsvUtil.readCsv("deleteTerminalPostmanPackByIdTestData.csv");
	}
	
	@DataProvider(name="getPostmanDeviceListForWechatTestData")
	public static Object[][] getPostmanDeviceListForWechatData(){
		return CsvUtil.readCsv("getPostmanDeviceListForWechatTestData.csv");
	}
	
	@DataProvider(name="getPostmanDeviceListTestData")
	public static Object[][] getPostmanDeviceListData(){
		return CsvUtil.readCsv("getPostmanDeviceListTestData.csv");
	}
	
	@DataProvider(name="getAllPostmanDevicePackTestData")
	public static Object[][] getAllPostmanDevicePackData(){
		return CsvUtil.readCsv("getAllPostmanDevicePackTestData.csv");
	}
	
	@DataProvider(name="getChinanetExceptionTerminalByDoorIdsTestData")
	public static Object[][] getChinanetExceptionTerminalByDoorIdsData(){
		return CsvUtil.readCsv("getChinanetExceptionTerminalByDoorIdsTestData.csv");
	}
	
	@DataProvider(name="getPostmanPackListTestData")
	public static Object[][] getPostmanPackListData(){
		/*return new Object[][] {
			{"13817564282"},
			{"15506250476"}
		};*/
		return CsvUtil.readCsv("getPostmanPackListTestData.csv");
	}
	
	@DataProvider(name="createPostmanDeviceTestData")
	public static Object[][] createPostmanDeviceData(){
		return new Object[][] {
			{3033L,1000L,5,2,10,1.0,"admin",22,"0.99","国际外包服务大厦","8D11E6A6F4384F023","OJS-NJ-0129302","3栋食堂","南京市鼓楼区汉中门大街301号国际服务外包大厦1幢云柜"
				,2,1,8,4249,"南京区","157","江苏",438,"南京",440,"华东",0,1.11,2.22,3.33,4.44}
		};
	}
	
	@DataProvider(name="getPreOpenTimeByDeviceIdAndTerminalCodesTestData")
	public static Object[][] getPreOpenTimeByDeviceIdAndTerminalCodesData(){
		List<String> termincodes = new ArrayList();
		termincodes.add("B01-10");
		termincodes.add("B11-20");
		termincodes.add("C01-10");
		termincodes.add("C11-20");
		termincodes.add("D01-10");
		termincodes.add("D11-20");
		return new Object[][] {
			{1000L,termincodes}
		};
	}
	
	
	@DataProvider(name = "registerAuthentication")
	public Object[][] getData(){
		
		/**
		 * openid : oRIQ3wDfHbi8-WVmqquI3p5426pc 
		 * idnumber:421125199102085512
		 * realname С���
		 * expcompanyid 171
		 * image 0000000000000000001.png
		 * back  0000000000000000002.png
		 * cityId 1463
		 * proveinceId 1325
		 * unionid ow3aot5FKSzSj0jORaIl1enSr59w
		 * password 111111
		 * tel 13565102354
		 */
		return new Object[][] {
			{"0000000000000000001.png","0000000000000000002.png"
				,"oRIQ3wDfHbi8-WVmqquI3p5426pc","421125199102085512","С���"
				,"171","1463","1325","ow3aot5FKSzSj0jORaIl1enSr59w","111111","13565102354"}
		};
	}
}
