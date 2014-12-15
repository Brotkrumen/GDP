
public class Roman {
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
			System.out.println( "bidde mehr als 0 wollen" );
			System.exit(-1);
		}
		
		return input;
		
	}
	
	static String toRomanSane(int n) {
		String romanstr = "";
		
		while( n >= 1000 ) {
			romanstr += "M";
			n -= 1000;
		}
		
		while( n >= 900 ) {
			romanstr += "CM";
			n -= 900;
		}
		
		while( n >= 500 ) {
			romanstr += "D";
			n -= 500;
		}
		
		while( n >= 400 ) {
			romanstr += "CD";
			n -= 400;
		}
		
		while( n >= 100 ) {
			romanstr += "C";
			n -= 100;
		}
		
		while( n >= 90 ) {
			romanstr += "XC";
			n -= 90;
		}
		
		while( n >= 50 ) {
			romanstr += "L";
			n -= 50;
		}
		
		while( n >= 40 ) {
			romanstr += "XL";
			n -= 40;
		}
		
		while( n >= 10 ) {
			romanstr += "X";
			n -= 10;
		}
		
		while( n >= 9 ) {
			romanstr += "IX";
			n -= 9;
		}
		
		while( n >= 5 ) {
			romanstr += "V";
			n -= 5;
		}
		
		while( n >= 4 ) {
			romanstr += "IV";
			n -= 4;
		}
		
		while( n >= 1 ) {
			romanstr += "I";
			n -= 1;
		}
		
		return romanstr;
	}
	
	static String toRoman(int n) {
		String romanstr = "";
		
		System.out.println( toRomanSane( n ));
		
		return romanstr;
	}
	
	public static void main(String[] args) {
		if (args.length==0) return;
		int N = parseInput( args );
		
		System.out.println(toRoman(N));
	}
}
