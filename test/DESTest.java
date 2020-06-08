import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import encrypt_algo.DES;
import fileIO.File_Obj;

public class DESTest {
	@Disabled
	@Test
	public void testDES() {
		String s = "AEdab1234e";
		byte[] sb = File_Obj.StringToByteArray(s);
		String out = File_Obj.toStringMethod(sb);
		System.out.print(out);
	}
	
	@Test
	public void testbytetoString() {
		DES des = new DES();
		String MS = "123456789";
		byte[] M = MS.getBytes();
		byte[] C = des.deal(M, "12345678", 1);
		String crypt_text = File_Obj.toStringMethod(C).toUpperCase();
		C = File_Obj.StringToByteArray(crypt_text);
		M = des.deal(C,"12345678",0);
		String origin_text = new String(M);
	}
}
