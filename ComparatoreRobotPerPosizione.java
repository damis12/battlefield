package battlefield_inside;

/*
 * Giuseppe Damis
 * *
 */

import java.util.*;

public class ComparatoreRobotPerPosizione implements Comparator<Robot> {
	
	public int compare(Robot r1, Robot r2) {
		
		if (r1.posizione.getX() > r2.posizione.getX())
			return 1;
		if (r1.posizione.getX() < r2.posizione.getX())
			return -1;
		return r1.posizione.getY() - r2.posizione.getY();
	}

}
