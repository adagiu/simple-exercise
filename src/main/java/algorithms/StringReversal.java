package algorithms;

import utils.test.Test;

public class StringReversal {

	private static String reverseString(String s) {
		char[] sArr = s.toCharArray();
		int r = s.length() - 1;
		int l = 0;

		while (l <= r)
			swap(sArr, l++, r--);

		return new String(sArr);
	}

	private static char[] swap(char[] c, int i, int j) {
		char tmp = c[i];
		c[i] = c[j];
		c[j] = tmp;

		return c;
	}

	public static void main(String[] args) {
		Test.equals(reverseString("hello"), "olleh");
		Test.equals(reverseString(""), "");
		Test.equals(reverseString("I am surpised, don't know why"), "yhw wonk t'nod ,desiprus ma I");
	}

}
