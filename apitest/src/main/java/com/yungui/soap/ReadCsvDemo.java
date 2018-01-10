package com.yungui.soap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReadCsvDemo {

	public static void main(String[] args) throws Exception {
		InputStream is = ReadCsvDemo.class.getClassLoader().getResourceAsStream("data.csv");//��classpath·����ȡcsv�ļ�
		InputStreamReader reader = new InputStreamReader(is);//���ֽ���ת��Ϊ�ַ���(��Ϊ�ļ�������)
		BufferedReader buf = new BufferedReader(reader);//��������buffer������ַ����������ȡ��������
		String line = buf.readLine();//������һ��
		while((line=buf.readLine())!=null) {
			String[] datas = line.split(",");
			System.out.println(Arrays.toString(datas));
		}
		
		buf.close();
		reader.close();
		is.close();
	}

}
