package battlefield_inside;

/*
 * Giuseppe Damis
 * *
 */

/*
 * Da sistemare. Vedi PositionTest.
 * (vedi DOMANDA 1)
 */
public class Position {
	
	private int x, y;

	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
		
	public String toString() {
		return this.x+"-"+this.y;
	}
	
	@Override
	public boolean equals(Object o) {
		return (this.getX() == ((Position)o).getX() && this.getY() == ((Position)o).getY());
	}
	
	@Override
	public int hashCode() {
		return this.getX() + this.getY();
	}


}