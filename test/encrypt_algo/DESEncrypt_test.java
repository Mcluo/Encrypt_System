package encrypt_algo;

import fileIO.*;
import ui.encrypt_frame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.math.BigInteger;
import java.util.Random;

import javax.swing.JOptionPane;

class DESEncrypt_test {
	int[] Padding() {
		int[] M = new int[64];
		for (int j = 0; j < 56; j++) {// 明文位到56位是01填充位，最后一个字节是表示填充长度。
			if (j % 2 == 0)
				M[j] = 0;
			else
				M[j] = 1;
		}
		String len = new String(Integer.toBinaryString(64));// 把填充长度表示成二进制
		for (int k = 8 - len.length(); k < 8; k++) {
			M[56 + k] = Integer.parseInt(len.substring(k - 8 + len.length(), k - 7 + len.length()));
		}
		for (int k = 0; k < 8 - len.length(); k++) {
			M[56 + k] = 0;
		}
		return M;
	}

	@Disabled
	@Test
	/**
	 * DESEncrypt功能测试正常
	 * 
	 * @throws IOException
	 */
	public void testDESEncrypt() throws IOException {
		File_Obj.Mtobyte = new ReadFile().getContent("C:\\Users\\18465\\Desktop\\问题3.txt");
		File_Obj.Keytext = File_Obj.StringToBinaryintArray("12345678");
		File_Obj.Mtextbi = File_Obj.byteArrayToBinaryintArray(File_Obj.Mtobyte);
		DES des = new DES();
		File_Obj.Ctextbi = des.DESDeEncrypt(File_Obj.Mtextbi, File_Obj.Keytext);

	}

//	@Disabled
	@Test
	/**
	 * DES算法功能测试异常
	 */
	public void testDESTest() {
		DES des = new DES();
		int[] Mtext = new int[64];
		Mtext = Padding();
		File_Obj.Keytext = File_Obj.StringToBinaryintArray("12345678");
		if (des.DESTest(Mtext, File_Obj.Keytext))
			System.out.println("功能正常");
		else
			System.out.println("功能异常");
	}

	@Disabled
	@Test
	/**
	 * AES算法功能测试正常
	 */
	public void testAESTest() {
		AES aes = new AES();
		byte[] Mtext = new byte[] { -1, -22, 3, 4, 5, 6, 65, 54, 9, 13, 108, 12, 13, 14, 115, 16 };
		if (aes.AESTest(Mtext, "1234567890abcdef"))
			System.out.println("功能正常");
		else
			System.out.println("功能异常");
	}

	@Disabled
	@Test
	/**
	 * 测试AESEncrypt函数
	 */
	public void testAESEncrypt() throws IOException {
		{
			File_Obj.Mtobyte = new ReadFile().getContent("C:\\Users\\18465\\Desktop\\问题3.txt");
			AES aes = new AES();
			File_Obj.Ctext = aes.AESEncrypt(File_Obj.Mtobyte, "1234567890abcdef");

		}
	}

	@Disabled
	@Test
	public void testRSA() {
	byte[] Mtobyte = {-16, 95, -116, 83, -18, -75, -85, -10, -85, 123, -69, -37, 100, -38, -101, -42};
//	BigInteger d = new BigInteger(Mtobyte);
//	RSA rsa = new RSA();
//	rsa.findp(100);
//	rsa.findq(100);
//	rsa.keypro();
//	BigInteger C = rsa.encode(d);
//	byte[] Ctobyte=C.toByteArray();
//	BigInteger e = new BigInteger(Ctobyte);
//	BigInteger m = rsa.decode(e);
//	byte[] M = m.toByteArray();
//	System.out.println(M.length);
	BigInteger C = new BigInteger(Mtobyte);
	byte[] Ctobyte = C.toByteArray(); 
	for(int i = 0;i<Mtobyte.length;i++)
		System.out.print(Mtobyte[i]+",");
	System.out.println("");
	for(int i = 0;i<Ctobyte.length;i++)
		System.out.print(Ctobyte[i]+",");
	}

	@Disabled
	@Test
	public void testURI() {
		String path = System.getProperty("user.dir");
		System.out.print(path + "\\key");
	}

	 @Disabled
	@Test
	public void testgetFileSize() throws IOException {
		File file = new File("D:\\软件\\Eclipseworkspace_32\\Encrypt_system\\test\\测试文件4.txt");
		long size = file.length();
		System.out.println("java.txt文件大小为: " + size + "bytes");
		ReadFile f1 = new ReadFile();
		byte[] M = f1.getContent("D:\\软件\\Eclipseworkspace_32\\Encrypt_system\\test\\测试文件4.txt");
		BigInteger MB = new BigInteger(M);
		String MBS = MB.toString(16);
		int len = MBS.length();
		System.out.println("模数n的位数必须大于" + len);
		RSA rsa = new RSA();
		rsa.findp(100);
		rsa.findq(100);
		rsa.keypro();
		BigInteger n = rsa.getn();
		if (new BigInteger(M).compareTo(n) > 0)
			JOptionPane.showMessageDialog(encrypt_frame.Enc_Btn, "文件大小超出加密范围！");

	}
}