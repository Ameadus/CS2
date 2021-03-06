package fug;

//Camera Time
//NP1
//Viviana Gonzalez Pascual
//March 2, 2017
//CS2 - Spring 2018

import java.util.*;
//Main class for the Camera Time
public class fugem{
	
	// The main of the program
	public static void main(String args[]){
		// Reading in the input file
		Scanner input = new Scanner(System.in);
	
		
		int object = input.nextInt();	// Reading in the number of objects with in the glass partition
		int distance = input.nextInt();	// Reading in the distance (meters) in the positive y direction
		int startOfGlass = input.nextInt();	// Reading the starting coordinate of the glass for the test case
		int endOfGlass = input.nextInt();	// Reading the ending coordinate of the glass for the test case
		
		//boolean[] taken = new boolean[object];
		
		// Array of vertices of object --> vertices
		vertices[] coordinates = new vertices[object]; 

		// This for loop will read in the coordinates given in every given test case
		for(int i=0; i<object; i++){
			coordinates[i] = new vertices(input.nextInt(), input.nextInt(), startOfGlass, endOfGlass, distance);
			
		}
		// Closing the input stream 
		input.close();
		
		// This will be sorting the array of objects based on their actualEndSeen variable
		Arrays.sort(coordinates);
		
		// The variable we will be using the store the minimum number of pictures 
		// This will be aquired from the method "findTheNumberOfPictures"
		int minimumNumberOfPictures = findTheNumberOfPictures(coordinates);
		
		// Print the line
		System.out.println(minimumNumberOfPictures);    
	}

	// This method will return the minimum number of pictures that will be taken during the trajectory
 public static int findTheNumberOfPictures(vertices[] points)
 {
		int theCarsCurrentSpot = 0;	// This will store each location (spot) of the car
		int numberOfPictures = 0;	// This variable will store the number of pictures taken
		int i = 0;	// This is the variable that will be used to look at each section within the array

		// This will loop through each objects and find the minimum snapshots needed 
		while(i < points.length)
		{
			// Set theCarsCurrentSpot to the current verticies ending value
			 theCarsCurrentSpot = points[i].actualEndSeen;
			
			// we will then call findOtherPoints to see the points that will be included in the
			// current picture and skip over them
			i = findOtherPoints(points, theCarsCurrentSpot, i);

			// This will increment the number of pictures 
			numberOfPictures+=1;

		}

		// This will return the number of pictures we were able to take in the scope
		return numberOfPictures;
	}
	
	// This function will find all the vertices that will be included in the picture
	// This is the main function that will do all the calculations regarding how many pictures are being
	// taken during a transition of points that are within the the slopes of the objects in respect to the car 
	public static int findOtherPoints(vertices[] points,  int theCarsCurrentSpot,int currentArrayPlacement)
	{
		// If the currentCarsSpot is within the start and ending points of the object 
		// at the currentArrayPlacement then we will increment currentArrayPlacement because
		// that is already included in the current picture we are taking
		while(currentArrayPlacement < points.length && theCarsCurrentSpot <= points[currentArrayPlacement].actualEndSeen && theCarsCurrentSpot >= points[currentArrayPlacement].actualStartSeen)
		break;//taken[currentArrayPlacement++] = true;

		// This will return the object that is out of range for the current picture
		return currentArrayPlacement;
	}

}	

//This class will implement the comparison of the vertices
class vertices implements Comparable<vertices>{

	int xIntercept, yIntercept; // The x and y coordinates of the current object
	int actualStartSeen, actualEndSeen;	// The spot where we will start seeing the object and where we will stop seeing it

	public vertices(int xIntercept, int yIntercept, int xGlass, int yGlass, int distance){

		// This will set the variable
		this.xIntercept = xIntercept;
		this.yIntercept = yIntercept;
		
		// This will call ethe slope method so we can set the 
		// actualEnding and actualStarting variables of this isnstance of the class
		slope(xGlass, yGlass, distance);

		//printDiscription();
		
	}
	
	
public int compareTo(vertices other){
		// This will allow a verticie class to compare its actaulEndingSeen variable
		// to the actaulEndingSeen of another class
		return actualEndSeen - other.actualEndSeen;
	}
	
	// This method start calculating the slopes between the object and the car
	public void slope(int xGlass, int yGlass, int distance){
		
		int xGlassStart=xGlass, yGlassStart=distance;	// The start of the glass
		int xGlassEnd=yGlass, yGlassEnd=distance;		// The end of the glass
		
		int runStart = xGlass - xIntercept; 	// x2-x1
	int riseStart = distance - yIntercept;	// y2-y1

	int runEnd = yGlass - xIntercept; 	// x2-x1
	int riseEnd = distance - yIntercept;	// y2-y1
	
	// With this will simplify the rise over run and not go to the negative bounds in the car's pathway
	// we are moding in order to see if it is divisible within the number
	// Later we divided it in a matter of integer 1 so that way we won't be passing to the negative values
	if(Math.abs(runStart) % Math.abs(riseStart) == 0)
	{
		runStart = runStart/Math.abs(riseStart);
		riseStart = riseStart/Math.abs(riseStart);
	}
	
	// This will simplify the rise over run
	if(Math.abs(runEnd) % Math.abs(riseEnd) == 0)
	{
		runEnd = runEnd/Math.abs(riseEnd);
		riseEnd = riseEnd/Math.abs(riseEnd);
	}

	// These method will change the rise due to go down towards the car rather than the opposite way
	// This method will change the rise if it's a negative value to a positive value
	if(riseStart > 0){
		
		riseStart = -riseStart;
		runStart = -runStart;
	}
	
	// These method will change the run due to go down towards the car rather than the opposite way
	// This method will change the run if it's a negative value to a positive value
	if(riseEnd > 0){
		
		riseEnd = -riseEnd;
		runEnd = -runEnd;
	}
	
	// The object that is in front of the glass can be taken a picture of it at any moment 
	if(xIntercept < distance)
	{
		// These values will make it so that we can take a picture of this
		// object at any point of the car
		actualEndSeen = Integer.MAX_VALUE;	// The very end of what it could be for the car
		actualStartSeen = 0;				// The very start position of the car

		return;
	}

	
	// Moving down the slope until we reach y = 0
	while(yGlassStart > 0){
		
		xGlassStart += runStart;
		yGlassStart += riseStart;
	}

	// Moving down the slope until we reach y = 0
	while(yGlassEnd > 0){
		
		xGlassEnd += runEnd;
		yGlassEnd += riseEnd;

	}
	
	// We can actually start to see the object from the car in these points
	actualStartSeen = xGlassStart;
	actualEndSeen = xGlassEnd;
	
}

//This method will print variable of this specific current class to standard output
public void printDiscription()
{
	
	System.out.println("x: " + xIntercept + " y: " + yIntercept);
	System.out.println("Start seen: " + actualStartSeen + " End seen: " + actualEndSeen + "\n");
}

}





