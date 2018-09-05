package algoritmhs;

import utils.test.Test;

/**
 * Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five integers. Then print
 * the respective minimum and maximum values as a single line of two space-separated long integers.
 * 
 * For example, . Our minimum sum is and our maximum sum is . We would print
 * 
 * 16 24
 * 
 * Function Description
 * 
 * Complete the miniMaxSum function in the editor below. It should print two space-separated integers on one line: the minimum sum and the maximum sum
 * of of elements.
 * 
 * miniMaxSum has the following parameter(s):
 * 
 * arr: an array of integers
 * 
 * Input Format
 * 
 * A single line of five space-separated integers.
 * 
 * Constraints
 * 
 * 1 <= arr[i] <= 10^9
 * 
 * Output Format
 * 
 * Print two space-separated long integers denoting the respective minimum and maximum values that can be calculated by summing exactly four of the
 * five integers. (The output can be greater than a 32 bit integer.)
 * 
 * Sample Input
 * 
 * 1 2 3 4 5
 * 
 * Sample Output
 * 
 * 10 14
 * 
 * @author giuseppe.adaldo
 *
 */
public class MiniMaxSum {

	static String miniMaxSum(int[] arr) {
		long min = arr[0], max = arr[0];
		long sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (arr[i] < min)
				min = arr[i];
			if (arr[i] > max)
				max = arr[i];
		}
		return (sum - max) + " " + (sum - min);
	}

	public static void main(String[] args) {
		Test.equals(miniMaxSum(new int[] { 1, 2, 3, 4, 5 }), "10 14");
		Test.equals(miniMaxSum(new int[] { 769082435, 210437958, 673982045, 375809214, 380564127 }), "1640793344 2199437821");
		Test.equals(miniMaxSum(new int[] { 396285104, 573261094, 759641832, 819230764, 364801279 }), "2093989309 2548418794");
		Test.equals(miniMaxSum(new int[] { 256741038, 623958417, 467905213, 714532089, 938071625 }), "2063136757 2744467344");
	}

}
