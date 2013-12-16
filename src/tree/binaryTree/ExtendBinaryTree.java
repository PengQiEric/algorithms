package tree.binaryTree;

import list.stack.MyStack;

public class ExtendBinaryTree extends BinaryTree {
	private int size = 0;
	private int height = 0;

	public void initTree() {

		TreeNode node1 = new TreeNode("a");
		TreeNode node2 = new TreeNode("b");
		TreeNode node3 = new TreeNode("c");
		TreeNode node4 = new TreeNode("d");
		TreeNode node5 = new TreeNode("e");
		TreeNode node6 = new TreeNode("f");
		TreeNode node7 = new TreeNode("g");
		TreeNode node8 = new TreeNode("h");
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
//		 node3.right = node7;
		node3.left = node6;
		node4.left = node8;
		node8.left = node7;
		root = node1;
	}

	/*
	 * ǰ����������ȷ��ʽڵ㣬��ȥ��������������
	 * UNIX�ļ�ϵͳ���������ļ�ʱ�����ַ������ȷ����Լ���Ȼ���ٷ���child�ڵ� Use no-recursive method to
	 * traversal a tree
	 * 
	 * @see tree.binaryTree.BinaryTree#preorder()
	 */
	public void preorder() {
		preorder(root);
	}

	private void preorder(TreeNode root) {
		MyStack stack = new MyStack();
		while (!stack.isEmpty() || root != null) {
			if (root != null) {
				System.out.print(root.element + " ");
				stack.push(root);
				root = root.left;
			} else {
				root = (TreeNode) stack.pop();
				// ������ݽڵ�������������ôҪ������ڵ�ѹ��ջ��
				if (root.right != null) {
					root = root.right;
				} else {
					root = null;
				}
			}
		}
	}

	/**
	 * ģ��ݹ���ã����Ƚ��ڵ�ѹ��ջ��Ȼ�󵯳������������������Ϊ�գ�ѹ��ջ��
	 */
	public void preorder2() {
		preorder2(root);
	}

	private void preorder2(TreeNode root) {
		if (root != null) {
			MyStack stack = new MyStack();
			stack.push(root);
			while (!stack.isEmpty()) {
				TreeNode node = (TreeNode) stack.pop();
				System.out.print(node.element + " ");
				if (node.right != null) {
					stack.push(node.right);
				}
				if (node.left != null) {
					stack.push(node.left);
				}
			}
		}
	}
	
	/* 
	 *  ����������ȷ���������Ȼ����ʸ��ڵ㣬������������
	 * (non-Javadoc)
	 * @see tree.binaryTree.BinaryTree#inorder()
	 */
	public void inorder(){
		inorder(root);
	}
	private void inorder(TreeNode root){
		MyStack stack = new MyStack();
		while(!stack.isEmpty() || root!=null){
			if(root!=null){
				stack.push(root);
				root = root.left;
			}else{
				root = (TreeNode)stack.pop();
				System.out.print(root.element +" ");
				if(root.right!=null){
					root = root.right;
				}else{
					//���û�������������뽫root����Ϊnull��Ȼ����ܴ�ջ��ȡ����һ��Ԫ��
					root = null;
				}
			}
		}
	}

	
	/**
	 * Return the height of the binary tree
	 * 
	 * @return
	 */
	public int getHeight() {
		getHeight(root);
		return height;
	}

	public int getHeight(TreeNode root) {
		// �˴���root�Ǵ��ݹ����Ĳ���
		TreeNode current = root;
		if (current == null)
			return 0;
		else {
			height = Math
					.max(getHeight(current.left), getHeight(current.right)) + 1;
			return height;
		}
	}

	public static void main(String[] args) {
		ExtendBinaryTree tree = new ExtendBinaryTree();
		tree.initTree();
//		System.out.println(tree.getHeight());
//		 tree.preorder();
//		tree.preorder2();
		tree.inorder();
	}
}
