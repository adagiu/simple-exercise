package algorithms;

import utils.test.Test;

/**
 * Given a number n, print n-th Fibonacci Number.
 *
 */
public class FibonacciIterativeThreeNumbers {

	public static void main(String[] args) {
		Test.equals(fibonacci(1), 1);
		Test.equals(fibonacci(2), 2);
		Test.equals(fibonacci(3), 3);
		Test.equals(fibonacci(4), 6);
		Test.equals(fibonacci(5), 11);
		Test.equals(fibonacci(6), 20);
		Test.equals(fibonacci(8), 68);
		Test.equals(fibonacci(9), 125);

		System.out.println("------ recoursive ------");
		Test.equals(fibonacciRecoursive(1), 1);
		Test.equals(fibonacciRecoursive(2), 2);
		Test.equals(fibonacciRecoursive(3), 3);
		Test.equals(fibonacciRecoursive(4), 6);
		Test.equals(fibonacciRecoursive(5), 11);
		Test.equals(fibonacciRecoursive(6), 20);
		Test.equals(fibonacciRecoursive(8), 68);
		Test.equals(fibonacciRecoursive(9), 125);
	}

	public static int fibonacci(int n) {
		int a = 0, b = 1, c = 2;
		int fibonacciNumber = 0;
		if (n <= 3)
			return n;
		for (int i = 3; i <= n; i++) {
			fibonacciNumber = a + b + c;
			a = b;
			b = c;
			c = fibonacciNumber;
		}
		return c;
	}

	public static int fibonacciRecoursive(int n) {
		if (n <= 3)
			return n;
		return fibonacciRecoursive(n - 1) + fibonacciRecoursive(n - 2) + fibonacciRecoursive(n - 3);
	}

}
