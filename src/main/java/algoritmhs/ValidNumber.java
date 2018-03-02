package algoritmhs;

public class ValidNumber {

	public boolean isNumber(String s) {
		if (s == null || s.trim().length() == 0)
			return false;

		s = s.trim();

		if (!Character.isDigit(s.charAt(0)) && s.charAt(0) != '+' && s.charAt(0) != '-' && s.charAt(0) != '.')
			return false;

		if (s.charAt(0) == '.' && s.length() == 1)
			return false;

		boolean dotFlag = false;
		boolean eFlag = false;

		// System.out.println(s.charAt(0));
		for (int i = 1; i < s.length(); i++) {
			// System.out.println(s.charAt(i));
			if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != 'e' && s.charAt(i) != '.') {
				return false;
			}

			else if (s.charAt(i) == '.') {
				if (dotFlag || eFlag)
					return false;
				else {
					dotFlag = true;

					// If '.' is the last character.
					if (i + 1 >= s.length())
						return false;

					// if '.' is not followed by a digit.
					if (!Character.isDigit(s.charAt(i + 1)))
						return false;
				}
			}

			else if (s.charAt(i) == 'e') {
				if (eFlag)
					return false;
				else {
					eFlag = true;

					// if there is no digit before 'e'.
					if (!Character.isDigit(s.charAt(i - 1)))
						return false;

					// If 'e' is the last Character
					if (i + 1 >= s.length())
						return false;

					// if e is not followed either by '.',
					// '+', '-' or a digit
					if (!Character.isDigit(s.charAt(i + 1)) &&
							s.charAt(i + 1) != '+' &&
							s.charAt(i + 1) != '-')
						return false;
				}
			}

		}
		return true;
	}

	public static void main(String[] args) {
		ValidNumber vn = new ValidNumber();
		System.out.println(vn.isNumber("3") + " -> 3");
		System.out.println(vn.isNumber(" 0.1") + " -> 0.1");
		System.out.println(vn.isNumber("abc") + " -> abc");
		System.out.println(vn.isNumber("1 a") + " -> 1 a");
		System.out.println(vn.isNumber("k 1") + " -> k 1");
		System.out.println(vn.isNumber("2e10") + " -> 2e10");
		System.out.println(vn.isNumber("2e10.9") + " -> 2e10.9");
		System.out.println(vn.isNumber("2e10e.9") + " -> 2e10e9");
		System.out.println(vn.isNumber("+2e10") + " -> +2e10");
		System.out.println(vn.isNumber("-2e1.9") + " -> -2e1.9");
		System.out.println(vn.isNumber(".") + " -> .");
		System.out.println(vn.isNumber("e") + " -> e");
		System.out.println(vn.isNumber("3.") + " -> 3.");
	}

}
