package Chess;


/**
 * 
 * @author Kexiang Wang(kwang66)
 *
 */
public class Position {
	public int x;
	public int y;
	
	/**
	 * constructor
	 * @param x
	 * @param y
	 */
	public Position(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	/**
	 * copy constructor
	 * @param copy : another position
	 */
	public Position(Position copy) {
		this.x=copy.x;
		this.y=copy.y;
	}
	
	/**
	 * check if two position is equal in values
	 * @param p
	 * @return true : if positions are the same
	 * 		   false: otherwise 
	 */
	public boolean equals(Position p) {
		return this.x==p.x && this.y==p.y;
	}
	
}
