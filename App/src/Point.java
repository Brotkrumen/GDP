
public class Point {
	
	private double x;
	private double y;
	
	Point( double x, double y ) {
		this.x = x;
		this.y = y;
	}
	
	double getx() {
		return this.x;
	}
	
	double gety() {
		return this.y;
	}
	
	void setx( double x) {
		this.x = x;
	}
	
	void sety( double y) {
		this.y = y;
	}
	
	Point rotate( Angle angle ) {
		
		Point newPoint = new Point( x, y );
		
		newPoint.x = x * Math.cos( Math.toRadians( angle.get() ) ) - y * Math.sin( Math.toRadians(angle.get()) );
		newPoint.y = x * Math.sin( Math.toRadians( angle.get() ) ) + y * Math.cos( Math.toRadians(angle.get()) );
		
		return newPoint;
	}
	
	Point translate( Point shiftpoint) {
		x += shiftpoint.x;
		y += shiftpoint.y;
		
		return this;
	}
}
