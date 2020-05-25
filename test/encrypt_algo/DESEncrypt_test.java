package encrypt_algo;

import fileIO.*;
import ui.encrypt_frame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.*;
 class DESEncrypt_test {
	 int[] Padding(){
//		 if (Mtext.length!=64) {
//			 DataFormatException e = new DataFormatException();
//		 throw e;
//		 }
		 int[] M = new int[64];
		 for (int j=0;j<56;j++) {//����λ��56λ��01���λ�����һ���ֽ��Ǳ�ʾ��䳤�ȡ�
				if (j%2==0)
					M[j]=0;
				else
					M[j]=1;
	 }
		 String len= new String(Integer.toBinaryString(64));//����䳤�ȱ�ʾ�ɶ�����
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
		 * ���ܲ�������
		 * @throws IOException
		 */
		public void testDESEncrypt() throws IOException {
	File_Obj.Mtobyte = new ReadFile().getContent("C:\\Users\\18465\\Desktop\\����3.txt");
	File_Obj.Keytext = File_Obj.StringToBinaryintArray("12345678");
	File_Obj.Mtextbi = File_Obj.byteArrayToBinaryintArray(File_Obj.Mtobyte);
	DES des = new DES();
		File_Obj.Ctextbi = des.DESDeEncrypt(File_Obj.Mtextbi,File_Obj.Keytext);
		
	}
	 	@Disabled
		@Test
		/**
		 * ���ܲ����쳣
		 */
		public void testDESTest() {
			DES des = new DES();
			int[] Mtext = new int[64];
			Mtext = Padding();
			File_Obj.Keytext = File_Obj.StringToBinaryintArray("12345678");
			if(des.DESTest(Mtext, File_Obj.Keytext))
				System.out.println("��������");
			else
				System.out.println("�����쳣");
		}
		@Test
		/**
		 * ���ܲ�������
		 */
		public void testAESTest() {
			AES aes = new AES();
			byte[] Mtext = new byte[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
			if(aes.AESTest(Mtext,"1234567890abcdef"))
				System.out.println("��������");
			else
				System.out.println("�����쳣");
		}
}