

import java.net.Socket;

public class IPFromSocket {
    String getIP(Socket socket){
    	String check=socket.getRemoteSocketAddress().toString();
        check=check.substring(check.lastIndexOf("/")+1,check.lastIndexOf(":"));
        return check;  	
    }
}
