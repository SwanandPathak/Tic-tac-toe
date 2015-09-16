import java.net.*;
import java.io.*;
import java.util.*;

public class TicTacToeServerTCP extends Thread {

   ServerSocket aServerSocket;
   static int port = 4242;

   public TicTacToeServerTCP()	{
       try { 
           aServerSocket = new ServerSocket(port);
           System.out.println ("Listening on port: " + aServerSocket.getLocalPort());
       } catch(Exception e) {
           System.out.println(e);
       }
   }


   
   public void run()	{
        try {
            for(;;) {
                Socket clnt = aServerSocket.accept();
                System.out.println(clnt.toString());
                PrintWriter out = new PrintWriter
                    (clnt.getOutputStream (), true);
                //out.println("It is now: " + new Date());
                clnt.close();
            }
        } catch(Exception e) {
            System.out.println(e);
	    e.printStackTrace();
        }
   }

    public static void main(String argv[]) {
     TicTacToeServerTCP ts = new TicTacToeServerTCP();
     ts.start();
    }
}