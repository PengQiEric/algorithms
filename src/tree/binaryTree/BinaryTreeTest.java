package tree.binaryTree;

import java.util.Stack;

public class BinaryTreeTest {

	private TreeNode root;
	
	private void init()
	{
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
		node3.right = node7;
		node3.left = node6;
		node4.left = node8;
		node8.left = node7;
		root = node1;
	}
	
	private void visit(TreeNode node)
	{
		System.out.print(node.elemet+" ");
	}
	
	public void preOrder1(TreeNode root)
	{
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if(root!=null)
		{
			visit(root);
			stack.push(root);
		}
		while(stack.size() != 0)
		{
			while(root.left != null && root.left.flag !=1 )
			{
				root = root.left;
				visit(root);
				root.flag = 1;
				stack.push(root);
			}
			root = stack.pop();
			if(root.right!=null)
			{
				root = root.right;
				visit(root);
				root.flag = 1;
				stack.push(root);
			}
		}
	}
	
	private void preOrder2(TreeNode root)
	{
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if(root!=null)
		{
			visit(root);
			stack.push(root);
		}
		while(stack.size()!=0)
		{
			if(root.left!=null && root.left.flag!=1)
			{
				root = root.left;
				visit(root);
				root.flag = 1;
				stack.push(root);
			}
			else
			{
				root = stack.pop();
				if(root.right!=null)
				{
					root = root.right;
					visit(root);
					root.flag = 1;
					stack.push(root);
				}
			}
		}
	}
	
	public void preOrder(TreeNode root)
	{
		if(root!=null)
		{
			visit(root);
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	
	public static void main(String[] args)
	{
		BinaryTreeTest btt = new BinaryTreeTest();
		btt.init();
		btt.preOrder(btt.root);
		System.out.println();
		btt.preOrder1(btt.root);
		btt.init();
		System.out.println();
		btt.preOrder2(btt.root);
	}
	
	private class TreeNode
	{
		String elemet;
		TreeNode left;
		TreeNode right;
		int flag = 0;
		public TreeNode(String element)
		{
			this.elemet = element;
		}
	}
}
