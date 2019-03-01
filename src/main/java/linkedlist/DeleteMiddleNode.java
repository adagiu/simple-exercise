package linkedlist;

import utils.test.Test;

/**
 * Given a node to a LinkedList remove the middle element.
 *
 */
public class DeleteMiddleNode {

	public static LinkedListNode deleteMiddleNode(LinkedListNode node) {
		if (node == null)
			return null;

		LinkedListNode middle = node;
		LinkedListNode prev = node;
		LinkedListNode runner = node;

		int i = 0;

		while (runner != null) {
			if (i % 2 == 1) {
				prev = middle;
				middle = middle.getNext();
			}
			i++;
			runner = runner.getNext();
		}
		prev.setNext(middle.getNext());

		return node;
	}

	public static void main(String[] args) {
		LinkedListNode node = null;
		Test.equals(deleteMiddleNode(node) == null, true);

		node = new LinkedListNode(9);
		Test.equals(deleteMiddleNode(node).toString(), "9");

		node = new LinkedListNode(9, new LinkedListNode(2));
		Test.equals(deleteMiddleNode(node).toString(), "9");

		node = new LinkedListNode(9, new LinkedListNode(2, new LinkedListNode(3)));
		Test.equals(deleteMiddleNode(node).toString(), "9 -> 3");

		node = new LinkedListNode(9, new LinkedListNode(2, new LinkedListNode(3, new LinkedListNode(16))));
		Test.equals(deleteMiddleNode(node).toString(), "9 -> 2 -> 16");

		node = new LinkedListNode(9, new LinkedListNode(2, new LinkedListNode(3, new LinkedListNode(16, new LinkedListNode(33)))));
		Test.equals(deleteMiddleNode(node).toString(), "9 -> 2 -> 16 -> 33");
		node = new LinkedListNode(9, new LinkedListNode(2, new LinkedListNode(3, new LinkedListNode(16, new LinkedListNode(33)))));
		Test.equals(deleteMiddleNode(node).toString(), "9 -> 2 -> 16 -> 33");

		node = new LinkedListNode(9, new LinkedListNode(2,
				new LinkedListNode(3, new LinkedListNode(16, new LinkedListNode(33, new LinkedListNode(146, new LinkedListNode(83)))))));
		Test.equals(deleteMiddleNode(node).toString(), "9 -> 2 -> 3 -> 33 -> 146 -> 83");
	}

}
