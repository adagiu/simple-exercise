package algorithms;

import utils.test.Test;

/**
 * Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x
 * and its coefficient.
 * 
 * If there is no solution for the equation, return "No solution".
 * 
 * If there are infinite solutions for the equation, return "Infinite solutions".
 * 
 * If there is exactly one solution for the equation, we ensure that the value of x is an integer.
 * 
 * 
 * Example 1:
 * 
 * Input: "x+5-3+x=6+x-2" Output: "x=2"
 * 
 * Example 2:
 * 
 * Input: "x=x" Output: "Infinite solutions"
 * 
 * Example 3:
 * 
 * Input: "2x=x" Output: "x=0"
 * 
 * Example 4:
 * 
 * Input: "2x+3x-6x=x+2" Output: "x=-1"
 * 
 * Example 5:
 * 
 * Input: "x=x+2" Output: "No solution"
 * 
 * @author giuseppe.adaldo
 *
 */
public class EquationSolver {

	private String solve(String equation) {
		if (equation == null || equation.trim().isEmpty())
			return "Invalid";

		int x = 0, y = 0;
		String sign = "";
		boolean left = true;

		for (int i = 0; i < equation.length(); i++) {
			if (equation.charAt(i) == '=')
				left = false;

			if (isX(equation, i)) {
				x += add(sign, "1", !left);
			} else {
				String number = "";
				while (equation.length() >= i + 1 && equation.charAt(i) != '-' && equation.charAt(i) != '+' && equation.charAt(i) != '=') {
					number += equation.charAt(i++);
				}
				if (!number.isEmpty()) {
					i--;
					if (number.contains("x")) {
						x += add(sign, number.replaceAll("x", ""), !left);
					} else {
						y += add(sign, number, left);
					}
				}
			}
			sign = "";
			if (i < equation.length() && (equation.charAt(i) == '-' || equation.charAt(i) == '+'))
				sign = "" + equation.charAt(i);
		}
		if (x == 0 && y == 0)
			return "Infinite solutions";
		if (x == 0 && y != 0)
			return "No solution";
		return "x=" + (y / x);
	}

	private int add(String sign, String number, boolean left) {
		if (sign.equals("-"))
			return (-1) * getCorrectInt(number, left);
		else
			return getCorrectInt(number, left);
	}

	private boolean isX(String equation, int i) {
		return equation.charAt(i) == 'x';
	}

	private static int getCorrectInt(String s, boolean left) {
		return left ? -1 * Integer.parseInt(s) : Integer.parseInt(s);
	}

	public static void main(String[] args) {
		EquationSolver solver = new EquationSolver();
		Test.equals(solver.solve(""), "Invalid");
		Test.equals(solver.solve(""), "Invalid");
		Test.equals(solver.solve(" "), "Invalid");
		Test.equals(solver.solve(null), "Invalid");
		Test.equals(solver.solve("x+4=8-x"), "x=2");
		Test.equals(solver.solve("-x+x+6+4=8-x"), "x=-2");
		Test.equals(solver.solve("x+5-3+x=6+x-2"), "x=2");
		Test.equals(solver.solve("x=x"), "Infinite solutions");
		Test.equals(solver.solve("x=x+2"), "No solution");
		Test.equals(solver.solve("2x+3x-6x=x+2"), "x=-1");
		Test.equals(solver.solve("3x=33+22+11"), "x=22");
		Test.equals(solver.solve("x=100"), "x=100");
		Test.equals(solver.solve("-12x+2x=-100"), "x=10");
	}

}
