package algorithms;

import java.text.DecimalFormat;

import utils.test.Test;

/**
 * Given an array of integers, calculate the fractions of its elements that are positive, negative, and are zeros. Print the decimal value of each
 * fraction on a new line.
 * 
 * Note: This challenge introduces precision problems. The test cases are scaled to six decimal places, though answers with absolute error of up to
 * are acceptable.
 * 
 * For example, given the array there are elements, two positive, two negative and one zero. Their ratios would be , and . It should be printed as
 * 
 * 0.400000 0.400000 0.200000
 * 
 * Function Description
 * 
 * Complete the plusMinus function in the editor below. It should print out the ratio of positive, negative and zero items in the array, each on a
 * separate line rounded to six decimals.
 * 
 * plusMinus has the following parameter(s):
 * 
 * arr: an array of integers
 * 
 * Input Format
 * 
 * The first line contains an integer, , denoting the size of the array. The second line contains space-separated integers describing an array of
 * numbers .
 * 
 * Constraints
 * 
 * 0 < n < 100 -100 <= a[i] <= 100
 * 
 * 
 * Output Format
 * 
 * You must print the following lines:
 * 
 * A decimal representing of the fraction of positive numbers in the array compared to its size. A decimal representing of the fraction of negative
 * numbers in the array compared to its size. A decimal representing of the fraction of zeros in the array compared to its size.
 * 
 * Sample Input
 * 
 * 6 -4 3 -9 0 4 1
 * 
 * Sample Output
 * 
 * 0.500000 0.333333 0.166667
 * 
 * Explanation
 * 
 * There are 3 positive numbers, 2 negative numbers, 1 and zero in the array. The proportions of occurrence are positive: , negative: and zeros:
 * 
 * 3/6 = 0.500000 2/6 = 0.333333 and 1/6 = .0166667
 * 
 * @author giuseppe.adaldo
 *
 */
public class PlusMinus {

	private static final DecimalFormat decimalFormat = new DecimalFormat("0.000000");

	static String plusMinus(int[] arr) {
		int count0 = 0, countPos = 0, countNeg = 0;

		for (int a : arr) {
			if (a == 0)
				count0++;
			else if (a < 0)
				countNeg++;
			else if (a > 0)
				countPos++;
		}

		return printFraction(countPos, arr.length) + " " +
				printFraction(countNeg, arr.length) + " " +
				printFraction(count0, arr.length);
	}

	static String printFraction(int a, int b) {
		return decimalFormat.format((double) a / (double) b);
	}

	public static void main(String[] args) {
		Test.equals("0.500000 0.333333 0.166667", plusMinus(new int[] { -4, 3, -9, 0, 4, 1 }));
		Test.equals("0.400000 0.400000 0.200000", plusMinus(new int[] { 0, 4, -3, 3, -6 }));
		Test.equals("0.428571 0.571429 0.000000", plusMinus(new int[] { 1, -2, -7, 9, 1, -8, -5 }));
		Test.equals("0.500000 0.333333 0.166667", plusMinus(new int[] { -4, 3, -9, 0, 4, 1 }));
		Test.equals("1.000000 0.000000 0.000000", plusMinus(new int[] { 4, 3, 9, 10, 124, 1 }));
		Test.equals("0.000000 0.835616 0.164384",
				plusMinus(new int[] { 0, -67, -74, -38, -72, -53, 0, -13, -95, -91, -100, -59, 0, -10, -8, -68, -71, -62, -21, 0, -42, -57,
						-16, -66, -23, 0, -80, -63, -68, -65, -71, 0, -71, -15, -32, -26, -8, 0, -6, -51, -87, -19, -71, 0, -93, -26, -35, -56, -89,
						0, -21, -74, -39, -57, -8, 0, -69, -29, -24, -99, -53, 0, -65, -42, -72, -18, -4, 0, -73, -46, -63, -78, -87 }));
	}

}
