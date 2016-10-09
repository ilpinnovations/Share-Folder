

import java.io.File;
import java.net.Socket;

public class CreateDirectory {
	
void dataDirectory(String data,String tempdata,String upload,String delete,String update){
 
	File datafile = new File(data);
	File tempfile = new File(tempdata);
	File uploadfile =new File(upload);
	File deletefile = new File(delete);
	File updatefile = new File(update);
	if(!datafile.exists())
		datafile.mkdirs();
	if(!tempfile.exists())
		tempfile.mkdirs();
	if(!uploadfile.exists())
		uploadfile.mkdirs();
	if(!deletefile.exists())
		deletefile.mkdirs();
	if(!updatefile.exists())
		updatefile.mkdirs();
	
    }


}
