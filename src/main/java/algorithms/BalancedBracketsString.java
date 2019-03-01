package algorithms;

import java.util.Stack;

import utils.test.Test;

public class BalancedBracketsString {

	public static boolean isBalanced(String expression) {
		Stack<Character> stack = new Stack<>();

		for (char c : expression.toCharArray()) {
			if (isOpenBracket(c)) {
				stack.push(c);
			} else {
				if (stack.isEmpty())
					return false;
				char lastBracket = stack.peek();
				if (isMatchingPair(c, lastBracket)) {
					stack.pop();
				}
			}
		}

		return stack.isEmpty();
	}

	private static boolean isOpenBracket(char c) {
		return c == '(' || c == '[' || c == '{';
	}

	private static boolean isMatchingPair(char current, char inserted) {
		if (current == ')' && inserted == '(')
			return true;
		if (current == ']' && inserted == '[')
			return true;
		if (current == '}' && inserted == '{')
			return true;
		return false;
	}

	public static void main(String[] args) {
		Test.equals(isBalanced(""), true);
		Test.equals(isBalanced("()"), true);
		Test.equals(isBalanced("[]"), true);
		Test.equals(isBalanced("{}"), true);
		Test.equals(isBalanced("{ [ 4 (a + b) ] + [ 7 - b (a) ] }"), true);
		Test.equals(isBalanced("{ [ 4 (a + b ] + [ 7 - b (a) ] }"), false);
		Test.equals(isBalanced("{ [ 4 (a + b) ] + [ 7 - b (a) ] } }"), false);
		Test.equals(isBalanced("[]][[] "), false);
		Test.equals(isBalanced("[[][]]  "), false);
	}

}
