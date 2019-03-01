package algorithms;

public class BinaryTreeSplitter {

	private static class TreeNode {
		private int val;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(int val, TreeNode l, TreeNode r) {
			this.val = val;
			this.left = l;
			this.right = r;
		}
	}

	public TreeNode flatten(TreeNode root) {
		return flatten(root, null);
	}

	public TreeNode flatten(TreeNode root, TreeNode last) {
		if (root == null)
			return last;

		root.right = flatten(root.left, flatten(root.right, last));
		root.left = null;

		return root;
	}

	public static void main(String args[]) {
		BinaryTreeSplitter tf = new BinaryTreeSplitter();

		TreeNode tree1 = new TreeNode(1, null, null);
		printTree(tf.flatten(tree1));

		System.out.println(tf.flatten(null));

		TreeNode tree2 = buildTree();
		printTree(tf.flatten(tree2));
	}

	private static void printTree(TreeNode root) {
		if (root == null)
			return;
		System.out.println(root.val);
		printTree(root.right);
	}

	private static final TreeNode buildTree() {
		TreeNode root5 = newTreeNode(2, newTreeNode(3), newTreeNode(4));

		TreeNode root25 = newTreeNode(6, newTreeNode(7), newTreeNode(8));
		TreeNode root26 = newTreeNode(5, root25, null);

		return newTreeNode(1, root5, root26);
	}

	private static final TreeNode newTreeNode(int val) {
		return new TreeNode(val, null, null);
	}

	private static final TreeNode newTreeNode(int val, TreeNode l, TreeNode r) {
		return new TreeNode(val, l, r);
	}

}
