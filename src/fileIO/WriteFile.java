package fileIO;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class WriteFile {
	// 将byte数组写入文件
	public void createFile(String path, byte[] content) throws IOException {
		if (path.equals(""))
			JOptionPane.showMessageDialog(null, "请键入输出文件路径!");
		FileOutputStream fos = new FileOutputStream(path);
		fos.write(content);
		fos.close();
	}

	/**
	 * 
	 * @param 测试用
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("D:\\文件\\下载\\QQ\\记录\\1846500641\\FileRecv\\问题31.txt");
		fos.write("Hello".getBytes("UTF-8"));
		fos.close();
	}
}
