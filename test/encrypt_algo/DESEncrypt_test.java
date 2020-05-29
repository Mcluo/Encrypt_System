package encrypt_algo;

import fileIO.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.math.BigInteger;
import java.util.Random;
 class DESEncrypt_test {
	 int[] Padding(){
		 int[] M = new int[64];
		 for (int j=0;j<56;j++) {//明文位到56位是01填充位，最后一个字节是表示填充长度。
				if (j%2==0)
					M[j]=0;
				else
					M[j]=1;
	 }
		 String len= new String(Integer.toBinaryString(64));//把填充长度表示成二进制
		 for (int k=8-len.length();k<8;k++) {
				M[56+k]=Integer.parseInt(len.substring(k-8+len.length(),k-7+len.length()));
			}
			for (int k=0;k<8-len.length();k++) {
				M[56+k]=0;
			}
		 return M;
	 }
	 	@Disabled
		@Test 
		/**
		 * DESEncrypt功能测试正常
		 * @throws IOException
		 */
		public void testDESEncrypt() throws IOException {
	File_Obj.Mtobyte = new ReadFile().getContent("C:\\Users\\18465\\Desktop\\问题3.txt");
	File_Obj.Keytext = File_Obj.StringToBinaryintArray("12345678");
	File_Obj.Mtextbi = File_Obj.byteArrayToBinaryintArray(File_Obj.Mtobyte);
	DES des = new DES();
		File_Obj.Ctextbi = des.DESDeEncrypt(File_Obj.Mtextbi,File_Obj.Keytext);
		
	}
	 	@Disabled
		@Test
		/**
		 * DES算法功能测试异常
		 */
		public void testDESTest() {
			DES des = new DES();
			int[] Mtext = new int[64];
			Mtext = Padding();
			File_Obj.Keytext = File_Obj.StringToBinaryintArray("12345678");
			if(des.DESTest(Mtext, File_Obj.Keytext))
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
			byte[] Mtext = new byte[] {-1,-22,3,4,5,6,65,54,9,13,108,12,13,14,115,16};
			if(aes.AESTest(Mtext,"1234567890abcdef"))
				System.out.println("功能正常");
			else
				System.out.println("功能异常");
		}
		@Disabled
		@Test
		/**
		 * 测试AESEncrypt函数
		 */
		public void testAESEncrypt() throws IOException{
		{
			File_Obj.Mtobyte = new ReadFile().getContent("C:\\Users\\18465\\Desktop\\问题3.txt");
			AES aes = new AES();
			File_Obj.Ctext = aes.AESEncrypt(File_Obj.Mtobyte,"1234567890abcdef");
			
		}
		}
		
		@Test
		public void testRSA() {
			BigInteger c = BigInteger.probablePrime(40,new Random());
			BigInteger d = new BigInteger(c.toByteArray());
			if(c.equals(d))
				System.out.println("功能正常");
			else
				System.out.println("功能异常");
		}
}