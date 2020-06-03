package encrypt_algo;

import org.junit.Test;//到底导入哪个包:org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import fileIO.File_Obj;
import fileIO.ReadFile;
import fileIO.WriteFile;
import ui.encrypt_frame;

import java.io.*;
import java.math.BigInteger;

import javax.swing.JFileChooser;

public class MD5_test {
	@BeforeAll
	public String getpath() {
		String Path = new String();
		JFileChooser fc1 = new JFileChooser(File_Obj.getProjectpath());
		int returnVal = fc1.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// 正常选择文件
			Path = new String(fc1.getSelectedFile().toString());
		}
		return Path;
	}
	// @Disabled
	// @Test
	// public void testMD5 () throws IOException{
	//// File f = new File(getpath());
	// File f = new File("");
	// Reader reader = new InputStreamReader(new FileInputStream(f));
	//
	// }
	// @Disabled
	// @Test
	// public void testFormat() {
	// System.out.print(String.format("%2s", "12"));
	// }

	/*
	 * 整数变成16进制字符串
	 */
	// @Disabled
	// @Test
	// public void changeHex() {
	// int a =123;
	// String str = "";
	// for (int i = 0; i < 4; i++) {
	// str += String.format("%2s",
	// Integer.toHexString(((a >> i * 8) % (1 << 8)) & 0xff)).replace(' ', '0');
	// }
	// System.out.print(str);
	// }

	// }
	// @Disabled
	// @Test
	// public void testNumberIndication() {
	// long a = 0x12345678L;//加一个L可以把一个数值转化成long型
	// long b = 0x12345678;
	// System.out.print(a+","+b);
	// }
	/**
	 * 测试成功
	 */
	@Disabled
	@Test
	public void test_outbyteArray() throws IOException {
		ReadFile f1 = new ReadFile();
		MD5 md = new MD5();
		byte[] Mtobyte = f1.getContent(getpath());
		long[] result = md.digest(Mtobyte);
		// md.digest("罗宏浩");
		// System.out.println(md.outString());
		// MD5 md1 = new MD5();
		// md1.digest("罗宏浩");
		byte[] mb = md.outbyteArray();
		String sb = md.outString();
		System.out.println("CString:"+sb);
//		String path = getpath();
//		WriteFile f2 = new WriteFile();
//		f2.createFile(path, mb);
		// for(int i=0;i<16;i++)
		// System.out.print(mb[i]+",");

	}
//	@Disabled
	@Test
	public void test_Output() throws IOException {
//		ReadFile f1 = new ReadFile();
//		byte[] M = f1.getContent(File_Obj.getProjectpath() + "\\签名.txt");
//		String s = "";
//		for (int i = 0; i < M.length; i++) {
//			s += Integer.toHexString(0xFF & (int) M[i]);
//		}
//		System.out.println(s);
//	}
		byte[] Sign = {12, 11, 3, 11, 8, 14, 5, 3, 6, 6, 13, 2, 9, 2, 8, 2, 8, 9, 15, 4, 9, 10, 1, 14, 12, 14, 11, 6, 10, 2, 2, 15};
		BigInteger b = new BigInteger(Sign);
		System.out.println(b);
		RSA rsa = new RSA();
		rsa.findp(100);
		rsa.findq(100);
		rsa.keypro();
		BigInteger C =rsa.encode(b);
		byte[] Signenc = C.toByteArray();
		BigInteger C_1 = new BigInteger(Signenc); 
		BigInteger M = rsa.decode(C_1);
		byte[] Sign_veri = M.toByteArray();
	}	
}