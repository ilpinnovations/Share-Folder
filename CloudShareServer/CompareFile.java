

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CompareFile {
    int flag=0;
	void compare(String lowFile,String highFile,String result) throws IOException{
		File fileName = new File(lowFile);        //"Metadata/127.0.0.1_Upload/Client.txt");
		File fileName2 = new File(highFile);        //"Metadata/127.0.0.1_Upload/Server.txt");       
		
		ArrayList<String> oldFile = getContents(fileName);
		ArrayList<String> newFile = getContents(fileName2);
		
		FileWriter fw = new FileWriter(result,false);
		fw.close();
			for(String path : newFile){
				if(!oldFile.contains(path))
					//System.out.println(path);
					writhingFile(path,result);
		
			}
			
			
	}
	
	   ArrayList<String> getContents(File fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		ArrayList<String> al = new ArrayList<String>();
		try {
        //StringBuilder sb = new StringBuilder();
		
        String line = br.readLine();

        while (line != null) {
            //sb.append(line);
        	
			al.add(line);
            //sb.append("\n");
            line = br.readLine();
        }
		/*for(String path : al)
			System.out.println(path);
		*/
		} catch(Exception e) {
        //br.close();
			e.printStackTrace();
		}
		return al;
	}
	
	 void writhingFile(String data,String result) throws IOException{
		if(flag==0){
			flag++;
			FileWriter fw = new FileWriter(result,false);//"Metadata/127.0.0.1_Upload/result.txt",false);
			fw.write(data);
			fw.write("\r\n");
			fw.close();
		}else{
			FileWriter fw = new FileWriter(result,true);//"Metadata/127.0.0.1_Upload/result.txt",true);
			fw.write(data);
			fw.write("\r\n");
			fw.close(); 
		}
	}
}
