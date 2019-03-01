package algorithms;

import utils.test.Test;

/**
 * Given a number n, print n-th Fibonacci Number.
 *
 */
public class FibonacciIterative {

	public static void main(String[] args) {
		Test.equals(fibonacci(2), 1);
		Test.equals(fibonacci(1), 1);
		Test.equals(fibonacci(5), 5);
		Test.equals(fibonacci(9), 34);
	}

	public static int fibonacci(int n) {
		int a = 0, b = 1, c;
		if (n == 0)
			return a;
		for (int i = 2; i <= n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return b;
	}

}
