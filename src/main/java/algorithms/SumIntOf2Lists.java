package algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * given two non null linked lists representing 2 non negative numbers. The most significat digit comes first, and heach of their nodes contain 1
 * single digit. add the two numbers and return as linked lists
 */
public class SumIntOf2Lists {
	public static List<Integer> sumLinkedList(LinkedList<Integer> l1, LinkedList<Integer> l2) {
		ListIterator<Integer> i1 = l1.listIterator(l1.size());
		ListIterator<Integer> i2 = l2.listIterator(l2.size());

		LinkedList<Integer> result = new LinkedList<>();

		int carry = 0;

		while (i1.hasPrevious() && i2.hasPrevious()) {
			int sum = i1.previous() + i2.previous() + carry;
			result.addFirst(sum % 10);
			carry = sum / 10;
		}

		if (l1.size() == l2.size() && carry > 0) {
			result.addFirst(carry);
		} else if (i1.hasPrevious()) {
			sumRemaining(result, i1, carry);
		} else {
			sumRemaining(result, i2, carry);
		}

		return result;
	}

	private static void sumRemaining(LinkedList<Integer> stack, ListIterator<Integer> i, int carry) {
		while (i.hasPrevious()) {
			int sum = i.previous() + carry;
			stack.addFirst(sum % 10);
			carry = sum / 10;
		}

		if (carry > 0) {
			stack.addFirst(carry);
		}
	}

	public static void main(String[] args) {
		LinkedList<Integer> l1 = new LinkedList<>(Arrays.asList(new Integer[] { 1, 4, 9, 3, 5, 6, 2, 4, 5, 6, 2 }));
		LinkedList<Integer> l2 = new LinkedList<>(Arrays.asList(new Integer[] { 9, 0, 0, 8, 5 }));
		List<Integer> expected = Arrays.asList(new Integer[] { 1, 4, 9, 3, 5, 7, 1, 4, 6, 4, 7 });
		System.out.println(sumLinkedList(l1, l2).equals(expected));
		System.out.println(sumLinkedList(l1, l2));

		l1 = new LinkedList<>(Arrays.asList(new Integer[] { 9, 9, 9, 9, 9, 9, 9 }));
		l2 = new LinkedList<>(Arrays.asList(new Integer[] { 9, 9, 9, 9, 9, 9, 9 }));
		expected = Arrays.asList(new Integer[] { 1, 9, 9, 9, 9, 9, 9, 8 });
		System.out.println(sumLinkedList(l1, l2).equals(expected));
		System.out.println(sumLinkedList(l1, l2));

		l1 = new LinkedList<>(Arrays.asList(new Integer[] { 9, 9, 9, 9, 9, 9, 9 }));
		l2 = new LinkedList<>(Arrays.asList(new Integer[] { 9, 9, 9, 9, 9, 9, 9, 9 }));
		expected = Arrays.asList(new Integer[] { 1, 0, 9, 9, 9, 9, 9, 9, 8 });
		System.out.println(sumLinkedList(l1, l2).equals(expected));
		System.out.println(sumLinkedList(l1, l2));
	}

}
