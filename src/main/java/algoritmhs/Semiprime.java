package algoritmhs;

import utils.test.Test;

/**
 * Requirement
 * 
 * A Semiprime number is one that is a product of two prime numbers. Write a program that can determine whether a given number is semiprime or not.
 * 
 * INPUT int number
 * 
 * OUTPUT boolean isSemiprime
 * 
 * CONSTRAINTS 1 < number <= 10^8
 * 
 * EXAMPLE Input 91 Output true
 * 
 * @author giuseppe.adaldo
 *
 */
public class Semiprime {

	public static boolean run(int number) {
		if (number < 1 || number > 100_000_000)
			return false;

		return calculate(number);
	}

	private static boolean calculate(int number) {
		for (int i = 2; i <= number / 2; i++) {
			System.out.println("iterations: " + i);
			if (number % i == 0) { // only calculating prime if division with rest = 0
				int divisionRest = number / i;
				if (isPrime(divisionRest) && (divisionRest == i || isPrime(i))) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isPrime(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		int[] a = { 4, 14, 15, 21, 22, 25, 26, 33, 34, 35, 38, 39, 46, 49, 51, 55, 57, 58, 62, 65, 69, 74, 77, 82, 85, 86, 87, 91, 93, 94 };
		System.out.println("============ semiprimes ============");
		for (int e : a) {
			Test.equals(true, run(e));
		}
		System.out.println("========== non semiprimes ==========");
		int[] b = { 5, 16, 64, 80, 90, 54, 27, 31, 101, 23, 71, 96 };
		for (int e : b) {
			Test.equals(false, run(e));
		}
	}
}
