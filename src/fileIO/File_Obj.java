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
		JOptionPane.showMessageDialog(null, "δ���������ļ�·����");
	else {
	Mtobyte = f1.getContent(encrypt_frame.InputFile.getText());//����ļ����ֽ���//read()�����������з��ŵ�����
	Mtextbi=byteArrayToBinaryintArray(Mtobyte);//���ֽ���ת���ɶ���������8�ı�����
	M_len = Mtextbi.length;//���Ķ����ƴ��ĳ���
	/**
	 * ������������ֽ�����
	 */
	for(int i=0;i<Mtobyte.length;i++)
		System.out.print(String.valueOf(Mtobyte[i])+",");
	System.out.println("");
	/**
	 * �������
	 */
	System.out.println("���Ķ����ƴ��ĳ���Ϊ:"+M_len);
	
	
	//TODO:�ж�ѡ����㷨
	if (encrypt_frame.DES_rBtn.isSelected()||encrypt_frame.Mode==1) {
	encrypt_algo.DES des = new encrypt_algo.DES();
	//TODO:�ж���Կ���ȵĺϹ棬�׳��쳣
	if (encrypt_frame.DESKey_text.getText().length()!=8) {
		System.out.println("�������Կ����8λ��");
	}
	Keytext = StringToBinaryintArray(encrypt_frame.DESKey_text.getText());
	if(encrypt_frame.Mode==0) 
	Ctextbi=des.DESEncrypt(Mtextbi,Keytext);//�õ����ĵ����ζ���������
	else 
		Ctextbi=des.DESDeEncrypt(Mtextbi,Keytext);//TODO: ������ʱҪ�������ĵĳ��ȣ�Ӧ�ÿ�����Collectionsʵ��
	String CString = new String(toStringMethod(Ctextbi));//������������ת��String
	Ctext = new int[CString.length()/8];
	/**
	 * �������
	 */
	System.out.println("���Ķ����ƴ��ĳ���Ϊ��"+CString.length());
	for (int i=0;i<CString.length()/8;i++) {//��8��һ�齫����������ת��intֵ
		Ctext[i] = Integer.parseInt(CString.substring(8*i,8*i+8),2);
	}
	Ctobyte = intArrayTobyteArray(Ctext);
	}
	
	
	else if (encrypt_frame.AES_rBtn.isSelected()||encrypt_frame.Mode==2) {
		encrypt_algo.AES aes = new encrypt_algo.AES();
		if(encrypt_frame.AESKey_text.getText().length()!=16) {
			System.out.println("�������Կ����16λ��");
		}
		if(encrypt_frame.Mode==0) {
			Ctext=aes.AESEncrypt(Mtobyte,encrypt_frame.AESKey_text.getText());//�õ����ĵ���������
		}
			else 
				try {
				Ctext=aes.AESDeEncrypt(Mtobyte,encrypt_frame.AESKey_text.getText());//TODO: ������ʱҪ�������ĵĳ��ȣ�Ӧ�ÿ�����Collectionsʵ��
				}catch(Exception e) {
					System.out.println("--Exception--");
				}
		Ctobyte = intArrayTobyteArray(Ctext);
			}
	
	else if(encrypt_frame.RSA_rBtn.isSelected()||encrypt_frame.Mode==3) {
		encrypt_algo.RSA rsa = new encrypt_algo.RSA();
		if(encrypt_frame.RSAkey_text.getText().equals(""))
			JOptionPane.showMessageDialog(encrypt_frame.Enc_Btn,"�����ɻ�����Կ�Ժ��ٽ��мӽ��ܲ�����");
		if(encrypt_frame.RSAkey_Lab.getText().contains("��")&&!encrypt_frame.RSA_rBtn.isEnabled())
			JOptionPane.showMessageDialog(null, "����˽Կ���ܣ�");
		if(encrypt_frame.RSAkey_Lab.getText().contains("˽")&&encrypt_frame.RSA_rBtn.isEnabled())
			JOptionPane.showMessageDialog(null, "���ù�Կ���ܣ�");
		else {
			n = new BigInteger(encrypt_frame.n_text.getText(),16);
			if(new BigInteger(Mtobyte).compareTo(n)>0)
				JOptionPane.showMessageDialog(encrypt_frame.Enc_Btn,"�ļ���С�������ܷ�Χ��");
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
			 * �����������ֽ�����
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

public static String getProjectpath() {
	String path = System.getProperty("user.dir");
	return path;
}
}
