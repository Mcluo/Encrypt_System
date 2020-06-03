package encrypt_algo;

import javax.swing.JOptionPane;

public class SHA {
    private long[] h = {0x67452301L,0xEFCDAB89L,0x98BADCFEL,0x10325476L,0xC3D2E1F0L};

    public static void main(String[] args) {
        String x1 = "";
        String x2 = "The quick brown fox jumps over the lazy dog";
        SHA sha = new SHA();
        sha.encrypt(x1);
        String hexDigest1 = sha.toString();
        byte[] hexdigest1 = sha.toByteArray();
        if(hexdigest1.length!=40)
        	JOptionPane.showMessageDialog(null, "wrong");
        	else
        	{String CString ="";
        		for(int i =0;i<40;i++)
        	CString+=Integer.toHexString(0xFF&(int)hexdigest1[i]).toUpperCase();
        		if(!CString.equals(hexDigest1.toUpperCase()))
        			JOptionPane.showMessageDialog(null, "ʧ��");
        	}
        sha.encrypt(x2);
        String hexDigest2 = sha.toString();
        System.out.println("empty:" + hexDigest1 + "\nstring:" + hexDigest2);
    }

    // ��λ
    public StringBuffer PAD(String x) {//һ���ַ�ת����8Ϊ�������ַ�����
        int ml; // ��Ϣ�ĳ���
        StringBuffer mbStr = new StringBuffer(); 
        for (int i = 0; i < x.length(); ++i) {
            StringBuffer temp = new StringBuffer(Long.toBinaryString(x.charAt(i))); // ����Ϣת���ɶ������ַ���
            while (temp.length() < 8) {
                temp.insert(0, 0);//�������0
            }
            mbStr.append(temp);
        }
        ml = mbStr.length();

        //���㲹0�ĳ���
        int d = 447 - ml; 
        while (d < 0) {
            d += 512;
        }

        //����Ϣ�Ķ����Ʊ�ʾ���ȷ������
        StringBuffer l = new StringBuffer(Long.toBinaryString(ml));
        while (l.length() < 64) {
            l.insert(0, 0);
        }

        //��ʼ��λ
        mbStr.append(1);
        for (int i = 0; i < d; ++i) {
            mbStr.append(0);
        }
        mbStr.append(l);
        return mbStr;
    }

    //��ѭ����λ
    public StringBuffer ROTL(StringBuffer temp, int s) {
        while (temp.length() < 32) {
            temp.insert(0, 0);
        }

        for (int i = 0; i < s; ++i) {
            temp.append(temp.charAt(0));
            temp.deleteCharAt(0);
        }
        return temp;
    }

    // SHA-1
    public void encrypt(String x) {
 

        StringBuffer mbStr = PAD(x);

        //����512bitһ�齫mbStr����
        int groupNum = mbStr.length() / 512;
        StringBuffer[] mbStrGroup = new StringBuffer[groupNum];
        for (int i = 0; i < groupNum; ++i) {
            mbStrGroup[i] = new StringBuffer(mbStr.substring(i * 512, (i + 1) * 512));
        }

        //������ϢժҪ
        for (int i = 0; i < groupNum; ++i) {
            StringBuffer[] w = new StringBuffer[80];

            //��ʼ�� ABCDE
            long a = h[0];
            long b = h[1];
            long c = h[2];
            long d = h[3];
            long e = h[4];

            //��ʼ�� w0 - w15
            for (int j = 0; j < 16; ++j) {
                w[j] = new StringBuffer(mbStrGroup[i].substring(j * 32, (j + 1) * 32));
            }

            //��ʼ�� w16 - w79
            for (int j = 16; j < 80; ++j) {
                w[j] = ROTL(new StringBuffer(Long
                        .toBinaryString(Long.parseLong(w[j - 3].toString(), 2) ^ Long.parseLong(w[j - 8].toString(), 2)
                                ^ Long.parseLong(w[j - 14].toString(), 2) ^ Long.parseLong(w[j - 16].toString(), 2))),
                        1);
            }

            //ִ�� 0 - 79 ѭ��
            long mod = (long) Math.pow(2, 32);
            for (int j = 0; j < 80; ++j) {
                Long f, k;
                if (j >= 0 && j <= 19) {
                    f = (b & c) | ((~b) & d);
                    k = 0x5A827999L;
                } else if (j >= 20 && j <= 39) {
                    f = b ^ c ^ d;
                    k = 0x6ED9EBA1L;

                } else if (j >= 40 && j <= 59) {
                    f = (b & c) | (b & d) | (c & d);
                    k = 0x8F1BBCDCL;
                } else {
                    f = b ^ c ^ d;
                    k = 0xCA62C1D6L;
                }
                long temp = (Long.parseLong(ROTL(new StringBuffer(Long.toBinaryString(a)), 5).toString(), 2) + f + e
                        + Long.parseLong(w[j].toString(), 2) + k) % mod;
                e = d;
                d = c;
                c = Long.parseLong(ROTL(new StringBuffer(Long.toBinaryString(b)), 30).toString(), 2);
                b = a;
                a = temp;
            }
            h[0] = (h[0] + a) % mod;
            h[1]= (h[1] + b) % mod;
            h[2] = (h[2]+ c) % mod;
            h[3] = (h[3] + d) % mod;
            h[4]= (h[4] + e) % mod;
        }
    }
        /**
         * �����ַ���
         */
        public String toString() {
        	String C ="";
        	byte[] out = toByteArray();
        	for(int i=0;i<40;i++)
				C+= Integer.toHexString(0x0F&(int)out[i]).toUpperCase();
        	return C;
    }
        /**
         * �����ֽ���
         */
        public byte[] toByteArray() {
        	byte[] out = new byte[40];
        	for (int i=0;i<5;i++) {
        		for(int j =0;j<8;j++) {
        			out[j+i*8] = (byte)(h[i]>>(4*(7-j))&0x0F);
        		}
        	}
        	return out;
        }
}