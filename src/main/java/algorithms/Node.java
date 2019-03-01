package algorithms;

public class Node {

	private Node left;
	private Node right;
	private int v;

	public Node(int v) {
		this(v, null, null);
	}

	public Node(int v, Node l, Node r) {
		this.v = v;
		this.left = l;
		this.right = r;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node l) {
		this.left = l;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node r) {
		this.right = r;
	}

	public int getVal() {
		return v;
	}

	public void setVal(int v) {
		this.v = v;
	}

	public boolean isLeaf() {
		return this != null && this.getLeft() == null && this.getRight() == null;
	}

	public void print() {
		print(this, "", 0);
	}

	private void print(Node n, String prefix, int level) {
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
