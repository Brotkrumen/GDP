import java.util.ArrayList;

/**
 * Springer tries to place the maximum number of knights on a chess board
 * 
 * solutions above n=4 are trivial:
 * 
 * S*S*S 
 * *S*S*
 * S*S*S
 * *S*S*
 * S*S*S
 * 
 * or
 * 
 * *S*S*
 * S*S*S
 * *S*S*
 * S*S*S* 
 * *S*S*
 *  
 * n = 3 and 4 have more solutions. n=4:
 *   
 * 	SSSS
 *	****
 *	****
 *	SSSS
 *	----
 *	SS*S
 *	S***
 *	***S
 *	S*SS
 *	----
 *	S*SS
 *	***S
 *	S***
 *	SS*S
 *	----
 *	S*S*
 *	*S*S
 *	S*S*
 *	*S*S
 *	----
 *	S**S
 *	S**S
 *	S**S
 *	S**S
 *	----
 *	*S*S
 *	S*S*
 *	*S*S
 *	S*S*
 * 
 * One could enforce the diagonal pattern for n>4 and call it
 * search tree pruning or backtracking.
 * 
 * Algorithm:
 * 
 * 1. start with empty board
 * 2. placeknight called
 *		isPopulated checks if could be max solution
 *		knightcollision checks for collisions
 *			if none are found
 *				knight gets placed
 *				placeknight called
 *				after return, knight gets removed as this branched as been checked
 * 3. setmaxboard at end of branch to check if is max solution
 * 4. results get printed
 * 
 */
public class Springer {
	
	/*******************************************************
	 * chessboard class
	 ******************************************************/
	static class Chessboard{
		
		//class variables
		/** List with all current maximum solutions */
		static ArrayList< Boolean[][] > MAXLIST = new ArrayList< Boolean[][] >();
		
		/** The count of placed knights of boards in maxlist. */
		static int MAXCOUNT = 0;
		
		//instance variables
		/** the current board */
		Boolean[][] board;
		int currentlyplaced = 0;
		
		//constructors
		Chessboard() {
		}
		
		Chessboard( int size ) {
			board = new Boolean[size][size];
			resetboard();
		}
		
		
		//getters
		/*******************************************************
		 * draws the board to stdout
		 *
		 * @param board to draw
		 * @param time to sleep after draw in ms
		 ******************************************************/
		public void draw( Boolean[][] board, int sleeptime, boolean separator ) {
			String outstr = "";
			
			for( int i = 0; i <= board.length-1; i++) {
				for( int j=0; j<=board[0].length-1;j++) {
					if( board[i][j] == true ) outstr += "S ";
					else outstr += "* ";
				}
				outstr += "\n";
			}
			
			if( separator ) {
				for( int i=0; i<board.length;i++) {
					outstr += "-";
				}
			}
			System.out.println( outstr );
			sleep( sleeptime );
		}
		
		/*******************************************************
		 * Draw all in maxlist
		 *
		 * @param time to sleep in ms
		 ******************************************************/
		public void drawAll( int sleeptime ) {
			
			for( int i=0; i<MAXLIST.size(); i++ ) {
				draw( MAXLIST.get(i), 0, true );
			}
		}
		
		/*******************************************************
		 * Checks if knights are placed further up on the board.
		 * If not, we are in a boardstate that cannot be a max solution
		 * Without assumptions about solution patterns, these fields are
		 * checked and at least 1 knight every 5 spaces are expected to be found:
		 * 
		 * 		*****
		 * 		*****
		 * 		  X
		 * 
		 * This should exclude search space branches where no knights
		 * have been placed, as will happen with this recursive alg
		 *  
		 *
		 * @param x		x coodinate of knight to be placed
		 * @param y		y coodinate of knight to be placed
		 * @return true if populated
		 ******************************************************/
		public boolean isPopulated( int x, int y  ) {
			
			//exclude case where x is first or second row
			if( x - 1 <= 0 )	
				return true;
			
			//exclude case where y is on the sides 
			if( y - 1 <= 0 || y + 1 >= board.length )
				return true;
				
			
			int match = 0;
			for( int i = 0; i<x-1; i++ ){
				for( int j = 0; j < board.length; j++ ) {
					if( board[i][j] ) match++;
				}
			}
			
			//expects a knight for every 4 fields
			if( match > (x*y)/5 )
				return true;
			else
				return false;
		}
		
		/*******************************************************
		 * Checks if the knight collides with another.
		 *
		 * @param x		x coodinate of knight to be placed
		 * @param y		y coodinate of knight to be placed
		 * @return true if collision
		 ******************************************************/
		public boolean knightcollision( int x, int y  ) {
			
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
			
			try {
				if ( board[x-2][y-1] ) return true;
			} catch( Throwable p ) {

			}
			
			try {
				if ( board[x-2][y+1] ) return true;
			} catch( Throwable p ) {

			}
			
			try {
				if ( board[x-1][y-2] ) return true;
			} catch( Throwable p ) {

			}
			
			try {
				if ( board[x-1][y+2] ) return true;
			} catch( Throwable p ) {

			}

			try {
				if ( board[x+1][y-2] ) return true;
			} catch( Throwable p ) {

			}

			try {
				if ( board[x+1][y+2] ) return true;
			} catch( Throwable p ) {

			}
			
			try {
				if ( board[x+2][y-1] ) return true;
			} catch( Throwable p ) {

			}
			
			try {
				if ( board[x+2][y+1] ) return true;
			} catch( Throwable p ) {

			}
			
			return false;
		}
		
		
		//MUTATORS
		
		/*******************************************************
		 * Checks if current board should be 
		 * added to maxlist.
		 ******************************************************/
		private void setMaxboard() {
			
			int placed = 0;
			//cause theres no std deep copy function?!?
			Boolean[][] candidateboard = new Boolean[ board.length][board.length];
			
			for( int i = 0; i <= board.length-1; i++) {
				for( int j=0; j<=board[0].length-1;j++) {
					candidateboard[i][j] = board[i][j];
					if( board[i][j] ) placed++;
				}
			}

			if( placed > MAXCOUNT ) {
				MAXLIST.clear();
			}
			
			if( placed >= MAXCOUNT ) {
				MAXLIST.add( candidateboard );
				MAXCOUNT = placed;
				//drawAll( 0 );
			}
		}
		
		/**
		 * Place placeKnight.
		 *
		 * @param startx the startx
		 * @param starty the starty
		 */
		public void placeKnight( int startx, int starty ) {
			
			int len = board.length;
			
			if( !isPopulated( startx, starty ) )
				return;
			
			for( int x = startx; x < len; x++ ) {
				for( int y = starty; y < len; y++ ) {
					
					//first time only from should y be initialized with starty
					//x+1 should have y=0
					if( starty != 0 ) starty = 0;
					
					
					//LOOK HERE! SEARCH TREE PRUNING AND BACKTRACKING
					//if the maximally placable knights is lower than the current
					//maxsolutions, dont go deeper. timesavings estimated at >=0;
					if( currentlyplaced + ( len*len - x - y ) < MAXCOUNT )
						return;
					
					if( !board[x][y] && !knightcollision( x, y ) ) {
						board[x][y] = true;
						currentlyplaced++;
						
						//System.out.println( currentlyplaced );
						//draw( board, 1000 );
						
						//LOOK! HERE! RECURSION!
						placeKnight( x, y );
						currentlyplaced--;
						board[x][y] = false;
					}
				}
			}
			setMaxboard();
		}
		
		
		/*******************************************************
		 * purge duplicates from maxlist with same n.
		 ******************************************************/
		public void purgeduplicates() {
			
			ArrayList< Boolean[][] > oldList = MAXLIST;
			ArrayList< Boolean[][] > newList = new ArrayList< Boolean[][] >();
			
			Boolean match = false;
			for( int i=0; i<oldList.size(); i++ ) {
				
				match = false;
				
				if( newList.size() == 0 ) {
					newList.add( oldList.get(i) );
					continue;
				}
				
				for( int j=0; j<newList.size(); j++ ) {
					if( isEqual( oldList.get(i), newList.get(j) ) ) {
						match = true;
						break;
					}
				}
				
				if( !match ) {
					newList.add( oldList.get(i) );
				}
			}
			
			MAXLIST = newList;
		}
		
		/*******************************************************
		 * Checks if lhs board is equal to rhs
		 *
		 * @param lhs the lhs
		 * @param rhs the rhs
		 * @return the boolean
		 ******************************************************/
		private Boolean isEqual( Boolean[][] lhs, Boolean[][] rhs ) {
			
			//draw( lhs, 0 );
			//draw( rhs, 2000 );
			
			for( int i=0; i<lhs.length; i++ ) { 
				for( int j=0; j<lhs.length; j++ ) { 
					if( lhs[i][j] != rhs[i][j] ) return false;
				}
			}
			
			//System.out.println( "is equal " );
			
			return true;		
		}
		
		/*******************************************************
		 * Reset board.
		 ******************************************************/
		public void resetboard() {
			
			for( int i = 0; i <= board.length-1; i++) {
				for( int j=0; j<=board[0].length-1;j++) {
					board[i][j] = false;
				}
			}
		}
	}
	
	/*****************************************************
	 * END CHESSBOARD CLASS
	 *****************************************************/
	
	
	
	
	
	
	
	
	/*******************************************************
	 * validates input.
	 *
	 * @param input argument string[]
	 * @return chess board height and width
	 ******************************************************/
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
			System.out.println( "bidde mehr als 0 felder wollen" );
			System.exit(-1);
		}
		
		return input;
	}
	
	/*******************************************************
	 * wrapper for sleep
	 *
	 * @param int in ms
	 ******************************************************/
	private static void sleep( int n ) {
		try {
			Thread.sleep( n );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*******************************************************
	 * The main method.
	 *
	 * @param args the arguments
	 ******************************************************/
	public static void main(String[] args) {
		
		int len = parseInput( args );
		
		Chessboard board = new Chessboard(len);
		
		board.placeKnight(0, 0);
		board.purgeduplicates();
		
		//board.drawAll(0);
		
		board.draw( Chessboard.MAXLIST.get(0), 0, false );
		
	}
}

/* 6 lösung
 * 	S*S*S*
	*S*S*S
	S*S*S*
	*S*S*S
	S*S*S*
	*S*S*S
	---------
	*S*S*S
	S*S*S*
	*S*S*S
	S*S*S*
	*S*S*S
	S*S*S*
	---------
*/
