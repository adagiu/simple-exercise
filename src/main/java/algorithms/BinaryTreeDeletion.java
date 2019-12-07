package algorithms;

/**
 * Given a tree, remove nodes with 0.
 *
 */
public class BinaryTreeDeletion {

	private static Node removeZeros(Node root) {
		if (root == null)
			return null;

		if (root.getVal() == 0 && root.isLeaf())
			return null;

		if (root.getVal() == 0) {
			Node deepestNode = removeDeepestNode(root, root);
			if (deepestNode == null) {
				return null;
			} else {
				root.setVal(deepestNode.getVal());
			}
		}
		root.setLeft(removeZeros(root.getLeft()));
		root.setRight(removeZeros(root.getRight()));

		return root;
	}

	private static Node removeDeepestNode(Node root, Node parent) {
		if (root == null)
			return root;

		// in this case I am swapping suitable node to return with 0 which will be delete later on
		if (root.getVal() != 0 && root.isLeaf()) {
			Node toReturn = new Node(root.getVal());
			root.setVal(0);
			return toReturn;
		}

		if (root.getVal() == 0 && root.isLeaf())
			return null;

		Node r = removeDeepestNode(root.getRight(), root);
		if (r != null && r.getVal() != 0) {
			return r;
		}
		Node l = removeDeepestNode(root.getLeft(), root);
		if (l != null && l.getVal() != 0) {
			return l;
		}
		return null;
	}

	public static void main(String[] args) {
		Node root = new Node(1,
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

		root = removeZeros(root);
		root.print();
	}

}
