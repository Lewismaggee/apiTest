package com.yungui.api;

import static org.testng.Assert.assertEquals;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.yungui.dataprovider.ApiData;
import com.yungui.utils.CsvUtil;
import com.yungui.utils.SoapUtil;

public class CalcServiceTest {
	
	/*@DataProvider(name="plusTestData")
	public Object[][] createData(){
		return new Object[][] {
			{"10","20","30"},
			{"40","50","90"},
			{"-10","-20","-30"}
		};
		return CsvUtil.readCsv("plusTestData.csv");
	}*/
	
	@Test(dataProvider="plusTestData",dataProviderClass=ApiData.class)
	public void testPlus(String x, String y, String expected) {
		URL url = null;
		String actual = null;
//		String expected = "50";
		try {
			url = new URL("http://localhost:8080/axis2/services/calcService");
			String xmlStr = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ser=\"http://service.yungui.com\">\r\n" + 
					"   <soap:Header/>\r\n" + 
					"   <soap:Body>\r\n" + 
					"      <ser:plus>\r\n" + 
					"         <ser:x>"+x+"</ser:x>\r\n" + 
					"         <ser:y>"+y+"</ser:y>\r\n" + 
					"      </ser:plus>\r\n" + 
					"   </soap:Body>\r\n" + 
					"</soap:Envelope>";
			SOAPMessage message = SoapUtil.createMessageUsingSOAP1_2(xmlStr);
			SOAPMessage response = SoapUtil.sendRequest(url, message);
//			方法一：通过dom节点提取值
//			actual = SoapUtil.getValueByElementName(response.getSOAPBody(), "ns:return");
//			方法二：通过正则表达式提取值
			String xmlresult = SoapUtil.soapXml2String(response);
			String regex = "<ns:return>(.*?)</ns:return>";
			actual = SoapUtil.regxpExtract(xmlresult, regex, 1, 1);
			System.out.println(actual);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		assertEquals(actual, expected);
		/*SOAPConnectionFactory factory = null;
		SOAPConnection conn = null;
		URL url = null;
		MessageFactory mFactory = null;
		MimeHeaders headers = null;
		SOAPMessage request = null;
		SOAPMessage response = null;
		String actual = "";
		String expected = "50";
		try {
			factory = SOAPConnectionFactory.newInstance();
			conn = factory.createConnection(); 
			url = new URL("http://localhost:8080/axis2/services/calcService");
			mFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
			headers = new MimeHeaders();
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
			request = mFactory.createMessage(headers,bais);
			response = conn.call(request, url);
			SOAPBody body = response.getSOAPBody();
			Iterator<?> iterator =  body.getChildElements();
//			while(iterator.hasNext()) {
//				System.out.println(iterator.next());
//			}
			if(iterator.hasNext()) {
				Object o = iterator.next();
				if(o instanceof SOAPElement) {
					SOAPElement firstChild = (SOAPElement)o;
					SOAPElement returnElement = (SOAPElement)firstChild.getChildElements().next();
					actual = returnElement.getFirstChild().getNodeValue();
					System.out.println(actual);
				}
			}
			conn.close();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		assertEquals(actual, expected);*/
		 
	}
}
