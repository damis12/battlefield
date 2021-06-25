package battlefield_inside;

/*
 * Giuseppe Damis
 * *
 */

import java.util.*;

public class Battlefield {

	static final private Class<?>[] TIPOLOGIE = { Walker.class, Chaser.class } ;

	static final private int NUMERO_TIPOLOGIE = TIPOLOGIE.length;

	static final private double OCCUPAZIONE_INIZIALE = 0.01d; // 1%

	private int dim;

	private Random random;
	
	private Map<Position, Walker> posizione2walker;
	private Map<Position, Chaser> posizione2chaser;
	private Map<Position, Robot> posizione2robot;

	public Battlefield(int dimensione) {
		this.dim = dimensione;
		this.posizione2walker = new HashMap<>();
		this.posizione2chaser = new HashMap<>();
		this.posizione2robot = new HashMap<>(); //DOMANDA 2
		this.random = new Random();
	}
	
	// DOMANDA 2
	public void addRobot(Robot r) {
		posizione2robot.put(r.getPosizione(), r);
	}
	

	// DOMANDA 1
	public void addWalker(Walker w) {
		posizione2walker.put(w.getPosizione(), w);
	}

	// DOMANDA 1
	public void addChaser(Chaser c) {
		posizione2chaser.put(c.getPosizione(), c);
	}

	
	// DOMANDA 2
	public Robot getRobot(Position p) {
		return posizione2robot.get(p);
	}
	


	public Walker getWalker(Position p) {
		return posizione2walker.get(p);
	}

	public Chaser getChaser(Position p) {
		return posizione2chaser.get(p);
	}

	public Collection<Walker> getAllWalkers() {
		return this.posizione2walker.values();
	}

	public Collection<Chaser> getAllChasers() {
		return this.posizione2chaser.values();
	}

	
	// DOMANDA 2
	public Collection<Robot> getAllRobots() {
		return this.posizione2robot.values();
	}

	@SuppressWarnings("rawtypes")
	// DOMANDA 3
	public Map<Class, Set<?>> raggruppaRobotPerTipo() {
		Map<Class, Set<?>> temp = new HashMap<Class, Set<?>>();
			
		Set<Robot> robotsPrimoTipo = new HashSet<Robot>();
		Set<Robot> robotsSecondoTipo = new HashSet<Robot>();
		
		Set<Position> positions = this.posizione2robot.keySet();
		
		Class primoTipo = Walker.class;
		Class secondoTipo = Chaser.class;
		
		for(Position p : positions) {
			Robot r = this.posizione2robot.get(p);
			
			if (r.getClass().equals(primoTipo))	
				robotsPrimoTipo.add(r);
			else
				robotsSecondoTipo.add(r);
		}
		
		temp.put(primoTipo, robotsPrimoTipo);
		temp.put(secondoTipo, robotsSecondoTipo);
		
		
		return temp;
	}
	
	// DOMANDA 4
	public List<?> getRobotOrdinatiPerPosizione() {
		ComparatoreRobotPerPosizione cmp = new ComparatoreRobotPerPosizione();
		List<Robot>  robots  = new ArrayList<Robot>(this.getAllRobots());
		Collections.sort(robots, cmp);
		return robots;
	}
	
	// DOMANDA 6
	public SortedSet<?> getRobotOrdinatiPerLongevita() {
		System.out.println(this.getAllRobots().size());
		
		for(Robot r : this.getAllRobots())
			System.out.println("-long " + r.longevita);
		
		SortedSet<Robot>  robots = new TreeSet<Robot>(this.getAllRobots());
		
		System.out.println("-> " + robots.size());
		
		return robots;
	}
	
	public List<Position> adiacenti(Position perno) {
		final List<Position> adiacenti = new LinkedList<>();
		int x = perno.getX();
		int y = perno.getY();

		for(int i = -1; i<2; i++) {
			for(int j = -1; j<2; j++) {
				Position adiacente = new Position(x+i, y+j);
				if (inCampo(adiacente))
					adiacenti.add(adiacente);
			}
		}
		adiacenti.remove(perno);  // coincidono: quindi non sono adiacenti
		
		Collections.shuffle(adiacenti); /* ordine casuale */
		return adiacenti;
	}

	private boolean inCampo(Position p) {
		return  p.getX()>=0 && p.getX()<this.dim && 
				p.getY()>=0 && p.getY()<this.dim  ;
				
	} 

	public Position posizioneLiberaVicino(Position posizione) {
		for(Position p : this.adiacenti(posizione)) {
			if (this.isLibera(p)) {
				return p;
			}
		}
		return null;
	}

	public boolean isLibera(Position posizione) {
		return ( this.getWalker(posizione)==null && this.getChaser(posizione)==null);
	}

	public int getDimensione() {
		return this.dim;
	}

	public void riempi() {
		long numeroIniziale = Math.round(OCCUPAZIONE_INIZIALE * dim * dim);
		for(int i=0 ; i<numeroIniziale; i++) {
			int x = this.random.nextInt(this.dim);
			int y = this.random.nextInt(this.dim);
			Position posizione = new Position(x, y);
			if (this.isLibera(posizione)) {
				switch (this.random.nextInt(NUMERO_TIPOLOGIE)) {
				case 0: Chaser chaser = new Chaser(posizione);
						//this.addChaser(chaser);
						this.addRobot(chaser); //DOMANDA 2
				break;
				case 1: Walker walker = new Walker(posizione);
						//this.addWalker(walker);
						this.addRobot(walker); //DOMANDA 2
				break;
				//case: NUMERO_TIPOLOGIE-1...
				default: throw new IllegalStateException();
				} 
			}
		}
	}

}
