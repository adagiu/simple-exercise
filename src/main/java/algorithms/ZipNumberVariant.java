package algorithms;

import utils.test.Test;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Given two int value a and b, create a solution that returns a number c which is done by the digits of the two given number as following:
 * <p>
 * c = a[1], b[1], a[2], b[2] ... a[n], b[m]
 * <p>
 * Considering that the two numbers can have different length.
 * <p>
 * The solution must returns -1 is the number is > 100000000.
 * <p>
 * <b>this solution doesn't use String</b>
 *
 * @author gadaldo
 */
public class ZipNumberVariant {

	public int solution(int numberA, int numberB) {
		if (numberA == 0 && numberB == 0)
			return 0;

		long number = 0;

		numberA = Math.abs(numberA) % Integer.MIN_VALUE;
		numberB = Math.abs(numberB) % Integer.MIN_VALUE;

		int aLength = getLength(numberA);
		int bLength = getLength(numberB);

		double a = getDecimalValue(numberA);
		double b = getDecimalValue(numberB);

		while (a != 0 || b != 0) {
			if (number >= 100000000)
				return -1;
			if (a != 0) {
				int digit = (int) a;
				a = subWithPrecision(a, digit, aLength--);
				number = (int) (number * 10) + digit;
			}

			if (number >= 100000000)
				return -1;

			if (b != 0) {
				int digit = (int) b;
				b = subWithPrecision(b, digit, bLength--);
				number = (int) (number * 10) + digit;
			}
		}
		return number >= 100000000 ? -1 : new Long(number).intValue();
	}

	private double subWithPrecision(double a, double b, int precision) {
		if (precision > 0)
			return new BigDecimal(a).subtract(new BigDecimal(b), new MathContext(precision)).doubleValue() * 10;
		return new BigDecimal(a).subtract(new BigDecimal(b)).doubleValue() * 10;
	}

	private double getDecimalValue(int n) {
		if (n == 0)
			return n;

		int length = getLength(n);

		return (n / (Math.pow(10, length)));
	}

	private int getLength(int n) {
		return n > 0 ? (int) Math.log10(n) : 0;
	}

	public static void main(String[] args) {
		ZipNumberVariant zipNumber = new ZipNumberVariant();
		Test.equals(zipNumber.solution(0, 0), 0);
		Test.equals(zipNumber.solution(123, 56), 15263);
		Test.equals(zipNumber.solution(12398, 34598), -1);
		Test.equals(zipNumber.solution(432, 876), 483726);
		Test.equals(zipNumber.solution(43234, 22), 4232234);
		Test.equals(zipNumber.solution(-43234, -22), 4232234);
		Test.equals(zipNumber.solution(42, 876057), 48276057);
		Test.equals(zipNumber.solution(42, -876057), 48276057);
		Test.equals(zipNumber.solution(0101, 000), 65);
		Test.equals(zipNumber.solution(Integer.MAX_VALUE, 0), -1);
		Test.equals(zipNumber.solution(0, Integer.MAX_VALUE), -1);
		Test.equals(zipNumber.solution(0, Integer.MIN_VALUE), 0);
	}
}
