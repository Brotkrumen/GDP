import gdp.stdlib.StdDraw;


public class Isogon {
	
	Point[] points;
	
	Isogon( int n ) {
		unit_isogon_points( n );
	}
	
	/**
	* Generiert Eckpunkte für Isogon mit n-Ecken auf Einhheitskreis.
	* (ein n-Einheitspolygon)
	* Isogone werden als Arrays ihrer Eckpunkte repraesentiert, um beim 
	* Zeichnen per StdDraw.polygon einen geschlossenen Polygonzug zu erhalten,
	* muss der Anfangspunkt auch als Endpunkt erfasst werden !
	* 
	* @param n
	*            Ecken
	* @return Eckpunkte
	*/
	void unit_isogon_points(int n) {
		points = new Point[n+1];
		
		points[0] = new Point( 1, 0 );
		
		Angle angle = new Angle( 360.0 / n );
		
		for( int i = 1; i <= n; i++ ) {
			points[i] = points[i-1].rotate( angle );
		}
	}
	
	Isogon rotate( Angle angle ) {
		
		for( int i = 0; i <= points.length - 1 ; i++ ) {
			points[i] = points[i].rotate( angle );
		}
		
		return this;
		
	}
	
	Isogon translate( Point shiftpoint) {
		
		for( int i = 0; i <= points.length - 1 ; i++ ) {
			points[i].translate( shiftpoint );
		}
		
		return this;		
	}
	
	double[] getCoordinates( int mode) {

		double[] coords = new double[ points.length ];
		
		for( int i = 0; i <= points.length - 1; i++ ) {
			
			if( mode == 0 ) { coords[i] = points[i].getx(); }
			else { coords[i] = points[i].gety(); }
		}
		
		return coords;
	}
    
	/**
	* Zeichnet ein Isogon mit n Ecken, welches aus dem
	* n-Einheitspolygon durch Drehung um angle und Verschiebung um shift
	* hervorgeht
	* 
	* @param n
	*            Ecken 
	* @param angle
	*            Drehung
	* @param shift
	*            Verschiebung
	* @return Eckpunkte
	*/
	void draw() {
		
		double[] isogonx = getCoordinates( 0 );
		double[] isogony = getCoordinates( 1 );
		
		StdDraw.polygon(isogonx, isogony);
	}
}
