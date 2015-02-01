
public class Brot extends Speisen{
	private Brot(String name, int menge) {
		super(name, menge);
	}
	
	public Brot(int type, int menge) {
		this( "Brot", menge );
		settype(type);		
	}
	
	//setters
	private void settype( int type ) {
		switch( type ) {
			case 0: 	name = "Weißbrot";
						break;
			case 1: 	name = "Schwarzbrot";
						break;
			case 2: 	name = "Mischbrot";
						break;
			default:	name = "Spezialbrot";
						break;
		}
	}
	
	

	//functions
	@Override
	public boolean essen() {
		return super.essen(50);
	}

	@Override
	public String status() {
		return "Brot: " + super.status();
	}
}
