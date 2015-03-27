package code;

public class BinarySearchTree {
	
	/**
	 * The root node
	 */
	private TreeNode rootNode;
	
	/**
	 * Number of elements in the binary tree
	 */
	private int count =0;
	
	/**
	 * Returns the number of elements in the binary tree
	 */
	public int getCount()
	{
		return this.count;
	}
	
	/**
	 * Create a binary search tree; set the value of the root node;
	 */
	public BinarySearchTree(int value) 
	{
		///will need to sort this stuff out later
		this.rootNode = new TreeNode(value);
		this.count++;
	}
	
	/**
	 * Insert an value into the binary search tree; 
	 */
	public void Insert (int value)
	{
		this.Insert(this.rootNode, value);
        this.count++;
	}
	
	/**
	 * Insert a value into the binary search tree; 
	 */
	private void Insert(TreeNode root, int value)
	{   
		///the root will always have some value
		while (this.rootNode != null)
		{
			///if the value is greater then the root then
			///then we go down the right side of the tree
			if((value > root.getValue()))
			{
                ///if null then we create a new node
				if (root.getNodeBranch(NodeBranch.RightBranch) == null)
				{
				    root.setNodeBranch(NodeBranch.RightBranch, new TreeNode(value));
                   // Count++; 
				    break;
				}
                /// else we amend the root value
                /// and we keep going down the right side of the tree
                /// we compare and update the root as needed
				root = root.getNodeBranch(NodeBranch.RightBranch);
			}
			else
			{
				if (root.getNodeBranch(NodeBranch.LeftBranch) == null)
				{
					 root.setNodeBranch(NodeBranch.LeftBranch, new TreeNode(value));
                    //Count++; 
					break;
				}
				root = root.getNodeBranch(NodeBranch.LeftBranch);
			}
		}	   
	}
	
	/**
	 * Displays the binary search tree in preorder
	 * @param rootNode
	 */
	private void Display_Preorder(TreeNode rootNode) 
	{
		if (rootNode == null) {return;}
		
		System.out.println(rootNode.getValue());
		Display_Preorder(rootNode.getNodeBranch(NodeBranch.LeftBranch));
		Display_Preorder(rootNode.getNodeBranch(NodeBranch.RightBranch)); 
		  
	}
	
	/**
	 * Displays the binary search tree in preorder
	 */
	public void Display_Preorder()
	{
		this.Display_Preorder(rootNode);
	}
	
	/**
	 * Searches for an element in the binary tree
	 */
	public boolean BinarySearch(int value)
	{
		return this.BinarySearch(value, this.rootNode);
	}
	
	/**
	 * Searches for an element in the binary tree
	 */
	private boolean BinarySearch(int value, TreeNode root)
     {
         while (root != null)
         {
             if ((value < root.getValue()) )
             {
                 root = root.getNodeBranch(NodeBranch.LeftBranch);
             }
             else if ((value > root.getValue()))
             {
                 root =root.getNodeBranch(NodeBranch.RightBranch);
             }
             else if (value==root.getValue())
             {
                 return true;
             }
         }

         return false;
     }


}
