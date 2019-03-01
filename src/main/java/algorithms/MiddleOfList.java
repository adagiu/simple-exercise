package algorithms;

import java.util.List;

import utils.test.Test;

/**
 * Locate the middle node in a single-linked list with one pass through the data.
 *
 */
public class MiddleOfList {

	@SafeVarargs
	private static <T> LinkedListNode<T> buildList(T... args) {
		LinkedListNode<T> head = push(null, args.length - 1, args);

		// printList(head);

		return head;
	}

	@SafeVarargs
	private static <T> LinkedListNode<T> push(LinkedListNode<T> previous, int i, T... args) {
		if (previous == null) {
			previous = new LinkedListNode<T>(args[i], null);
			return push(previous, --i, args);
		}

		if (i < 0)
			return previous;

		LinkedListNode<T> currentNode = new LinkedListNode<T>(args[i], previous);
		return push(currentNode, --i, args);
	}

	private static <T> T findMiddle(LinkedListNode<T> head) {
		LinkedListNode<T> middle = head;
		int length = 0;

		while (head.next != null) {
			length++;
			if ((length & 1) == 1) { // update mid, when 'length' is odd number. So the mid will move only half of the total length of the list
				middle = middle.next;
			}
			head = head.next;
		}
		return middle.data;
	}

	private static <T> T findMiddle2(LinkedListNode<T> head) {
		LinkedListNode<T> fastNode = head;
		LinkedListNode<T> middle = head;

		while (fastNode != null && fastNode.next != null) { // fast node traverse the list faster then 
			fastNode = fastNode.next.next;
			middle = middle.next;
		}
		return middle.data;
	}

	private static <T> void printList(LinkedListNode<T> l) {
		if (l != null) {
			System.out.println(l.data);
			printList(l.next);
		}
	}

	private static class LinkedListNode<T> {
		public T data = null;
		public LinkedListNode<T> next = null;

		public LinkedListNode(T data, LinkedListNode<T> next) {
			this.data = data;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		// test method 1
		Test.equals(findMiddle(buildList(1, 2, 3)), 2);
		Test.equals(findMiddle(buildList(1, 2, 3, 4, 5, 6, 7)), 4);
		Test.equals(findMiddle(buildList(1, 2, 3, 4, 5, 6)), 4);
		Test.equals(findMiddle(buildList("")), "");
		Test.equals(findMiddle(buildList("data1")), "data1");
		Test.equals(findMiddle(buildList("data1", "data2")), "data2");
		Test.equals(findMiddle(buildList("data1", "data2", "data3", "data4", "data5")), "data3");

		// test method 2
		System.out.println("======================================");
		Test.equals(findMiddle2(buildList(1, 2, 3)), 2);
		Test.equals(findMiddle2(buildList(1, 2, 3, 4, 5, 6, 7)), 4);
		Test.equals(findMiddle2(buildList(1, 2, 3, 4, 5, 6)), 4);
		Test.equals(findMiddle2(buildList("")), "");
		Test.equals(findMiddle2(buildList("data1")), "data1");
		Test.equals(findMiddle2(buildList("data1", "data2")), "data2");
		Test.equals(findMiddle2(buildList("data1", "data2", "data3", "data4", "data5")), "data3");
	}

}
