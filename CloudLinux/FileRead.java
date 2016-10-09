
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileRead {
	
	
	  ArrayList<String> getContents(String file) throws IOException{
		    File fileName = new File(file);
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			ArrayList<String> al = new ArrayList<String>();
			try {
	        
			
	        String line = br.readLine();

	        while (line != null) {
	           
	        	if(System.getProperty("os.name").contains("Windows"))
				   al.add(line.replace("/", "\\"));                                   //For windows
	        	else
	        		al.add(line.replace("\\", "/"));	                              //For Linux
	            
	            line = br.readLine();
	        }
			
			} catch(Exception e) {
	      
				e.printStackTrace();
			}
			return al;
		}
}
