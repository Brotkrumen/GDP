
public abstract class Speisen extends Lebensmittel {

	protected Speisen(String name, int menge) {
		super(name, menge);
	}
	
	@Override
	public boolean trinken() {
		return false;
	}
	
	public boolean essen( int menge ) {
		if( menge > this.menge ) {
			this.menge = 0;
			return false;
		} else {
			this.menge -= menge;
			return true;
		}
	}
	
	public String status() {
		return name + " " + (menge) + "g";
	}
}
