package algorithms;

import java.util.LinkedHashSet;
import java.util.Set;

import utils.test.Test;

/**
 * Find the first (lowest) common ancestor of two nodes in a binary tree without allocating any tree data structure in memory. The first common
 * ancestor of two nodes v and w is the lowest (i.e. deepest) node that has both v and w as descendants. Consider the binary tree's indexes starting
 * from 1 in the root, increasing from the leftmost node to the right at each level. (standard tree node indexing from left to right)
 * 
 * INPUT int index1, int index2
 * 
 * OUTPUT int indexCommonAncestor
 * 
 * CONSTRAINTS 1 <= index1 <= 1000000 1 <= index2 <= 1000000
 * 
 * EXAMPLES:
 * 
 * Input 10, 9 Output 2
 * 
 * Input 11, 13 Output 1
 * 
 * Input 13, 15 Output 3
 * 
 * Input 10, 11 Output 5
 * 
 * Input 25, 100 Output 12
 * 
 * Input 11000, 11103 Output 21
 * 
 * @author giuseppe.adaldo
 *
 */
public class CommonAnchestor {

	private static int run(int index1, int index2) {
		if (index1 <= 1 || index1 >= 1000000 || index2 <= 1 || index2 >= 1000000)
			return -1;

		if (index1 > index2) {
			index1 = swap(index2, index2 = index1);
		}

		final Set<Integer> anchA = calculateAnchestor(index1);

		return getMaxCommonAnchestorOf(index2 / 2, anchA);
	}

	private static int getMaxCommonAnchestorOf(int n, Set<Integer> anchB) {
		if (anchB.contains(n))
			return n;

		return getMaxCommonAnchestorOf(n / 2, anchB);
	}

	private static Set<Integer> calculateAnchestor(int n) {
		final Set<Integer> anch = new LinkedHashSet<>();
		while (n > 1) {
			anch.add(n = n / 2);
		}
		return anch;
	}

	private static int swap(int a, int b) {
		return a;
	}

	public static void main(String[] args) {
		Test.equals(run(10, 9), 2);
		Test.equals(run(11, 13), 1);
		Test.equals(run(13, 15), 3);
		Test.equals(run(10, 11), 5);
		Test.equals(run(25, 100), 12);
		Test.equals(run(11000, 11103), 21);
	}

}
