package encrypt_algo;

import org.junit.jupiter.api.Test;

public class SHA_test {
	@Test
	/**
	 * PAD�Ĺ����ǿ��԰���Ϣת���ɶ������ַ���
	 */
	public void testPAD() {
		SHA sha = new SHA();
//		StringBuffer sb = sha.PAD("12345678901234567");
//		System.out.print("����Ϊ"+sb.length()+","+sb.toString());
		StringBuffer temp = new StringBuffer(Integer.toBinaryString('1'));
		System.out.print(temp.toString());
	}
	@Test
	public void testtoStringMethod() {
		
	}
}
