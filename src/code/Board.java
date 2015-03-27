package code;

import java.util.ArrayList;
import java.util.List;

/**
 * An instance represents a grid of pieces from two opposing
 * players in a game of Connect Four. The grid is 0-indexed first by rows
 * starting at the top, then by columns 0-indexed starting at the left.
 */
public class Board {
    /**
     * The number of rows on the Connect Four board.
     */
    public static final int NUM_ROWS = 6;
    /**
     * The number of columns on the Connect Four board.
     */
    public static final int NUM_COLS = 7;

    private static final int FOUR = 4; // four in a line

    /**
     * vertical, horizontal, uphill, downhill, directions from any position
     */
    private static final int[][] deltas = {{1, 0}, {0, 1}, {-1, 1}, {1, 1}};

    /**
     * The grid of Player pieces.
     */
    private Player[][] board;
    
    /**
     * A set of moves
     */
    private Move[] moveSet;
    
    private final int moveSetLength = NUM_ROWS*NUM_COLS;
    
    private int numberOfMoves=-1;

    /**
     * Constructor: an empty Board.
     */
    public Board() {
        board = new Player[NUM_ROWS][NUM_COLS];
        
        ///number is arbitrary...
        this.moveSet = new Move[moveSetLength];
    }

    /**
     * Constructor: a duplicate of Board b.
     */
    public Board(Board b) {
        board = new Player[NUM_ROWS][NUM_COLS];
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                board[r][c] = b.board[r][c];
            }
        }
        
        ///number is arbitrary...
        this.moveSet = new Move[moveSetLength];
    }

    /**
     * Return the element in row r col c.
     * Precondition: r and c give a position on the board
     */
    public Player getPlayer(int r, int c) {
        assert 0 <= r && r < NUM_ROWS && 0 <= c && c < NUM_COLS;
        return board[r][c];
    }

    /**
     * Constructor: a Board constructed by duplicating b and
     * applying nextMove to the new Board.
     */
    public Board(Board b, Move nextMove) {
        this(b);        
        makeMove(nextMove);      
        
    }

    /**
     * Return the Player at board position (row, col). Rows are
     * 0-indexed starting at the top and columns are 0-indexed starting
     * at the left. A null return value indicates an empty tile.
     */
    public Player getTile(int row, int col) {
        return board[row][col];
    }

    /**
     * Apply Move move to this Board by placing a piece from move's
     * player into move's column on this Board.
     * Throw an IllegalArgumentException if move's column is full on this Board.
     */
    public void makeMove(Move move) {       
    	
    	///gets the player's position; uses the parameter Move to obtain value
    	int playerColumnPosition = move.getColumn();
    	
    	///used to determine the position of the player input
    	int rowDeterminator = 1;
    	    	
    	////loop will continue incrementing the rowDeterminator if the current array 
    	///position is occupied
    	rowDeterminator = rowDeterminator(playerColumnPosition, rowDeterminator);
    	
    	///set the board so it contains the current position of the player
    	board[NUM_ROWS-rowDeterminator][playerColumnPosition] = move.getPlayer();
    	
    	//rowDeterminator=1;    	
    	//System.out.println(getTile(NUM_ROWS-row,playerCol));    	
    	//System.out.println((NUM_ROWS-row));
    	
    	this.numberOfMoves++;
    	this.moveSet[numberOfMoves] = move;   	
    	
    }

    /**
     * Calculates the current row position
     */
	private int rowDeterminator(int playerColumnPosition, int rowDeterminator) {
		while (boardCellCheck(playerColumnPosition, rowDeterminator) == true)    	 
    	{
			if(rowDeterminator>=NUM_ROWS){break;}
			else{rowDeterminator++;}
    	}
		return rowDeterminator;
	}

    /**
     * Checks to see whether a cell on the board is occupied
     */
	boolean boardCellCheck(int playerColumnPosition, int rowDeterminator) {
		return board[NUM_ROWS-rowDeterminator][playerColumnPosition] != null;
	}    
	
	/**
     * Checks whether the column is full
     */
	public boolean isColumnFull(int playerColumnPosition) {
		
		int rowDeterminator=1;
		
		rowDeterminator = rowDeterminator(playerColumnPosition, rowDeterminator);		
		
		if(board[NUM_ROWS - rowDeterminator][playerColumnPosition] != null)
		{
			return true;
		}
		else
		{
			return false; 
		}
		
	}

	/**
     * Return an array of all moves that can possibly be made by Player p on this
     * board. The moves must be in order of increasing column number.
     * Note: The length of the array must be the number of possible moves.
     * Note: If the board has a winner (four things of the same colour in a row), no
     * move is possible because the game is over.
     * Note: If the game is not over, the number of possible moves is the number
     * of columns that are not full. Thus, if all columns are full, return an
     * array of length 0.
     */
    public Move[] getPossibleMoves(Player p) {
    	
    	///test to see if the game is over
    	///if so, then we won't bother running the below
    	if(this.hasConnectFour() != null)
    	{
    		System.out.println("Player has achieved four in a row.\nThe game is over and no more moves are possible. Go away - no more fun.");
    		
    		///just return an array with zero length
    		return new Move[0];
    	}
          
    	///an array to hold all column possibilities
    	Move[] temp = new Move[Board.NUM_COLS];
    	int count = 0, possibleMovesIndex = 0;
    	
    	///we test to see if the column is full
    	///if it's not then we add it to our array
    	for(int i = 0; i < (NUM_COLS); i++)
    	{
    		if(this.isColumnFull(i)==false)
    		{
    			temp[i] = new Move(p,i);
    			count++;
    		}
    	}
    	
    	///create new array so we contain only elements which are not null
    	Move[] possibleMoves = new Move[count];
    	
    	///now we add all valid column entries
    	for(int i = 0; i <NUM_COLS; i++)
    	{
    		if(temp[i]== null)
    		{
    			///do nothing    			
    		}
    		else
    		{
    			possibleMoves[possibleMovesIndex] = temp[i];
    			possibleMovesIndex++;
    		}
    	}  
    	
        return possibleMoves;
    }

    /**
     * Return a representation of this board
     */
    @Override
    public String toString() {
        return toString("");
    }

    /**
     * Return the String representation of this Board with prefix
     * prepended to each line. Typically, prefix contains space characters.
     */
    public String toString(String prefix) {
        StringBuilder str = new StringBuilder("");
        for (Player[] row : board) {
            str.append(prefix + "|");
            for (Player spot : row) {
                if (spot == null) {
                    str.append(" |");
                } else if (spot == Player.RED) {
                    str.append("R|");
                } else {
                    str.append("Y|");
                }
            }
            str.append("\n");
        }
        return str.toString();
    }

    /**
     * Return the Player that has four in a row (or null if no player has).
     */
    public Player hasConnectFour() {
        for (Player[] loc : winLocations()) {
            if (loc[0] != null && loc[0] == loc[1] && loc[0] == loc[2] && loc[0] == loc[3]) {
                return loc[0];
            }
        }
        return null;
    }

    /**
     * Return a list of all locations where it is possible to
     * achieve connect four. In this context, a "win location" is an
     * array of the Player pieces on this Board from four connected tiles.
     */
    public List<Player[]> winLocations() {
        List<Player[]> locations = new ArrayList<>();
        for (int[] delta : deltas) {
            for (int r = 0; r < NUM_ROWS; r++) {
                for (int c = 0; c < NUM_COLS; c++) {
                    Player[] loc = possibleWin(r, c, delta);
                    if (loc != null) {
                        locations.add(loc);
                    }
                }
            }
        }
        return locations;
    }

    /**
     * If the four locations in a row beginning in board[r][c] going in the direction
     * given by [delta[0]][delta[1]] are on the board, return an array of them.
     * Otherwise, return null;
     * <p/>
     * Precondition: board[r][c] is on the board and delta is one of the elements of
     * static variable deltas.
     */
    public Player[] possibleWin(int r, int c, int[] delta) {
        Player[] location = new Player[FOUR];
        for (int i = 0; i < FOUR; i++) {
            int newR = r + i * delta[0];
            int newC = c + i * delta[1];
            if (!(0 <= newR && newR < NUM_ROWS && 0 <= newC && newC < NUM_COLS)) {
                return null;
            }
            location[i] = board[newR][newC];
        }
        return location;
    }
}
