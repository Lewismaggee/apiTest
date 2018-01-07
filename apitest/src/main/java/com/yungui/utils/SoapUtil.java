package com.yungui.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class SoapUtil {
	/**
	 * 根据url地址和发送的消息进行soap协议请求，返回soap应答
	 * @param url webservice的endpoint地址
	 * @param message soap协议包
	 * @return soap响应包
	 */
	public static SOAPMessage sendRequest(URL url , SOAPMessage message) {
		SOAPConnectionFactory factory = null;
		SOAPConnection conn = null;
		SOAPMessage response = null;
		try {
			factory = SOAPConnectionFactory.newInstance();// 实例化soap连接工厂
			conn = factory.createConnection();// 用soap连接工厂对象创建连接
			response = conn.call(message, url);// 发送请求包，并等待响应
		} catch (UnsupportedOperationException | SOAPException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * 根据xml请求内容生成soap协议包
	 * @param xmlStr xml内容
	 * @return soap协议包
	 */
	public static SOAPMessage createMessageUsingSOAP1_2(String xmlStr) {
		MessageFactory mFactory = null;
		SOAPMessage message = null;
		try {
			mFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);// 用soap1.2协议创建消息工厂
			MimeHeaders headers = new MimeHeaders();// 协议头对象，空的即可
			ByteArrayInputStream bais = new ByteArrayInputStream(xmlStr.getBytes());// 将xml数据包装为输入流
			message = mFactory.createMessage(headers,bais);//使用消息工厂创建协议包
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	/**
	 * 根据提供的元素节点名字（标签名），从指定的根节点开始进行递归查询
	 * @param rootElement 指定的xml数据根节点
	 * @param elementName 要查找的元素节点名字
	 * @return 符合名称的元素节点
	 */
	public static SOAPElement getElementByElementName(SOAPElement rootElement , String elementName) {
		if(rootElement.getElementName().getQualifiedName().equals(elementName)) {// 如果该根节点的名字就是指定的名称则直接返回
			return rootElement;
		}else {
			Iterator<SOAPElement> iterator = rootElement.getChildElements();// 获取根节点的所有子元素节点
			List<SOAPElement> elements = new ArrayList<>();
			while(iterator.hasNext()) {// 遍历每一个子节点
				elements.add(getElementByElementName(iterator.next(), elementName));// 递归查询
			}
			return elements.get(0); //返回第一个符合条件的元素节点
		}
	}
	
	/**
	 * 根据提供的元素节点名字（标签名），从xml数据中查询出该节点的值
	 * @param rootElement 指定的xml数据根节点
	 * @param elementName 要查找的元素节点名字
	 * @return 符合名称的元素节点的值
	 */
	public static String getValueByElementName(SOAPElement rootElement, String elementName) {
		SOAPElement element = getElementByElementName(rootElement, elementName); // 找到符合要求的节点
		return element.getFirstChild().getNodeValue(); //返回节点的值
	}
}
