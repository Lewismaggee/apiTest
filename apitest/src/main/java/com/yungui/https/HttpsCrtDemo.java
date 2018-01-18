package com.yungui.https;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

public class HttpsCrtDemo {
	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL("https://192.168.206.130/hello.html");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpsURLConnection conns = null;
		try {
			conns = (HttpsURLConnection)url.openConnection();
			
			
			/**
			 * ���������֤,�ܶ�ʱ����Ҫ��
			 */
			conns.setHostnameVerifier(new HostnameVerifier() {//���������֤
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true; //ǿ���κ�����������
				}
			});
			
			/**
			 * ��֤������Ϊ�����εģ���֤����ص��������б��У�ʹ���ǿ������θ�֤��
			 */
			CertificateFactory cf = CertificateFactory.getInstance("X.509");//��ȡ֤�鹤��ʵ����ʹ�ñ�׼ΪX.509
			Certificate cert = 
					cf.generateCertificate(HttpsCrtDemo.class.getClassLoader()
							.getResourceAsStream("apache.cer"));//ʹ�ù������뵼����֤�����certificate����
			
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());//��ȡ�洢Կ��
			keyStore.load(null, null);//���ص����룬����Ϊ��
			keyStore.setCertificateEntry("ca",cert);//����֤��
			TrustManagerFactory tmf = 
					TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());//�������ι���������
			tmf.init(keyStore);//��ʼ�����ι���
			
			SSLContext context = null;
			context = SSLContext.getInstance("TLS");//������TLSЭ��Ϊ��׼��SSL�������Ķ���
			context.init(null, tmf.getTrustManagers(), null);//��ʼ��context����
			conns.setSSLSocketFactory(context.getSocketFactory());//����SSL֤�������
			
			
			conns.setDoInput(true);
			InputStream is = conns.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			int c = -1;
			StringBuffer sb = new StringBuffer();
			while((c=reader.read())!=-1) {
				sb.append((char)c);
			}
			System.out.println(sb);
			reader.close();
			is.close();
			conns.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		
		
	}
}
