package fileIO;

import java.io.IOException;
import java.math.BigInteger;

import javax.swing.JOptionPane;

import encrypt_algo.MD5;
import encrypt_algo.RSA;
import encrypt_algo.SHA;
import ui.DigSign_frame;
import ui.encrypt_frame;

public class File_Obj {
	public static byte[] Mtobyte, Signbyte, Signenc, Sign;
	private byte Signenc_len;
	public byte[] Ctobyte;
	public byte[] Keytobyte;
	public byte[] PublicKey;
	public byte[] PrivateKey;
	public static int[] Mtext;
	public static int[] Ctext;
	public static int[] Mtextbi;
	public static int[] Ctextbi;
	public static int M_len;
	public static BigInteger e, d, n;
	private static String CString, veri_String;
	private static RSA rsa;
	private static MD5 md5;
	private static SHA sha;
	public static String filename;
	public File_Obj() {
	}

	public void CryptBtn() throws IOException {
		ReadFile f1 = new ReadFile();
		if (encrypt_frame.InputFile.getText().equals(""))
			JOptionPane.showMessageDialog(null, "δ���������ļ�·����");
		else {
			Mtobyte = f1.getContent(encrypt_frame.InputFile.getText());// ����ļ����ֽ���//read()�����������з��ŵ�����
			Mtextbi = byteArrayToBinaryintArray(Mtobyte);// ���ֽ���ת���ɶ���������8�ı�����
			M_len = Mtextbi.length;// ���Ķ����ƴ��ĳ���

			if (encrypt_frame.DES_rBtn.isSelected() || encrypt_frame.Mode == 1) {
				encrypt_algo.DES des = new encrypt_algo.DES();
				if (encrypt_frame.DESKey_text.getText().length() != 8) {
					System.out.println("�������Կ����8λ��");
				}
				String Keytext = encrypt_frame.DESKey_text.getText();
				if (encrypt_frame.Mode == 0) {
					Ctobyte = des.deal(Mtobyte, Keytext, 1);// �õ����ĵ����ζ���������
					filename = encrypt_frame.selectedFilename+ ".des";
				}
				else {
					Ctobyte = des.deal(Mtobyte, Keytext,0);// TODO: ������ʱҪ�������ĵĳ��ȣ�Ӧ�ÿ�����Collectionsʵ��
				CString = new String(Ctobyte);// ������������ת��String
				filename = encrypt_frame.selectedFilename.substring(0,encrypt_frame.selectedFilename.length()-4);
				}
			}

			else if (encrypt_frame.AES_rBtn.isSelected() || encrypt_frame.Mode == 2) {
				encrypt_algo.AES aes = new encrypt_algo.AES();
				if (encrypt_frame.AESKey_text.getText().length() != 16) {
					System.out.println("�������Կ����16λ��");
				}
				if (encrypt_frame.Mode == 0) {
					Ctext = aes.AESEncrypt(Mtobyte, encrypt_frame.AESKey_text.getText());// �õ����ĵ���������
					filename = encrypt_frame.selectedFilename+ ".aes";
				} else
					try {
						Ctext = aes.AESDeEncrypt(Mtobyte, encrypt_frame.AESKey_text.getText());
						filename = encrypt_frame.selectedFilename.substring(0,encrypt_frame.selectedFilename.length()-4);		
					} catch (Exception e) {
						System.out.println("--Exception--");
					}
				Ctobyte = intArrayTobyteArray(Ctext);
			}

			else if (encrypt_frame.RSA_rBtn.isSelected() || encrypt_frame.Mode == 3) {
				rsa = new encrypt_algo.RSA();
				if (encrypt_frame.RSAKey_text.getText().equals(""))
					JOptionPane.showMessageDialog(encrypt_frame.Enc_Btn, "�����ɻ�����Կ�Ժ��ٽ��мӽ��ܲ�����");
				else if (encrypt_frame.RSAkey_Lab.getText().contains("��") && !encrypt_frame.RSA_rBtn.isEnabled())
					JOptionPane.showMessageDialog(null, "����˽Կ���ܣ�");
				else if (encrypt_frame.RSAkey_Lab.getText().contains("˽") && encrypt_frame.RSA_rBtn.isEnabled())
					JOptionPane.showMessageDialog(null, "���ù�Կ���ܣ�");
				else {
					n = new BigInteger(encrypt_frame.n_text.getText(), 16);
					if (new BigInteger(Mtobyte).compareTo(n) > 0)
						JOptionPane.showMessageDialog(encrypt_frame.Enc_Btn, "�ļ���С�������ܷ�Χ��");
					else {
						if (encrypt_frame.RSA_rBtn.isSelected() && encrypt_frame.RSA_rBtn.isEnabled()) {
							e = new BigInteger(encrypt_frame.RSAKey_text.getText(), 16);
							rsa.sete(e);
							filename =encrypt_frame.selectedFilename+ ".rsa";
						} else {
							d = new BigInteger(encrypt_frame.RSAKey_text.getText(), 16);
							rsa.setd(d);
							filename = encrypt_frame.selectedFilename.substring(0,encrypt_frame.selectedFilename.length()-4);
						}
						rsa.setn(n);
						BigInteger C;
						if (encrypt_frame.Mode == 0)
							C = rsa.encode(new BigInteger(Mtobyte));
						else
							C = rsa.decode(new BigInteger(Mtobyte));
						Ctobyte = C.toByteArray();
					}
				}
			}
			/**
			 * �����������ֽ�����
			 */
			WriteFile f2 = new WriteFile();
			if(!encrypt_frame.TolerantPath_Box.isSelected())
			f2.createFile(encrypt_frame.OutputFile.getText(), Ctobyte);
			else {
				f2.createFile(encrypt_frame.TolerantPath_text.getText()+"\\"+filename, Ctobyte);
		}
	}
	}

	public void SignBtn() throws IOException {
		ReadFile f1 = new ReadFile();
		if (DigSign_frame.inputFile.getText().equals(""))
			JOptionPane.showMessageDialog(null, "δ���������ļ�·����");
		else if (DigSign_frame.RSAKey_text.getText().equals(""))
			JOptionPane.showMessageDialog(null, "δ����ǩ����Կ��");
		else if (DigSign_frame.n_text.getText().equals(""))
			JOptionPane.showMessageDialog(null, "δָ��ǩ��ģ����");
		else if (DigSign_frame.RSAkey_Lab.getText().contains("��"))
			JOptionPane.showMessageDialog(null, "��ʹ��˽Կǩ����");
		else {
			Mtobyte = f1.getContent(DigSign_frame.inputFile.getText());
			if (DigSign_frame.md5_Btn.isSelected()) {
				md5 = new MD5();
				md5.digest(Mtobyte);
				Signbyte = md5.outbyteArray();
				CString = md5.outString();
				filename = DigSign_frame.selectedFilename+".md5";
			} else if (DigSign_frame.sha_Btn.isSelected()) {
				sha = new SHA();
				String MString = toStringMethod(Mtobyte);
				System.out.println("MString:"+MString);
				sha.encrypt(MString);
				Signbyte = sha.toByteArray();
				CString = sha.toString().toUpperCase();
				filename = DigSign_frame.selectedFilename+".sha";
			}
			rsa = new RSA();
			e = new BigInteger(DigSign_frame.RSAKey_text.getText(), 16);// �˴��������˽Կ������ֵ��rsa�������e
			rsa.sete(e);
			n = new BigInteger(DigSign_frame.n_text.getText(), 16);
			rsa.setn(n);

			DigSign_frame.textPane.setText(CString);
			BigInteger C;
			C = rsa.encode(new BigInteger(Signbyte));
			Signenc = C.toByteArray();
			
			Signenc_len = (byte) Signenc.length;
			Ctobyte = new byte[Signenc.length + Mtobyte.length + 1];
			for (int i = 0; i < Mtobyte.length; i++)
				Ctobyte[i] = Mtobyte[i];
			for (int i = 0; i < Signenc.length; i++)
				Ctobyte[i + Mtobyte.length] = Signenc[i];
			Ctobyte[Signenc.length + Mtobyte.length] = Signenc_len;
			WriteFile f2 = new WriteFile();
			if(!DigSign_frame.TolerantPath_Box.isSelected())
			f2.createFile(DigSign_frame.outputFile.getText(), Ctobyte);
			else {
				f2.createFile(DigSign_frame.Tolerantpath_text.getText()+"\\"+filename, Ctobyte);
			}
			md5 = null;
			rsa = null;
			sha = null;
			CString = null;
		}
	}

	public void VeriBtn() throws IOException {
		if (rsa == null)
			JOptionPane.showMessageDialog(null, "���ȶ�ȡǩ����");
		else {
			DigSign_frame.textPane_1.setText(CString);
			System.out.println(CString);
			if (CString.equals(veri_String))
				JOptionPane.showMessageDialog(null, "��֤ͨ��!");
			else
				JOptionPane.showMessageDialog(null, "��֤��ͨ��!");
		}
	}

	public void read_Btn() throws IOException {
		ReadFile f1 = new ReadFile();
		if (DigSign_frame.inputFile.getText().equals(""))
			JOptionPane.showMessageDialog(null, "δ���������ļ�·����");
		if (DigSign_frame.RSAKey_text.getText().equals(""))
			JOptionPane.showMessageDialog(null, "δ������֤��Կ��");
		if (DigSign_frame.n_text.getText().equals(""))
			JOptionPane.showMessageDialog(null, "δָ��ǩ��ģ����");
		if (DigSign_frame.RSAkey_Lab.getText().contains("˽"))
			JOptionPane.showMessageDialog(null, "��ʹ�ù�Կ��֤ǩ����");
		else {
			Mtobyte = f1.getContent(DigSign_frame.inputFile.getText());
			M_len = Mtobyte.length;
			rsa = new RSA();
			d = new BigInteger(DigSign_frame.RSAKey_text.getText(), 16);
			rsa.setd(d);
			n = new BigInteger(DigSign_frame.n_text.getText(), 16);
			rsa.setn(n);
			Signenc_len = (byte) Mtobyte[M_len - 1];
			Signenc = new byte[Signenc_len];
			byte[] M = new byte[M_len - 1 - Signenc_len];
			veri_String = "";
			for (int i = 0; i < M_len - 1 - Signenc_len; i++)
				M[i] = Mtobyte[i];
			for (int i = 0; i < Signenc_len; i++)
				Signenc[i] = Mtobyte[M_len - Signenc_len - 1 + i];
			BigInteger C;
			C = rsa.decode(new BigInteger(Signenc));
			Sign = C.toByteArray();

			if (DigSign_frame.md5_Btn.isSelected()) {
				md5 = new MD5();
				if (Sign.length != 32)
					JOptionPane.showMessageDialog(null, "��֤��ͨ��!");
				else {
					for (int i = 0; i < 32; i++)
						veri_String += Integer.toHexString(0xFF & (int) Sign[i]).toUpperCase();// ��д�����ĸ
				}
				DigSign_frame.textPane.setText(veri_String);// veri_String�Ƕ�ȡ��ǩ��
				System.out.println(veri_String);
				md5.digest(M);
				Ctobyte = md5.outbyteArray();
				CString = md5.outString().toUpperCase();// CString�����¼����ǩ��
			} else if (DigSign_frame.sha_Btn.isSelected()) {
				sha = new SHA();
				if (Sign.length != 40)
					JOptionPane.showMessageDialog(null, "��֤��ͨ��!");
				else {
					for (int i = 0; i < 40; i++)
						veri_String += Integer.toHexString(0x0F & (int) Sign[i]).toUpperCase();// ��д�����ĸ
				}
				DigSign_frame.textPane.setText(veri_String);
				System.out.println(veri_String);
				String MString = toStringMethod(M);
				System.out.println(MString);
				sha.encrypt(MString);
				Ctobyte = sha.toByteArray();
				CString = sha.toString().toUpperCase();
				System.out.println(Ctobyte.length + "," + CString.length());
			}
		}
	}

	public static int[] byteArrayTointArray(byte[] b) {
		int[] in1 = new int[b.length];
		for (int i = 0; i < in1.length; i++) {
			in1[i] = b[i];
		}
		return in1;
	}

	public static byte[] intArrayTobyteArray(int[] in) {
		byte[] b1 = new byte[in.length];
		for (int i = 0; i < b1.length; i++) {
			b1[i] = (byte) in[i];
		}
		return b1;
	}

	public static int[] byteArrayToBinaryintArray(byte[] in) {
		int[] m = new int[in.length * 8];// ����ĳ�����length,��������
		for (int i = 0; i < in.length; i++) {
			String binaryStr = Integer.toBinaryString((in[i] & 0xFF) + 0x100).substring(1);// ȥ����λ
			for (int j = 0; j < binaryStr.length(); j++) {
				m[i * 8 + j + 8 - binaryStr.length()] = Integer.parseInt(binaryStr.substring(j, j + 1));
			}
			for (int k = 0; k < (8 - binaryStr.length()); k++) {
				m[i * 8 + k] = 0;
			}
		}
		return m;
	}

	public static int[] StringToBinaryintArray(String s) {
		int[] n = new int[8];
		int[] m = new int[64];
		for (int i = 0; i < s.length(); i++) {
			n[i] = Integer.parseInt(s.substring(i, i + 1));
			String binaryStr = java.lang.Integer.toBinaryString(n[i]);
			for (int j = 0; j < binaryStr.length(); j++) {
				m[i * 8 + j + 8 - binaryStr.length()] = Integer.parseInt(binaryStr.substring(j, j + 1));
			}
			for (int k = 0; k < (8 - binaryStr.length()); k++) {
				m[i * 8 + k] = 0;
			}
		}
		return m;
	}
public static byte[] StringToByteArray(String str) {
	char[] s = str.toCharArray();
	int tmp;
	int[] outin = new int[str.length()];
	byte[] out = new byte[str.length()/2];
	for (int i = 0; i < s.length; i++) {
		tmp = 0;
		if (s[i] <= '9') {
			tmp = s[i] - '0';
		} else if (s[i] <= 'F') {
			tmp = s[i] - 'A' + 10;
		} else {
			tmp = s[i] - 'a' + 10;
		}
		outin[i] = tmp;
	}
	for (int i = 0; i < s.length/2; i++) {
		out[i] = (byte)((outin[2*i]<<4)+outin[2*i+1]);
	}
	return out;
}
	private static String toStringMethod(int[] arr) {
		// �Զ���һ���ַ���������
		StringBuilder sb = new StringBuilder();
		// ����int���飬����int�����е�Ԫ��ת�����ַ������浽�ַ���������ȥ
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	public static String toStringMethod(byte[] arr) {
		String sb ="";
		for (int i = 0; i < arr.length; i++) {
			sb+=Integer.toHexString(0x0F&arr[i]>>4);
			sb+=Integer.toHexString(0x0F&arr[i]);
		}
		return sb;
	}

	public static String getProjectpath() {
		String path = System.getProperty("user.dir");
		return path;
	}
}
