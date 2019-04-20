import java.io.*;
import java.util.*;

public class farmer_problem{
    
    public static HashSet<String> seen;
    
    // Our main method
    public static void main(String[] Args){
        // If I was to read things in I would use the following...
        // Scanner sc = new Scanner(System.in);
        // BufferedReader br = new BufferedReader(System.in);
        
        // Stores the list of seen states of items and farmer
        seen = new HashSet<String>();
        
        // We try to solve the problem where no one is across the river
        canWeSolve(false, false, false, false);
    }
    
    // The recursive method for 
    public static boolean canWeSolve(boolean farmer, boolean chick, boolean fox, boolean hay){
        
        // Check if we have seen this state otherwise add the state to the set of seen states.
        if (seen.contains(farmer + " " + chick + " " + fox + " " + hay))
        {
            return false;
        }
        else
        {
            seen.add(farmer + " " + chick + " " + fox + " " + hay);
        }
        
        // Check if the fox can eat the chicken
        // Why is the farmer buying a fox?
        // Part 1 of backtracking
        if ((farmer != chick) && (chick == fox)){
            
            // We cannot solve this state
            return false;
        }
        
        // Check if the chicken can eat the hay
        // Chickens probably shouldn't eat hay...
        // Part 2 of backtracking
        if ((farmer ^ chick) && (chick == hay))
        {
            
            // We cannot solve this state
            return false;
        }
        
        // Check if the chicken, farmer, fox, and hay
        if (farmer && chick && fox && hay)
        {
            
            // Print the state of the farmer and things
            print(farmer, chick, fox, hay);
            return true;
        }
        
        // Check that the farmer is on the farm side of the river...
        if (farmer)
        {
            
            // Check that the chicken is on the same side as the farmer
            if (chick == farmer)
            {
                
                // Check if there is a solution by moving the chicken
                if (canWeSolve(false, false, fox, hay))
                {
                    
                    // Print the current state of the items with respect to the river
                    print(farmer, chick, fox, hay);
                    
                    // Return that a solution was found
                    return true;
                }
            }
            
            // See if the fox and the farmer are on the farm side
            if (fox == farmer)
            {
                
                // Check if there is a solution my moving the fox
                if (canWeSolve(false, chick, false, hay))
                {
                    
                    // Print the current state of the items
                    print(farmer, chick, fox, hay);
                    
                    // Return that a solution was found
                    return true;
                }
            }
            
            // See if the hay and the farmer are on the farm side
            if (hay == farmer)
            {
                
                // Try to take the hay across the river
                if (canWeSolve(false, chick, fox, false))
                {
                    
                    // Print out the state if the solution was found
                    print(farmer, chick, fox, hay);
                    
                    // Return that a solution was found
                    return true;
                }
            }
            
            // Try to move just the farmer to the other side of the river
            if (canWeSolve(false, chick, fox, hay))
            {
                
                // Print out the state if the solution was found
                print(farmer, chick, fox, hay);
                
                // Return that a solution was found
                return true;
            }
        }
        else
        {
            // Check if the chicken is on the same side of the river as the farmer
            if (farmer == chick)
            {
                
                // Check for a solution my moving the chicken to the other side of the river
                if (canWeSolve(true, true, fox, hay))
                {
                    
                    // Print the state of the items
                    print(farmer, chick, fox, hay);
                    
                    // Return that a soluiton was found
                    return true;
                }
            }
            
            // Check if the fox is on the same side of the river as the farmer
            if (farmer == fox)
            {
                
                // Check if a solution exists by moving the fox to the other side of the river
                if (canWeSolve(true, chick, true, hay))
                {
                    
                    // Print the state of the items
                    print(farmer, chick, fox, hay);
                    
                    // Return that a solution was found
                    return true;
                }
            }
            
            // Check if the hay is on the same side of the river as the farmer
            if (farmer == hay)
            {
                
                // Check if a solution exists by moving the hay to the other side of the river
                if (canWeSolve(true, chick, fox, true))
                {
                    
                    // Print the state of the items
                    print(farmer, chick, fox, hay);
                    
                    // Return that a solution was found
                    return true;
                }
            }
            
            // Check if there is a solution by going across the river by yourself
            if (canWeSolve(true, chick, fox, hay))
            {
                
                // Print the state of the items
                print(farmer, chick, fox, hay);
                
                // Return that a solution was found
                return true;
            }
        }
        
        // No possible movement resulted in a solution so we return false!
        return false;
    }
    
    public static void print(boolean farmer, boolean chick, boolean fox, boolean hay)
    {
        
        // Store what is on the far side of the river
        String Left = "";
        
        // Store what is on the farm side of the river
        String Right = "";
        
        // Check if the farmer has made the journey across
        if (farmer){
            Right = Right + "F";
            Left = Left + " ";
        }else{
            Right = Right + " ";
            Left = Left + "F";
        }
        
        // Add the chicken to the appropriate side of the river
        if (chick){
            Right = Right + "c";
            Left = Left + " ";
        }else{
            Right = Right + " ";
            Left = Left + "c";
        }
        
        // Add the fox to the appropriate side of the river
        if (fox){
            Right = Right + "f";
            Left = Left + " ";
        }else{
            Right = Right + " ";
            Left = Left + "f";
        }
        
        // Add the hay to the appropriate side of the river
        if (hay){
            Right = Right + "h";
            Left = Left + " ";
        }else{
            Right = Right + " ";
            Left = Left + "h";
        }
        
        // Print the state of the river and such
        System.out.println("farm " + Left + " | river | " + Right);
    }
}