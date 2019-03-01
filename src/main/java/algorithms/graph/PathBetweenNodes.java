package algorithms.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;

import utils.test.Test;

/**
 * Given a graph, write a function to determine if there is a path between 2 nodes
 * 
 */
public class PathBetweenNodes {

	private static boolean checkPathDSF(Graph graph, String a, String b) {
		return checkPathDSF(graph, a, b, new Stack<>());
	}

	private static boolean checkPathDSF(Graph graph, String a, String b, Stack<String> visitedNodes) {
		if (a.equals(b))
			return true;

		visitedNodes.push(a);

		for (String child : graph.getAdjiacents().get(a)) {
			if (child.equals(b))
				return true;
			if (!visitedNodes.contains(child))
				if (checkPathDSF(graph, child, b, visitedNodes))
					return true;
		}
		return false;
	}

	private static boolean checkPathBSF(Graph graph, String a, String b) {
		if (a.equals(b))
			return true;

		Map<String, String> nodeStatus = new HashMap<>();
		nodeStatus.put(a, "visiting");

		Deque<String> queue = new ArrayDeque<String>();
		queue.push(a);

		while (!queue.isEmpty()) {
			String current = queue.pop();
			for (String child : graph.getAdjiacents().get(current)) {
				if (child.equals(b))
					return true;

				if (!nodeStatus.containsKey(child)) {
					queue.push(child);
					nodeStatus.put(child, "visiting");
				}
			}
			nodeStatus.put(current, "visited");
		}
		return false;
	}

	public static void main(String[] args) {
		String graphString = "a:b a:c a:d b:c b:e e:f f:g d:g k:a k:m";
		Graph graph = buildGraph(graphString);

		System.out.println(graph.getAdjiacents());

		System.out.println(" ================ DSF ================");

		Test.equals(checkPathDSF(graph, "a", "b"), true);
		Test.equals(checkPathDSF(graph, "b", "b"), true);
		Test.equals(checkPathDSF(graph, "b", "a"), false);
		Test.equals(checkPathDSF(graph, "a", "f"), true);
		Test.equals(checkPathDSF(graph, "f", "a"), false);
		Test.equals(checkPathDSF(graph, "k", "a"), true);
		Test.equals(checkPathDSF(graph, "e", "g"), true);
		Test.equals(checkPathDSF(graph, "b", "g"), true);
		Test.equals(checkPathDSF(graph, "a", "g"), true);
		Test.equals(checkPathDSF(graph, "k", "b"), true);
		Test.equals(checkPathDSF(graph, "k", "c"), true);
		Test.equals(checkPathDSF(graph, "k", "f"), true);
		Test.equals(checkPathDSF(graph, "m", "g"), false);

		System.out.println(" ================ BSF ================");

		Test.equals(checkPathBSF(graph, "a", "b"), true);
		Test.equals(checkPathBSF(graph, "b", "b"), true);
		Test.equals(checkPathBSF(graph, "b", "a"), false);
		Test.equals(checkPathBSF(graph, "a", "f"), true);
		Test.equals(checkPathBSF(graph, "f", "a"), false);
		Test.equals(checkPathBSF(graph, "k", "a"), true);
		Test.equals(checkPathBSF(graph, "e", "g"), true);
		Test.equals(checkPathBSF(graph, "b", "g"), true);
		Test.equals(checkPathBSF(graph, "a", "g"), true);
		Test.equals(checkPathBSF(graph, "k", "b"), true);
		Test.equals(checkPathBSF(graph, "k", "c"), true);
		Test.equals(checkPathBSF(graph, "k", "f"), true);
		Test.equals(checkPathBSF(graph, "m", "g"), false);
	}

	private static Graph buildGraph(String nodes) {
		Map<String, Set<String>> adj = new HashMap<>();
		Stream.of(nodes.split(" "))
				.map(pairs -> pairs.split(":"))
				.forEach(pair -> {
					if (!adj.containsKey(pair[0])) {
						Set<String> children = new HashSet<>();
						children.add(pair[1]);
						adj.put(pair[0], children);
					} else {
						adj.get(pair[0]).add(pair[1]);
					}
					if (!adj.containsKey(pair[1])) {
						adj.put(pair[1], new HashSet<>());
					}
				});

		return new Graph(adj);
	}

	private static class Graph {
		private Map<String, Set<String>> adjiacents;

		public Graph(Map<String, Set<String>> adjiacents) {
			this.adjiacents = adjiacents;
		}

		public Map<String, Set<String>> getAdjiacents() {
			return adjiacents;
		}

	}

}
