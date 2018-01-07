package com.yungui.soap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

public class SoapDemo {
	public static void main(String[] args) throws Exception {
		SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
		SOAPConnection conn = factory.createConnection();
		URL url = new URL("http://localhost:8080/axis2/services/calcService");
		MessageFactory mFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
		MimeHeaders headers = new MimeHeaders();
		headers.addHeader("Content-Type", "application/soap+xml;charset=utf-8");
		String xmlStr = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ser=\"http://service.yungui.com\">\r\n" + 
				"   <soap:Header/>\r\n" + 
				"   <soap:Body>\r\n" + 
				"      <ser:plus>\r\n" + 
				"         <ser:x>20</ser:x>\r\n" + 
				"         <ser:y>30</ser:y>\r\n" + 
				"      </ser:plus>\r\n" + 
				"   </soap:Body>\r\n" + 
				"</soap:Envelope>";
		ByteArrayInputStream bais = new ByteArrayInputStream(xmlStr.getBytes());
		SOAPMessage request = mFactory.createMessage(headers,bais);
		SOAPMessage response = conn.call(request, url);
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		Source source = response.getSOAPPart().getContent();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StreamResult result = new StreamResult(baos);
		tf.transform(source, result);
		System.out.println(baos.toString());
		conn.close();
	}
}
