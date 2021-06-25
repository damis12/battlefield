package battlefield_inside;

/*
 * Giuseppe Damis
 * *
 */

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;

public class RobotOrdinatiPerPosizioneTest {
	
	// (vedi DOMANDA 5)
	// scrivere dei test-case minimali per il metodo
	// Battlefield.getRobotOrdinatiPerPosizione()
	
	private Battlefield field;
	private List<Robot> robots ;
	

	@Test
	public void initRobots() {
		
		if (this.robots != null) {
				this.robots.clear();
				assertEquals(0, robots.size());
				System.out.println("sono qui");		
		}
		this.field = new Battlefield(2);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRobotOrdinatiPerPosizione() {
		
		// empty field
		this.initRobots();
		this.robots =  (List<Robot>) this.field.getRobotOrdinatiPerPosizione();
		assertEquals(0, robots.size());
		
		//field contains a single robot
		this.initRobots();
		this.field.addRobot(new Walker(new Position(2,0)));
		this.robots =  (List<Robot>) this.field.getRobotOrdinatiPerPosizione();
		assertEquals(1, robots.size());
		
		//field contains two robots with two different X's position
		this.initRobots();
		this.field.addRobot(new Walker(new Position(2,0)));
		this.field.addRobot(new Chaser(new Position(1,0)));
		this.robots =  (List<Robot>) this.field.getRobotOrdinatiPerPosizione();
		assertEquals(2, robots.size());
		assertTrue(robots.get(0).posizione.equals(new Position(1,0)));
		assertTrue(robots.get(1).posizione.equals(new Position(2,0)));
		
		//campo contenente due robot in posizione di pari X ma Y diversa
		this.initRobots();
		this.field.addRobot(new Chaser(new Position(2,3)));
		this.field.addRobot(new Walker(new Position(2,0)));
		this.robots =  (List<Robot>) this.field.getRobotOrdinatiPerPosizione();
		assertEquals(2, robots.size());
		assertTrue(robots.get(0).posizione.equals(new Position(2,0)));
		assertTrue(robots.get(1).posizione.equals(new Position(2,3)));
		
		// quattro robot nel campo
		this.initRobots();
		this.field.addRobot(new Walker(new Position(2,3)));
		this.field.addRobot(new Chaser(new Position(7,9)));
		this.field.addRobot(new Walker(new Position(5,4)));
		this.field.addRobot(new Chaser(new Position(6,10)));
		this.robots =  (List<Robot>) this.field.getRobotOrdinatiPerPosizione();
		assertEquals(4, robots.size());
		assertTrue(robots.get(0).posizione.equals(new Position(2,3)));
		assertTrue(robots.get(1).posizione.equals(new Position(5,4)));
		assertTrue(robots.get(2).posizione.equals(new Position(6,10)));
		assertTrue(robots.get(3).posizione.equals(new Position(7,9)));
		
	}
	
	
}
