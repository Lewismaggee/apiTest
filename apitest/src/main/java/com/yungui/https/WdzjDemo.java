package com.yungui.https;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class WdzjDemo {
	/**
	 * bbs.wdzj.com∑√Œ 
	 * @param args
	 */
	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL("https://bbs.wdzj.com");
			HttpsURLConnection conns = (HttpsURLConnection)url.openConnection();
			conns.setRequestProperty("User-Agent", 
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) "
					+ "Chrome/44.0.2403.89 Safari/537.36");
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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
