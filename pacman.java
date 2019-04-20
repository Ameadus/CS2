/*
Alexander Meade Program6  Pacman
CS2 Spring 18
im tired and only max path works 
*/


import java.util.*;
import java.io.*;

public class pacman {

	 public static void main(String[] args) {
		Scanner userIn = new Scanner(System.in); //reader user input
    int row = userIn.nextInt();
    int col = userIn.nextInt();
    int[][]board = new int[row][col]; //board for da gamez
    String charReplacer; //replace the P and E and start with ints so this
    //is temp for P and E

    //buuild the boardstate
    for(int i = 0; i < row; i++){
      for(int j =0; j < col;j++){
        charReplacer = userIn.next();
        //replace the P and E with 0 since this wont impact sums
        if (charReplacer.equals("P") || charReplacer.equals("E")) {
          board[i][j] = 0;
        }
        else{
          board[i][j] = Integer.parseInt(charReplacer); //else build normally
        }
      }
    }
            //print the max cost of the board state
            System.out.println(maxCost(board, row-1, col-1));
	}

  public static int maxCost(int [][]cost,int m,int n){

         int temp[][] = new int[m+1][n+1];
         int sum = 0;

         //start off all the top row with 0
         for(int i=0; i <= n; i++){
             temp[0][i] = sum + cost[0][i];
             sum = temp[0][i];
         }
         sum = 0; //reset sum

         //start far right col with 0
         for(int i=0; i <= m; i++){
             temp[i][0] = sum + cost[i][0];
             sum = temp[i][0];
         }

         //now go thru the board and look thru all 3 diff ways
         for(int i=1; i <= m; i++){
             for(int j=1; j <= n; j++){
                 temp[i][j] = cost[i][j] + max(temp[i-1][j-1], temp[i-1][j],temp[i][j-1]);
                 //i-1 is for down j-1 is for right both is fer right down
             }
         }
         return temp[m][n]; //return value summed up here at end
     }

     //return the max of 3 values
     private static int max(int a,int b, int c){
         int temp = Math.max(a, b);
         return Math.max(temp, c);
     }
}
