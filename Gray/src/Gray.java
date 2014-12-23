// TODO: Auto-generated Javadoc
/**
 * Functions to encode any integer as its corresponding Gray code integer.
 * Recursive idea: generate INT_MAX long array with sedgewicks algorithm
 * and get toGray per index and fromGray per search.
 * Is silly, so abandoned and used bitwise xor instead.
 */
public class Gray {
	
	/**
	 * Parses the input.
	 *
	 * @param passed user input
	 * @return validated input
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
			System.out.println("argument keine zahl" );
			System.exit(-1);
		}
		
		if( input < 0 ) {
			System.out.println("argument kleiner 0" );
			System.exit(-1);
		}
		
		return input;
		
	}
	
	/**
	 * The main method.
	**/
	public static void main(String[] args) {
		
		int maxGray = parseInput( args );
		
		int grayMapped = toGray( maxGray );
		//System.out.println( fromGray( grayMapped ) );
		
		gray( "", maxGray );

		
	}
	
	/**
	 * Takes and Integer and reflects it to Gray code
	 *
	 * @param any integer
	 * @return corresponding Gray code
	 */
	public static int toGray( int n ) {
		return bitwiseXOR( n );
	}
	
	/**
	 * Takes an integer that presumably was a gray code
	 * and returns the original integer
	 *
	 * @param any integer
	 * @return the corresponding integer
	 */
	public static int fromGray( int n ) {
		return reverseGray( n );
	}
	
	/**
	 * Bitwise xor.
	 *
	 * @param any integer
	 * @return bitwise xor
	 */
	private static int bitwiseXOR( int n ) {
		
		String binary = Integer.toBinaryString(n);
		String xor = "";
		
		for( int i = 0; i <= binary.length()-1; i++ ) {
			if( i == 0 ) {
				xor += binary.charAt( 0 );
				continue;
			}
			
			xor += binary.charAt( i-1 ) ^ binary.charAt( i );
		}
		
		return Integer.parseInt( xor, 2);
	}
	
	/**
	 * Reverse gray.
	 *
	 * @param integer
	 * @return non-gray code integer
	 */
	private static int reverseGray( int n ) {
		
		String graycode = Integer.toBinaryString(n);
		String binary = "";
		
		for( int i = 0; i <= graycode.length()-1; i++ ) {
			if( i == 0 ) {
				binary += graycode.charAt( 0 );
				continue;
			}
			
			binary += binary.charAt( i-1 ) ^ graycode.charAt( i );
		}
		
		return Integer.parseInt( binary, 2);
	}
	
	/**
	 * recursive gray generation. appends 0 first
	 * http://introcs.cs.princeton.edu/java/23recursion/
	 * pull date 20.12.2014
	 *
	 * @param the current graycode string
	 * @param the current depth
	 */
	private static void gray( String graycode, int n ) {
		if( n == 0 ) {
			System.out.println( graycode );
			return;
		}
		gray( graycode + "0", n - 1 );
		yarg( graycode + "1", n - 1 );
	}
	
	/**
	 * recursive gray generation. appends 1 first.
	 *
	 * @param the current graycode string
	 * @param the current depth
	 * @return the generated graycode
	 */
	private static void yarg( String graycode, int n ) {
		
		if( n == 0 ) {
			System.out.println( graycode );
			return;
		}
		
		gray( graycode + "1", n - 1);
		yarg( graycode + "0", n - 1 );
	}
}