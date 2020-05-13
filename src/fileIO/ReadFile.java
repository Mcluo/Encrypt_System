package fileIO;
import java.io.*;
public class ReadFile {
	//TODO:判断文件是否可读（用try..什么什么语句）canRead()
	public byte[] getContent(String filepath) throws IOException{
		File file = new File(filepath);
		 long fileSize = file.length();
	byte[] buffer = new byte[(int) fileSize];
	  if (fileSize > Integer.MAX_VALUE) {
          System.out.println("file too big...");
          return null;
	  }
	  FileInputStream fi = new FileInputStream(file);  
	  int offset = 0;
      int numRead = 0;
      while (offset < buffer.length
      && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
          offset += numRead;
      }
      if (offset != buffer.length) {
          throw new IOException("Could not completely read file "
                      + file.getName());
          }
          fi.close();
          return buffer;
	    }
	}

