import gdp.stdlib.StdDraw;

public class Isogons {
	
	private static int parseInput( String[] args ) {
		
		if( args.length > 1 ) {
			System.out.println( "nur ein argument bidde" );
			System.exit(-1);
		}
		
		int input = 0;
		try {
			input = Integer.parseInt(args[0]);
		} catch ( Throwable ablageP ) {
			System.out.println("argument kein int" );
			System.exit(-1);
		}
		
		if( input < 1 ) {
			System.out.println( "bidde mehr als 1 polygon wollen" );
			System.exit(-1);
		}
		
		return input;
		
	}

	public static void main(String[] args) {
		
		int n = parseInput(args); // Anzahl der auszugebenen Vielecke

		double myscale = 5.0; // Groeße der Zeichenebene in alle Richtungen

		// Gleichmaessige Anordnung
		double angle_diff = 360. / n;
		double angle = 0.0;
		double[] pos = new double[] { 3., 0 }; // alle Punkte sind Arrays mit zwei Eintraegen 

		StdDraw.setXscale(-myscale, myscale);
		StdDraw.setYscale(-myscale, myscale);
		
		for (int i = 0; i < n; i++, angle += angle_diff, pos = rotateP(angle_diff, pos)) {
			    drawIsogon(3 + i, angle, pos); // Beginn mit Dreieck
		}
		
	}

	/**
	* Rotiert 2D-Punkt
	* 
	* @param angle
	*            Winkel 0...360
	* @param point
	*            Koordinaten des Punktes als Array
	* @return Koordinaten des rotierten Punktes
	*/
	static double[] rotateP(double angle, double[] point) {
		double[] rotatedP = new double[2];
		
		rotatedP[0] = point[0] * Math.cos( Math.toRadians(angle) ) - point[1] * Math.sin( Math.toRadians(angle) );
		rotatedP[1] = point[0] * Math.sin( Math.toRadians(angle) ) + point[1] * Math.cos( Math.toRadians(angle) );
		
		return rotatedP;
	}

	/**
	* Verschiebt Menge von Punkten in 2D
	* 
	* @param shift
	*            2D Verschiebung als Array
	* @param points
	*            urspruengliche Punkte
	* @return neue Punkte
	*/
	static double[][] translatePoints2D(double[] shift, double[][] points) {
		
		for( int i = 0; i <= points.length - 1; i++ ) {
			points[i][0] += shift[0];
			points[i][1] += shift[1];
		}
		
		return points;
	}

	/**
	* Rotiert Menge von Punkten in 2D
	* 
	* @param angle
	*            Drehwinkel in Grad
	* @param points
	*            urspruengliche Punkte
	* @return neue Punkte
	*/
	static double[][] rotatePoints2D(double angle, double[][] points) {
		
		for( int i = 0; i <= points.length - 1; i++ ) {
			points[i] = rotateP( angle, points[i] );
		}
		
		return points;
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
	static double[][] unit_isogon_points(int n) {
		double[][] points = new double[n+1][2];
		
		points[0][0] = 1;
		points[0][1] = 0;
		
		double angle_chg = 360.0 / n;
		
		for( int i = 1; i <= n; i++ ) {
			points[i] = rotateP( angle_chg, points[i-1] );
		}
		
		//points[n][0] = 1;
		//points[n][1] = 0;
		
		return points;
	}
    
	/**
	* Generiert Eckpunkte für Isogon mit n Ecken, welches aus einem
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
	static double[][] isogon_points(int n, double angle, double[] shift) {
		
		double[][] unit_isogon = unit_isogon_points( n );
		
		rotatePoints2D( angle, unit_isogon );
		translatePoints2D( shift, unit_isogon );
		
		
		return unit_isogon;
	}

	/**
	* Wandelt die Eckpunkte eines Isogons (Array von Punkten) in das fuer 
	* die grafische Ausgabe (mittels StdDraw.polygon) noetige Format um
	*
	* @param points
	*            die Ecken eines Isogons (als Punktpaare) 
	* @param index
	*            0 - alle x-Koordinaten
	*            1 - alle y-Koordinaten
	* @return Array von x- oder y-Koordinaten
	*/
	static double[] getCoordinates(double[][] points, int index) {

		double[] coords = new double[ points.length];
		
		for( int i = 0; i <= points.length - 1; i++ ) {
			coords[i] = points[i][index];
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
	static void drawIsogon(int n, double angle, double[] shift) {
		
		double[][] isogon = isogon_points( n, angle, shift );
		
		double[] isogonx = getCoordinates( isogon, 0 );
		double[] isogony = getCoordinates( isogon, 1 );
		
		StdDraw.polygon(isogonx, isogony);
	}
}