package encrypt_algo;

import org.junit.jupiter.api.Test;

public class SHA_test {
	@Test
	/**
	 * PAD的功能是可以把信息转化成二进制字符串
	 */
	public void testPAD() {
		SHA sha = new SHA();
//		StringBuffer sb = sha.PAD("12345678901234567");
//		System.out.print("长度为"+sb.length()+","+sb.toString());
		StringBuffer temp = new StringBuffer(Integer.toBinaryString('1'));
		System.out.print(temp.toString());
	}
	@Test
	public void testtoStringMethod() {
		
	}
}
