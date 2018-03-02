package algoritmhs;

import utils.test.Test;

public class EquationSolver {

	public String solve(String equation) {
		if (equation == null || equation.trim().isEmpty())
			return "Invalid";

		int x = 0, vars = 0;
		String sign = "";
		boolean left = true;

		for (int i = 0; i < equation.length(); i++) {
			if (equation.charAt(i) == '=')
				left = false;

			if (equation.charAt(i) == 'x') {
				if (sign.equals("-"))
					x -= getCorrectInt('1', !left);
				else
					x += getCorrectInt('1', !left);
				sign = "";
			}
			if (Character.isDigit(equation.charAt(i))) {
				if (sign.equals("-"))
					vars -= getCorrectInt(equation.charAt(i), left);
				else
					vars += getCorrectInt(equation.charAt(i), left);
				sign = "";
			}
			if (equation.charAt(i) == '-' || equation.charAt(i) == '+')
				sign = "" + equation.charAt(i);
		}
		if (x == 0 && vars == 0)
			return "Infinite solutions";
		if (x == 0 && vars != 0)
			return "No solution";
		return "x=" + (vars / x);
	}

	private static int getCorrectInt(char s, boolean left) {
		return left ? -1 * Integer.valueOf("" + s) : Integer.valueOf("" + s);
	}

	public static void main(String[] args) {
		EquationSolver solver = new EquationSolver();
		Test.equals(solver.solve(""), "Invalid");
		Test.equals(solver.solve(""), "Invalid");
		Test.equals(solver.solve("   "), "Invalid");
		Test.equals(solver.solve(null), "Invalid");
		Test.equals(solver.solve("x+4=8-x"), "x=2");
		Test.equals(solver.solve("-x+x+6+4=8-x"), "x=-2");
		Test.equals(solver.solve("x+5-3+x=6+x-2"), "x=2");
		Test.equals(solver.solve("x=x"), "Infinite solutions");
		Test.equals(solver.solve("x=x+2"), "No solution");
		Test.equals(solver.solve("2x+3x-6x=x+2"), "x=-1");
	}

}
