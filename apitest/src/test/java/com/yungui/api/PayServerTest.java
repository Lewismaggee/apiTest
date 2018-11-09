package com.yungui.api;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.testng.annotations.Test;

import com.yungui.entity.UserPay;

public class PayServerTest {
	@Test
	public void testPay() {
		UserPay userPay = new UserPay();
		userPay.setName("Lewis002");
		userPay.setCard("200010");
		userPay.setMoney(100.10);
		String expected = "ok";
		try {
			Socket socket = new Socket("localhost",6666);
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(userPay);
			oos.flush();
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
			String actual = reader.readLine();
			reader.close();
			isr.close();
			is.close();
			oos.close();
			os.close();
			socket.close();
			assertEquals(actual, expected);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
