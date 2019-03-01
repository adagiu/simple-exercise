package algorithms;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * given an array of non negative integers, return the next integer. </br>
 * i.g [1,2,3] -> [1,2,4]
 *
 */
public class NextNumber {

	public static int[] getNextNumber(int[] a) {
		int tmp = 0;
		int carry = 0;
		int n = a.length - 1;
		do {
			tmp = a[n] + 1;
			if (tmp >= 10) {
				carry = 1;
				tmp = 0;
			} else
				carry = 0;
			a[n--] = tmp;
		} while (n >= 0 && carry > 0);

		return carry > 0 ? IntStream.concat(Arrays.stream(new int[] { carry }), Arrays.stream(a)).toArray() : a;
	}

	public static void main(String[] args) {
		int[] expected = new int[] { 1, 2, 4 };
		int[] result = getNextNumber(new int[] { 1, 2, 3 });
		System.out.println(Arrays.equals(result, expected));

		result = getNextNumber(new int[] { 9, 9, 9, 9 });
		expected = new int[] { 1, 0, 0, 0, 0 };
		System.out.printf("%s, array=%s %n", Arrays.equals(result, expected), Arrays.toString(result));

		result = getNextNumber(new int[] { 9, 8, 9, 9 });
		expected = new int[] { 9, 9, 0, 0 };
		System.out.printf("%s, array=%s%n", Arrays.equals(result, expected), Arrays.toString(result));
	}

}
