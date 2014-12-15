package arc;
import java.util.Arrays;

/**
 * @author orlowsks
 * Algorithm by mark diamond
 * http://www.markdiamond.com.au/download/joous-3-1-1.pdf
 * download: 11.11.2014
 */

class CalcThread extends Thread {
	
	long[] loweststepNrs = new long[11];
	
    //primzahl iteratoren
    int maxnumlen = 29; //lowest number necessary to get 11 step quer
    int primeItStart;
    int it7 = 0;
    int it5 = 0;
    int it3 = 0;
    int it2 = 0;
    int lastit2 = 0;
    int lastit3 = 0;
    int lastit5 = 0;
    int lastit7 = 0;
    String zahlstring = "";
    
    //compacting variables
    String newzahlstr = "";
    int count2s = 0;
	int count3s = 0;
	String thisstr = "";
	String newstr = "";
	int dontskip = 0;
    
    //quer berechnung vars
    
    int[] intArray = new int[ maxnumlen ];
    long quer;
    int steps = 0;
    long tempzahl = 0;
   	long zahl;
	
	CalcThread( int primeItStart, int calcRange ) {
        loweststepNrs[0] = Long.MAX_VALUE;	//2 steps
        loweststepNrs[1] = Long.MAX_VALUE;	//3 steps
        loweststepNrs[2] = Long.MAX_VALUE;	//4 steps
        loweststepNrs[3] = Long.MAX_VALUE;	//5 steps
        loweststepNrs[4] = Long.MAX_VALUE;	//6 steps
        loweststepNrs[5] = Long.MAX_VALUE;	//7 steps
        loweststepNrs[6] = Long.MAX_VALUE;	//8 steps
        loweststepNrs[7] = Long.MAX_VALUE;	//9 steps
        loweststepNrs[8] = Long.MAX_VALUE;	//10 steps
        loweststepNrs[9] = Long.MAX_VALUE;	//11 steps
        
        this.primeItStart = primeItStart;
        it2 = primeItStart;
        it3 = primeItStart;
        it5 = primeItStart;
        it7 = primeItStart;
        lastit2 = primeItStart;
		lastit3 = primeItStart;
		lastit5 = primeItStart;
		lastit7 = primeItStart;
        
        maxnumlen = primeItStart + calcRange;
    }
    
    public long[] getResultArray() {
    	
    	return loweststepNrs;
    }

    public void run() {
        
    	/**********    Primzahl komposition iterator *************/
       	/* 
       	 * letztendlich eine 4 fach geschachtelte for-schleife, 
       	 * nur breitgemehrt wegen übersichtlichkeit.
       	 * 
       	 * die anzahl der primzahlen wird sich gemerkt um nicht in
       	 * jeder iteration die gleiche zahl neu zusammenzukleben
       	 *
       	 **********************************************************/
        while(true) {
        	it2++;
        	
        	if( it2 + it3 + it5 + it7 > maxnumlen
        			&& it2 >= it3 ) {
        		it2 = primeItStart;
        		it3++;
        		
        		lastit2 = primeItStart;
        		lastit3 = primeItStart;
        		lastit5 = primeItStart;
        		lastit7 = primeItStart;
            	zahlstring = "";
        	}
        	
        	if( it2 + it3 + it5 + it7 > maxnumlen
        			&& it3 >= it5 ) {
        		it2 = primeItStart;
        		it3 = primeItStart;
        		it5++;
        		
        		lastit2 = primeItStart;
        		lastit3 = primeItStart;
        		lastit5 = primeItStart;
        		lastit7 = primeItStart;
            	zahlstring = "";
        	}
        	
        	if( it2 + it3 + it5 + it7 > maxnumlen
        			&& it5 >= it7 ) {
        		it2 = primeItStart;
        		it3 = primeItStart;
        		it5 = primeItStart;
        		it7++;
        		
        		lastit2 = primeItStart;
        		lastit3 = primeItStart;
        		lastit5 = primeItStart;
        		lastit7 = primeItStart;
            	zahlstring = "";
        	}
        	
        	//Primzahlen werden mit errechneten haeufigleit
        	//zusammengeklebt
        	
        	/*
        	
        	dontskip = 0;
        	if( it3 == 0 && it7 == 0 && it2 == 1 && it5 == 1 ) {
        		dontskip = 1;
        	}
        	
        	if( dontskip == 0 && it2 != 0 && it5 != 0 ) {
        		it2 = maxnumlen + 1;
        		continue;
        	}
        	
        	*/
        	
        	if( it7 > maxnumlen ) {
        		break;
        	}
        	
        	for( ; lastit2 < it2; lastit2++ ) {
        		zahlstring += "2";
        	}
        	
        	
        	for( ; lastit3 < it3; lastit3++ ) {
        		zahlstring += "3";
        	}
        	
        	for( ; lastit5 < it5; lastit5++ ) {
        		zahlstring += "5";
        	}
        	
        	for( ; lastit7 < it7; lastit7++ ) {
        		zahlstring += "7";
        	}
        	
        	
        	/************* Compacting *****************
        	 * die kompacteste zahl mit den gleichen 
        	 * primzahlen ist hat den niedrigeren wert
        	 * bsp: 26 < 223
        	 * 
        	 * priority: 
        	 * 1. 2 2 2 -> 8
        	 * 2. 3 3 -> 9
        	 * 3. 2 3 -> 6
        	 * 4. 2 2 -> 4
        	 ****************************************/
        	newzahlstr = zahlstring;
        	
        	count2s = 0;
        	count3s = 0;
        	thisstr = "";
        	newstr = "";
        	for( int i = 0; i <= newzahlstr.length() - 1; i++ ) {
        		thisstr = 	newzahlstr.substring(i, i+1);
        		if( thisstr.equals( "2" ) ) {
        			count2s++;
        		} else if( thisstr.equals( "3" ) ) {
        			count3s++;
        		} else {
        			newstr += thisstr;
        		}
        		
        		if( count2s == 3 ) {
        			newstr += "8";
        			count2s = 0;
        		}
        		
        		if( count3s == 2 ) {
        			newstr += "9";
        			count3s = 0;
        		}
        	}
        	
        	for( int i = 1; i <= count2s && i <= count3s; i++ ) {
        		newstr += "6";
        		count2s--;
        		count3s--;
        	}
        	
        	for( int i = 1; i <= count2s / 2; i++ ) {
        		newstr += "4";
        		count2s -= 2;
        	}
        	
        	for( int i = 1; i <= count2s; i++ ) {
        		newstr += "2";
        	}
        	
        	for( int i = 1; i <= count3s; i++ ) {
        		newstr += "3";
        	}
        	
        	
        	/********** Sortieren ***************
        	 * 23 < 32
        	 * thats all
        	 ************************************/
        	       	
        	
        	newzahlstr = newstr;
        	
        	intArray = Arrays.copyOf(intArray, newzahlstr.length() );
        	
        	for( int i = 0; i <= newzahlstr.length() -1; i++ ) {
        		intArray[i] = Integer.parseInt( newzahlstr.substring( i, i+1) );
        	}
        	
        	Arrays.sort( intArray );
       	
        	newzahlstr = "";
        	for( int i = 0; i <= intArray.length - 1; i++ ) {
        		
        		if( intArray[i] != 0 )
        			newzahlstr += Integer.toString( intArray[i] );
        	}
        	
        	try {
        		zahl = Long.parseLong( newzahlstr );
        	} catch ( Throwable ablageP) {
        		continue;
        	}

        	
        	/********** Querprodukt ****************
        	 * und steps um auf länge 1 zu kommen.
        	 * persistence oder beharrlichkeit
        	 * multiplicative digital root oder multiplikative zifferwurzel
        	 *************************************************/
        	steps = 0;        	
        	quer = zahl;
        	while( quer > 9 ) {
        		
        		tempzahl = quer;
        		quer = 1;
	        	while( tempzahl != 0 ) {
	        		quer *= tempzahl % 10;
	        		tempzahl /= 10;
	        	}
	        	steps++;
        	}
      
        	try {
        		zahl = Long.parseLong(newzahlstr);
        	} catch( Throwable ablageP ) {
        		continue;
        	}
        	
        	if( steps > 1 ) {
	        	if( loweststepNrs[ steps - 2 ] > zahl ) {
	        		loweststepNrs[ steps - 2 ] = zahl;
	        	}
        	}
        }
    	
    }
}

public class Quer4 {
	
	public static void main(String[] args) {
		int minsteps = Integer.parseInt(args[0]);
		
		//negative Zahlen sind verboten!
        if( minsteps <= 0 ) {
        	System.out.println( "nur positive ganze Zahlen als Argumente erlaubt. Idealerweise unter 11" );
        	System.exit(-1);
        }
        
        long starttime = System.currentTimeMillis();
        
        CalcThread[] threadArray = new CalcThread[3];
        
        threadArray[0] = new CalcThread( 0, 10);
        threadArray[1] = new CalcThread( 9, 10);
        threadArray[2] = new CalcThread( 19, 11);
        
        for( int i = 0; i < threadArray.length - 1; i++ ) {
        	threadArray[i].start();
        }
        
        long[] loweststepNrs = new long[11];
        loweststepNrs[0] = Long.MAX_VALUE;	//2 steps
        loweststepNrs[1] = Long.MAX_VALUE;	//3 steps
        loweststepNrs[2] = Long.MAX_VALUE;	//4 steps
        loweststepNrs[3] = Long.MAX_VALUE;	//5 steps
        loweststepNrs[4] = Long.MAX_VALUE;	//6 steps
        loweststepNrs[5] = Long.MAX_VALUE;	//7 steps
        loweststepNrs[6] = Long.MAX_VALUE;	//8 steps
        loweststepNrs[7] = Long.MAX_VALUE;	//9 steps
        loweststepNrs[8] = Long.MAX_VALUE;	//10 steps
        loweststepNrs[9] = Long.MAX_VALUE;	//11 steps
        
        for( int i = 0; i <= threadArray.length - 1; i++ ) {
        	System.out.println( "bef join " +  i );
        	try {
				threadArray[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        	long[] thisResult = threadArray[i].getResultArray();
        	for( int k = 0; k <= thisResult.length - 1; k++ ) {
        		try {
        			System.out.println( i + " Schritte: " + thisResult[ k ] );
        		} catch( Throwable ablageP ) {
        			System.out.println( "pop pop!");
        			continue;
        		}
        	}
       	
        	for( int j = 0; j <= thisResult.length - 1; j++ ) {
        		if( thisResult[j] < loweststepNrs[j] ) {
        			loweststepNrs[j] = thisResult[j];
        		}
        	}

        }
        
        /************ Ausgabe ergebnis *******************
         ************************************************/
        
        for( int i = 0; i < loweststepNrs.length - 1 && i < minsteps - 1; i++ ) {
        	System.out.println( "Schritte: " + loweststepNrs[ i ] );
    	}
        
        
        System.out.println( ( System.currentTimeMillis() - starttime) );
        System.exit(0);
	}
}
