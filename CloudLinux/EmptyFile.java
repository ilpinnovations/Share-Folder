

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class EmptyFile {
	boolean isEmpty(String file) throws IOException{
		//System.out.println(file);
	InputStream is = new FileInputStream(file);
	if (is.read() == -1) {
	    return true;
	} else {
		return false;
	    // The file is NOT empty
	}
  }
}
