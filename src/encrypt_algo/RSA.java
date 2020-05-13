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
            n = p.multiply(q); // n = p * q;//totient =(p-1)*(q-1)即 (n)
            totient = (p.subtract(BigInteger.valueOf(1)).multiply(q
                    .subtract(BigInteger.valueOf(1))));//BigInteger.substract().multiply()这种形式是允许的
            e = getE();//选择公钥
            BigInteger y = egcd(totient, e)[1];
            d = y.mod(totient); //产生私钥
        }
    public BigInteger getE() {
        // 这里以totient/4为种子，选取一个素数作为公钥
        return totient.divide(BigInteger.valueOf(4)).nextProbablePrime();//nextProbablePrime()返回比this大的可能为素数的第一个整数（此方法返回的数是合数的概率不超出 2-100）
    }
    // 扩展的Euclid算法，目的：算出e-1 mod n
    public static BigInteger[] egcd(BigInteger d1, BigInteger d2) {
        BigInteger[] ret = new BigInteger[3];
        BigInteger u = BigInteger.valueOf(1), u1 = BigInteger.valueOf(0);
        BigInteger v = BigInteger.valueOf(0), v1 = BigInteger.valueOf(1);//u=1;u1=0;v=0;v1=1.
        if (d2.compareTo(d1) > 0) {
            BigInteger tem = d1;
            d1 = d2;
            d2 = tem;
        }
        while (d2.compareTo(BigInteger.valueOf(0)) != 0) {//d2不等于0，则执行循环
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
            ret[2] = d1;//最大公因子
        }
        return ret;
    }
    // 加密
    public BigInteger encode(BigInteger d) {
        return d.modPow(this.e, this.n);
    }//这里的d是明文
    // 解密
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

