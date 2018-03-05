package algoritmhs;

import utils.test.Test;

/*
 * You have two strings representing large ints that cannot be parsed into ints. How do you find their product?
 * 
 *  1. The length of both num1 and num2 is < 110.
 *  2. Both num1 and num2 contains only digits 0-9.
 *  3. Both num1 and num2 does not contain any leading zero.
 *  4. You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class LargeNumberProduct {

	public String solution(String num1, String num2) {
		if (num1 == null || num2 == null || num1.trim().isEmpty() || num2.trim().isEmpty())
			return "Invalid";

		if (num1.equals("0") || num2.equals("0"))
			return "0";

		num1 = new StringBuilder(num1).reverse().toString();
		num2 = new StringBuilder(num2).reverse().toString();

		int[][] partial = new int[2 * num1.length()][num2.length() * num1.length() + 1];

		for (int i = 0, offset = 0; i < num1.length(); i++, offset++) {
			int rest = 0;
			for (int k = 0; k < num2.length(); k++) {
				int prod = getInt(num1, i) * getInt(num2, k);
				int res = (prod + rest) % 10;
				partial[i][k + offset] = res;
				rest = (prod + rest) / 10;
			}
			if (rest > 0)
				partial[i][offset + num2.length()] = rest;
		}

		String sum = "";
		int rest = 0;
		for (int k = 0; k < partial[0].length; k++) {
			int partialSum = 0;
			for (int i = 0; i < partial.length; i++) {
				partialSum += partial[i][k];
			}
			sum += (partialSum + rest) % 10;
			rest = (partialSum + rest) / 10;
		}
		if (rest > 0)
			sum += rest;

		return new StringBuilder(sum).reverse().toString().replaceFirst("^0+(?!$)", "");
	}

	private static int getInt(String s, int i) {
		return Integer.valueOf("" + s.charAt(i));
	}

	public static void main(String[] args) {
		LargeNumberProduct l = new LargeNumberProduct();
		Test.equals(l.solution("9133", "0"), "0");
		Test.equals(l.solution("408", "5"), "2040");
		Test.equals(l.solution("9", "9"), "81");
		Test.equals(l.solution("999", "999"), "998001");
		Test.equals(l.solution("222", "222"), "49284");
		Test.equals(l.solution("987348734687462", "23984987238757"), "23681546801681646980768364734");
		Test.equals(l.solution("76240047126666041961133788375854678695507175099108423713540245804984464373874905749210597511224714",
				"50508987390094918079940272645150122130992191514531426745139766970214425636907460704571014980779812464"),
				"3850807578941017405043936428334617006206578681059793185019338920318752709804789931220097477302896139962461703898399750453490343458217019930720602316843466382402743174163712702358367940356131882035296");
	}

}
