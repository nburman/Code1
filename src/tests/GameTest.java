package tests;

import java.util.Random;

// Needed for message box
import javax.swing.JOptionPane;

// JUnit stuff
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

// Game stuff
import code.AI;
import code.BinarySearchTree;
import code.Board;
import code.Dummy;
import code.Game;
import code.GUI;
import code.Human;
import code.Move;
import code.Player;
import code.Solver;
import code.State;

public class GameTest {

	private Solver p1 = new Human(Player.RED);
	private Solver p2 = new Dummy(Player.YELLOW);
           
    private Board board = new Board();
    
    Random rand = new Random();
	
    /** 
     * Test moves.
     * 
     */
	@Test
	public void testGetPossibleMoves() {		
		
		board.makeMove(new Move(Player.RED, 1));
		board.makeMove(new Move(Player.RED, 1));
		board.makeMove(new Move(Player.RED, 1));
	    
	    //Game game= new Game(p1, p2, board, false);
	    
	    State s =  new State(Player.RED, board, new Move(Player.RED, 1));
		
	    System.out.println("Player can move piece to the following columns:");
	    for(int i = 0; i < board.getPossibleMoves(Player.RED).length;i++)
        {
        	System.out.println(board.getPossibleMoves(Player.RED)[i].getColumn());
        }
	    
	    GameTest.fillColumn(board, 2, Player.YELLOW);
		
		System.out.println("\nPlayer can move piece to the following columns:");
	    for(int i = 0; i < board.getPossibleMoves(Player.RED).length;i++)
        {
        	System.out.println(board.getPossibleMoves(Player.RED)[i].getColumn());
        }
	    
	    s.initializeChildren();
	    
	    // Whatever happens, YELLOW goes next
	    for (State ss : s.getChildren())
	    { 
	    	assertEquals("Who goes next", ss.toString().substring(0,14), "YELLOW to play");
	    }
	}
	
	/**
	 * Test the binary tree search works.
	 * 
	 */
	@Test
	public void testBinaryTree()
	{		
		int[] arr = new int[4];
		
		// Set the root
		int root = 25;
		
		System.out.format("Binary tree root: %d\n", root);
		BinarySearchTree t = new BinarySearchTree(root);
		
		for(int i = 0 ; i< arr.length; i++)
		{
			arr[i]=rand.nextInt(50);
			System.out.format("Value inserted into binary tree: %d\n", arr[i]);
			t.Insert(arr[i]);
		}
		
		System.out.println("\n--------PREORDER---------");
		t.Display_Preorder();
		
		// Verify elements
		assertTrue("Element exists", t.BinarySearch(arr[3]));
		assertFalse("15 does not exist in the binary search tree", t.BinarySearch(15));
	}
	
	/**
	 * Tests all the possible moves a player can make across the game board. 
	 * 
	 */
	@Test
	public void testInitializeChildren()
	{
		//add moves
		board.makeMove(new Move(Player.RED, 1));
		board.makeMove(new Move(Player.YELLOW, 6));    
	    
	    //new state
	    State s =  new State(Player.RED, board, new Move(Player.RED, 1));
	    
	    //run the initialise children method
	    s.initializeChildren();
	    
	    //now test to see what happens...hopefully it works
	    State[] children = s.getChildren();
	    
	    // Should get 7 states
	    assertEquals("Number of states", 7, children.length);
	    
	}
	
	/**
	 * Testing out the generation of game tree.
	 * 
	 */
	@Test
	public void testGameTree()
	{
		// Set the depth
		int depth = 1;
		
		// Add moves
		board.makeMove(new Move(Player.YELLOW, 1));
		board.makeMove(new Move(Player.YELLOW,2));
		board.makeMove(new Move(Player.YELLOW, 4));	
		board.makeMove(new Move(Player.RED, 5));
		board.makeMove(new Move(Player.YELLOW, 6));	
		board.makeMove(new Move(Player.RED, 0));
		board.makeMove(new Move(Player.YELLOW, 4));	
		board.makeMove(new Move(Player.RED, 5));
		board.makeMove(new Move(Player.YELLOW, 6));	
		board.makeMove(new Move(Player.RED, 0));
		
	    // New state
	    State s =  new State(Player.RED, board, new Move(Player.RED, 1));
	    
	    // Run the initialise children method
	    s.initializeChildren();
	    
	    // Create the game tree
	    AI.createGameTree(s, depth);	        
	    	    
	    // Check that the state is not empty
	    assertNotNull(s.toString());
	}

	/**
    * This is not a test - it is used by testGetPossibleMoves above.
    * Fill a column with alternating coloured pieces.
	*
    */
	@Ignore
	public static void fillColumn(Board board, int column, Player player)
	{
		for(int i =0; i < Board.NUM_COLS; i++)
		{
			player = (player==Player.RED) ? Player.YELLOW: Player.RED;
			
			board.makeMove(new Move(player, column));
		}
	}

	/**
	 * Make sure the game works.
	 * 
	 */
	@Test
	public void zzRunGame() {
		
		// Ask the user to play the game so that they know what to do.
		JOptionPane.showMessageDialog(null,
				"Please play the game on the panel that will now appear\nso that the test can be completed.\nThank you.",
				"Testing...", JOptionPane.INFORMATION_MESSAGE);
		
		// Create and run a game.
		Game game= new Game(p1, p2, board, false);
		game.setGUI(new GUI(game));

	    // Make sure there is a game.
	    assertNotNull(game);

	    // Now run it.
		game.runGame();
	    
	}

}
