package fileIO;
import java.io.*;
public class test{
	public static int[] Mtextbi;
	public static void Inputtest() throws IOException{
		File f=new File("D:\\�ļ�\\�ĵ�\\�齫����.docx");
	try (InputStream input = new FileInputStream(f)) {
        long n = f.length();
        System.out.println(n);
        int[] N = new int[(int)n];
        int i=0;
        while ((N[i] = input.read()) != -1) {
           i++;
}
}
	}
	public static void main(String[] args) throws IOException {
		Inputtest();
//		int[] N = new int[]
	}
}