package fileIO;

import java.io.IOException;
import java.math.BigInteger;

import javax.swing.JOptionPane;

import ui.encrypt_frame;

public class File_Obj {
public static byte[] Mtobyte;
public byte[] Ctobyte;
public byte[] Keytobyte;
public byte[] PublicKey;
public byte[] PrivateKey;
public static int[] Mtext;
public static int[] Ctext;
public static int[] Keytext;
public static int[] Mtextbi;
public static int[] Ctextbi;
public static int M_len;
public static BigInteger e,d,n;

public File_Obj(){
}
public void CryptBtn() throws IOException{
	ReadFile f1 = new ReadFile();
	if(encrypt_frame.InputFile.getText().equals(""))
		JOptionPane.showMessageDialog(null, "未键入输入文件路径！");
	else {
	Mtobyte = f1.getContent(encrypt_frame.InputFile.getText());//获得文件的字节流//read()读出来的是有符号的整形
	Mtextbi=byteArrayToBinaryintArray(Mtobyte);//将字节流转换成二进制流（8的倍数）
	M_len = Mtextbi.length;//明文二进制串的长度
	/**
	 * 输出函数输入字节数组
	 */
	for(int i=0;i<Mtobyte.length;i++)
		System.out.print(String.valueOf(Mtobyte[i])+",");
	System.out.println("");
	/**
	 * 测试输出
	 */
	System.out.println("明文二进制串的长度为:"+M_len);
	
	
	//TODO:判断选择的算法
	if (encrypt_frame.DES_rBtn.isSelected()||encrypt_frame.Mode==1) {
	encrypt_algo.DES des = new encrypt_algo.DES();
	//TODO:判断密钥长度的合规，抛出异常
	if (encrypt_frame.DESKey_text.getText().length()!=8) {
		System.out.println("输入的密钥不是8位！");
	}
	Keytext = StringToBinaryintArray(encrypt_frame.DESKey_text.getText());
	if(encrypt_frame.Mode==0) 
	Ctextbi=des.DESEncrypt(Mtextbi,Keytext);//得到密文的整形二进制序列
	else 
		Ctextbi=des.DESDeEncrypt(Mtextbi,Keytext);//TODO: 当解密时要返回明文的长度，应该可以用Collections实现
	String CString = new String(toStringMethod(Ctextbi));//将二进制序列转成String
	Ctext = new int[CString.length()/8];
	/**
	 * 测试输出
	 */
	System.out.println("密文二进制串的长度为："+CString.length());
	for (int i=0;i<CString.length()/8;i++) {//按8个一组将二进制序列转成int值
		Ctext[i] = Integer.parseInt(CString.substring(8*i,8*i+8),2);
	}
	Ctobyte = intArrayTobyteArray(Ctext);
	}
	
	
	else if (encrypt_frame.AES_rBtn.isSelected()||encrypt_frame.Mode==2) {
		encrypt_algo.AES aes = new encrypt_algo.AES();
		if(encrypt_frame.AESKey_text.getText().length()!=16) {
			System.out.println("输入的密钥不是16位！");
		}
		if(encrypt_frame.Mode==0) {
			Ctext=aes.AESEncrypt(Mtobyte,encrypt_frame.AESKey_text.getText());//得到密文的整形序列
		}
			else 
				try {
				Ctext=aes.AESDeEncrypt(Mtobyte,encrypt_frame.AESKey_text.getText());//TODO: 当解密时要返回明文的长度，应该可以用Collections实现
				}catch(Exception e) {
					System.out.println("--Exception--");
				}
		Ctobyte = intArrayTobyteArray(Ctext);
			}
	
	else if(encrypt_frame.RSA_rBtn.isSelected()||encrypt_frame.Mode==3) {
		encrypt_algo.RSA rsa = new encrypt_algo.RSA();
		if(encrypt_frame.RSAkey_text.getText().equals(""))
			JOptionPane.showMessageDialog(encrypt_frame.Enc_Btn,"请生成或导入密钥对后再进行加解密操作！");
		if(encrypt_frame.RSAkey_Lab.getText().contains("公")&&!encrypt_frame.RSA_rBtn.isEnabled())
			JOptionPane.showMessageDialog(null, "请用私钥解密！");
		if(encrypt_frame.RSAkey_Lab.getText().contains("私")&&encrypt_frame.RSA_rBtn.isEnabled())
			JOptionPane.showMessageDialog(null, "请用公钥加密！");
		else {
			n = new BigInteger(encrypt_frame.n_text.getText(),16);
			if(new BigInteger(Mtobyte).compareTo(n)>0)
				JOptionPane.showMessageDialog(encrypt_frame.Enc_Btn,"文件大小超出加密范围！");
			else {
				if(encrypt_frame.RSA_rBtn.isSelected()&&encrypt_frame.RSA_rBtn.isEnabled()) {
					e = new BigInteger(encrypt_frame.RSAkey_text.getText(),16);
			rsa.sete(e);}
				else {
					d = new BigInteger(encrypt_frame.RSAkey_text.getText(),16);
					rsa.setd(d);}
				rsa.setn(n);
			BigInteger C;
			if(encrypt_frame.Mode==0) 			
				 C = rsa.encode(new BigInteger(Mtobyte));
		else 
			 C = rsa.decode(new BigInteger(Mtobyte));
			Ctobyte = C.toByteArray();
			/**
			 * 输出函数输出字节数组
			 */
			for (int i=0;i<Ctobyte.length;i++)
				System.out.print(String.valueOf(Ctobyte[i])+",");
				System.out.println("");
				WriteFile f2 = new WriteFile();
				f2.createFile(encrypt_frame.OutputFile.getText(), Ctobyte);
		}
		}
	}
	}
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

public static String getProjectpath() {
	String path = System.getProperty("user.dir");
	return path;
}
}
