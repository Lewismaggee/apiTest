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
}
