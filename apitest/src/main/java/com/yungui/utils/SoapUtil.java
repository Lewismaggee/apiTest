package com.yungui.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

public class SoapUtil {
	/**
	 * ����url��ַ�ͷ��͵���Ϣ����soapЭ�����󣬷���soapӦ��
	 * @param url webservice��endpoint��ַ
	 * @param message soapЭ���
	 * @return soap��Ӧ��
	 */
	public static SOAPMessage sendRequest(URL url , SOAPMessage message) {
		SOAPConnectionFactory factory = null;
		SOAPConnection conn = null;
		SOAPMessage response = null;
		try {
			factory = SOAPConnectionFactory.newInstance();// ʵ����soap���ӹ���
			conn = factory.createConnection();// ��soap���ӹ������󴴽�����
			response = conn.call(message, url);// ��������������ȴ���Ӧ
		} catch (UnsupportedOperationException | SOAPException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * ����xml������������soapЭ���
	 * @param xmlStr xml����
	 * @return soapЭ���
	 */
	public static SOAPMessage createMessageUsingSOAP1_2(String xmlStr) {
		MessageFactory mFactory = null;
		SOAPMessage message = null;
		try {
			mFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);// ��soap1.2Э�鴴����Ϣ����
			MimeHeaders headers = new MimeHeaders();// Э��ͷ���󣬿յļ���
			ByteArrayInputStream bais = new ByteArrayInputStream(xmlStr.getBytes());// ��xml���ݰ�װΪ������
			message = mFactory.createMessage(headers,bais);//ʹ����Ϣ��������Э���
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	/**
	 * �����ṩ��Ԫ�ؽڵ����֣���ǩ��������ָ���ĸ��ڵ㿪ʼ���еݹ��ѯ
	 * @param rootElement ָ����xml���ݸ��ڵ�
	 * @param elementName Ҫ���ҵ�Ԫ�ؽڵ�����
	 * @return �������Ƶ�Ԫ�ؽڵ�
	 */
	@SuppressWarnings("unchecked")
	public static SOAPElement getElementByElementName(SOAPElement rootElement , String elementName) {
		if(rootElement.getElementName().getQualifiedName().equals(elementName)) {// ����ø��ڵ�����־���ָ����������ֱ�ӷ���
			return rootElement;
		}else {
			Iterator<SOAPElement> iterator = rootElement.getChildElements();// ��ȡ���ڵ��������Ԫ�ؽڵ�
			List<SOAPElement> elements = new ArrayList<>();
			while(iterator.hasNext()) {// ����ÿһ���ӽڵ�
				elements.add(getElementByElementName(iterator.next(), elementName));// �ݹ��ѯ
			}
			return elements.get(0); //���ص�һ������������Ԫ�ؽڵ�
		}
	}
	
	/**
	 * �����ṩ��Ԫ�ؽڵ����֣���ǩ��������xml�����в�ѯ���ýڵ��ֵ
	 * @param rootElement ָ����xml���ݸ��ڵ�
	 * @param elementName Ҫ���ҵ�Ԫ�ؽڵ�����
	 * @return �������Ƶ�Ԫ�ؽڵ��ֵ
	 */
	public static String getValueByElementName(SOAPElement rootElement, String elementName) {
		SOAPElement element = getElementByElementName(rootElement, elementName); // �ҵ�����Ҫ��Ľڵ�
		return element.getFirstChild().getNodeValue(); //���ؽڵ��ֵ
	}
	
	/**
	 * ��SOAPMessage�е�����xml����ת��Ϊ�ַ���
	 * @param message ��Ҫת����SOAPMessage����
	 * @return xml��Ӧ���ַ���
	 */
	public static String soapXml2String(SOAPMessage message) {
		TransformerFactory tff = TransformerFactory.newInstance();// ����ת������
		Transformer tf = null;
		String str = null;
		try {
			tf = tff.newTransformer();// ʹ��ת����������ת��������
			Source source = message.getSOAPPart().getContent();// ��Ҫת����Դ��ָ��ΪSOAPMessage��part���ֵ�����
			ByteArrayOutputStream baos = new ByteArrayOutputStream();// �����ֽ���������������ڱ���ת���Ľ��
			StreamResult result = new StreamResult(baos);// ���������������Ϊ��һ���������ֽ�������
			tf.transform(source, result);// ʹ��ת������ʼת��
			str = baos.toString();// �ֽ�������������������ת��Ϊ�ַ���
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * �����ṩ��������ʽ����ָ���ַ����г�ȡ��Ҫ������
	 * @param input ָ���ַ���
	 * @param regexp ���з����﷨(С����)��������ʽ
	 * @param index �ڼ���ƥ���ʵ��
	 * @param groupNum �ڼ����飬0-��ʾ����ƥ�䣬1-��ʾС���ŵ���ȡֵ
	 * @return ����Ҫ����ַ���
	 */
	public static String regxpExtract(String input, String regexp, int index, int groupNum) {
		Pattern p = Pattern.compile(regexp); //����������ʽ
		Matcher m = p.matcher(input); //ƥ���ַ���
		String result = null;
		boolean isFound = false;
		for(int i = 1 ; i<=index ; i++) { //����ָ����ƥ��ʵ��������ѭ�������find
			if(!m.find()) {
				isFound = false;
				break;
			}
			isFound = true;
		}
		if(isFound) { //������ҵ�ƥ��,����ȡ��Ҫ����
			result = m.group(groupNum);
		}
		return result;
	}
	
}
