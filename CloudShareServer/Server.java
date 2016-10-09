
import java.io.*;
import java.net.*;
import java.util.*;

public class Server{

  static  ArrayList<Socket> socketArray = new ArrayList<Socket>();


   public static void main(String args[])throws Exception{

    Socket soc;
    ServerSocket socket=new ServerSocket(35621);
    System.out.println("Server is up and Running");
    
    while(true){
    	  
       soc=socket.accept();
       System.out.println("Socket is connected with client "+soc.getRemoteSocketAddress());
       checkArrayList(soc);
       Communication com=new Communication(soc,socketArray);
       
     }
   }

   public static void checkArrayList(Socket soc){                                              //Checking for existing IP Client

     String main;
     String check=soc.getRemoteSocketAddress().toString();
     check=check.substring(check.lastIndexOf("/")+1,check.lastIndexOf(":"));
    // System.out.println("Connecting IP:  "+check);
     Socket removeSocketObject=null;
     for(Socket socket : socketArray)
        {

        main=socket.getRemoteSocketAddress().toString();
        main=main.substring(main.lastIndexOf("/")+1,main.lastIndexOf(":"));

        if(main.equals(check))
           {

            System.out.println("Pre build Connection");
          //  System.out.println(socket);
          //  System.out.println(soc);
            removeSocketObject=socket;
            break;
           }

        }
    if(removeSocketObject!=null)
      {
       System.out.println("Socket connected : "+removeSocketObject.isClosed());
       try{removeSocketObject.close();}catch(Exception e){System.out.println(e);}
       System.out.println("Socket connected : "+removeSocketObject.isClosed());
      }
    socketArray.remove(removeSocketObject);
    //System.out.println("After delete");
   // displaySocket();
    socketArray.add(soc);
    System.out.println("After add");
    displaySocket();
   }


  public static void displaySocket(){


   for(Socket socket : socketArray)
      System.out.println(socket);

  }

}

class Communication extends Thread{                                   //commenucate with the Client
  
   ArrayList<Socket> socketArray;	
   Socket clientSocket;
   DataInputStream din;
   DataOutputStream dout;
   boolean flag=true;                                               // to check the socket is closed
   CheckingExsistingSocket checking;
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
   String OS;
   IpFromSocket ipGet;
   String IP;
   CreateDirectory directory;
   RecursiveFileDisplay fileDisplay;
   ZipFiles zipping;
   FileRead readfile;
   UnZip unZip;
   Map mapping;
   RecursiveFileDisplay fileMetadata;
   UnZip unzipping;
   String temp;
   String filename;
   CompareFile comparing;
   EmptyFile empty;
   ZipFiles zipfile;
   String systemData;
   String Hi;
   String uploadServerMetadataZip;
   String uploadClientMetadataZip;
   ZipMetadata zipmetadata;
   

   Communication(Socket soc,ArrayList<Socket> socketArray){

   try{
       this.clientSocket=soc;
       this.socketArray=socketArray;
       din=new DataInputStream(clientSocket.getInputStream());
       dout=new DataOutputStream(clientSocket.getOutputStream());
       System.out.println("Server is ready to commenucate with : "+clientSocket);
       checking=new CheckingExsistingSocket();
       
       ipGet=new IpFromSocket();
       IP=ipGet.getIP(clientSocket);
       
       if(System.getProperty("os.name").contains("Windows")){                          //Windows
    	   ShareData=System.getProperty("user.home") + "\\Desktop\\ShareServer\\";
    	   data=ShareData+"data";                                                      //data directory
    	   Hi=data+"\\Hi Client.txt";
    	   tempdata=ShareData+"System Data\\tmp";                                      //temporary directory
    	   systemData=ShareData+"System Data\\";  
    	   
    	   
    	   uploadMetadata=ShareData+"System Data\\Metadata\\"+IP+"_Upload";
		   	   clientUploadMetadata=uploadMetadata+"\\Client.txt";
		   	   serverUploadMetadata=uploadMetadata+"\\Server.txt";
		   	   uploadMetadataResult=uploadMetadata+"\\Result.txt";
		       
		       deletedata=ShareData+"System Data\\Metadata\\"+IP+"_Delete";
		       updatedata=ShareData+"System Data\\Metadata\\"+IP+"_Update";
		       tmpZipFilename=ShareData+"System Data\\tmp\\data.zip";
		       tmpZipMetadata=ShareData+"System Data\\tmp\\extract\\Result.txt";
		       tmpZipExtractDirectory=ShareData+"System Data\\tmp\\extract";
		       
		       uploadServerMetadataZip= uploadMetadata+"\\Server.zip";
	    	   uploadClientMetadataZip=uploadMetadata+"\\Client.zip";
		       
       }else{
    	   ShareData=System.getProperty("user.home") + "/Desktop/ShareServer/";              //Linux
    	   data=ShareData+"data";                                                      //data directory
    	   Hi=data+"/Hi Client.txt";
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
		       tmpZipExtractDirectory=ShareData+"System Data/tmp/extract";
		       
		       uploadServerMetadataZip= uploadMetadata+"/Server.zip";
	    	   uploadClientMetadataZip=uploadMetadata+"/Client.zip";
       }
       directory=new CreateDirectory();
       directory.dataDirectory(data, tempdata, uploadMetadata, deletedata, updatedata);
       fileDisplay=new RecursiveFileDisplay();
       zipping=new ZipFiles();
       readfile=new FileRead();
       unZip=new UnZip();
       mapping=new Map();
       fileMetadata=new RecursiveFileDisplay();
       unzipping=new UnZip();
       comparing=new CompareFile();
       empty=new EmptyFile();
       zipfile=new ZipFiles();
       zipmetadata=new ZipMetadata();
       
       FileWriter fw = new FileWriter(Hi,false);       //Hi Client txt... Donot delete this otherwise it will not work.
		 fw.write("HI! Client");
		 fw.write("\r\n");
		 fw.close();
       start();
      }catch(Exception e){
                           System.out.println("Exception is caught in Communication constructor : "+e);
                         }

   }

   public void run(){
	 
   while(flag==true){

        try{
            flag=checking.check(socketArray,clientSocket);
            System.out.println("result of flag "+flag);
            System.out.println("Client says : "+din.readUTF());
            dout.writeUTF("Hi! Client");
            
            
            
            
            /*dout.writeUTF("data/cloudClient.zip");
            System.out.println(din.readUTF());
            File n = new File(data+"/cloudClient.zip");
            System.out.println((int)n.length());
            dout.writeUTF(""+(int)n.length());
            sendZip(data+"/cloudClient.zip");*/
            
            
         
            //client to server data transfer
            
           
		     temp=din.readUTF();
	            System.out.println("Server Say : "+temp);
	            
	              if(temp.equals("SEND METADATA1")){
	            	dout.writeUTF("READY");
	            	
	            	System.out.println(din.readUTF());
	            	dout.writeUTF(uploadServerMetadataZip.replace(ShareData, ""));
	            	
	            	 if(fileDisplay.disc(data,ShareData,serverUploadMetadata)){
	            		dout.writeUTF("YES");
	            		zipmetadata.zip(serverUploadMetadata,Server,uploadServerMetadataZip);
	            			File n1=new File(uploadServerMetadataZip);
	            		dout.writeUTF(""+n1.length());
	            		sendZip(uploadServerMetadataZip);
					
						
	            		
	            		temp=din.readUTF();
	            		System.out.println(temp);
	            		if(temp.equals("NOT EMPTY")){
	            			dout.writeUTF("Server is ready");
	          			  String file=din.readUTF();
	          			 if(System.getProperty("os.name").contains("Windows")){
	  					   
	  					   file=file.replace("/", "\\");
	  					   
	  				   }else{
	  					   file=file.replace("\\", "/");
	  				   }
	          		  System.out.println("Filename : "+file);
	          		    dout.writeUTF("Server waiting");
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
         
            
            
            
           
           System.out.println("First Part");
            
              dout.writeUTF("SEND METADATA");
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
				   receiveZip(filename,uploadMetadata,sizeMetadata);
				   unzipping.unZipIt(uploadClientMetadataZip,uploadMetadata);
				   File n2=new File(uploadClientMetadataZip);
				   n2.delete();
			       fileDisplay.disc(data,ShareData,serverUploadMetadata);
			       comparing.compare(clientUploadMetadata,serverUploadMetadata,  uploadMetadataResult);
				    if(!empty.isEmpty(uploadMetadataResult)){ 
					   dout.writeUTF("NOT EMPTY");
					  
					   ArrayList<String> data1=readfile.getContents(uploadMetadataResult);
					   zipfile.zip(ShareData,uploadMetadataResult, Result, tmpZipFilename, data1);
					   dout.writeUTF(tmpZipFilename.replace(systemData,""));
					   n2=new File(tmpZipFilename);
					   dout.writeUTF(""+(int)n2.length());
					   sendZip(tmpZipFilename);
					   System.out.println(din.readUTF());
					   if(System.getProperty("os.name").contains("Windows")){
						   dout.writeUTF("WINDOWS");
					   }else{
						   dout.writeUTF("LINUX");
					   }
					   File n=new File(tmpZipFilename);
					   dout.writeUTF(Integer.toString((int)n.length()));
					   
					   System.out.println(din.readUTF());
					   sendZip(tmpZipFilename);
			    	  
				   }else{
					   dout.writeUTF("EMPTY");
				   }
			 }


             

           
           
			 System.out.println("Second Part");
           
           
            
            
           }catch(Exception e){
                               System.out.println("Exception is caught in Communication run() : "+e);
                              }

     }

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
   public void receiveZip(String fileName,String destination,int size)throws IOException{
             
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
   public void receive(String fileName,String destination) throws IOException{
		  
	       
               System.out.println("receive file "+fileName);
	      System.out.println("upload file "+destination);
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


