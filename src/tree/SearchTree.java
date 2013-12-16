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
		if(root == null) return;	//�����ڹؼ���Ϊkey��Ԫ�ؽ��
		if(root.info == key){
			 delete(root);	//�ҵ��˹ؼ���Ϊkey�Ľ��
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
		if(deleteNode.right == null)	//����������ֻ���ؽ�����������
		{
			deleteNode = deleteNode.left;	//��ʵ����֪��ϵͳ�᲻���Զ�����ԭ����deleteNode���
		}
		else if(deleteNode.left == null)
		{
			deleteNode = deleteNode.right;
		}
		else	//��������������
		{
			//��һ���ؼ��Ľ�㣬��������Ҫɾ���Ľ����������ϵ��������ϵ���û���������Ľ��
			TreeNode q = deleteNode.left;
			while(q.right!=null)	//����ֱ����ͷ
			{
				q = q.right;
			}
			q.right = deleteNode.right;	//��ɾ�������������޽ӵ����������������
			deleteNode = deleteNode.left;	//ɾ��ԭ���Ľ��
			//�����ǽ��������滻Ҫɾ���Ľ�㣬������������������λ����
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
