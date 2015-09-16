
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TicTacToeClientUDP {

	String hostName = "127.0.0.1";
    int  port = 4242;
    static int isServerChance = 1;
    DatagramSocket aClientsocket;

   public void doTheJob()	{
	try {
		
		Game g = new Game();
		System.out.println("Player connected: " + hostName);
		System.out.println("Starting Game...");
		g.printBoard();
		Scanner scanner = new Scanner(System.in);
		int row,col =0;
		char r;
		byte buf[] = new byte[64];
		DatagramSocket aClientsocket = new DatagramSocket();
		
		
		for(;;){
			if(isServerChance==1){
				
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				aClientsocket.receive(dp);
				System.out.println("received: -" +
					new String(dp.getData() ) + "-"  );
				String info=new String(dp.getData());
				r=info.charAt(0);
				row=Character.getNumericValue(r);
				r=info.charAt(1);
				col=Character.getNumericValue(r);
				g.play(row, col);
				isServerChance=0;
				r=info.charAt(2);
				//isServerChance=Character.getNumericValue(r);
				System.out.println("row="+row);
				System.out.println("Col="+col);
				System.out.println("isServerChance="+isServerChance);
			}
			
			if(isServerChance==0){

            	System.out.println("Your Move!");
            	System.out.println("Enter the row position");
            	row = scanner.nextInt();
            	System.out.println("Enter the col position");
            	col = scanner.nextInt();
            	g.play(row, col);
            	//isServerChance = 1;
            	DatagramPacket packetsend = new DatagramPacket(buf, buf.length);
            	String info=""+row+col+isServerChance;
            	buf=info.getBytes();
            	InetAddress address = packetsend.getAddress();
            	packetsend = new DatagramPacket(buf, buf.length,address,port);
            	aClientsocket.send(packetsend);
			}
		}
		
		
	} catch (Exception e) {
		System.out.println (e);
	}
   }
   

   public static void main(String argv[]) {
	TicTacToeClientUDP client = new TicTacToeClientUDP();
	client.doTheJob();

   }
}