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
	@SuppressWarnings("unchecked")
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
	
	/**
	 * 将SOAPMessage中的正文xml部分转换为字符串
	 * @param message 需要转换的SOAPMessage对象
	 * @return xml对应的字符串
	 */
	public static String soapXml2String(SOAPMessage message) {
		TransformerFactory tff = TransformerFactory.newInstance();// 创建转换工厂
		Transformer tf = null;
		String str = null;
		try {
			tf = tff.newTransformer();// 使用转换工厂创建转换器对象
			Source source = message.getSOAPPart().getContent();// 需要转换的源，指定为SOAPMessage中part部分的正文
			ByteArrayOutputStream baos = new ByteArrayOutputStream();// 创建字节数组输出流，用于保存转换的结果
			StreamResult result = new StreamResult(baos);// 创建流结果对象，流为上一部创建的字节数组流
			tf.transform(source, result);// 使用转换器开始转换
			str = baos.toString();// 字节数组输出流保存的内容转换为字符串
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
	 * 根据提供的正则表达式，从指定字符串中抽取需要的内容
	 * @param input 指定字符串
	 * @param regexp 带有分组语法(小括号)的正则表达式
	 * @param index 第几个匹配的实例
	 * @param groupNum 第几个组，0-表示完整匹配，1-表示小括号的提取值
	 * @return 符合要求的字符串
	 */
	public static String regxpExtract(String input, String regexp, int index, int groupNum) {
		Pattern p = Pattern.compile(regexp); //编译正则表达式
		Matcher m = p.matcher(input); //匹配字符串
		String result = null;
		boolean isFound = false;
		for(int i = 1 ; i<=index ; i++) { //根据指定的匹配实例数进行循环，多次find
			if(!m.find()) {
				isFound = false;
				break;
			}
			isFound = true;
		}
		if(isFound) { //如果能找到匹配,则提取需要的组
			result = m.group(groupNum);
		}
		return result;
	}
	
}
