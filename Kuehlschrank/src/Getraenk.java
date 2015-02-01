
public abstract class Getraenk extends Lebensmittel {

	protected Getraenk(String name, int menge) {
		super(name, menge);
	}
	
	@Override
	public boolean essen() {
		return false;
	}
	
	public boolean trinken( int menge ) {
		if( menge > this.menge ) {
			this.menge = 0;
			return false;
		} else {
			this.menge -= menge;
			return true;
		}
	}
	
	@Override
	public String status() {
		return name + " " + (menge) + "ml";
	}
}
