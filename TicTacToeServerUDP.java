
import java.net.*;
import java.io.*;
import java.util.*;


public class TicTacToeServerUDP extends Thread {
   DatagramSocket aServerSocket;
  // ServerSocket 	aServerSocket;
   static int	port     = 4242;

   public TicTacToeServerUDP()	{
       try { 
    	   aServerSocket=new DatagramSocket(port);
          // aServerSocket = new ServerSocket(port); // in java.net
           System.out.println ("Listening on port: " + aServerSocket.getLocalPort());
       } catch(Exception e) {
           System.out.println(e);
       }
   }


   
   public void run()	{
	   int row,col=0;
	   char r;
	  
	   Game g = new Game();
	   int isServerChance = 1;
	   byte[] buf = new byte[512];
	    try {
	    
            System.out.println("Starting Game...");
	        Scanner scanner = new Scanner(System.in);
        	while(true){
                    if(isServerChance==1){
                    	System.out.println("Your Move!");
                    	System.out.println("Enter the row position");
                    	row = scanner.nextInt();
                    	System.out.println("Enter the col position");
                    	col = scanner.nextInt();
                    	//isServerChance = 0;
                    	g.play(row, col);
                    	DatagramPacket packetsend = new DatagramPacket(buf, buf.length);
                    	String info=""+row+col+isServerChance;
                    	buf=info.getBytes();
                    	InetAddress address = packetsend.getAddress();
                    	packetsend = new DatagramPacket(buf, buf.length,address,port);
                    	aServerSocket.send(packetsend);
                    }
                if(isServerChance==0)
                {
                	DatagramSocket socket = new DatagramSocket();
    				DatagramPacket dp = new DatagramPacket(buf, buf.length);
    				socket.receive(dp);
    				System.out.println("received: -" +
    					new String(dp.getData() ) + "-"  );
    				String info=new String(dp.getData());
    				
    				r=info.charAt(0);
    				row=Character.getNumericValue(r);
    				r=info.charAt(1);
    				col=Character.getNumericValue(r);
    				r=info.charAt(2);
    				g.play(row, col);
    				isServerChance=Character.getNumericValue(r);
    				isServerChance=1;
    				System.out.println("row="+row);
    				System.out.println("Col="+col);
    				System.out.println("isServerChance="+isServerChance);
                	
                }
                	
                }
        	
        } catch(Exception e) {
            System.out.println(e);
	    e.printStackTrace();
        }
   }

    public static void main(String argv[]) {
     TicTacToeServerUDP ts = new TicTacToeServerUDP();
     ts.start();
    }
}