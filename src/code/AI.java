package code;

import java.util.List;
import java.util.Random;

/**
 * An instance represents a Solver that intelligently determines
 * Moves using the Minimax algorithm.
 */
public class AI implements Solver {

    private Player player; // the current player

    /**
     * The depth of the search in the game space when evaluating moves.
     */
    private int depth;

    /**
     * Constructor: an instance with player p who searches to depth d
     * when searching the game space for moves.
     */
    public AI(Player p, int d) {
        player = p;
        depth = d;
    }

    /**
     * See Solver.getMoves for the specification.
     */
    @Override
    public Move[] getMoves(Board b) {
        // TODO
        return null;
        
        //this is the detail in the dummy application
        //Random rand = new Random();
        //int column = rand.nextInt(Board.NUM_COLS);
        //while (b.getTile(0, column) != null) {
        //    column = rand.nextInt(Board.NUM_COLS);
        //}
        //Move[] move = {new Move(player, column)};
        //return move;
    }

    /**
     * Generate the game tree with root s of depth d.
     * The game tree's nodes are State objects that represent the state of a game
     * and whose children are all possible States that can result from the next move.
     * <p/>
     * NOTE: this method runs in exponential time with respect to d.
     * With d around 5 or 6, it is extremely slow and will start to take a very
     * long time to run.
     * <p/>
     * Note: If s has a winner (four in a row), it should be a leaf.
     */
    public static void createGameTree(State s, int d) {
        // Note: This method must be recursive, recurse on d,
        // which should get smaller with each recursive call
    	
    	///we have to initialise states
    	///the testInitializeChildren method showed that if this method is not filled out then everything is fucked - so it's important
    	s.initializeChildren();
    	
    	 if (d <= 1) {
    		 ///we have to do something...
    		 ///nothing???
    		 
    		 return;
    		 //break;
    	 }
    	 else
    	 {
    		 createGameTree(s, d - 1);
    	 }
    	 
    	 ///if this is a recursive method, then there's no need for a binary search tree; why did I build one???
    	 ///how do you do binary search without a tree????
    }

    /**
     * Call minimax in ai with state s.
     */
    public static void minimax(AI ai, State s) {
        ai.minimax(s);
    }

    /**
     * State s is a node of a game tree (i.e. the current State of the game).
     * Use the Minimax algorithm to assign a numerical value to each State of the
     * tree rooted at s, indicating how desirable that java.State is to this player.
     */
    public void minimax(State s) {
        // TODO
    }

    /**
     * Evaluate the desirability of Board b for this player
     * Precondition: b is a leaf node of the game tree (because that is most
     * effective when looking several moves into the future).
     */
    public int evaluateBoard(Board b) {
        Player winner = b.hasConnectFour();
        int value = 0;
        if (winner == null) {
            // Store in sum the value of board b. 
            List<Player[]> locs = b.winLocations();
            for (Player[] loc : locs) {
                for (Player p : loc) {
                    value += (p == player ? 1 : p != null ? -1 : 0);
                }
            }
        } else {
            // There is a winner
            int numEmpty = 0;
            for (int r = 0; r < Board.NUM_ROWS; r = r + 1) {
                for (int c = 0; c < Board.NUM_COLS; c = c + 1) {
                    if (b.getTile(r, c) == null) numEmpty += 1;
                }
            }
            value = (winner == player ? 1 : -1) * 10000 * numEmpty;
        }
        return value;
    }
}
