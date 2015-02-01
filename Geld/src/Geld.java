class Geld {
	
	private static boolean USE_CACHE = false;
	private static long[][] CACHE = new long[1000][8];
	private static int[] VALUE = {1, 2, 5, 10, 20, 50, 100, 200 };
	
	private static int parseInput( String[] args ) {
		
		int pos = 0;
		
		if( args.length == 0 ) {
			System.out.println( "Aufruf mit Geldbetrag (in Cent) als Parameter" );
			System.exit(-1);
		}
		
		if( args.length > 2 ) {
			System.out.println( "nur 2 argumente bidde" );
			System.exit(-1);
		}
		
		if( args[pos].equals("-c") ) {
			USE_CACHE = true;
			pos++;
		}
		
		int input = 0;
		try {
			input = Integer.parseInt(args[pos]);
		} catch ( Throwable ablageP ) {
			System.out.println("Aufruf mit Geldbetrag (in Cent) als Parameter" );
			System.exit(-1);
		}
		
		if( input < 0 ) {
			System.out.println("argument kleiner 0" );
			System.exit(-1);
		}
		
		return input;
		
	}
	
	public static void main(String[] s) {
		
		// interpretiere (ersten) Programmparameter als Centbetrag in sum
		// bei Aufruf ohne Parameter - erzeuge Fehlerausschrift
		int sum = parseInput( s );

		System.out.print(euro(sum)); 
		System.out.print(" kann auf ");
		System.out.print(pay(sum));
		System.out.println(" verschiedene Arten passend bezahlt werden");
	}

	public static long pay (int m) {
		// implementiert die Funktion |M(x)|, s.o.
		
		if( USE_CACHE && CACHE.length <= m ) {
			CACHE = new long[m*2][8];
		}
		
		return pay( m, 7 );
	}
	
	private static long pay( int m, int n) {
		
		if( n == 0 || m == 1 )
			return 1;
		if( m < 0 )
			return 0;
		
		if( USE_CACHE ) {
			if( CACHE[m][n] != 0 ) {
				return CACHE[m][n];
			} else {
				CACHE[m][n] = pay( m-VALUE[n], n ) + pay( m, n-1 );
				return CACHE[m][n];
			}
		}
		
		// no cache
		return pay( m-VALUE[n], n ) + pay( m, n-1 );
		
	}

	public static String million() {
		// ermittelt den kleinsten Betrag, der auf mindestens 1.000.000 
		// verschiedene Weisen bezahlt werden kann
		
		USE_CACHE = true;
		int m = 0;
		
		while( pay( m ) < 1000000 ) {
			m++;
		}
		
		return euro(m);
	}

	public static String billion() {
		// ermittelt den kleinsten Betrag, der auf mindestens 1.000.000.000 
		// verschiedene Weisen bezahlt werden kann
		
		USE_CACHE = true;
		int m = 0;
		
		while( pay( m ) < 1000000000 ) {
			m++;
		}
		
		return euro(m);
	}

	public static String euro(int cent) {
		//erzeuge eine Zeichenkette, die den Centbetrag cent in Euro im Format "ee...ee,cc Euro" enthaelt
		
		if( cent % 100 < 10 )		
			return cent / 100 + ",0" + cent % 100 + " Euro";
		else
			return cent / 100 + "," + cent % 100 + " Euro";
	}
	
}