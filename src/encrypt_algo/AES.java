package encrypt_algo;

import java.util.Arrays;
public class AES {
	private static final int[] Sbox = new int[] {
	 /* 0     1     2     3     4     5     6     7     8     9     A     B     C     D     E     F  */
		0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76,
	    0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0,
	    0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15,
	    0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75,
	    0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84,
	    0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf,
	    0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8,
	    0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2,
	    0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73,
	    0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb,
	    0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79,
	    0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08,
	    0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a,
	    0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e,
	    0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf,
	    0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16
	};
	private static final int[] inv_Sbox = new int[] {
	 /* 0     1     2     3     4     5     6     7     8     9     A     B     C     D     E     F  */
	    0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb,
	    0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb,
	    0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e,
	    0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25,
	    0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92,
	    0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84,
	    0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06,
	    0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b,
	    0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73,
	    0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e,
	    0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b,
	    0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4,
	    0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f,
	    0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef,
	    0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61,
	    0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d
	};
	private static final long[] rcon = new long[] {	//�ֳ���ֵ��
	    0x01000000L, 0x02000000L, 0x04000000L, 0x08000000L, 0x10000000L, 
	    0x20000000L, 0x40000000L, 0x80000000L, 0x1b000000L, 0x36000000L
	};
	private static final int[] clo_mix_index = new int[]{ //�л��
		2, 3, 1, 1, 
		1, 2, 3, 1, 
		1, 1, 2, 3, 
		3, 1, 1, 2
	};
	private static final int[] inv_clo_mix_index = new int[] { //���л��
		0x0e, 0x0b, 0x0d, 0x09,  
		0x09, 0x0e, 0x0b, 0x0d,
        0x0d, 0x09, 0x0e, 0x0b,  
        0x0b, 0x0d, 0x09, 0x0e
    };
	private long[] w = new long[44];	//�����Կ��չ
	private int[] M = new int[16];		//����
	private int[] C = new int[16];		//����
	private int[] result = new int[16];	//���
	private int[] key = new int[16];	//��Կ
	
	private void key_expansion() {	//��Կ��չ
		int i;
		for (i = 0; i < 4; i++) {	//���ɳ�ʼ��Կ��ǰ4��
			w[i] = 0;
			for (int j = 0; j < 4; j++) {
				w[i] = w[i] << 8;//����8λ
				w[i] += key[i * 4 + j];
			}
		}
		for (i = 4; i < 44; i++) {	//��40����չ
			if (i % 4 == 0) {	//�� i Ϊ 4 �ı�����w[i] = w[i - 4] ^ T(w[i - 1])
				long tmp = w[i - 1];
				tmp = ((tmp & 0xff000000) >> 24) ^ ((tmp & 0x00ffffff) << 8);	//ѭ������ 1 �ֽ�
				long[] tmp1 = new long[4];
				tmp1[0] = ((tmp & 0xff000000) >> 24);
				tmp1[1] = ((tmp & 0x00ff0000) >> 16);
				tmp1[2] = ((tmp & 0x0000ff00) >> 8);
				tmp1[3] =  (tmp & 0x000000ff);
				for (int j = 0; j < 4; j++) {
					tmp1[j] = Sbox[(int)tmp1[j]];	//�ֽڴ���
				}
				tmp = 0;
				for (int j = 0; j < 4; j++) {
					tmp = tmp << 8;
					tmp += tmp1[j];
				}
				tmp ^= rcon[i / 4 - 1]; 	//�ֳ������
				w[i] = w[i - 4] ^ tmp;
			} else {	//���� w[i] = w[i - 4] ^ w[i - 1]
				w[i] = w[i - 4] ^ w[i - 1];
			}
		}
	}
	private void round_key (int n) {	//����Կ��
		long[] tmp = new long[4];
		for (int i = 0; i < 4; i++) {
			tmp[i] = 0;
			for (int j = 0; j < 4; j++) {
				tmp[i] = tmp[i] << 8;
				tmp[i] += result[i * 4 + j];
			}
			tmp[i] ^= w[4 * n + i];
			
			for (int j = 3; j >= 0; j--) {
				result[i * 4 + j] = (int)(tmp[i] & 0xff);
				tmp[i] = tmp[i] >> 8;
			}
		}
	}
	private int aes_x(int x, int ts)	//ģ 1 0001 1011 ��̫��
	{
	    while (ts-- > 0) {
	        x = ((0xff & (x << 1)) ^ (((x >> 7) & 1) * 0x1b));
	    }
	    return x;
	}
	private int aes_mul(int x, int y) {	//����ʽ�˷�
	    return ((((y >> 0) & 1) * aes_x(x, 0)) ^
	            (((y >> 1) & 1) * aes_x(x, 1)) ^
	            (((y >> 2) & 1) * aes_x(x, 2)) ^
	            (((y >> 3) & 1) * aes_x(x, 3)) ^
	            (((y >> 4) & 1) * aes_x(x, 4)) ^
	            (((y >> 5) & 1) * aes_x(x, 5)) ^
	            (((y >> 6) & 1) * aes_x(x, 6)) ^
	            (((y >> 7) & 1) * aes_x(x, 7)) );
	}
	
	private void byte_replace() {	//�ֽڴ���
		for (int i = 0; i < 16; i++) {
			result[i] = Sbox[result[i]];
		}
	} 
	private void row_shift() {	//����λ
		int[] tmp = new int[16];
		for (int i = 0; i < 4; i++) {
			tmp[4 * i] = result[4 * i];
			tmp[4 * i + 1] = result[(4 * i +  5) % 16];
			tmp[4 * i + 2] = result[(4 * i + 10) % 16];
			tmp[4 * i + 3] = result[(4 * i + 15) % 16];
		}
		for (int i = 0; i < 16; i++) {
			result[i] = tmp[i];
		}
	}
	private void col_mix() {	//�л�� ��������ˣ�
		int i, j, r;
		int[] tmp = new int[4];
	    for (i = 0; i < 4; i++) {
	        for (r = 0; r < 4; r++) {
	            tmp[r] = 0;
	            for (j = 0; j < 4; j++) {
	            	tmp[r] = tmp[r] ^ (0xff & (aes_mul(result[i * 4 + j], clo_mix_index[r * 4 + j])));
	            }
	        }
	        for (r = 0; r < 4; r++) {
	        	result[i * 4 + r] = tmp[r];
	        }
	    }
	}
	
	private void inv_byte_replace() {	//�����ֽڴ���
		for (int i = 0; i < 16; i++) {
			result[i] = inv_Sbox[result[i]];//Sbox[i]��ʾSbox�е�i��Ԫ��
		}
	}
	private void inv_row_shift() {	//��������λ
		int[] tmp = new int[16];
		for (int i = 0; i < 4; i++) {
			tmp[4 * i] = result[4 * i];
			tmp[4 * i + 1] = result[(4 * i + 13) % 16];
			tmp[4 * i + 2] = result[(4 * i + 10) % 16];
			tmp[4 * i + 3] = result[(4 * i +  7) % 16];
		}
		for (int i = 0; i < 16; i++) {
			result[i] = tmp[i];
		}
	}
	private void inv_col_mix() {	//�����л��
		int i, j, r;
		int[] tmp = new int[4];
	    for (i = 0; i < 4; i++) {
	        for (r = 0; r < 4; r++) {
	            tmp[r] = 0;
	            for (j = 0; j < 4; j++) {
	            	tmp[r] = tmp[r] ^ (0xff & (aes_mul(result[i * 4 + j], inv_clo_mix_index[r * 4 + j])));
	            }
	        }
	        for (r = 0; r < 4; r++) {
	        	result[i * 4 + r] = tmp[r];
	        }
	    }
	}
	
	public void encrypt () {
		for (int i = 0; i < 16; i++) {
			result[i] = M[i];
		}
		key_expansion();
		round_key(0);
		int i;
		for (i = 1; i < 10; i++) {
			byte_replace();
			row_shift();
			col_mix();
			round_key(i);
		}
		byte_replace();
		row_shift();
		round_key(i);
	}
	public void decrypt () {
		for (int i = 0; i < 16; i++) {
			result[i] = C[i];
		}
		key_expansion();
		round_key(10);
		int i;
		for (i = 9; i > 0; i--) {
			inv_row_shift();
			inv_byte_replace();
			round_key(i);
			inv_col_mix();
		}
		inv_byte_replace();
		inv_row_shift();
		round_key(i);
	}
	
	public void setM (byte[] str) { //�������� ���������е�����ת���ɶ�Ӧ��ASCII������
		for (int i = 0; i < 16; i++)
		M[i]=(int)str[i];
	}
//		char[] s = str.toCharArray();//���ַ���ת�����ַ����飬���Է���ÿһ���ַ�
//		int tmp;
//		for (int i = 0; i < 16; i++) {//��������Ϊʲô��32��charΪһ��״̬����
//			tmp = 0;
//			for (int j = 0; j < 2; j++) {
//				if (j == 1) {
//					tmp *= 16;
//				}
//				if (s[2 * i + j] <= '9') {
//					tmp += s[2 * i + j] - '0';
//				} else if (s[2 * i + j] <= 'F'){
//					tmp += s[2 * i + j] - 'A' + 10;
//				} else {
//					tmp += s[2 * i + j] - 'a' + 10;
//				} 
//			}
//			M[i] = tmp;
//		}
		
//	}
	public void setC (byte[] str) {//��������
		for (int i = 0; i < 16; i++) {
			C[i]=(int)str[i];
		}
	}
//		char[] s = str.toCharArray();
//		int tmp;
//		for (int i = 0; i < 16; i++) {
//			tmp = 0;
//			for (int j = 0; j < 2; j++) {
//				if (j == 1) {
//					tmp *= 16;
//				}
//				if (s[2 * i + j] <= '9') {
//					tmp += s[2 * i + j] - '0';
//				} else if (s[2 * i + j] <= 'F'){
//					tmp += s[2 * i + j] - 'A' + 10;
//				} else {
//					tmp += s[2 * i + j] - 'a' + 10;
//				} 
//			}
//			C[i] = tmp;
//		}
//	}
	public void setKey (String str) {
		char[] s = str.toCharArray();
		int tmp;
		for (int i = 0; i < 16; i++) {
			tmp = 0;
				if (s[i] <= '9') {
					tmp = s[i] - '0';
				} else if (s[i] <= 'F'){
					tmp = s[i] - 'A' + 10;
				} else {
					tmp= s[i] - 'a' + 10;
				} 
			key[i] = tmp;
		}
	}
	public String getResult () {
		String hexString = "";
		char[] hex = {
			'0', '1', '2', '3',
			'4', '5', '6', '7',
			'8', '9', 'a', 'b',
			'c', 'd', 'e', 'f'
		};
		StringBuilder ss = new StringBuilder();//stringbuilder�������string��������޸ĵ�ȱ����Ľ��������޸ĵ��ص����
		int bit;
		for (int i = 0; i < 16; i++) {
			bit = (result[i] & 0xf0) >>4;
			ss.append(hex[bit]);
			bit = result[i] & 0x0f;
			ss.append(hex[bit]);
		}
		hexString = ss.toString();
		return hexString;
	}
	public AES(int[] M,int[] key) {
		this.M=M;
		this.key=key;
	}
	public AES() {
		// TODO �Զ����ɵĹ��캯�����
	}
		
	public int[] AESEncrypt(byte[] mtext, String keytext) {
		// TODO �Զ����ɵķ������
		int n = mtext.length/16;
//		int[] c = new int[(n+1)*128];
			n=n+1;
			int[] c = new int[n*16];
			setKey(keytext);
		for (int i=0;i<n-1;i++) {
//			M=Arrays.copyOfRange(Mtext,i*64,(i+1)*64);
			byte[] m = Arrays.copyOfRange(mtext, i*16, (i+1)*16);
			setM(m);
			encrypt();
			for (int j=0;j<16;j++)
			c[i*16+j] = result[j];
//		for (int j=0;j<64;j++) {
//			c[j+i*64]=C[j];
		}
		byte[] m = new byte[16];
		for (int i=0;i<mtext.length%16;i++) //���һ�����ĵĲ�λ
			m[i] = mtext[(n-1)*16+i];
		for (int i=mtext.length%16;i<15;i++)
			m[i] = 0;
		m[15] = (byte) ((16-mtext.length%16)*8);
		setM(m);
		encrypt();
		for (int j=0;j<16;j++)
		c[(n-1)*16+j] = result[j];
//			M[i]=Mtext[64*(n-1)+i];
//			for (int j=0;j<56-Mtext.length%64;j++) {//����λ��56λ��01���λ�����һ���ֽ��Ǳ�ʾ��䳤�ȡ�
//			if (j%2==0)
//				M[Mtext.length%64+j]=0;
//			else
//				M[Mtext.length%64+j]=1;
//		}
//		String len= new String(Integer.toBinaryString(Mtext.length%64));//����䳤�ȱ�ʾ�ɶ�����
//		for (int k=8-len.length();k<8;k++) {
//			M[56+k]=Integer.parseInt(len.substring(k-8+len.length(),k-7+len.length()));
//		}
//		for (int k=0;k<8-len.length();k++) {
//			M[56+k]=0;
//		}
//		setM(M);
//		for(int k=0;k<64;k++)
//		c[64*(n-1)+k]=C[k];
//		return c;
		return c;
	}
	public int[] AESDeEncrypt(byte[] ctext, String keytext) {
		// TODO �Զ����ɵķ������
		int n = ctext.length/16;
		int[] m = new int[(n-1)*16+ctext.length%16];
		setKey(keytext);
		for (int i=0;i<n-1;i++) {
			byte[] c = Arrays.copyOfRange(ctext, i*16, (i+1)*16);
			setC(c);
			decrypt();
			for (int j=0;j<16;j++)
				m[i*16+j] = result[j];
		}
		byte[] c = new byte[16];
	for (int i=0;i<16;i++) 
		c[i] = ctext[(n-1)*16+i];
		setC(c);
		decrypt();		
		for (int i=0;i<ctext.length%16;i++) //���һ�����ĵĲ�λ
			m[(n-1)*16+i]=result[i];
		return m;
	}
}
