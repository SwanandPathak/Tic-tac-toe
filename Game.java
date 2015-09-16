/**
 * This class is the tic tac toe game
 *
 * Version 1.0
 * 
 * 
 * @author Swanand Pathak
 * @author Amit Doshi
 *
 * Date: 12/08/2014
 * 
 */
public class Game {


    private char[][] board; 
   static public char currentPlayerMark;
   static boolean isClient;
	
   /* Constructor
    * @param
    * 
    * @return
    */
    public Game() {
        board = new char[3][3];
        currentPlayerMark = 'X';
        initializeBoard();
    }
	
	
    // Set/Reset the board back to all empty values.
	
    /* 
     * @param null
     * 
     * @return void
     */
    public void initializeBoard() {
		
        // Loop through rows
        for (int i = 0; i < 3; i++) {
			
            // Loop through columns
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }
	
	
    // Print the current board (may be replaced by GUI implementation later)
    public void printBoard() {
        System.out.println("-------------");
		
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
	
	
    // Loop through all cells of the board and if one is found to be empty (contains char '-') then return false.
    // Otherwise the board is full.
	
    /* 
     * @param
     * 
     * @return boolean
     */
    public boolean isBoardFull() {
        boolean isFull = true;
		
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                }
            }
        }
		
        return isFull;
    }
	
	
    // Returns true if there is a win, false otherwise.
    // This calls our other win check functions to check the entire board.
    /* 
     * @param
     * 
     * @return boolean
     */
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }
	
	
    // Loop through rows and see if any are winners.
    /* 
     * @param
     * 
     * @return boolean
     */
    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }
	
	
    // Loop through columns and see if any are winners.
    /* 
     * @param
     * 
     * @return boolean
     */
    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;
    }
	
	
    // Check the two diagonals to see if either is a win. Return true if either wins.
    /* 
     * @param
     * 
     * @return boolean
     */
    private boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
    }
	
	
    // Check to see if all three values are the same (and not empty) indicating a win.
    /* 
     * @param
     * 
     * @return boolean
     */
    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }
	
	
    // Change player marks back and forth.
    public void changePlayer() {
        if (currentPlayerMark == 'X') {
            currentPlayerMark = 'O';
        }
        else {
            currentPlayerMark = 'X';
        }
    }
	
	
    // Places a mark at the cell specified by row and col with the mark of the current player.
    /* 
     * @param row,col int
     * 
     * @return boolean
     */
    public boolean placeMark(int row, int col) {
		
        // Make sure that row and column are in bounds of the board.
        if ((row >= 0) && (row < 3)) {
            if ((col >= 0) && (col < 3)) {
                if (board[row][col] == '-') {
                    board[row][col] = currentPlayerMark;
                    return true;
                }
            }
        }
		
        return false;
    }
    /* Play
     * @param int row,col
     * 
     * @return boolean
     */
    void play(int row, int col){
    	placeMark(row,col);

    	// Lets print the board
    	printBoard();

    	// Did we have a winner?
    	if (checkForWin()) {
    		if(isClient)
    	   System.out.println("YOU WON!");
    		else
    			System.out.println("YOU LOST!");
    	   System.exit(0);
    	}
    	else if (isBoardFull()) {
    	   System.out.println("Appears we have a draw!");
    	   System.exit(0);
    	}

    	// No winner or draw, switch players to 'o'
    	changePlayer();	
    	
    }
}


