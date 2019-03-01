package linkedlist;

import utils.test.Test;

/**
 * Given a node to a LinkedList and a int value k, return the value of the k-th element to n
 *
 */
public class KthToNElement {

	public static int kthToN(LinkedListNode head, int k) {
		LinkedListNode runner = head;
		LinkedListNode kthNode = head;
		int counter = 0;

		while (runner != null) {
			if ((counter - k) > 0) {
				kthNode = kthNode.getNext();
			}
			counter++;
			runner = runner.getNext();
		}
		return counter <= k ? Integer.MIN_VALUE : kthNode.getVal();
	}

	public static int kthToNRecursive(LinkedListNode head, int k) {
		return kthToNRecursive(head, k, new int[] { 0 }, 1);
	}

	public static int kthToNRecursive(LinkedListNode head, int k, int[] lengthObj, int level) {
		if (head == null)
			return Integer.MIN_VALUE;

		lengthObj[0]++;
		int res = kthToNRecursive(head.getNext(), k, lengthObj, level + 1);

		if (level == (lengthObj[0] - k))
			return head.getVal();

		return res;
	}

	public static void main(String[] args) {
		System.out.println("=========== iterative ===========");
		LinkedListNode node = null;
		Test.equals(kthToN(node, 2), Integer.MIN_VALUE);

		node = new LinkedListNode(2);
		Test.equals(kthToN(node, 2), Integer.MIN_VALUE);
		Test.equals(kthToN(node, 1), Integer.MIN_VALUE);
		Test.equals(kthToN(node, 0), 2);

		node = new LinkedListNode(2, new LinkedListNode(3));
		Test.equals(kthToN(node, 1), 2);
		Test.equals(kthToN(node, 0), 3);
		Test.equals(kthToN(node, 7), Integer.MIN_VALUE);

		node = new LinkedListNode(2, new LinkedListNode(3, new LinkedListNode(65,
				new LinkedListNode(7, new LinkedListNode(23, new LinkedListNode(4))))));
		Test.equals(kthToN(node, 3), 65);

		node.getNext().getNext().getNext().getNext().getNext().setNext(new LinkedListNode(98, new LinkedListNode(52)));
		Test.equals(kthToN(node, 0), 52);
		Test.equals(kthToN(node, 100), Integer.MIN_VALUE);
		Test.equals(kthToN(node, 6), 3);
		Test.equals(kthToN(node, 5), 65);

		System.out.println("=========== recursive ===========");
		node = null;
		Test.equals(kthToNRecursive(node, 2), Integer.MIN_VALUE);

		node = new LinkedListNode(2);
		Test.equals(kthToNRecursive(node, 2), Integer.MIN_VALUE);
		Test.equals(kthToNRecursive(node, 1), Integer.MIN_VALUE);
		Test.equals(kthToNRecursive(node, 0), 2);

		node = new LinkedListNode(2, new LinkedListNode(3));
		Test.equals(kthToNRecursive(node, 1), 2);
		Test.equals(kthToNRecursive(node, 0), 3);
		Test.equals(kthToNRecursive(node, 7), Integer.MIN_VALUE);

		node = new LinkedListNode(2, new LinkedListNode(3, new LinkedListNode(65,
				new LinkedListNode(7, new LinkedListNode(23, new LinkedListNode(4))))));
		Test.equals(kthToNRecursive(node, 3), 65);

		node.getNext().getNext().getNext().getNext().getNext().setNext(new LinkedListNode(98, new LinkedListNode(52)));
		Test.equals(kthToNRecursive(node, 0), 52);
		Test.equals(kthToNRecursive(node, 100), Integer.MIN_VALUE);
		Test.equals(kthToNRecursive(node, 6), 3);
		Test.equals(kthToNRecursive(node, 5), 65);
	}

}
