
public class Wasser extends Getraenk {
	public Wasser(String name, int menge) {
		super(name, menge);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean trinken() {
		return super.trinken( 200 );
	}

	@Override
	public String status() {
		return "Wasser: " + super.status();
	}
}
