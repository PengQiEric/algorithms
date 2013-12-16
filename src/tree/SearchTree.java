package tree;

public class SearchTree {

	private static TreeNode root;

	public TreeNode insertIntoTree(TreeNode root, TreeNode insertNode) {
		TreeNode current = root;
		TreeNode parent = current;
		while (current != null) {
			if (current.info == insertNode.info) {
				break;
			} else if (current.info < insertNode.info) {
				parent = current;
				current = current.right;
			} else {
				parent = current;
				current = current.left;
			}
		}

		if (parent == null) {
			root = insertNode;
		} else if (parent.info == insertNode.info) {
		} else if (parent.info < insertNode.info) {
			parent.right = insertNode;
		} else {
			parent.left = insertNode;
		}
		return root;
	}

	public static void main(String[] args)
	{
		SearchTree st = new SearchTree();
		TreeNode leaf1 = new TreeNode(45);
		TreeNode leaf2 = new TreeNode(24);
		TreeNode leaf3 = new TreeNode(53);
		TreeNode leaf4 = new TreeNode(45);
		TreeNode leaf5 = new TreeNode(12);
		TreeNode leaf6 = new TreeNode(24);
		TreeNode leaf7 = new TreeNode(90);
		root = st.insertIntoTree(root, leaf1);
		root = st.insertIntoTree(root, leaf2);
		root = st.insertIntoTree(root, leaf3);
		root = st.insertIntoTree(root, leaf4);
		root = st.insertIntoTree(root, leaf5);
		root = st.insertIntoTree(root, leaf6);
		root = st.insertIntoTree(root, leaf7);
		st.deleteBST(root, 24);
		System.out.println();
	}
	
	public void deleteBST(TreeNode root,int key)
	{
		if(root == null) return;	//不存在关键字为key的元素结点
		if(root.info == key){
			 delete(root);	//找到了关键字为key的结点
		}
		else if(root.info <= key){
			deleteBST(root.right,key);
		}
		else{
			deleteBST(root.left,key);
		}
	}	
	
	public void delete(TreeNode deleteNode)
	{
		if(deleteNode.right == null)	//右子树空则只许重接它的左子树
		{
			deleteNode = deleteNode.left;	//其实我想知道系统会不会自动回收原来的deleteNode结点
		}
		else if(deleteNode.left == null)
		{
			deleteNode = deleteNode.right;
		}
		else	//左右子树均不空
		{
			//找一个关键的结点，这个结点是要删除的结点的左子树上的右子树上的其没有右子树的结点
			TreeNode q = deleteNode.left;
			while(q.right!=null)	//向右直到尽头
			{
				q = q.right;
			}
			q.right = deleteNode.right;	//将删除结点的右子树嫁接到这个结点的右子树上
			deleteNode = deleteNode.left;	//删除原来的结点
			//或者是将这个结点替换要删除的结点，而将其左子树放入其位置上
//			deleteNode.info = q.info;
//			q = q.left;
		}
	}
	
	public static class TreeNode {
		int info;
		TreeNode left;
		TreeNode right;

		public TreeNode(int info) {
			this.info = info;
		}
	}
}
