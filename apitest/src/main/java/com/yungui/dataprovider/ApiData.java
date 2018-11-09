package com.yungui.dataprovider;

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
	
	@DataProvider(name="createKdybgOrderData")
	public static Object[][] CreateKdybgOrderData(){
		return new Object[][] {
			{1035L},
			{1041L}
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
