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
        			JOptionPane.showMessageDialog(null, "失败");
        	}
        sha.encrypt(x2);
        String hexDigest2 = sha.toString();
        System.out.println("empty:" + hexDigest1 + "\nstring:" + hexDigest2);
    }

    // 补位
    public StringBuffer PAD(String x) {//一个字符转化成8为二进制字符串。
        int ml; // 信息的长度
        StringBuffer mbStr = new StringBuffer(); 
        for (int i = 0; i < x.length(); ++i) {
            StringBuffer temp = new StringBuffer(Long.toBinaryString(x.charAt(i))); // 把信息转换成二进制字符串
            while (temp.length() < 8) {
                temp.insert(0, 0);//不断添加0
            }
            mbStr.append(temp);
        }
        ml = mbStr.length();

        //计算补0的长度
        int d = 447 - ml; 
        while (d < 0) {
            d += 512;
        }

        //把信息的二进制表示长度放在最后
        StringBuffer l = new StringBuffer(Long.toBinaryString(ml));
        while (l.length() < 64) {
            l.insert(0, 0);
        }

        //开始补位
        mbStr.append(1);
        for (int i = 0; i < d; ++i) {
            mbStr.append(0);
        }
        mbStr.append(l);
        return mbStr;
    }

    //左循环移位
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

        //按照512bit一组将mbStr分组
        int groupNum = mbStr.length() / 512;
        StringBuffer[] mbStrGroup = new StringBuffer[groupNum];
        for (int i = 0; i < groupNum; ++i) {
            mbStrGroup[i] = new StringBuffer(mbStr.substring(i * 512, (i + 1) * 512));
        }

        //计算消息摘要
        for (int i = 0; i < groupNum; ++i) {
            StringBuffer[] w = new StringBuffer[80];

            //初始化 ABCDE
            long a = h[0];
            long b = h[1];
            long c = h[2];
            long d = h[3];
            long e = h[4];

            //初始化 w0 - w15
            for (int j = 0; j < 16; ++j) {
                w[j] = new StringBuffer(mbStrGroup[i].substring(j * 32, (j + 1) * 32));
            }

            //初始化 w16 - w79
            for (int j = 16; j < 80; ++j) {
                w[j] = ROTL(new StringBuffer(Long
                        .toBinaryString(Long.parseLong(w[j - 3].toString(), 2) ^ Long.parseLong(w[j - 8].toString(), 2)
                                ^ Long.parseLong(w[j - 14].toString(), 2) ^ Long.parseLong(w[j - 16].toString(), 2))),
                        1);
            }

            //执行 0 - 79 循环
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
         * 返回字符串
         */
        public String toString() {
        	String C ="";
        	byte[] out = toByteArray();
        	for(int i=0;i<40;i++)
				C+= Integer.toHexString(0x0F&(int)out[i]).toUpperCase();
        	return C;
    }
        /**
         * 返回字节流
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