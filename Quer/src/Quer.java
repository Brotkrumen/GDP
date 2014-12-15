import java.util.Arrays;

/**
 * @author orlowsks
 * Algorithm by mark diamond
 * http://www.markdiamond.com.au/download/joous-3-1-1.pdf
 * download: 11.11.2014
 */
class test implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
	
}



public class Quer {
	
	public static void main(String[] args) {
		int minsteps = Integer.parseInt(args[0]);
		
		//negative Zahlen sind verboten!
        if( minsteps <= 0 ) {
        	System.out.println( "nur positive ganze Zahlen als Argumente erlaubt. Idealerweise unter 11" );
        	System.exit(-1);
        }
        
        int cores = Runtime.getRuntime().availableProcessors();
        
        long starttime = System.currentTimeMillis();
        
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
        
        //primzahl arrays bauen
		String prime2component[] = new String[] {	"",
									        		"2",
									        		"22",
									        		"8",
									        		"82",
									        		"822",
									        		"88",
									        		"882",
									        		"8822",
									        		"888",
									        		"8882",
									        		"88822",
									        		"8888",
									        		"88882",
									        		"888822",
									        		"88888",
									        		"888882",
									        		"8888822",
									        		"888888",
									        		"8888882",
									        		"88888822",
									        		"8888888",
									        		"88888882",
									        		"888888822",
									        		"88888888",
									        		"888888882",
									        		"8888888822",
									        		"888888888",
									        		"8888888882",
									        		"88888888822" };

		String prime3component[] = new String[] {	"",
									        		"3",
									        		"9",
									        		"93",
									        		"99",
									        		"993",
									        		"999",
									        		"9993",
									        		"9999",
									        		"99993",
									        		"99999",
									        		"999993",
									        		"999999",
									        		"9999993",
									        		"9999999",
									        		"99999993",
									        		"99999999",
									        		"999999993",
									        		"999999999",
									        		"9999999993",
									        		"9999999999",
									        		"99999999993",
									        		"99999999999",
									        		"999999999993",
									        		"999999999999",
									        		"9999999999993",
									        		"9999999999999",
									        		"99999999999993",
									        		"99999999999999",
									        		"999999999999993" };

		String prime5component[] = new String[] {	"",
									        		"5",
									        		"55",
									        		"555",
									        		"5555",
									        		"55555",
									        		"555555",
									        		"5555555",
									        		"55555555",
									        		"555555555",
									        		"5555555555",
									        		"55555555555",
									        		"555555555555",
									        		"5555555555555",
									        		"55555555555555",
									        		"555555555555555",
									        		"5555555555555555",
									        		"55555555555555555",
									        		"555555555555555555",
									        		"5555555555555555555",
									        		"55555555555555555555",
									        		"555555555555555555555",
									        		"5555555555555555555555",
									        		"55555555555555555555555",
									        		"555555555555555555555555",
									        		"5555555555555555555555555",
									        		"55555555555555555555555555",
									        		"555555555555555555555555555",
									        		"5555555555555555555555555555",
									        		"55555555555555555555555555555" };
		
		String prime7component[] = new String[] {	"",
									        		"7",
									        		"77",
									        		"777",
									        		"7777",
									        		"77777",
									        		"777777",
									        		"7777777",
									        		"77777777",
									        		"777777777",
									        		"7777777777",
									        		"77777777777",
									        		"777777777777",
									        		"7777777777777",
									        		"77777777777777",
									        		"777777777777777",
									        		"7777777777777777",
									        		"77777777777777777",
									        		"777777777777777777",
									        		"7777777777777777777",
									        		"77777777777777777777",
									        		"777777777777777777777",
									        		"7777777777777777777777",
									        		"77777777777777777777777",
									        		"777777777777777777777777",
									        		"7777777777777777777777777",
									        		"77777777777777777777777777",
									        		"777777777777777777777777777",
									        		"7777777777777777777777777777",
									        		"77777777777777777777777777777" };
        
        
        //primzahl iteratoren
        int maxnumlen = 29; //lowest number necessary to get 11 step quer
        int it7 = 0;
        int it5 = 0;
        int it3 = 0;
        int it2 = 0;
        String zahlstring = "";
        
        //compacting variables
        String newzahlstr = "";
        int count2s = 0;
    	int count3s = 0;
    	String thisstr = "";
    	String newstr = "";
    	int dontskip = 0;
        
        //quer berechnung vars
        
        int[] intArray = new int[40];
        long quer;
        int steps = 0;
        long tempzahl = 0;
       	long zahl;
        
       	/**********    Primzahl komposition iterator *************/
       	/* 
       	 * letztendlich eine 4 fach geschachtelte for-schleife, 
       	 * nur breitgemehrt wegen ueberrsichtlichkeit.
       	 * 
       	 * die anzahl der primzahlen wird sich gemerkt um nicht in
       	 * jeder iteration die gleiche zahl neu zusammenzukleben
       	 *
       	 ***********************************************************/
       	
       	
       	
        while(true) {
        	it2++;
        	
        	if( it2 + it3 + it5 + it7 > maxnumlen
        			&& it2 >= it3 ) {
        		it2 = 0;
        		it3++;

            	zahlstring = "";
        	}
        	
        	if( it2 + it3 + it5 + it7 > maxnumlen
        			&& it3 >= it5 ) {
        		it2 = 0;
        		it3 = 0;
        		it5++;

            	zahlstring = "";
        	}
        	
        	if( it2 + it3 + it5 + it7 > maxnumlen
        			&& it5 >= it7 ) {

        		it2 = 0;
        		it3 = 0;
        		it5 = 0;
        		it7++;
        		
            	zahlstring = "";
        	}
        	
        	//Primzahlen werden mit errechneten laenge
        	//zusammengeklebt
        	
        	dontskip = 0;
        	if( it3 == 0 && it7 == 0 && it2 == 1 && it5 == 1 ) {
        		dontskip = 1;
        	}
        	
        	//wenn 2 und 5 zusammen vorkommen, kann alles mit 2 weggeworfen werden
        	if( dontskip == 0 && it2 != 0 && it5 != 0 ) {
        		it2 = maxnumlen + 1;
        		continue;
        	}
     	
        	if( it7 > maxnumlen ) {
        		break;
        	}
        	
        	zahlstring 	= 	prime2component[it2] 
        				+ 	prime3component[it3] 
        				+ 	prime5component[it5] 
        				+ 	prime7component[it7] ;
	
        	
        	/************* Compacting *****************
        	 * die kompacteste zahl mit den gleichen 
        	 * primzahlen ist hat den niedrigeren wert
        	 * bsp: 26 < 223
        	 * 
        	 * priority: 
        	 * 1. 2 2 2 -> 8 (wird schon im primmzahlcomp array gemacht)
        	 * 2. 3 3 -> 9 (das auch)
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
        	 * und steps um auf laenge 1 zu kommen.
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
        
        /************ Ausgabe ergebnis *******************
         ************************************************/
        
        for( int i = 0; i < loweststepNrs.length - 1 && i < minsteps - 1; i++ ) {
        	System.out.println( "Schritte: " + loweststepNrs[ i ] );
    	}
        
        System.out.println( ( System.currentTimeMillis() - starttime) );
        System.exit(0);
	}
	
}