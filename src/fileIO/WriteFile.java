package fileIO;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class WriteFile {
	// ��byte����д���ļ�
	public void createFile(String path, byte[] content) throws IOException {
		if (path.equals(""))
			JOptionPane.showMessageDialog(null, "���������ļ�·��!");
		FileOutputStream fos = new FileOutputStream(path);
		fos.write(content);
		fos.close();
	}

	/**
	 * 
	 * @param ������
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("D:\\�ļ�\\����\\QQ\\��¼\\1846500641\\FileRecv\\����31.txt");
		fos.write("Hello".getBytes("UTF-8"));
		fos.close();
	}
}
