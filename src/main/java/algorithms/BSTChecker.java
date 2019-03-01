package algorithms;

import utils.test.Test;

/**
 * Given a binary tree, write an algorithm to check if it is BST or not.
 * 
 * A binary search tree (BST) is a node based binary tree data structure which has the following properties.
 * <ul>
 * <li>The left subtree of a node contains only nodes with keys less than the node’s key</li>
 * <li>The right subtree of a node contains only nodes with keys greater than the node’s key</li>
 * <li>Both the left and right subtrees must also be binary search trees</li>
 * </ul>
 * 
 * From the above properties it naturally follows that: each node (item in the tree) has a distinct key.
 */
public class BSTChecker {

	private static class Node {

		Node left;
		Node right;
		int v;

		Node(int v) {
			this(v, null, null);
		}

		Node(int v, Node l, Node r) {
			this.v = v;
			this.left = l;
			this.right = r;
		}
	}

	public static boolean isBST(Node n) {
		return isBST(n, null);
	}

	public static boolean isBST(Node n, Node prev) {
		if (n != null) {
			if (!isBST(n.left, prev))
				return false;

			if (prev != null && n.v <= prev.v)
				return false;

			return isBST(n.right, n);
		}
		return true;
	}

	public static boolean isBST(Node node, int min, int max) {
		// an empty tree is BST
		if (node == null)
			return true;

		// false if this node violates the min/max constraints
		if (node.v < min || node.v > max)
			return false;

		// otherwise check the subtrees recursively tightening the min/max constraints Allow only distinct values
		return (isBST(node.left, min, node.v - 1) &&
				isBST(node.right, node.v + 1, max));
	}

	public static void main(String[] args) {
		Node n = new Node(5);
		Test.equals(true, isBST(n));

		Node n1 = new Node(10, n, null);
		Test.equals(true, isBST(n1));

		Node n2 = new Node(20, n1, null);
		Test.equals(true, isBST(n2));

		Node n3 = new Node(15, n1, n2);
		Test.equals(isBST(n3), false);

		Node n4 = new Node(20,
				// root left side
				new Node(10,
						new Node(5,
								new Node(4, null, null),
								new Node(6, null, null)),
						new Node(15,
								new Node(13, null, null),
								new Node(17, null, null))),
				// root right side
				new Node(30,
						new Node(27,
								new Node(21, null, null),
								new Node(28, null, null)),
						new Node(32, null, null)));
		Test.equals(true, isBST(n4));
		print(n4, "", 0);

		Node n5 = new Node(20,
				// root left side
				new Node(10,
						new Node(5,
								new Node(4, null, null),
								new Node(6, null, null)),
						new Node(15,
								new Node(13, null, null),
								new Node(17, null, null))),
				// root right side
				new Node(30,
						new Node(27,
								new Node(21, null, null),
								new Node(28, null, null)),
						new Node(12, null, null)));
		Test.equals(isBST(n5), false);
		print(n5, "", 0);
	}

	public static void print(Node n, String prefix, int level) {
		if (n == null)
			return;

		String suffix = "";
		for (int i = 0; i <= level; i++)
			suffix += "     ";
		print(n.left, suffix + "┌── ", level + 1);
		System.out.println(prefix + "(" + n.v + ")");
		print(n.right, suffix + "└── ", level + 1);
	}

}
