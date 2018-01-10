package com.yungui.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CsvUtil {
	public static Object[][] readCsv(String csvFileName){
		InputStream is = CsvUtil.class.getClassLoader().getResourceAsStream(csvFileName);
		InputStreamReader reader = new InputStreamReader(is);//将字节流转换为字符流
		BufferedReader bur = new BufferedReader(reader); //创建Buffer缓存的字符流，方便读取整行数据
		Object[][] obj = null;
		List<Object[]> list = new ArrayList<>();
		try {
			String line = bur.readLine();
			while((line=bur.readLine())!=null) {
				String[] datas = line.split(",");
				list.add(datas); //将一行转换后的字符串添加到list中
			}
			obj = new Object[list.size()][list.get(0).length]; //实际创建的二维数组大小
			for(int i = 0 ; i<obj.length ; i++) {
				obj[i] = list.get(i); //将obj的第一维赋值为list中的每个元素
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bur.close();
				reader.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
}
