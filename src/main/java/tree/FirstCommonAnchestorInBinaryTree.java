package tree;

import algorithms.Node;
import utils.test.Test;

public class FirstCommonAnchestorInBinaryTree {

	public static Node firstCommonAnchestor(Node root, int a, int b) {
		return firstCommonAnchestor(root, null, a, b);
	}

	private static Node firstCommonAnchestor(Node root, Node parent, int a, int b) {
		if (root == null)
			return parent;

		boolean findALeft = false;
		boolean findBLeft = false;

		Node nodeA = null;
		Node nodeB = null;

		if (root.getVal() == a && (nodeB = findNode(root, null, b)) != null)
			return root;

		if (root.getVal() == b && (nodeA = findNode(root, null, a)) != null)
			return root;

		nodeA = findNode(root.getLeft(), null, a);
		if (nodeA != null)
			findALeft = true;
		else if ((nodeA = findNode(root.getRight(), null, a)) == null)
			return null;

		nodeB = findNode(root.getLeft(), null, b);
		if (nodeB != null)
			findBLeft = true;
		else if ((nodeB = findNode(root.getRight(), null, b)) == null)
			return null;

		if ((findALeft && !findBLeft) || (!findALeft && findBLeft))
			return root;

		Node next = findALeft && findBLeft ? firstCommonAnchestor(root.getLeft(), root, a, b) : firstCommonAnchestor(root.getRight(), parent, a, b);
		return next == null ? parent : next;
	}

	private static Node findNode(Node root, Node parent, int a) {
		if (root == null)
			return null;

		if (root.getVal() == a)
			return root;

		Node tmp = findNode(root.getLeft(), root, a);
		return tmp != null ? tmp : findNode(root.getRight(), root, a);
	}

	public static void main(String[] args) {
		Test.isNull(firstCommonAnchestor(null, 12, 13));
		Test.isNull(firstCommonAnchestor(new Node(1), 12, 13));
		Test.isNull(firstCommonAnchestor(new Node(12), 12, 13));
		Test.equals(firstCommonAnchestor(new Node(12, new Node(13), null), 12, 13).getVal(), 12);
		Test.equals(firstCommonAnchestor(new Node(11, new Node(13), new Node(12)), 12, 13).getVal(), 11);

		Node n = new Node(11,
				new Node(13,
						new Node(45),
						new Node(22, new Node(90, new Node(2334), new Node(890)), null)),
				new Node(12,
						new Node(55),
						new Node(900,
								new Node(70),
								new Node(44))));
		n.print();
		Test.equals(firstCommonAnchestor(n, 890, 55).getVal(), 11);
		Test.equals(firstCommonAnchestor(n, 890, 2334).getVal(), 90);
		Test.equals(firstCommonAnchestor(n, 890, 45).getVal(), 13);
		Test.equals(firstCommonAnchestor(n, 900, 55).getVal(), 12);
		Test.equals(firstCommonAnchestor(n, 12, 44).getVal(), 12);
		Test.equals(firstCommonAnchestor(n, 11, 890).getVal(), 11);
		Test.isNull(firstCommonAnchestor(n, 12, 130));
	}
}
