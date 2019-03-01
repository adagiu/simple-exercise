package algorithms;

import utils.test.Test;

/**
 * Given a number n, print n-th Fibonacci Number.
 *
 */
public class FibonacciRecoursive {

	public static void main(String[] args) {
		Test.equals(fibonacci(2), 1);
		Test.equals(fibonacci(1), 1);
		Test.equals(fibonacci(5), 5);
		Test.equals(fibonacci(9), 34);
	}

	public static int fibonacci(int n) {
		if (n <= 1)
			return n;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

}
