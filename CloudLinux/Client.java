
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
   public static void main(String args[]){
    	Socket socket;
    	
       
     while(true){
    	try{
    		socket=new Socket("163.122.226.24",35621);
    		Communication com=new Communication(socket);
    		com.communicate();
    	}catch(Exception e){
    		                System.out.println("Exception in Client main method");
    	                   }
    	}
    	
   }
}

class Communication{
	   
	   Socket mySocket;
	   DataInputStream din;
	   DataOutputStream dout;
	   boolean flag=true;                                    //checking for creating directory
	   String data;
	   String tempdata;
	   String uploadMetadata;
	   String deletedata;
	   String updatedata;
	   String ShareData;
	   String tmpZipFilename;
	   String tmpZipMetadata;
	   String tmpZipExtractDirectory;
	   String clientUploadMetadata;
	   String serverUploadMetadata;
	   String uploadMetadataResult;
	   String zipEntryPathResult;
	   String Client="Client.txt";
	   String Server="Server.txt";
	   String Result="Result.txt";
	   String uploadServerMetadataZip;
	   String uploadClientMetadataZip;
	   
	   String deletePrevFile;
	   String deleteNewFile;
	   String deletePrevDirectory;
	   String deleteNewDirectory;
	   
	   IPFromSocket ipGet;
	   String IP;
	   CreateDirectory directory;
	   RecursiveFileDisplay fileDisplay;
	   ZipFiles zipping;
	   FileRead readfile;
	   String systemData;
	   UnZip unZip;
	   Map mapping;
	   CompareFile comparing;
	   EmptyFile empty;
	   ZipFiles zipfile;
	   String temp;
	   UnZip unzipping;
	   ZipMetadata zipmetadata;
	  
	   
	   Communication(Socket soc){
		   
		   try{
		       mySocket=soc;
		       din=new DataInputStream(mySocket.getInputStream());
	           dout=new DataOutputStream(mySocket.getOutputStream());
	           ipGet=new IPFromSocket();
	           IP=ipGet.getIP(mySocket);
	           
	           if(System.getProperty("os.name").contains("Windows")){                          //Windows
	        	   ShareData=System.getProperty("user.home") + "\\Desktop\\Cloud Share\\";
	        	   data=ShareData+"data";                                                      //data directory
	        	   tempdata=ShareData+"System Data\\tmp";                                      //temporary directory
	   		       systemData=ShareData+"System Data\\";
	   		       uploadServerMetadataZip=ShareData+"System Data\\Metadata\\"+IP+"_Upload\\Server.zip";
	   		       uploadClientMetadataZip=ShareData+"System Data\\Metadata\\"+IP+"_Upload\\Client.zip";
	        	   
	        	   uploadMetadata=ShareData+"System Data\\Metadata\\"+IP+"_Upload";
	   		   	   clientUploadMetadata=uploadMetadata+"\\Client.txt";
	   		   	   serverUploadMetadata=uploadMetadata+"\\Server.txt";
	   		   	   uploadMetadataResult=uploadMetadata+"\\Result.txt";
	   		       
	   		       deletedata=ShareData+"System Data\\Metadata\\"+IP+"_Delete";
	   		       updatedata=ShareData+"System Data\\Metadata\\"+IP+"_Update";
	   		       tmpZipFilename=ShareData+"System Data\\tmp\\data.zip";
	   		       tmpZipMetadata=ShareData+"System Data\\tmp\\extract\\Result.txt";
	   		       tmpZipExtractDirectory=ShareData+"System Data\\tmp\\extract";
	   		       
	   		       deletePrevFile="";
	   		       deleteNewFile= "";
	   		       
	           }else{
	        	   ShareData=System.getProperty("user.home") + "/Desktop/Cloud Share/";              //Linux
	        	   data=ShareData+"data";                                                      //data directory
	        	   tempdata=ShareData+"System Data/tmp";                                     //temporary directory
	        	   systemData=ShareData+"System Data/";
	        	   
	   		       uploadMetadata=ShareData+"System Data/Metadata/"+IP+"_Upload";
	   		       clientUploadMetadata=uploadMetadata+"/Client.txt";
	   		   	   serverUploadMetadata=uploadMetadata+"/Server.txt";
	   		   	   uploadMetadataResult=uploadMetadata+"/Result.txt";
	   		       
	   		   	   deletedata=ShareData+"System Data/Metadata/"+IP+"_Delete";
	   		       updatedata=ShareData+"System Data/Metadata/"+IP+"_Update";
	   		       tmpZipFilename=ShareData+"System Data/tmp/data.zip";
	   		       tmpZipMetadata=ShareData+"System Data/tmp/extract/Result.txt";
	   		       tmpZipExtractDirectory=ShareData+"System Data/tmp/extract/";
	   		       
	   		       
	   		       uploadServerMetadataZip=ShareData+"System Data/Metadata/"+IP+"_Upload/Server.zip";
	   		       uploadClientMetadataZip=ShareData+"System Data/Metadata/"+IP+"_Upload/Client.zip";
	   		       
	   		       
	           }
	           directory=new CreateDirectory();
	           directory.dataDirectory(data, tempdata, uploadMetadata, deletedata, updatedata);
	           fileDisplay=new RecursiveFileDisplay();
	           zipping=new ZipFiles();
	           readfile=new FileRead();
	           unZip=new UnZip();
	           mapping=new Map();
	           comparing=new CompareFile();
	           empty=new EmptyFile();
	           zipfile=new ZipFiles();
	           unzipping=new UnZip();
	           zipmetadata=new ZipMetadata();
	          
		      }catch(Exception e){
		    	  System.out.println("Exception in commenucation constructor");
		      }
		   
	   }
	   void communicate(){
		try{   
		     dout.writeUTF("Hi Server");
		     System.out.println("Server says : "+din.readUTF());		   
		   
		    /* String filename=din.readUTF();
		     System.out.println(filename);
		     if(System.getProperty("os.name").contains("Windows")){
				   
				   filename=filename.replace("/", "\\");
				   
			   }else{
				   filename=filename.replace("\\", "/");
			   }
		     System.out.println(filename);                                 transfer data from server
		     dout.writeUTF("Send Size");
		     String size=din.readUTF();
		     System.out.println(size);
		     receiveZip(filename,data,Integer.parseInt(size));
		     System.out.println("Sucess");*/
		     
		     
		     
		     
		     
		     
		     
		    
		     //Client to Server
		      dout.writeUTF("SEND METADATA1");
	           //server to client data transfer      
	            
			   
			    temp = din.readUTF();
			      String filename=null;
				 System.out.println("Server says : "+temp );
				   if(temp.equals("READY")){
					  dout.writeUTF("Debugging.....");
					   filename=din.readUTF();
					   System.out.println("File Name: " +filename);
					   
					   if(System.getProperty("os.name").contains("Windows")){
						   
						   filename=filename.replace("/", "\\");
						   
					   }else{
						   filename=filename.replace("\\", "/");
					   }
					   System.out.println("File Name: " +filename);
					   				   
				   }
				 temp=din.readUTF();
				 System.out.println(temp);
				  if(temp.equals("YES")){                                  //server receive data
					   int sizeMetadata=Integer.parseInt(din.readUTF());
					   System.out.println(sizeMetadata);
					    receiveZip(filename,uploadMetadata,sizeMetadata);
					   unzipping.unZipIt(uploadServerMetadataZip,uploadMetadata);
					   File n2=new File(uploadServerMetadataZip);
					   n2.delete();
				       fileDisplay.disc(data,ShareData,clientUploadMetadata);
				       comparing.compare(serverUploadMetadata,clientUploadMetadata,  uploadMetadataResult);
				        if(!empty.isEmpty(uploadMetadataResult)){ 
						   dout.writeUTF("NOT EMPTY");
						  
						   ArrayList<String> data1=readfile.getContents(uploadMetadataResult);
						   zipfile.zip(ShareData,uploadMetadataResult, Result, tmpZipFilename, data1);
						   System.out.println(din.readUTF());
						   dout.writeUTF(tmpZipFilename.replace(systemData,""));
						   n2=new File(tmpZipFilename);
						   System.out.println(din.readUTF());
						   dout.writeUTF(""+(int)n2.length());
						    sendZip(tmpZipFilename);
						  /* System.out.println(din.readUTF());
						   if(System.getProperty("os.name").contains("Windows")){
							   dout.writeUTF("WINDOWS");
						   }else{
							   dout.writeUTF("LINUX");
						   }
						   File n=new File(tmpZipFilename);
						   dout.writeUTF(Integer.toString((int)n.length()));
						   
						   System.out.println(din.readUTF());
						   sendZip(tmpZipFilename);
				    	  
						   */
					   }else{
						   dout.writeUTF("EMPTY");
					   }
				 }

		     
		     
		//	This below code is ok
			 //Server to Client Data transfer
		     temp=din.readUTF();
	            System.out.println("Server Say : "+temp);
	            
	               if(temp.equals("SEND METADATA")){
	            	dout.writeUTF("READY");
	            	
	            	System.out.println(din.readUTF());
	            	dout.writeUTF(uploadClientMetadataZip.replace(ShareData, ""));
	            	///System.out.println(din.readUTF());
	            	 if(fileDisplay.disc(data,ShareData,clientUploadMetadata)){
	            		dout.writeUTF("YES");
	            		zipmetadata.zip(clientUploadMetadata,Client,uploadClientMetadataZip);
	            		File n1=new File(uploadClientMetadataZip);
	            		dout.writeUTF(""+n1.length());
	            		sendZip(uploadClientMetadataZip);
					
						
	            		
	            		temp=din.readUTF();
	            		System.out.println(temp);
	            		if(temp.equals("NOT EMPTY")){
	          			  String file=din.readUTF();
	          			 if(System.getProperty("os.name").contains("Windows")){
	  					   
	  					   file=file.replace("/", "\\");
	  					   
	  				   }else{
	  					   file=file.replace("\\", "/");
	  				   }
	          			  System.out.println("Filename : "+file);
	          			  int size=Integer.parseInt(din.readUTF());
	          			  System.out.println(size);
	          			  receiveZip(file,tempdata,size);
	          			  //receive(file,tempdata);
	          			 // System.out.println("Anjan");
	          			  unzipping.unZipIt(tmpZipFilename,tmpZipExtractDirectory);
	          			 mapping.base(tmpZipMetadata, ShareData, tmpZipExtractDirectory, tempdata);
	            		}
	            	}else{
	            		dout.writeUTF("NO");
	            	}
	            }
		     
	   
		   
		   
		   
		   
		  
		   }catch(Exception e){
			                   System.out.println("Exception in communicate()"+e);
		                      }
	   
	   }
	   public void receiveZip(String fileName,String destination,int size)throws IOException{
        /*   if(OS.equals("WINDOWS")){ 
			  
                       System.out.println(fileName);
			  
		   }else{
			   
                        System.out.println(fileName);
				  
		   }*/
             if(System.getProperty("os.name").contains("Windows")){  
            	 fileName=fileName.substring(fileName.lastIndexOf("\\")+1);
                fileName = destination+"\\"+fileName;
             }else{
            	 fileName=fileName.substring(fileName.lastIndexOf("/")+1);
                fileName = destination+"/"+fileName;
             }
          File file=new File(fileName);
          FileOutputStream fos=new FileOutputStream(file);
          byte[] buffer=new byte[size];
          int read=0;
          int totalread=0;
          int remaining=size; 
          while((read=din.read(buffer,0,Math.min(buffer.length,remaining)))>0){
             totalread+=read;
             remaining-=read;
              fos.write(buffer,0,read);
          }
          fos.close();

}
	   public void sendZip(String fileName) throws IOException{
		   File myFile = new File(fileName);
		   FileInputStream fis = new FileInputStream(myFile);
		   byte[] buffer = new byte[(int) myFile.length()];
			
			while (fis.read(buffer) > 0) {
				dout.write(buffer);
			}
			
			fis.close();
	   }
	   public void receive(String fileName,String destination) throws IOException{
			  
	       if(System.getProperty("os.name").contains("Windows")){ 
			  fileName=fileName.substring(fileName.lastIndexOf("\\")+1);
			  fileName = destination+"\\"+fileName;
		   }else{
			   fileName=fileName.substring(fileName.lastIndexOf("/")+1);
				  fileName = destination+"/"+fileName;
		   }
	       System.out.println(fileName);
		   File f=new File(fileName);
		   FileOutputStream fout=new FileOutputStream(f);
            int ch;
            String temp;
            do
            {
                temp=din.readUTF();
                ch=Integer.parseInt(temp);
                if(ch!=-1)
                {
                    fout.write(ch);  
                   // System.out.println(ch);
                }
            }while(ch!=-1);
          try{
            fout.close();
          }catch(Exception e){System.out.println(e);}
	      
	     }
	   public void send(String fileName) throws IOException{                     //sending data
	    	
		   File f=new File(fileName);
		   FileInputStream fin=new FileInputStream(f);
	       int ch;
	       do
	       {
	           ch=fin.read();
	           dout.writeUTF(String.valueOf(ch));
	       }
	       while(ch!=-1);    
	       fin.close(); 
 
		   
	    }
	
}
