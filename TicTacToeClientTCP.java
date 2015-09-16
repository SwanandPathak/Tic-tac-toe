import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
/**
 * This class is the client which plays tic tac toe with the server using the TCP connection.
 *
 * Version 1.0
 * 
 * @author Amit Doshi
 * @author Swanand Pathak
 *
 * Date: 12/08/2014
 * 
 */
public class TicTacToeClientTCP {

	String hostName = "129.21.30.38";
    int  port = 4242;
    static boolean isServerChance = true;

	/**
	* This method plays the game and communicates with the server with the TCP communication..
	*
	* @param   void 
	* 
	* @return void
	*/
   public void doTheJob()	{
	try {
		GameClient g = new GameClient();
		Socket sock = new Socket(hostName, port);
		System.out.println("Player connected: " + hostName);
		System.out.println("Starting Game...");
		g.printBoard();
		Scanner scanner = new Scanner(System.in);
		int row,col =0;
		
		
		BufferedReader din = new BufferedReader (
			new InputStreamReader (sock.getInputStream()));
		PrintWriter out = new PrintWriter
                (sock.getOutputStream (), true);
		for(int i =0;i<9;i++){
			if(isServerChance){
				while(!din.ready()){
					;
				}
				row = Integer.parseInt(din.readLine());
				while(!din.ready()){
					;
				}
				col = Integer.parseInt(din.readLine());
				g.isClient = false;
				g.play(row, col);
			}
			while(!din.ready()){
				;
			}
			isServerChance = Boolean.parseBoolean(din.readLine());
			if(!isServerChance){
				System.out.println("Your Move!");
				System.out.println("Enter the row");
				row = scanner.nextInt();
				System.out.println("Enter the column");
				col = scanner .nextInt();
				out.println(row);
				out.println(col);
				g.isClient = true;
				g.play(row, col);
			}


		}
		sock.close();
	} catch (Exception e) {
		System.out.println (e);
	}
   }
   
	/**
	* The main program.
	*
	* @param    args    command line arguments 
	*/
   public static void main(String argv[]) {
	TicTacToeClientTCP client = new TicTacToeClientTCP();
	client.doTheJob();

   }
}