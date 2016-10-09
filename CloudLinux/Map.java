


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Map {
	
	public static void main(String[] args)throws IOException{
        String ShareData=System.getProperty("user.home") + "/Desktop/Share/";  
	String tmpZipMetadata=ShareData+"System Data/tmp/extract/Result.txt";
        String tmpZipExtractDirectory=ShareData+"System Data/tmp/extract";
        String tempdata=ShareData+"System Data/tmp"; 
        Map mapping=new Map();
	mapping.base(tmpZipMetadata, ShareData, tmpZipExtractDirectory, tempdata);
	}
    public void base(String Result,String Share,String extract,String delete) throws IOException{
    	
    	String result=Result;                             //"C:\\Users\\TEMP\\ClientWorkspace\\Client\\tmp\\extract\\result.txt";
    	String sharefolder=Share;          //"C:\\Users\\TEMP\\Desktop\\Cloud_Client\\";
    	System.out.println(result);
    	System.out.println(sharefolder);
    	String take=extract;               //"tmp\\extract\\";
    	if(System.getProperty("os.name").contains("Windows"))
    		take=take+"\\";
    	else
    		take=take+"/";
    	
    	
    	System.out.println(extract);
    	System.out.println(delete);
    	ArrayList<String> data=getContents(result);
         for(String k:data){
            System.out.println(k);
         }
    	ArrayList<String> resultPath = new ArrayList<String>();
    	ArrayList<String> resultFile = new ArrayList<String>();
    	ArrayList<String> source = new ArrayList<String>();
    	for(String k:data){
    		
    		//k=k.replace("\\", "\\\\");
    		
    		source.add(take+k);
    		k=sharefolder+k;
    		System.out.println(k);
                String path;
    		
              
                if(System.getProperty("os.name").contains("Windows")){ 
                	
                   resultFile.add(k.replace("/","\\"));
                   
                 }
                 else{
                	  
                    resultFile.add(k.replace("\\","/"));
                   
                }
    		
    	}
          System.out.println("------------------File------------------");
         for(String k:resultFile){
            System.out.println(k);
            if(System.getProperty("os.name").contains("Windows"))
                resultPath.add(k.substring(0,k.lastIndexOf("\\")));
            else
            	resultPath.add(k.substring(0,k.lastIndexOf("/")));
         }
         System.out.println("------------------Path------------------");
         
         
         
        for(String k:resultPath){
            System.out.println(k);
        }
    	
       
       createDirectory(resultPath);
        int i=0;
        for(String k:resultFile){
        	String sou=source.get(i);
        	String res=k;
        	 System.out.println("");
        	if(System.getProperty("os.name").contains("Windows")){   
        		sou=sou.replace("/", "\\");
        		res=res.replace("/", "\\");
        	}else{
        		sou=sou.replace("\\", "/");
        		res=res.replace("\\", "/");
        		
        	
        	 }
        	
        	System.out.println("Source: "+sou);
        	System.out.println("Result : "+res);
        	copyDirectory(new File(sou),new File(res));
        	i++;
       
        
     }
       deleteFile(new File(delete));               //"tmp"));
       File n=new File(delete);               //"tmp"));
        n.mkdir();
    }
    
    void deleteFile(File element) {
        if (element.isDirectory()) {
            for (File sub : element.listFiles()) {
                deleteFile(sub);
            }
        }
        element.delete();
    }
    
    
   void createDirectory(ArrayList<String> path)throws IOException{
	   for(String k:path){
		   File file=new File(k);
		   if(!file.exists()){
			   file.mkdirs();
		   }
	   }
   } 
    void copyDirectory(File sourceLocation , File targetLocation)
		    throws IOException {

	       
	   
	   
	   
	   
	   			if (sourceLocation.isDirectory()) {
		            if (!targetLocation.exists()) {
		                targetLocation.mkdir();
		            }

		            String[] children = sourceLocation.list();
		            for (int i=0; i<children.length; i++) {
		                copyDirectory(new File(sourceLocation, children[i]),
		                        new File(targetLocation, children[i]));
		            }
		        } else {

		            InputStream in = new FileInputStream(sourceLocation);
		            OutputStream out = new FileOutputStream(targetLocation);

		            // Copy the bits from instream to outstream
		            byte[] buf = new byte[1024];
		            int len;
		            while ((len = in.read(buf)) > 0) {
		                out.write(buf, 0, len);
		            }
		            in.close();
		            out.close();
		        }
		    }
    ArrayList<String> getContents(String file) throws IOException{
	    File fileName = new File(file);
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
}
