package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class CommonManager {

	static String commonManager(int count, String[] in) {
		Map<String, String> adjacents = new HashMap<>();
		String firstSelected = in[0];
		String secondSelected = in[1];

		for (int i = 0; i <= count; i++) {
			String manager = getManager(in[i]);
			String employee = getSub(in[i]);

			// since from input you don't know the root, the only strategy is to keep a 'report to' relation between employees.
			if (employee == null) {
				adjacents.put(manager, "");
			} else {
				adjacents.put(employee, manager);
			}
		}

		// case of one selected employee reports to the other directly print the highest
		if (adjacents.containsKey(firstSelected) && adjacents.get(firstSelected).equals(secondSelected))
			return secondSelected;
		else if (adjacents.containsKey(secondSelected) && adjacents.get(secondSelected).equals(firstSelected))
			return firstSelected;

		// case of one selected employee reports to the other indirectly print the highest
		final List<String> personAChain = buildParents(firstSelected, adjacents);
		if (personAChain.contains(secondSelected))
			return secondSelected;

		final List<String> personBChain = buildParents(secondSelected, adjacents);
		if (personBChain.contains(firstSelected))
			return firstSelected;

		// only keeps common manager
		personAChain.retainAll(personBChain);

		return personAChain.get(0);
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

	private static List<String> buildParents(String person, Map<String, String> adj) {
		final List<String> list = new ArrayList<>();
		if (person == null)
			return list;
		if (adj.get(person) != null && !adj.get(person).isEmpty())
			list.add(adj.get(person));
		list.addAll(buildParents(adj.get(person), adj));
		return list;
	}

	public static void main(String args[]) throws Exception {
		Test.equals(
				commonManager(6, new String[] { "hilary", "james", "sara fred", "sara paul", "fred hilary", "fred jenny", "jenny james" }), "fred");
		Test.equals(commonManager(4, new String[] { "simon", "claudid", "sara claudid", "sara paul", "claudid simon" }), "claudid");
		Test.equals(commonManager(5, new String[] { "simon", "claudid", "sara claudid", "sara paul", "claudid maria", "maria simon" }), "claudid");
		Test.equals(commonManager(5, new String[] { "Gareth", "Alex", "June Alex", "June Qing", "Qing Paul", "Qing Gareth" }), "June");
	}
}
