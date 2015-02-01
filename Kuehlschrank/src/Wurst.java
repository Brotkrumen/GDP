
public class Wurst extends Speisen {
	
	public Wurst(String name, int menge) {
		super(name, menge);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean essen() {
		return super.essen(10);
	}

	@Override
	public String status() {
		return "Wurst: " + super.status();
	}
}
