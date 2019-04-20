/*
 * Alexander Meade 
 * Program 3 Camera COP3532C
 * Spring 2018
 */
import java.util.*;

public class Camera{

	public static void main(String args[]){
		// Reading in the input file
		Scanner userIn = new Scanner(System.in);
		int numObjects = userIn.nextInt();	//total num of objects
		int distance = userIn.nextInt();	//distance total in y direction
		//if(numObjects == 0) System.out.print("0"); //no objects no pictures 
		int startOfGlass = userIn.nextInt();
		int endOfGlass = userIn.nextInt();	
		boolean[] seen = new boolean[numObjects];  
		Vertex[] coordinates = new Vertex[numObjects];
		//loop will read in the number of objects 
			for(int i=0; i<numObjects; i++){ 
					coordinates[i] = new Vertex(userIn.nextInt(),userIn.nextInt(),startOfGlass,endOfGlass,distance);	//create array of objects with values scanned in 
			}
			userIn.close();	
			
			Arrays.sort(coordinates); //sorted but we care about end xpoints only cuz muh scheduling algorithm 
			System.out.print(findPicture(coordinates,seen)); //print min amount of pictures 
					
	}	
	
	//gives us the minimum amount of pictures 
    public static int findPicture(Vertex[] verticies, boolean[] seen)
    {
		int carPos = 0;		//car's position 
		int numPics = 0;	// number of pictures taken 
		int currPos = 0;	// current position in the arry 

		// This will loop through each objects and find the minimum snapshots needed 
		while(currPos < verticies.length){
			//set car position to the end of the view cuz optimal scheduling 
			 carPos = verticies[currPos].endSeen;
			
			 //see if other points are intersecting with current point 
			 currPos = findIntersection(verticies, seen, carPos, currPos);

			//if we cant find intersection then we have to take at least 1 more pic  
			numPics++;

		}
		return numPics; //return pics 
	}
	
	public static int findIntersection(Vertex[] verticies,  boolean[] seen ,int carPos,int currPos){
		//calculates intsections based off if its in total length and if the car is currently <= current end or>= current start
		while(currPos < verticies.length && carPos <= verticies[currPos].endSeen && carPos >= verticies[currPos].startSeen)
			seen[currPos++] = true; //if so add this to seen cuz we've seen it 
		return currPos; //once we can no longer see anything no more intersection 
	}

}


class Vertex implements Comparable<Vertex>{

	int xIntercept, yIntercept; //x and y coordinates of the current object
	int startSeen, endSeen;	//verticies where we will start seeing the object and where we will stop seeing it

	public Vertex(int xIntercept, int yIntercept, int xGlass, int yGlass, int distance){

		// This will set the variable
		this.xIntercept = xIntercept;
		this.yIntercept = yIntercept;
		slope(xGlass, yGlass, distance);
	}
	
	
public int compareTo(Vertex vert){
		return endSeen - vert.endSeen; //muh optimal scheduling algorithm 
	}
	
	//calculate the slopes between the object and the car
	public void slope(int xGlass, int yGlass, int distance){
		
		int xGlassStart=xGlass, yGlassStart=distance;	//start partition
		int xGlassEnd=yGlass, yGlassEnd=distance;		//end partition
		
		int runStart = xGlass - xIntercept; 	// x2-x1
		int riseStart = distance - yIntercept;	// y2-y1

		int runEnd = yGlass - xIntercept; 	// x2-x1
		int riseEnd = distance - yIntercept;	// y2-y1
	
	//prevents negative traversal and makes sure we only go in increments of 1 
	if(Math.abs(runStart) % Math.abs(riseStart) == 0)
	{
		runStart = runStart/Math.abs(riseStart);
		riseStart = riseStart/Math.abs(riseStart);
	}
	
	if(Math.abs(runEnd) % Math.abs(riseEnd) == 0)
	{
		runEnd = runEnd/Math.abs(riseEnd);
		riseEnd = riseEnd/Math.abs(riseEnd);
	}

	//both of the rises are actually negative since we work with respect of object to car 
	
	if(riseStart > 0){
		
		riseStart = -riseStart;
		runStart = -runStart;
	}
	
	if(riseEnd > 0){
		
		riseEnd = -riseEnd;
		runEnd = -runEnd;
	}
	
	//iff in front of glass take picture at anytime 
	if(xIntercept < distance)
{
		endSeen = Integer.MAX_VALUE;	// The very end of what it could be for the car
		startSeen = 0;				// The very start position of the car
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
	
	//where we see objects 
	startSeen = xGlassStart;
	endSeen = xGlassEnd;
	
	}
}




	

