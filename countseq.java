/*
Alexander Meade
COP3532C Program 5 
*/

import java.util.*;

public class countseq {

public static void main(String [] args){
	Scanner userIn = new Scanner(System.in);
	int numCases = userIn.nextInt();
	for(int i = 0; i < numCases;i++){
			String string1 = userIn.next();
			String string2 = userIn.next();
			int result = lcss(string1,string2); //compare string 1 to 2 to find lccs
			System.out.print(result);
		}
		//System.userIn.close();
	}



//longest common subsqeuence algoritm 
	public static int lcss(String first, String second)
	{
		int [][] matrix = new int[first.length()] [second.length()];

		int i, j;

		for (i = 0; i < second.length() && second.charAt(i) != first.charAt(0); i++)
			matrix[0][i] = 0;

		for (i = i; i < second.length(); i++)
			matrix[0][i] = 1;

		for (i = 0; i < first.length() && first.charAt(i) != second.charAt(0); i++)
			matrix[i][0] = 0;

		for (i = i; i < first.length(); i++)
			matrix[i][0] = 1;

		for (i = 1; i < first.length(); i++)
			for (j = 1; j < second.length(); j++)
				if (first.charAt(i) == second.charAt(j))
					matrix[i][j] = matrix[i-1][j-1] + 1;
				else
					matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);

		return matrix[first.length()-1][second.length()-1];
	}
}