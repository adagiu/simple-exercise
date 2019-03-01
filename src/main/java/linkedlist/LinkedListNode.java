package linkedlist;

public class LinkedListNode {

	private int val;

	private LinkedListNode next;

	public LinkedListNode(int val) {
		this(val, null);
	}

	public LinkedListNode(int val, LinkedListNode next) {
		this.val = val;
		this.next = next;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public LinkedListNode getNext() {
		return next;
	}

	public void setNext(LinkedListNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		if (this.getNext() != null)
			return "" + this.val + " -> " + this.getNext().toString();
		return "" + this.val;
	}

}
