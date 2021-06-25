package battlefield_inside;

/*
 * Giuseppe Damis
 * *
 */

public abstract class Robot implements Comparable<Robot> {
	
	protected Position posizione;
	protected int longevita;
	
	public Robot(Position p) {
		this.posizione = p;
		this.longevita = 0 ;
	}
	
	abstract public Position getPosizione();
	abstract public int incrementaLongevita();
	
	@Override
	public int compareTo(Robot r) {
		return this.longevita - r.longevita;
	}
}
