package com.yungui.soap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReadCsvDemo {

	public static void main(String[] args) throws Exception {
		InputStream is = ReadCsvDemo.class.getClassLoader().getResourceAsStream("data.csv");//从classpath路径获取csv文件
		InputStreamReader reader = new InputStreamReader(is);//将字节流转换为字符流(因为文件有中文)
		BufferedReader buf = new BufferedReader(reader);//创建带有buffer缓存的字符流，方便读取整行数据
		String line = buf.readLine();//跳过第一行
		while((line=buf.readLine())!=null) {
			String[] datas = line.split(",");
			System.out.println(Arrays.toString(datas));
		}
		
		buf.close();
		reader.close();
		is.close();
	}

}
