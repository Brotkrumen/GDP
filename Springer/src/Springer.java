
public class Springer {
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
		
		int N = parseInput( args );
		
		Boolean [][] field = new Boolean[N][N];
		
		resetfield( field);
		
		placeJumper( N-1, N-1, field );
		
		/*
		while( y <= N-1 ) {
			placeJumper( x, y, field );
			drawField( field );
			resetfield( field);
			y++;
		}
		*/
	}
	
	public static void resetfield( Boolean [][] field ) {
		
		for( int i = 0; i <= field.length-1; i++) {
			for( int j=0; j<=field[0].length-1;j++) {
				field[i][j] = false;
			}
		}
	}
	
	public static void drawField( Boolean [][] field ) {
		String outstr = "";
		
		for( int i = 0; i <= field.length-1; i++) {
			for( int j=0; j<=field[0].length-1;j++) {
				if( field[i][j] == true ) outstr += "S";
				else outstr += "*";
			}
			outstr += "\n";
		}
		
		System.out.println( outstr + "---------");
	}
	
	public static void placeJumper( int x, int y, Boolean[][] field ) {
		
		if( y < 0 || x < 0 ) {
			return;
		}
		
		//if( !jumpercollision( x, y, field ) ) field[x][y] = true;
		field[x][y]= true;
		drawField( field );
		
		int afsd = 0;
		
		placeJumper( x-1, y, field);
		field[x][y]= false;
		placeJumper( x, y-1, field);

		
	}
	
	public static boolean jumpercollision( int x, int y, Boolean[][] field ) {
		
		int max = field.length-1;
		
		/* Fälle:
		 * x-2 y-1
		 * x-2 y+1
		 * x-1 y-2
		 * x-1 y+2
		 * x+1 y-2
		 * x+1 y+2
		 * x+2 y-1
		 * x+2 y+1 
		 */		
		
		if( !(x-2 < 0 || y-1 < 0) && field[x-2][y-1] ) {
			return true;
		}
		
		if( !(x-2 < 0 || y+1 > max) && field[x-2][y+1] ) {
			return true;
		}
		
		if( !(x-1 < 0 || y-2 < 0) && field[x-1][y-2] ) {
			return true;
		}
		
		if( !(x-1 < 0 || y+2 > max) && field[x-1][y+2] ) {
			return true;
		}
		
		if( !(x+1 > max || y-1 < 0) && field[x+1][y-1] ) {
			return true;
		}
		
		if( !(x+1 > max || y+1 > max) && field[x+1][y+1] ) {
			return true;
		}
		
		if( !(x+2 > max || y-2 < 0) && field[x+2][y-2] ) {
			return true;
		}
		
		if( !(x+2 > max || y+2 > max) && field[x+2][y+2] ) {
			return true;
		}
		
		
		
		return false;
	}
}
