package algoritmhs;

import utils.test.Test;

/**
 * Given a set of distinct integers, print the size of a maximal subset of S where the sum of any 2 numbers in S1 is not evenly divisible by k.
 * 
 * For example, the array and S = [19 10 12 10 24 25 22 and k = 4] . One of the arrays that can be created is S1[0] = [10 12 25]. Another is S1[1] =
 * [19 22 24]. After testing all permutations, the maximum length solution array has 3 elements.
 * 
 * Function Description
 * 
 * Complete the nonDivisibleSubset function in the editor below. It should return an integer representing the length of the longest subset of meeting
 * the criteria.
 * 
 * nonDivisibleSubset has the following parameter(s):
 * 
 * S: an array of integers k: an integer k: an integer
 * 
 * 
 * @author giuseppe.adaldo
 *
 */
public class NonDivisibleSubset {

	static int nonDivisibleSubset(int k, int[] S) {
		int remainders[] = new int[k];

		for (int i = 0; i < S.length; i++) {
			remainders[S[i] % k]++;
		}

		for (int s : remainders)
			System.out.print(s);

		int result = 0;
		if (k % 2 == 0)
			result++; // it will not have any conjugate pair
		result = result + Math.min(remainders[0], 1);// If no number wholly divisible dont add it to pair else add once
		for (int j = 1; j <= k / 2; j++)
			if (j != k - j)
				result += Math.max(remainders[j], remainders[k - j]);
		return result;
	}

	public static void main(String[] args) {
		Test.equals(nonDivisibleSubset(3, new int[] { 1, 7, 2, 4 }), 3);
		Test.equals(nonDivisibleSubset(4, new int[] { 19, 10, 12, 10, 24, 25, 22 }), 3);
		Test.equals(nonDivisibleSubset(1, new int[] { 1, 2, 3, 4, 5 }), 1);
		Test.equals(
				nonDivisibleSubset(5,
						new int[] { 770528134, 663501748, 384261537, 800309024, 103668401, 538539662, 385488901, 101262949, 557792122, 46058493 }),
				6);
	}

}
