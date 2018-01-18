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
			 * 添加主机认证,很多时候不需要的
			 */
			conns.setHostnameVerifier(new HostnameVerifier() {//添加主机认证
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true; //强制任何主机都可以
				}
			});
			
			/**
			 * 将证书设置为可信任的，将证书加载到可信任列表中，使我们可以信任该证书
			 */
			CertificateFactory cf = CertificateFactory.getInstance("X.509");//获取证书工厂实例，使用标准为X.509
			Certificate cert = 
					cf.generateCertificate(HttpsCrtDemo.class.getClassLoader()
							.getResourceAsStream("apache.cer"));//使用工厂读入导出的证书打造certificate对象
			
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());//获取存储钥匙
			keyStore.load(null, null);//加载的密码，设置为空
			keyStore.setCertificateEntry("ca",cert);//设置证书
			TrustManagerFactory tmf = 
					TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());//创建信任管理器工厂
			tmf.init(keyStore);//初始化信任工厂
			
			SSLContext context = null;
			context = SSLContext.getInstance("TLS");//创建以TLS协议为标准的SSL层上下文对象
			context.init(null, tmf.getTrustManagers(), null);//初始化context对象
			conns.setSSLSocketFactory(context.getSocketFactory());//设置SSL证书管理工厂
			
			
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
