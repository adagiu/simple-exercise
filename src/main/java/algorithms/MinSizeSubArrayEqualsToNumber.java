package algorithms;

import utils.test.Test;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum = s. If there
 * isn't one, return 0 instead.
 * 
 * Example:
 * 
 * Input: s = 7, nums = [2,3,1,2,4,3] Output: 2 Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * 
 * Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 * 
 */
public class MinSizeSubArrayEqualsToNumber {

	/**
	 * O(n X n) complexity.
	 * 
	 * @param s
	 * @param a
	 * @return
	 */
	public static int minSubArrayLen(int s, int[] a) {
		int minimumLen = a.length + 1;
		for (int i = 0; i < a.length; i++) {
			int sum = 0;
			int right = i;
			while (sum < s && right < a.length) {
				sum += a[right++];
			}
			if (sum == s)
				minimumLen = Math.min(minimumLen, right - i);
		}
		return minimumLen >= a.length + 1 ? 0 : minimumLen;
	}

	/**
	 * 2 pointers solution, like windowing the solution into the array
	 * 
	 * @param s
	 * @param nums
	 * @return
	 */
	public static int minSubArrayLen3(int s, int[] nums) {
		if (nums == null || nums.length == 0 || s == 0)
			return 0;

		int len = nums.length;
		int minSize = len + 1;
		int left = 0, right = 0, sum = 0;

		while (left < len) {
			while (sum < s && right < len) {
				sum += nums[right++];
			}
			if (sum == s) {
				minSize = Math.min(minSize, right - left);
			}
			sum -= nums[left++];
		}
		return minSize == len + 1 ? 0 : minSize;
	}

	public static void main(String[] args) {
		Test.equals(minSubArrayLen(7, new int[] { 2, 3, 1, 2, 4, 3 }), 2);
		Test.equals(minSubArrayLen(7, new int[] { 2, 3, 1, 2, 4, 5 }), 3);
		Test.equals(minSubArrayLen(7, new int[] { 2, 3, 2, 2, 4, 5 }), 3);
		Test.equals(minSubArrayLen(7, new int[] { 2, 3, 5, 2, 4, 5 }), 2);
		Test.equals(minSubArrayLen(7, new int[] { 2, 3, 6, 2, 4, 5 }), 0);
		Test.equals(minSubArrayLen(7, new int[] { 2, 3, 7, 2, 4, 5 }), 1);

		Test.equals(minSubArrayLen3(7, new int[] { 2, 3, 1, 2, 4, 3 }), 2);
		Test.equals(minSubArrayLen3(7, new int[] { 2, 3, 1, 2, 4, 5 }), 3);
		Test.equals(minSubArrayLen3(7, new int[] { 2, 3, 2, 2, 4, 5 }), 3);
		Test.equals(minSubArrayLen3(7, new int[] { 2, 3, 5, 2, 4, 5 }), 2);
		Test.equals(minSubArrayLen3(7, new int[] { 2, 3, 6, 2, 4, 5 }), 0);
		Test.equals(minSubArrayLen3(7, new int[] { 2, 3, 7, 2, 4, 5 }), 1);
	}

}
