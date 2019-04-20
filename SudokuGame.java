import java.io.*;
import java.util.* ;

public class SudokuGame {
	 public static final int maxSize = 9;
	 public int[][] gameBoard;
	 public static final int empty = 0;
	 static Scanner userIn = new Scanner(System.in); //scanner for inputs 
	 
	 //constructor
	 public SudokuGame() {
		 this.gameBoard = new int[maxSize][maxSize];
	 }
	 
	 
	public static void main (String[] Args){
		int numGames = userIn.nextInt();
		SudokuGame game = new SudokuGame(); 
        for(int amount = 0; amount < numGames; amount++) {
        	for(int row = 0; row < maxSize; row++)
        		for(int col = 0; col < maxSize; col++) {
        			game.gameBoard[row][col] = userIn.nextInt(); 
        		}       	
        }
        
        game.solveIt(); 
        for( int i = 0; i < maxSize; i++) {
			for( int j =0 ; j < maxSize; j++) {
				System.out.print(game.gameBoard[i][j] + " "); //format for reading
				if(j == (maxSize - 1)) //end of row 
					System.out.println(); //prints enter for format 
			}
		}
        
        if(game.solveIt() == false) {
        	System.out.println("Cannot be solved");
        	
        }
    }
	
	//print the sudoku board  
	public static void printBoard(int [][] game) {
		for( int i = 0; i < maxSize; i++) {
			for( int j =0 ; j < maxSize; j++) {
				System.out.print(game[i][j] + " "); //format for reading
				if(j == (maxSize - 1)) //end of row 
					System.out.println(); //prints enter for format 
			}
		}
	}
	
	//checks rows to see if the number is already in it 
	public boolean checkRows(int row, int value) {
		//loop thru all rows till 9 to search for number 
		for(int counter = 0; counter < maxSize; counter++) {
			if(gameBoard[row][counter] == value) //if the number is found return true 
				return true;
		}
			return false; //if not found return false and can be placed 
	}
	
	//checks columns to see if the number is already in it 
		public boolean checkCol(int col, int value) {
			//loop thru all columns till 9 to search for number 
			for(int row = 0; row < maxSize; row++) {
				if(gameBoard[row][col] == value) { //if the number is found return true 
					return true;
				}
			}
				return false; //if not found return false and can be placed 
		}
	
		//check if in the mini 3x3 grid 
		public boolean checkGrid(int row, int col, int value) {
			int gridRow = row - (row % 3); //this will find the initial row of the miniGrid which will be 0 indexed
			//ex 1 - (1 mod 3) = 0 so this would be the start of the top left grid so we can later parse thru the rest of the rows
			//within that same grid 
			int gridCol = col - (col % 3); //same idea as above but performs the checks for columns 
			for(int i = gridRow; i < gridRow + 3; i++ ) { //we need to add 3 to make sure we stay in bounds while still checking all 9 spots
				for(int j = gridCol; j < gridCol + 3; j++ ) { //same logic as above
					if(gameBoard[i][j] == value)
						return true;
					 
				}
			}
						return false; 
		}
		
		//tells us T or F can the number be placed in that spot
		public boolean placeNumber(int row, int col, int value) {
			return !(checkRows(row, value) || checkCol(col, value) || checkGrid(row, col, value) );
			//returns true if its not in a row or column and its not in a grid
		}
		
		public boolean solveIt() {
			for(int row = 0 ; row < maxSize; row++) { //check all rows 
				for(int col = 0 ; col < maxSize; col++){ //check all columns
					if(gameBoard[row][col] == empty) { //if they are 0 aka empty then go to for 
						for(int value = 1; value <= maxSize; value++) { //see if it has the value 1-9 
							if(placeNumber(row,col,value)) {
								gameBoard[row][col] = value; //place value iff allowed
								if( solveIt() ) {
									return true;
								}
							else {	
								gameBoard[row][col] = empty; 	
								}
							}
						}
					
					return false; 
					}
				}
			}
			return true; 
		}
}
