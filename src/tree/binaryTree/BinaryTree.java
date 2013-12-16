package tree.binaryTree;

public class BinaryTree {
	protected TreeNode root;
	private int size = 0;
	/**
	 * Insert element o into the binary tree
	 * Return true of the element is inserted successfully
	 * @param o
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean insert(Object o) {
		if (root == null) {
			root = new TreeNode(o);
		} else {
			TreeNode current = root;
			// it must initialize, for the 24 line
			TreeNode parent = null;
			while (current != null) {
				if (((Comparable) o).compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (((Comparable) o).compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else {
					return false;
				}
			}
			// Create the new node and attach it to the parent node
			if (((Comparable) o).compareTo(parent.element) > 0) {
				parent.right = new TreeNode(o);
			} else {
				parent.left = new TreeNode(o);
			}
			
		}
		size++;
		return true;
	}
	/**
	 * Inorder traversal
	 */
	public void inorder(){
		inorder(root);
	}
	/**
	 * Inorder traversal from a subtree
	 * @param root
	 */
	private void inorder(TreeNode root){
		if(root == null) return ;
		inorder(root.left);
		System.out.println(root.element+" ");
		inorder(root.right);
	}
	
	public void postorder(){
		postorder(root);
	}
	private void postorder(TreeNode root){
		if(root == null) return;
		postorder(root.left);
		postorder(root.right);
		System.out.println(root.element+" ");
	}
	
	/**
	 *为什么需要再写一个带参数的方法呢？＃preorder(TreeNode root)＃
	 *就是为了实现递归
	 */
	public void preorder(){
		preorder(root);
	}
	private void preorder(TreeNode root){
		if(root == null) return;
		System.out.println(root.element+" ");
		preorder(root.left);
		preorder(root.right);
	}
	
	/**
	 * Return the number of element in the tree
	 * @return
	 */
	public int getSize(){
		return this.size;
	}
	
	protected static class TreeNode {
		public Object element;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(Object o) {
			this.element = o;
		}
	}
}
