package tree.binaryTree;

import java.util.Stack;

import list.queue.MyQueue;
import list.stack.MyStack;

/**
 * 其实无论是用栈还是再添加一个指向父节点的指针，目的都是如何实现 回溯
 * 
 * @author qipeng
 * 
 */
public class MarkingBinaryTree extends ExtendBinaryTree {
	public TreeNode root;

	public void initTree() {
		// 如果调用父类的这个方法，那么父类该方法会调用其父类的内部类，但我们的TreeNode已经从写，
		// 所以这个方法必须重写
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
		// node3.right = node7;
		node3.left = node6;
		node4.left = node8;
		node8.left = node7;
		root = node1;
	}

	/*
	 * 中序遍历的模拟递归，这时要在每个TreeNode上加一个标记位，如果其左右子树已经被 压入栈中，那么这个元素可以访问了
	 * 
	 * @see tree.binaryTree.ExtendBinaryTree#inorder()
	 */
	public void inorder() {
		inorder(root);
	}

	private void inorder(TreeNode root) {
		MyStack stack = new MyStack();
		if (root != null) {
			stack.push(root);
		}
		while (!stack.isEmpty()) {
			TreeNode current = (TreeNode) stack.pop();
			if (current.bPushed) {
				System.out.print(current.element + " ");
			} else {
				if (current.right != null) {
					stack.push(current.right);
				}
				stack.push(current);
				if (current.left != null) {
					stack.push(current.left);
				}
				current.bPushed = true;
			}
		}
	}

	private class TreeNode {
		public Object element;
		public TreeNode left;
		public TreeNode right;
		public boolean bPushed;

		public TreeNode(Object o) {
			this.element = o;
			this.bPushed = false;
		}
	}

	/*
	 * 模拟出栈和入栈与中序遍历一样，也需要标记，只是如栈的时候，根节点先如栈
	 * 
	 * @see tree.binaryTree.BinaryTree#postorder()
	 */
	public void postorder() {
		Stack stack = new Stack();
		stack.push(root);
		while(!stack.isEmpty())
		{
			if(root.right!=null&& root.right.bPushed!=true)
			{
				stack.push(root.right);
				root.right.bPushed = true;
			}
			else if(root.left!=null && root.left.bPushed!=true)
			{
				stack.push(root.left);
				root.left.bPushed = true;
				root = root.left;
			}
			else
			{
				System.out.println(((TreeNode)stack.pop()).element);
			}
		}
	}

	public void levelOrder() {
		levelOrder(root);
	}

	private void levelOrder(TreeNode root) {
		MyQueue queue = new MyQueue();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode current = (TreeNode) queue.poll();
			System.out.print(current.element+" ");
			if (current.left != null) {
				queue.offer(current.left);
			}
			if (current.right != null) {
				queue.offer(current.right);
			}
		}
	}

	public static void main(String[] args) {
		MarkingBinaryTree tree = new MarkingBinaryTree();
		tree.initTree();
//		tree.inorder();
//		tree.levelOrder();
		tree.postorder();
	}
}
