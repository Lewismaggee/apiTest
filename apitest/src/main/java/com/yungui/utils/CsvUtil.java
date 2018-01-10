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
		InputStreamReader reader = new InputStreamReader(is);//���ֽ���ת��Ϊ�ַ���
		BufferedReader bur = new BufferedReader(reader); //����Buffer������ַ����������ȡ��������
		Object[][] obj = null;
		List<Object[]> list = new ArrayList<>();
		try {
			String line = bur.readLine();
			while((line=bur.readLine())!=null) {
				String[] datas = line.split(",");
				list.add(datas); //��һ��ת������ַ�����ӵ�list��
			}
			obj = new Object[list.size()][list.get(0).length]; //ʵ�ʴ����Ķ�ά�����С
			for(int i = 0 ; i<obj.length ; i++) {
				obj[i] = list.get(i); //��obj�ĵ�һά��ֵΪlist�е�ÿ��Ԫ��
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
