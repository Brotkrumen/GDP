package arc;
/**
 * 
 */

/**
 * @author orlowsks
 *
 */
public class Quer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int minsteps = Integer.parseInt(args[0]);
		
		//negative Zahlen sind verboten!
        if( minsteps <= 0 ) {
        	System.out.println( "nur positive ganze Zahlen als Argumente erlaubt. Idealerweise unter 11" );
        	System.exit(-1);
        }
        
        long starttime = System.currentTimeMillis();
        
        long zahl = 1;
        long tempzahl = 0;
        long quer = 1;
        int steps = 0;
        int laststepcount = 1;
        String zahlstring;
        String comparedigit = "";
        String lastdigit = "1";
        int skipthis = 0;
        int foundandfive = 0;
        
        
        while(true) {
        	
        	if( zahl % 10000000 == 0 ) {
        		System.out.println( zahl + " in " + ((System.currentTimeMillis() - starttime)));
        	}
        	
        	//checks ob wir die zahl ueberhaupt checken m�ssen
    		zahlstring = Long.toString(zahl);
    		lastdigit = "1";
    		skipthis = 0;
    		foundandfive = 0;
    		for( int i = 0; i <= zahlstring.length()-1; i++ ) {
    			
    			comparedigit = zahlstring.substring(i, i+1);
    			//System.out.println( zahl + " " + comparedigit);
    			
    			//wenn in Zahl 0 ist, wird quer 0. nicht checken!
     			if( comparedigit == "0" ) {
    				//System.out.println( zahl + " 0");
    				skipthis = 1;
    				break;
    			}
    			
    			//wenn in zahl 1 ist, wurden steps schon errechnet
    			//Bsp 991 hat gleiches quer wie 99
    			if( comparedigit == "1" ) {
    				//System.out.println( zahl + " 1");
    				skipthis = 1;
    				break;
    			}
    			
    			// 123 gleiches quer wie 321. nur erstes checken.
    			if( Integer.parseInt( comparedigit ) < Integer.parseInt( lastdigit ) ) {
    				//System.out.println( comparedigit + " < " + lastdigit);
    				skipthis = 1;
    				break;
    			}
    			
    			//wenn 2 und 5 als als digits existieren ist das produkt ein multiles von 10
    			//nächster schritt ergibt also immer 0
    			if( comparedigit == "2" || comparedigit == "5" ) {
    				
    				foundandfive++;
    				if( foundandfive == 2 && zahl != 25 ) {
	    				//System.out.println( zahl + " hat 2 und 5");
	    				skipthis = 1;
	    				break;
    				}
    			}
    			
    			lastdigit = comparedigit;
    		}
    		
    		//quer und schritte berechnen
        	steps = 0;        	
        	quer = zahl;
        	while( quer > 9 && skipthis == 0 ) {
        		
        		tempzahl = quer;
        		quer = 1;
	        	while( tempzahl != 0 ) {
	        		quer *= tempzahl % 10;
	        		tempzahl /= 10;
	        		
	        		//System.out.println( "Schritte: " + zahl );
	        	}
	        	steps++;
        	}
        	
        	if( steps > laststepcount ) {
        		System.out.println( "Schritte: " + zahl      );
        		laststepcount++;
        		//System.out.println( laststepcount );
        	}
        	
        	if( steps == minsteps ) {
        		break;
        	}
        	        	
        	zahl++;
        }
        System.out.println( ( System.currentTimeMillis() - starttime) );
        System.exit(0);
	}
}
