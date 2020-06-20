//Name:Randheer Vennapureddy
 //CWID:10457392
 
package Maze;

public class PairInt {
	private int x;
	private int y;
	
	public PairInt(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	public boolean equals(Object p) {
		if(p==null) {
			return false;
		}
		PairInt a=(PairInt)p;
		boolean res=(this.x==a.x && this.y==a.y);
		return res;
	}
	
	public String toString() {
		return ("("+x+","+y+")");
		
	}
	
	public PairInt copy() {
		PairInt temp=new PairInt(x,y);
		return temp;
	}

}
