package algoritmhs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NTreeSplitter {

	private static class TreeNode {
		private int val;
		private List<TreeNode> children;

		public TreeNode(int val, List<TreeNode> children) {
			this.val = val;
			this.children = children;
		}
	}

	public List<Integer> flatten(TreeNode root) {
		if (root == null)
			return null;

		List<Integer> l = new ArrayList<>();
		if (root.children != null) {
			root.children.forEach(t -> l.addAll(flatten(t)));
		}
		l.add(root.val);
		return l;
	}

	public static void main(String args[]) {
		NTreeSplitter tf = new NTreeSplitter();

		TreeNode tree1 = new TreeNode(1, null);
		System.out.println(tf.flatten(tree1));

		System.out.println(tf.flatten(null));

		TreeNode tree2 = buildTree();
		System.out.println(tf.flatten(tree2));
	}

	private static final TreeNode buildTree() {
		TreeNode root5 = newTreeNode(111, newTreeNode(4310), newTreeNode(235324), newTreeNode(4311210));

		TreeNode root25 = newTreeNode(778899, newTreeNode(345346), newTreeNode(2180), newTreeNode(444555), newTreeNode(234));
		TreeNode root26 = newTreeNode(778899, root25);

		return newTreeNode(10564, root5, root26);
	}

	private static final TreeNode newTreeNode(int val, TreeNode... children) {
		return new TreeNode(val, Arrays.asList(children));
	}

}
