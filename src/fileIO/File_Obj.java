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
	
	Mtobyte = f1.getContent(encrypt_frame.InputFile.getText());//����ļ����ֽ���//read()�����������з��ŵ�����
	Mtextbi=byteArrayToBinaryintArray(Mtobyte);//���ֽ���ת���ɶ���������8�ı�����
	M_len = Mtextbi.length;//���Ķ����ƴ��ĳ���
	//TODO:�ж�ѡ����㷨
	if (encrypt_frame.DES_rBtn.isSelected()) {
	encrypt_algo.DES des = new encrypt_algo.DES();
	Keytext = StringToBinaryintArray(encrypt_frame.DESKey_text.getText());
	if(encrypt_frame.Mode==0) 
	Ctextbi=des.DESEncrypt(Mtextbi,Keytext);//�õ����ĵ����ζ���������
	else 
		Ctextbi=des.DESDeEncrypt(Mtextbi,Keytext);//TODO: ������ʱҪ�������ĵĳ��ȣ�Ӧ�ÿ�����Collectionsʵ��
	String CString = new String(toStringMethod(Ctextbi));//������������ת��String
	Ctext = new int[CString.length()/8];
	System.out.println(CString.length());
	for (int i=0;i<CString.length()/8;i++) {//��8��һ�齫����������ת��byteֵ
		Ctext[i] = Integer.parseInt(CString.substring(8*i,8*i+8),2);
	}
	}
	else if (encrypt_frame.AES_rBtn.isSelected()) {
		encrypt_algo.AES aes = new encrypt_algo.AES();
		if(encrypt_frame.Mode==0) 
			Ctext=aes.AESEncrypt(Mtobyte,encrypt_frame.AESKey_text.getText());//�õ����ĵ����ζ���������
			else 
				Ctext=aes.AESDeEncrypt(Mtobyte,encrypt_frame.AESKey_text.getText());//TODO: ������ʱҪ�������ĵĳ��ȣ�Ӧ�ÿ�����Collectionsʵ��
			}
	
//	}
	
//	String CString = new String(toStringMethod(Ctextbi));//������������ת��String
//	Ctext = new int[M_len/8]; 
//	System.out.println(CString.length());
//	System.out.println(M_len);
//	for (int i=0;i<M_len/8;i++) {//��8��һ�齫����������ת��byteֵ
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
	 int[] m=new int[in.length*8];//����ĳ�����length,��������
	for(int i = 0;i<in.length;i++){
		 String binaryStr = Integer.toBinaryString((in[i] & 0xFF)+0x100).substring(1);//ȥ����λ
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
	//TODO:�ж���Կ���ȵĺϹ棬�׳��쳣
	if(s.length()!=8)
		System.out.println("�������Կ����8λ��");
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
	// �Զ���һ���ַ���������
	StringBuilder sb = new StringBuilder();
	//����int���飬����int�����е�Ԫ��ת�����ַ������浽�ַ���������ȥ
	for(int i=0;i<arr.length;i++) {
		sb.append(arr[i]);
	}
	return sb.toString();
}
}
