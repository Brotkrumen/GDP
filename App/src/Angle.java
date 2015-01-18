
public class Angle {
	
	private double angle;
	
	Angle( double angle ) {
		this.angle = angle;
	}
	
	public Angle add( Angle angle ) {
		this.angle += angle.angle;
		return this;
	}
	
	public double get() {
		return this.angle;
	}
}
