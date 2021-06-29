package battlefield_inside;

/*
 * Giuseppe Damis
 * *
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;

/* Senza introdurre nuove classi, implementare un metodo 
 * SortedSet<Robot> getRobotOrdinatiPerLongevità() 
 * all'interno della classe Battlefield che restituisca 
 * l'insieme ordinato di tutti robot del campo ordinato 
 * per longevità. 
 * (vedi DOMANDA 6) 
 */
public class RobotOrdinatiPerLongevitaTest {

	private Battlefield field;
	
	private Position origine;
	private Position unitari;
	
	@Before
	public void setUp() throws Exception {
		this.field = new Battlefield(2);
		this.origine = new Position(0, 0);
		
		this.unitari = new Position(1, 1);
	}
	
	
	//Rifattorizzato per DOMANADA 2
	private void setLongevita(Robot r, int l) {
		for(int i=0; i<l; i++)
			r.incrementaLongevita();
	}
	
	@Test
	public void testRobotOrdinatiPerLongevitaTest_equals() {
		this.field.addRobot(new Walker(origine)); 
		this.field.addRobot(new Walker(origine)); 
		
		assertEquals(1, this.field.getRobotOrdinatiPerLongevita().size());
	}
	
	
	//Rifattorizzato per DOMANADA 2
	@Test 
	public void testRobotOrdinatiPerLongevitaTest_stessaLongevitaTipoDiverso() {
		this.field.addRobot(new Walker(origine)); 
		this.field.addRobot(new Chaser(unitari)); 
		
		assertEquals(1, this.field.getRobotOrdinatiPerLongevita().size());
	}
	
	//Rifattorizzato per DOMANADA 2
	@Test 
	public void testRobotOrdinatiPerLongevitaTest_longevitaDiverse() {
		final Robot giovane = new Walker(origine);
		
		setLongevita(giovane, 0);   
		this.field.addRobot(giovane);
		
		final Robot vecchio = new Chaser(unitari);
		setLongevita(vecchio, 100); 
		
		this.field.addRobot(vecchio);
		final SortedSet<?> perLongevita = this.field.getRobotOrdinatiPerLongevita();
		
		assertEquals(2, perLongevita.size());
		assertSame(giovane, perLongevita.first());
		
		assertSame(vecchio, perLongevita.last());
	}

}
