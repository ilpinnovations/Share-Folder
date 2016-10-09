


import java.io.File;
import java.io.*; 
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

public class RecursiveFileDisplay {
	  int flag=0;
	  int flag2=0; 
	  String ShareData;
	 boolean disc(String dir,String ShareData,String resultDir)throws Exception {           //Socket socket
		File currentDir = new File(dir); // current directory		
		this.ShareData=ShareData;
	   return displayDirectoryContents(currentDir,dir,resultDir);	
	}
   
	 public boolean displayDirectoryContents(File dir,String directory,String resultDir){
			try {
			  
			   String data;
				File[] files = dir.listFiles();
				for (File file : files) {
					if (file.isDirectory()) {
						//System.out.println("directory:" + file.getCanonicalPath());
						displayDirectoryContents(file, directory,resultDir);
					} else {
					    
						data=file.getCanonicalPath();
						data=data.replace(ShareData,"");
						System.out.println(data);
						 writhingFile(data,resultDir);
						 flag2++;
						 //System.out.println(i++);
						//System.out.println("     file:" + file.getCanonicalPath());
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(flag2>0)
				return true;
			else
				return false;
			
		}
	 
	public void writhingFile(String data,String resultDir) throws IOException{	
	if(flag==0){
	flag++;
	FileWriter fw = new FileWriter(resultDir,false);
	fw.write(data);
	fw.write("\r\n");
	fw.close();
	}else{
	
	FileWriter fw = new FileWriter(resultDir,true);
	fw.write(data);
	fw.write("\r\n");
	fw.close(); }
	}

}
