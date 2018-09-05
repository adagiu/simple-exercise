package algoritmhs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import utils.test.Test;

/**
 * Find the Common Manager
 * 
 * You are given as standard input the number of employees in a company, the first names of two selected employees in a company, and the direct line
 * management relations between every employee. Each person in the company can directly line manage a maximum of 2 other employees. The input has the
 * following format:
 * <ul>
 * <li>on the first line, the number of unique employees in the company</li>
 * <li>on the second line, the name of the first selected employee (a first name only without spaces)</li>
 * <li>on the third line, the name of the second selected employee (a first name only without spaces, guaranteed to be different from the first
 * selected employee)</li>
 * <li>on the subsequent lines, the line management relations in the format "EmployeeX EmployeeY" - meaning EmployeeX manages EmployeeY (first names
 * without spaces and spaces are used to separate the two names)</li>
 * </ul>
 * 
 * The input is correct (there are only direct line management relations, no cycles, all employees appear in the input). For simplicity, the first
 * line after the selected employees (line 4) always contains the manager at the top of the hierarchy.
 * 
 * <br>
 * <br>
 * Write a program that reads the input from stdin and then outputs out the name of the single employee at the lowest point in the hierarchy to which
 * the two selected employees report, either directly or indirectly. If one employee reports to the other, either directly or indirectly, print out
 * the name of the highest of the two selected employees.
 * 
 * Examples:
 * 
 * Input:
 * 
 * 6 Hilary, James, Sarah Fred, Sarah Paul, Fred Hilary, Fred Jenny, Jenny James
 * 
 * Output:
 * 
 * Fred
 * 
 * Input:
 * 
 * 4 Simon, Claudiu, Sarah Claudiu, Sarah Paul, Claudiu Simon
 * 
 * Output:
 * 
 * Claudiu
 * 
 * Input:
 * 
 * 5 Gareth, Alex, June Alex, June Qing, Qing Paul, Qing Gareth
 * 
 * Output:
 * 
 * June
 * 
 * 
 * @author giuseppe.adaldo
 *
 */
public class CommonManagerV2 {

	static String commonManager(int count, String[] in) {
		Map<String, List<String>> adjacents = loadData(in);
		String firstSelected = in[0];
		String secondSelected = in[1];

		System.out.println(adjacents);

		String root = getRootEmpoloye(adjacents);

		return getCommonManager(adjacents, firstSelected, secondSelected, root, root);
	}

	private static String getRootEmpoloye(Map<String, List<String>> adj) {
		List<String> root = new ArrayList<>(adj.keySet());
		root.removeAll(adj.values().stream()
				.flatMap(List::stream)
				.collect(Collectors.toList()));
		return root.get(0);
	}

	private static String getCommonManager(Map<String, List<String>> adj, String a, String b, String parent, String current) {
		if (current == null)
			return parent;
		if (current.equals(a) || current.equals(b))
			return current;

		if (adj.get(current) != null) {
			String left = "";
			String right = "";
			if (adj.get(current).size() > 0)
				left = getCommonManager(adj, a, b, parent, adj.get(current).get(0));

			if (adj.get(current).size() > 1)
				right = getCommonManager(adj, a, b, parent, adj.get(current).get(1));

			if (right.isEmpty())
				return left;

			if (left.equals(right))
				return left;

			if ((left.equals(a) || left.equals(b)) && parent.equals(left))
				return left;

			if ((right.equals(a) || right.equals(b)) && parent.equals(right))
				return right;
		}
		return current;
	}

	private static Map<String, List<String>> loadData(String[] in) {
		Map<String, List<String>> adjacents = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			String employe = getManager(in[i]);
			String sub = getSub(in[i]);
			if (adjacents.containsKey(employe)) {
				if (adjacents.get(employe) == null) {
					buildEmployRelation(adjacents, employe, sub);
				} else {
					adjacents.get(employe).add(sub);
					adjacents.put(sub, new ArrayList<>());
				}
			} else {
				buildEmployRelation(adjacents, employe, sub);
			}
		}
		return adjacents;
	}

	private static void buildEmployRelation(Map<String, List<String>> adj, String employe, String sub) {
		if (sub == null)
			adj.put(employe, new ArrayList<>());
		else {
			adj.put(employe, new ArrayList<>(Arrays.asList(sub)));
			adj.put(sub, new ArrayList<>());
		}
	}

	private static String getManager(String line) {
		if (line.contains(" "))
			return line.split(" ")[0];
		return line;
	}

	private static String getSub(String line) {
		if (line.contains(" "))
			return line.split(" ")[1];
		return null;
	}

	public static void main(String args[]) throws Exception {
		Test.equals(
				commonManager(6, new String[] { "hilary", "james", "sara fred", "sara paul", "fred hilary", "fred jenny", "jenny james" }), "fred");
		Test.equals(commonManager(4, new String[] { "simon", "claudid", "sara claudid", "sara paul", "claudid simon" }), "claudid");
		Test.equals(commonManager(5, new String[] { "simon", "claudid", "sara claudid", "sara paul", "claudid maria", "maria simon" }), "claudid");
		Test.equals(commonManager(5, new String[] { "Gareth", "Alex", "June Alex", "June Qing", "Qing Paul", "Qing Gareth" }), "June");
	}
}
