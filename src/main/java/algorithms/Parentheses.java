package algorithms;

import java.util.HashSet;
import java.util.Set;

import utils.test.Test;

/**
 * Given an integer, write a function that returns all valid combination of pairs of parentheses.
 * 
 */
public class Parentheses {

	public static String parens(int n) {
		return parens(n, new HashSet<>());
	}

	public static String parens(int n, Set<String> set) {
		if (n <= 0)
			return "";

		if (n == 1) {
			set.add("()");
			return "()";
		}

		String prevParens = parens(n - 1, set);
		String toReturnParens = "";
		for (String s : prevParens.split(" ")) {
			if (!set.contains("(" + s + ")")) {
				set.add("(" + s + ")");
				toReturnParens += "(" + s + ") ";
			}
			if (!set.contains("()" + s)) {
				set.add("()" + s);
				toReturnParens += "()" + s + " ";
			}
			if (!set.contains(s + "()")) {
				set.add(s + "()");
				toReturnParens += s + "() ";
			}
		}
		return toReturnParens.trim();
	}

	public static void main(String[] args) {
		Test.equals(parens(0), "");
		Test.equals(parens(1), "()");
		Test.equals(parens(2), "(()) ()()");
		Test.equals(parens(3), "((())) ()(()) (())() (()()) ()()()");
		Test.equals(parens(4),
				"(((()))) ()((())) ((()))() (()(())) ()()(()) ()(())() ((())()) (())()() ((()())) ()(()()) (()())() (()()()) ()()()()");
	}

}
