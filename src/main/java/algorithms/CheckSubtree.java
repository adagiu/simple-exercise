package algorithms;

import utils.test.Test;

/**
 * given 2 large binary trees T1 and T2, determine if T2 is a subtree of T1. A tree T2 is a subtree of T1 if there exists a node in T1 such that the
 * subtree of n is identical to T2. That is, if you cut off the tree at node n, the two trees would be identical.
 * 
 */
public class CheckSubtree {

	public static boolean checkSubtree(Node t1, Node t2) {
		Node t1Root = searchNodeN(t1, t2.getVal());
		return identical(t1Root, t2);
	}

	private static Node searchNodeN(Node t1, int val) {
		if (t1 == null)
			return null;

		if (t1.getVal() == val)
			return t1;

		Node tmp = searchNodeN(t1.getLeft(), val);
		if (tmp != null && tmp.getVal() == val)
			return tmp;

		return searchNodeN(t1.getRight(), val);
	}

	public static boolean identical(Node t1, Node t2) {
		if (t1 == null && t2 == null)
			return true;

		if (t1 == null || t2 == null)
			return false;

		if (t1.getVal() != t2.getVal())
			return false;

		return identical(t1.getLeft(), t2.getLeft()) && identical(t1.getRight(), t2.getRight());
	}

	public static void main(String[] args) {
		Node root2 = new Node(1,
				new Node(2,
						new Node(0,
								new Node(17,
										new Node(20),
										new Node(7)),
								new Node(18,
										new Node(13),
										new Node(14))),
						new Node(10,
								new Node(0,
										null,
										new Node(15)),
								null)),
				new Node(7,
						new Node(4,
								new Node(8,
										new Node(0,
												new Node(9),
												null),
										null),
								new Node(5)),
						new Node(0,
								new Node(0),
								new Node(0))));

		Node root1 = new Node(100,
				new Node(76,
						new Node(56),
						new Node(102,
								new Node(45),
								root2)),
				new Node(88,
						new Node(34),
						new Node(309,
								new Node(323,
										new Node(3409), null),
								new Node(77))));

		Test.equals(checkSubtree(root1, root2), true);

		Node root3 = new Node(88,
				new Node(34),
				new Node(309,
						new Node(323), new Node(22)));

		Node root4 = new Node(100,
				new Node(99,
						new Node(56),
						new Node(102,
								new Node(45),
								null)),
				new Node(88,
						new Node(34),
						new Node(309,
								new Node(323), null)));

		Test.equals(checkSubtree(root4, root3), false);

		Node root5 = new Node(88,
				new Node(34),
				new Node(309,
						new Node(323), null));

		Node root6 = new Node(100,
				new Node(99,
						new Node(56),
						new Node(102,
								new Node(45),
								root2)),
				new Node(88,
						new Node(34),
						new Node(309,
								new Node(323), new Node(22))));

		Test.equals(checkSubtree(root6, root5), false);

		Node root7 = new Node(88,
				new Node(34),
				new Node(309,
						new Node(323), null));

		Node root8 = new Node(100,
				new Node(99,
						new Node(56),
						new Node(102,
								new Node(45),
								root2)),
				new Node(88,
						new Node(34),
						new Node(309,
								new Node(322), null)));

		Test.equals(checkSubtree(root8, root7), false);
	}

}
