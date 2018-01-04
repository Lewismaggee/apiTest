package com.yungui.soap;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlainSoap {
	public static void main(String[] args) throws Exception {
//		soapTest1();
		soapTest2();
	}
	
	/**
	 * soap1.1协议
	 * @throws Exception 
	 */
	public static void soapTest1() throws Exception {
		URL url = new URL("http://localhost:8080/axis2/services/calcService");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
		conn.setRequestProperty("SOAPAction", "urn:plus");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		OutputStream out = conn.getOutputStream();
		String msg = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.yungui.com\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <ser:plus>\r\n" + 
				"         <ser:x>10</ser:x>\r\n" + 
				"         <ser:y>20</ser:y>\r\n" + 
				"      </ser:plus>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>";
		out.write(msg.getBytes());
		out.flush();
		out.close();
		InputStream in = conn.getInputStream();
		int c = -1;
		while((c=in.read())!=-1) {
			System.out.print((char)c);
		}
		in.close();
		conn.disconnect();
	}
	
	/**
	 * Soap1.2协议
	 * @throws Exception
	 */
	public static void soapTest2() throws Exception {
		URL url = new URL("http://localhost:8080/axis2/services/calcService");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/soap+xml;charset=utf-8");
//		conn.setRequestProperty("SOAPAction", "urn:plus");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		OutputStream out = conn.getOutputStream();
		String msg = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ser=\"http://service.yungui.com\">\r\n" + 
				"   <soap:Header/>\r\n" + 
				"   <soap:Body>\r\n" + 
				"      <ser:plus>\r\n" + 
				"         <ser:x>20</ser:x>\r\n" + 
				"         <ser:y>30</ser:y>\r\n" + 
				"      </ser:plus>\r\n" + 
				"   </soap:Body>\r\n" + 
				"</soap:Envelope>";
		out.write(msg.getBytes());
		out.flush();
		out.close();
		InputStream in = conn.getInputStream();
		int c = -1;
		while((c=in.read())!=-1) {
			System.out.print((char)c);
		}
		in.close();
		conn.disconnect();
	}
}
