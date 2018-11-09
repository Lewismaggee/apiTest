package com.yungui.socket;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import org.testng.annotations.Test;

public class ConfigUpTest {
	@Test
	public void testConfigUp() {
		Socket socket;
		String str = "20171122160000002|COM1|2|A$1$01$1$1$1$1$1$1$HSKDG_014$HSKDG_014.DLL";
		String expected = "";
		try {
			socket = new Socket("localhost",6666);
			OutputStream os = socket.getOutputStream();
			os.write(str.getBytes());
			os.flush();
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
			String actual = reader.readLine();
			reader.close();
			isr.close();
			is.close();
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
