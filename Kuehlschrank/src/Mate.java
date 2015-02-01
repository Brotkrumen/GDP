
public class Mate extends Getraenk {

	private Mate(String name, int menge) {
		super(name, menge);
		// TODO Auto-generated constructor stub
	}
	
	public Mate(String name) {
		super(name, 500);
	}

	@Override
	public boolean trinken() {
		return super.trinken( 100 );
	}

	@Override
	public String status() {
		return "Mate: " + super.status();
	}
}
