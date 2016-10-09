


import java.net.Socket;
import java.util.ArrayList;

public class CheckingExsistingSocket {
	  public boolean check(ArrayList<Socket> socketArray,Socket clientSocket){

		     String check=clientSocket.getRemoteSocketAddress().toString();
		     check=check.substring(1);
		     System.out.println("Connecting IP:  "+check);
		     String main;
		     for(Socket socket : socketArray)
		        {

		        main=socket.getRemoteSocketAddress().toString();
		        main=main.substring(1);

		        if(main.equals(check))
		           {

		            System.out.println("it will run");
		          //  System.out.println(socket);
		          //  System.out.println(soc);
		            return true;
		           }

		        }
		       return false;

		   }
}
