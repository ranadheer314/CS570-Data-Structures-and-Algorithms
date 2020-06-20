//Name:Randheer Vennapureddy
//CWID:10457392
 
package Maze;

import java.util.Stack;
import java.util.ArrayList;


/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */

    public boolean findMazePath(int x, int y) {
    	if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) 
    		return false;
    	else if (!maze.getColor(x, y).equals(NON_BACKGROUND))
    		return false; 
    	else if (x == (maze.getNCols() - 1) && y == (maze.getNRows() - 1)){
    		maze.recolor(x, y, PATH); 
    		return true;
    		}
    	else {
    		maze.recolor(x, y, PATH); 
    		if (findMazePath(x - 1, y) || findMazePath(x + 1, y) || findMazePath(x, y - 1) || findMazePath(x, y + 1)) {
    			return true; 
    		} 
    		else { 
    			maze.recolor(x, y, TEMPORARY); // Dead end. 
    			return false;
    			}     
    	} 
    }
        
     public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
   	    int xmove[]={0,1,0,-1};
      	int ymove[]={1,0,-1,0};
      	int nextx,nexty;   
      	if(x<0 || x>=maze.getNRows() || y<0 || y>=maze.getNCols() || !maze.getColor(x, y).equals(NON_BACKGROUND) ) {
      		return;
      	}
      	else if(x==(maze.getNRows()-1) && y==(maze.getNCols()-1)) {
   		   if(!maze.getColor(x, y).equals(NON_BACKGROUND) ) {
   		   		return;
   		   }
   		   PairInt temp3=new PairInt(x,y);
   		   trace.push(temp3);
   		   ArrayList<PairInt> temp=new ArrayList<PairInt>();
   		   temp.addAll(trace);
   		   result.add(temp);
   		   maze.recolor(x, y,NON_BACKGROUND);
   		   trace.pop();
   		   return;
   	   }
   	   
   	   else {
      		for(int i=0;i<4;i++) { // for loop 4 times covering (x+1,y), (x-1,y), (x,y+1), (x,y-1) in nextx, nexty
      			nextx=x+xmove[i];
      			nexty=y+ymove[i];
      			PairInt temp2=new PairInt(x,y);
      			trace.push(temp2);
      			maze.recolor(x, y,PATH);
      			findMazePathStackBased(nextx,nexty,result,trace);//Recursion
      			maze.recolor(x, y,NON_BACKGROUND);
      			trace.pop();		
      		}
      		return;	
      	  }
      	
       }
     
     public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
         ArrayList<ArrayList<PairInt>> result = new ArrayList<>(); 
         Stack<PairInt> trace = new Stack<>(); 
         findMazePathStackBased(0,0,result, trace); 
        return result;
        }
       
     
       public ArrayList<PairInt> findMazePathMin(int x, int y){
       	ArrayList<ArrayList<PairInt>> allroutes=findAllMazePaths(x, y);
       	ArrayList<PairInt> temp4=new ArrayList<PairInt>();
       	if(allroutes.size()==0) {
       		return temp4;
       	}
       	int minIndex=0;
       	int minsize=allroutes.get(minIndex).size();
       	for(int i=0;i<allroutes.size();i++) {
       		temp4=allroutes.get(i);
       		int tempsize=temp4.size();
       		if(tempsize<=minsize) {
       			minIndex=i;
       		}
       	}
       	return allroutes.get(minIndex);
       }
       

    // ADD METHOD FOR PROBLEM 2 HERE
    
    // ADD METHOD FOR PROBLEM 3 HERE
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
