package encrypt_algo;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
        private BigInteger p = null;
        private BigInteger q = null;
        private BigInteger n = null;
        private BigInteger totient = null;
        private BigInteger e = null;
        private BigInteger d = null;
        public RSA(BigInteger p, BigInteger q) {
            this.p = p;
            this.q = q;
            n = p.multiply(q); // n = p * q;//totient =(p-1)*(q-1)�� (n)
            totient = (p.subtract(BigInteger.valueOf(1)).multiply(q
                    .subtract(BigInteger.valueOf(1))));//BigInteger.substract().multiply()������ʽ�������
            e = getE();//ѡ��Կ
            BigInteger y = egcd(totient, e)[1];
            d = y.mod(totient); //����˽Կ
        }
    public BigInteger getE() {
        // ������totient/4Ϊ���ӣ�ѡȡһ��������Ϊ��Կ
        return totient.divide(BigInteger.valueOf(4)).nextProbablePrime();//nextProbablePrime()���ر�this��Ŀ���Ϊ�����ĵ�һ���������˷������ص����Ǻ����ĸ��ʲ����� 2-100��
    }
    // ��չ��Euclid�㷨��Ŀ�ģ����e-1 mod n
    public static BigInteger[] egcd(BigInteger d1, BigInteger d2) {
        BigInteger[] ret = new BigInteger[3];
        BigInteger u = BigInteger.valueOf(1), u1 = BigInteger.valueOf(0);
        BigInteger v = BigInteger.valueOf(0), v1 = BigInteger.valueOf(1);//u=1;u1=0;v=0;v1=1.
        if (d2.compareTo(d1) > 0) {
            BigInteger tem = d1;
            d1 = d2;
            d2 = tem;
        }
        while (d2.compareTo(BigInteger.valueOf(0)) != 0) {//d2������0����ִ��ѭ��
            BigInteger tq = d1.divide(d2); // tq = d1 / d2
            BigInteger tu = u;
            u = u1;
            u1 = tu.subtract(tq.multiply(u1)); // u1 =tu - tq * u1
            BigInteger tv = v;
            v = v1;
            v1 = tv.subtract(tq.multiply(v1)); // v1 = tv - tq * v1
            BigInteger td1 = d1;
            d1 = d2;
            d2 = td1.subtract(tq.multiply(d2)); // d2 = td1 - tq * d2
            ret[0] = u;
            ret[1] = v;
            ret[2] = d1;//�������
        }
        return ret;
    }
    // ����
    public BigInteger encode(BigInteger d) {
        return d.modPow(this.e, this.n);
    }//�����d������
    // ����
    public BigInteger decode(BigInteger c) {
        return c.modPow(this.d, this.n);
    }
    public BigInteger findp_q(int bitLength, Random rnd){
            BigInteger p=null;
            p=BigInteger.probablePrime(bitLength,rnd);
            return p;
    }
/*    public String loadPrivateKeyByFile(String path){

    }*/
}

