package code;
/**
 * A representation of a binary tree with the current node as the root.
 * Invariant: There are no duplicate values in the tree.
 */
public class TreeNode {
    private int value;
    private TreeNode left; //null if no left child.
    private TreeNode right;//null if no right child.
    
    /**
     * Returns a branch node
     */
    public TreeNode getNodeBranch(NodeBranch direction)
    {
    	NodeBranch direct = direction;
    	
    	if(direct == NodeBranch.LeftBranch)
    	{
    		return this.left;
    	}
    	else
    	{
    		return this.right;
    	}
    }
    
    /**
     * Sets a branch node
     */
    public void setNodeBranch(NodeBranch direction, TreeNode Node)
    {
    	NodeBranch direct = direction;
    	
    	if(direct == NodeBranch.LeftBranch)
    	{
    		this.left = Node;
    	}
    	else
    	{
    		this.right = Node;
    	}
    }
    
    /**
     * Sets the value of this node
     */
    public void setValue(int value)
    {
    	this.value=value;
    }
    
    /**
     * Returns the value of this node
     */
    public int getValue()
    {
    	return this.value;
    }

    /**
     * Constructor: A tree with root value v, left subtree left, and right subtree right.
     */
    public TreeNode(TreeNode left, int v, TreeNode right) {
        this.value = v;
        this.left = left;
        this.right = right;
    }
    
    /**
     * Alternative, simpler, constructor for the tree node; 
     */
    public TreeNode(int v) {
        this.value = v;
    }

    /**
     * Return true iff this tree is a Binary Search Tree (BST).
     * A BST has these following properties:
     *
     * 1. The values in the left subtree of every TreeNode in
     * this tree are less than the TreeNode's value.
     *
     * 2. The values in the right subtree of every TreeNode in
     * this tree are greater than the TreeNode's value.
     *
     * 3. The left and right subtree each must also be a binary search tree.
     * <p/>
     * Precondition: This tree has no duplicate values.
     */
    public boolean isBST() {
        //TODO: implement the function isBST
        return false;
    }

}
