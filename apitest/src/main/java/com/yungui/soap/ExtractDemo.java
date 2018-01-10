package com.yungui.soap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractDemo {
	public static void main(String[] args) {
		String str = "<soapenv:Envelope xmlns:soapenv=\"http://www.w3.org/2003/05/soap-envelope\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <ns:plusResponse xmlns:ns=\"http://service.yungui.com\">\r\n" + 
				"         <ns:return>30</ns:return>\r\n" + 
				"      </ns:plusResponse>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>";
		Pattern p = Pattern.compile("<ns:return>(.*?)</ns:return>");
		Matcher m = p.matcher(str);
		if(m.find()) {
			System.out.println(m.group(0));//<ns:return>30</ns:return>
			System.out.println(m.group(1));//50
		}
	}
}
