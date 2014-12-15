// TODO: Auto-generated Javadoc
/**
 * Functions to encode any integer as it's corresponding Gray code integer.
 * No recursion to be found here. And that's okay too.
 */
public class Gray {
	
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
}