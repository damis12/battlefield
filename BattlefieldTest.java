package battlefield_inside;

/*
 * Giuseppe Damis
 * *
 */

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.List;

import org.junit.Before;
import org.junit.Test;

/* Modificare la classe Position affinche' 
 * il primo test abbia successo (vedi DOMANDA 1) 
 */
public class BattlefieldTest {
	
	private Battlefield field;
	
	@Before
	public void setUp() throws Exception {
		this.field = new Battlefield(2);
	}

	@Test
	public void testAddWalker() {
		assertEquals(0, this.field.getAllWalkers().size());
		this.field.addWalker(new Walker(new Position(0,0)));
		assertEquals(1, this.field.getAllWalkers().size());
	}
	
	@Test
	public void testRaggruppaRobotDiDueTipiDiversi() {
		
		this.field.addRobot(new Walker(new Position(0,0)));
		this.field.addRobot(new Walker(new Position(2,0)));
		this.field.addRobot(new Walker(new Position(7,5)));
		
		this.field.addRobot(new Chaser(new Position(1,1)));
		this.field.addRobot(new Chaser(new Position(5,6)));
		this.field.addRobot(new Chaser(new Position(1,0)));
		this.field.addRobot(new Walker(new Position(0,2)));
		
		@SuppressWarnings("rawtypes")
		Map<Class, Set<?>> rpt = this.field.raggruppaRobotPerTipo();
		
		assertEquals(2, rpt.size());
		
		assertTrue(rpt.containsKey(new Chaser(new Position(0,0)).getClass()));
		
		assertTrue(rpt.containsKey(new Walker(new Position(0,0)).getClass()));
		
		assertEquals(2, rpt.values().size());
			
		
		//fail("vedi DOMANDA 3");
	}

}
