package algorithms;

import utils.test.Test;

/**
 * Given a string with numbers, repeat the previous substring many times as indicated from numbers.
 * 
 * e.g. <br>
 * "" -> "" <br>
 * "a3b2c" -> "aaabbc" <br>
 * "abba2cc4 -> "abbaabbacccccccc" <br>
 * "elephant" -> "elephant"
 * 
 */
public class RepeatedStringLength {

	public static String repeatedStringLength(String s) {
		if (s.trim().isEmpty())
			return s;
		String prevString = "";
		String toReturn = "";
		for (char c : s.toCharArray()) {
			if (Character.isAlphabetic(c)) {
				prevString += Character.toString(c);
			}
			
			if (Character.isDigit(c)) {
				int repeatTimes = Character.getNumericValue(c);
				toReturn += concatNTimes(prevString.toString(), repeatTimes);
				prevString = "";
			}
		}
		if (!Character.isDigit(s.charAt(s.length() - 1))) {
			toReturn += prevString;
		}
		return toReturn;
	}

	private static String concatNTimes(String toConcat, int times) {
		String toReturn = "";
		for (int i = 0; i < times; i++) {
			toReturn += toConcat;
		}
		return toReturn;
	}

	public static void main(String[] args) {
		Test.equals(repeatedStringLength(""), "");
		Test.equals(repeatedStringLength("a3b2c"), "aaabbc");
		Test.equals(repeatedStringLength("abba2cc4"), "abbaabbacccccccc");
		Test.equals(repeatedStringLength("abba2cc0"), "abbaabba");
		Test.equals(repeatedStringLength("abba2cc0b5"), "abbaabbabbbbb");
		Test.equals(repeatedStringLength("elephant"), "elephant");
	}

}
