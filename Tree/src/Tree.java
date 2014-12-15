import gdp.stdlib.StdDraw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 * Recursively generates a pythagorean tree.
 */
public class Tree {
	
	/**
	 * validates the input.
	 *
	 * @param args from main
	 * @return integer
	 */
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
	
	/**
	 * The main method.
	 *
	 * @param depth of recursion
	 */
	public static void main(String[] args) {
		
		int n = parseInput(args); // Anzahl der auszugebenen Vielecke

		double myscale = 10.0; // Groeße der Zeichenebene in alle Richtungen
		StdDraw.setXscale(-myscale, myscale);
		StdDraw.setYscale(-myscale, myscale);
		double[] pos = new double[] { 0, -myscale + 0.3 }; // alle Punkte sind Arrays mit zwei Eintraegen 
		
		TreeList Tree = new TreeList();
		Tree.init( pos, 3 );
		
		Tree.addBranches( n );
		
		Tree.drawTree();
		
	}
	
	/**
	 * Isogon class handles the construction of the squares.
	 */
	static class Isogon {
		
		/** Holding the squares coordinates */
		private double[][] coordinates = new double[4][2];
		
		/** Length of the sides */
		public double sidelen = 0;
		
		/** Angle of the square */
		public double angle = 0;
		
		/**
		 * Instantiates a new isogon, sets the attributes, shifts and rotates it.
		 *
		 * @param where the bottom left point of the square should be put
		 * @param the rotation of the square in degrees
		 * @param scale in terms of the unit square
		 */
		public Isogon( double[] startpoint, double angle, double scale ) {
			
			//starting left bottom on the unit circle
			this.coordinates[0][0] = 0;
			this.coordinates[0][1] = 0;
			
			//counter clockwise points
			this.coordinates[1][0] = scale;
			this.coordinates[1][1] = 0;
			
			this.coordinates[2][0] = scale;
			this.coordinates[2][1] = scale;
			
			this.coordinates[3][0] = 0;
			this.coordinates[3][1] = scale;
			
			sidelen = scale;
			this.angle = angle;
			
			rotatePoints2D( angle, this.coordinates );
			translatePoints2D( startpoint, this.coordinates );
	
		}
		
		/**
		 * Instantiates a new isogon.
		 */
		public Isogon() {
		}
		
		/**
		 * Gets the top left corner.
		 *
		 * @return the top left point
		 */
		public double[] getTopLeft() {
			return this.coordinates[3];
		}
		
		/**
		 * Gets the bot right corner.
		 *
		 * @return the bot right point
		 */
		public double[] getBotRight() {
			return this.coordinates[1];
		}
		
		/**
		 * Rotiert 2D-Punkt.
		 *
		 * @param angle            Winkel 0...360
		 * @param point            Koordinaten des Punktes als Array
		 * @return Koordinaten des rotierten Punktes
		 */
		private static double[] rotateP(double angle, double[] point) {
			double[] rotatedP = new double[2];
			
			rotatedP[0] = point[0] * Math.cos( Math.toRadians(angle) ) - point[1] * Math.sin( Math.toRadians(angle) );
			rotatedP[1] = point[0] * Math.sin( Math.toRadians(angle) ) + point[1] * Math.cos( Math.toRadians(angle) );
			
			return rotatedP;
		}
		
		/**
		 * Rotiert Menge von Punkten in 2D.
		 *
		 * @param angle            Drehwinkel in Grad
		 * @param points            urspruengliche Punkte
		 */
		private static void rotatePoints2D( double angle, double[][] points ) {
			
			for( int i = 0; i<= points.length-1; i++ ) {
				points[i] = rotateP( angle, points[i] );
			}
			
		}

		/**
		 * Verschiebt Menge von Punkten in 2D.
		 *
		 * @param shift            2D Verschiebung als Array
		 * @param points            urspruengliche Punkte
		 * @return neue Punkte
		 */
		private static double[][] translatePoints2D(double[] shift, double[][] points) {
			
			for( int i = 0; i <= points.length - 1; i++ ) {
				points[i][0] += shift[0];
				points[i][1] += shift[1];
			}
			
			return points;
		}
		
	}
	
	
	/**
	 * Holds references to all isogons, handles drawing them.
	 */
	static class TreeList {
		
		/** ArrayList of the Isogons. */
		private ArrayList < Isogon > IsoArray =  new ArrayList < Isogon >();
		
		/** Random number generator */
		private Random rnd = new Random();
		
		//constructor with parameters
		/**
		 * Inits the.
		 *
		 * @param botleft point of the starting square
		 * @param scale of the square
		 */
		private void init( double[] start, double scale ) {
			this.IsoArray.add( new Isogon( start, 0, scale ) );
		}
		
		/**
		 * Adds n branches recursively
		 *
		 * @param n		depth of recursion
		 */
		private void addBranches( int n ) {
			
			if( n == 0 ) return;
			
			Isogon lastIso = IsoArray.get( IsoArray.size() - 1 );

			//angle between 30 and 60;
			double angle = rnd.nextInt( 30) + 30;
			
			double sidelen = calcside( lastIso.sidelen, angle );
			
			Isogon newIso = new Isogon( lastIso.getTopLeft(), angle + lastIso.angle, sidelen );
			IsoArray.add( newIso );
			
			addBranches( n-1 );
			
			sidelen = calcside( lastIso.sidelen, 90 - angle );
			newIso = new Isogon( newIso.getBotRight(), angle + lastIso.angle - 90 , sidelen );
			IsoArray.add( newIso );
			
			addBranches( n-1 );
		}
		
		/**
		 * Calculate the length of the opposite side
		 *
		 * @param length of the hypthenuse
		 * @param angle
		 * @return length of the opposite side
		 */
		private double calcside( double sidelen, double angle ) {

			return sidelen * Math.cos( Math.toRadians( angle ) );					
		}
		
		/**
		 * Loops through the List of isogons and draws them line by line
		 */
		private void drawTree() {
			Iterator<Isogon> it = IsoArray.iterator();
			Isogon iso = new Isogon();
			while( it.hasNext() ) {
				iso = it.next();
				
				StdDraw.line( iso.coordinates[0][0], iso.coordinates[0][1],
						iso.coordinates[1][0], iso.coordinates[1][1]);
				
				StdDraw.line( iso.coordinates[1][0], iso.coordinates[1][1],
						iso.coordinates[2][0], iso.coordinates[2][1]);
				
				StdDraw.line( iso.coordinates[2][0], iso.coordinates[2][1],
						iso.coordinates[3][0], iso.coordinates[3][1]);
				
				StdDraw.line( iso.coordinates[3][0], iso.coordinates[3][1],
						iso.coordinates[0][0], iso.coordinates[0][1]);
			
			}
		}
	}
}