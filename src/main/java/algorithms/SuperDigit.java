package algorithms;

import utils.test.Test;

/**
 * We define super digit of an integer x using the following rules:
 * 
 * Given an integer, we need to find the super digit of the integer.
 * 
 * If x has only 1 digit, then its super digit is x.
 * 
 * Otherwise, the super digit of x is equal to the super digit of the sum of the digits of x.
 * 
 * For example, the super digit of 9875 will be calculated as:
 * 
 * super_digit(9875) 9+8+7+5 = 29 <br>
 * super_digit(29) 2 + 9 = 11 <br>
 * super_digit(11) 1 + 1 = 2 <br>
 * super_digit(2) = 2 <br>
 * 
 * superDigit has the following parameter(s):
 * 
 * n: a string representation of an integer <br>
 * k: an integer, the times to concatenate n to make p
 * 
 * return the superdigit of given input
 * 
 * @author giuseppe.adaldo
 *
 */
public class SuperDigit {

	private static int superDigit(String n, int k) {
		return calculate(String.valueOf(calculate(n) * k));
	}

	private static int calculate(String s) {
		if (s.length() == 1)
			return Integer.valueOf(s);

		return calculate(String.valueOf(Integer.valueOf("" + s.charAt(0)) + calculate(s.substring(1, s.length()))));
	}

	public static void main(String[] args) {
		Test.equals(superDigit("148", 3), 3);
		Test.equals(superDigit("9875", 4), 8);
		Test.equals(superDigit("3546630947312051453014172159647935984478824945973141333062252613718025688716704470547449723886626736", 100000), 5);
	}

}
