// Arup Guha
// 3/14/2013
// Solution to COP 3503 Program #5A: Counting Subsequences

import java.util.*;

public class countseq {
	
	public static void main(String[] args) {
		
		Scanner stdin = new Scanner(System.in);
		
		int numCases = stdin.nextInt();
		
		for (int loop=0; loop<numCases; loop++) {
			String text = stdin.next();
			String pattern = stdin.next();
			System.out.println(solve(text, pattern));
		}
	}
	
	public static long solve(String text, String pattern) {
			
		long[][] dp = new long[text.length()+1][pattern.length()+1];
			
		// Initialize table - be CAREFUL here (Note 0s and 1s!!!)
		for (int i=0; i<dp[0].length; i++)
			dp[0][i] = 0;
		for (int i=0; i<dp.length; i++)		
			dp[i][0] = 1;

		// Solve each subcase in order.
		for (int i=1; i<dp.length; i++) {
			for (int j=1; j<dp[i].length; j++) {
				
				// These last characters match.
				if (text.charAt(i-1) == pattern.charAt(j-1))
					dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
					
				// Just copy over old matches, since this character doesn't help.
				else
					dp[i][j] = dp[i-1][j];
			}
		}
		
		// /*** JUST TO VERIFY INPUT ***/
		// if (hasNeg(dp))
		// 	System.out.println("There is overflow in this case.");
		
		// // Answer is stored here.
		// return dp[dp.length-1][dp[0].length-1];
	}
	
	// /*** Used to verify input. ***/
	// public static boolean hasNeg(long[][] array) {
		
	// 	for (int i=0; i<array.length; i++)
	// 		for (int j=0; j<array[0].length; j++)
	// 			if (array[i][j] < 0)
	// 				return true;
	// 	return false;
	}
}