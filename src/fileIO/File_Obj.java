package fileIO;

import java.io.IOException;

import ui.encrypt_frame;

public class File_Obj {
public static byte[] Mtobyte;
public byte[] Ctobyte;
public byte[] Keytobyte;
public static int[] Mtext;
public static int[] Ctext;
public static int[] Keytext;
public static int[] Mtextbi;
public static int[] Ctextbi;
public static int M_len;

public File_Obj() throws IOException{
	ReadFile f1 = new ReadFile();
	
	Mtobyte = f1.getContent(encrypt_frame.InputFile.getText());//获得文件的字节流//read()读出来的是有符号的整形
	Mtextbi=byteArrayToBinaryintArray(Mtobyte);//将字节流转换成二进制流（8的倍数）
	M_len = Mtextbi.length;//明文二进制串的长度
	//TODO:判断选择的算法
	if (encrypt_frame.DES_rBtn.isSelected()) {
	encrypt_algo.DES des = new encrypt_algo.DES();
	Keytext = StringToBinaryintArray(encrypt_frame.DESKey_text.getText());
	if(encrypt_frame.Mode==0) 
	Ctextbi=des.DESEncrypt(Mtextbi,Keytext);//得到密文的整形二进制序列
	else 
		Ctextbi=des.DESDeEncrypt(Mtextbi,Keytext);//TODO: 当解密时要返回明文的长度，应该可以用Collections实现
	String CString = new String(toStringMethod(Ctextbi));//将二进制序列转成String
	Ctext = new int[CString.length()/8];
	System.out.println(CString.length());
	for (int i=0;i<CString.length()/8;i++) {//按8个一组将二进制序列转成byte值
		Ctext[i] = Integer.parseInt(CString.substring(8*i,8*i+8),2);
	}
	}
	else if (encrypt_frame.AES_rBtn.isSelected()) {
		encrypt_algo.AES aes = new encrypt_algo.AES();
		if(encrypt_frame.Mode==0) 
			Ctext=aes.AESEncrypt(Mtobyte,encrypt_frame.AESKey_text.getText());//得到密文的整形二进制序列
			else 
				Ctext=aes.AESDeEncrypt(Mtobyte,encrypt_frame.AESKey_text.getText());//TODO: 当解密时要返回明文的长度，应该可以用Collections实现
			}
	
//	}
	
//	String CString = new String(toStringMethod(Ctextbi));//将二进制序列转成String
//	Ctext = new int[M_len/8]; 
//	System.out.println(CString.length());
//	System.out.println(M_len);
//	for (int i=0;i<M_len/8;i++) {//按8个一组将二进制序列转成byte值
//		Ctext[i] = Integer.parseInt(CString.substring(8*i,8*i+8),2);
//	}
//	}
	Ctobyte = intArrayTobyteArray(Ctext);
	WriteFile f2 = new WriteFile();
	f2.createFile(encrypt_frame.OutputFile.getText(), Ctobyte);
	
	
}
public static int[] byteArrayTointArray(byte[] b) {
	int[] in1 = new int[b.length];
	for (int i = 0; i < in1.length; i++) {
		in1[i] =  b[i];
		}
	return in1;
}
public static byte[]  intArrayTobyteArray(int[] in) {
	byte[] b1=new byte[in.length];
	for (int i = 0; i < b1.length; i++) {
		b1[i] =  (byte)in[i];
		}
	return b1;
}
public static int[] byteArrayToBinaryintArray(byte[] in){
	 int[] m=new int[in.length*8];//数组的长度用length,不加括号
	for(int i = 0;i<in.length;i++){
		 String binaryStr = Integer.toBinaryString((in[i] & 0xFF)+0x100).substring(1);//去除高位
		 for(int j=0;j<binaryStr.length();j++) {
			 m[i*8+j+8-binaryStr.length()] = Integer.parseInt(binaryStr.substring(j,j+1));
		 }
		 for(int k=0;k<(8-binaryStr.length());k++) {
			 m[i*8+k] = 0;
		 }
	}
	return m;
}
public static int[] StringToBinaryintArray(String s) {
	int[] n = new int[8]; 
	//TODO:判断密钥长度的合规，抛出异常
	if(s.length()!=8)
		System.out.println("输入的密钥不是8位！");
	int[] m=new int[64];
	 for(int i = 0;i<s.length();i++){
	 n[i] = Integer.parseInt(s.substring(i,i+1));
	 String binaryStr = java.lang.Integer.toBinaryString(n[i]);
	 for(int j=0;j<binaryStr.length();j++) {
		 m[i*8+j+8-binaryStr.length()] = Integer.parseInt(binaryStr.substring(j,j+1));
	 }
	 for(int k=0;k<(8-binaryStr.length());k++) {
		 m[i*8+k] = 0;
	 }
	 }
	 return m;
}
private static String toStringMethod(int[] arr) 
{
	// 自定义一个字符缓冲区，
	StringBuilder sb = new StringBuilder();
	//遍历int数组，并将int数组中的元素转换成字符串储存到字符缓冲区中去
	for(int i=0;i<arr.length;i++) {
		sb.append(arr[i]);
	}
	return sb.toString();
}
}
