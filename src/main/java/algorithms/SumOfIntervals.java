package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import utils.test.Test;

/**
 * given a list of arrays of time intervals, write a function that calculates the total amount of time covered by intervals.
 *
 * sample [(1, 4) (6, 8) (2, 4) (7, 9) (10, 15)] -> 11
 */
public class SumOfIntervals {

	public static int calculateTimeCovered(List<Integer[]> intervals) {
		// sorting intervals by intervals
		TreeMap<Integer, Integer> tree = new TreeMap<>();
		for (Integer[] interval : intervals) {
			if (tree.containsKey(interval[0]))
				tree.put(interval[0], Math.max(interval[1], tree.get(interval[0])));
			else
				tree.put(interval[0], interval[1]);
		}

		System.out.println(tree);

		// merge intervals
		Integer current = tree.firstKey();
		while (current != null) {
			Integer next = tree.higherKey(current);

			while (next != null && tree.get(current) >= next) {
				tree.put(current, Math.max(tree.get(next), tree.get(current)));
				tree.remove(next);
				next = tree.higherKey(next);
			}
			current = next;
		}

		System.out.println(tree);

		// sum the intervals
		int sum = 0;
		for (Entry<Integer, Integer> i : tree.entrySet()) {
			sum += i.getValue() - i.getKey();
		}

		return sum;
	}

	public static int calculateTimeCovered1(List<Integer[]> intervals) {
		// sorting intervals by intervals
		TreeMap<Integer, Integer> tree = new TreeMap<>();
		for (Integer[] interval : intervals) {
			if (tree.containsKey(interval[0]))
				tree.put(interval[0], Math.max(interval[1], tree.get(interval[0])));
			else
				tree.put(interval[0], interval[1]);

			Integer current = interval[0];
			Integer next = tree.higherKey(current);
			while (next != null && interval[1] >= tree.get(next)) {
				tree.put(current, Math.max(tree.get(next), tree.get(current)));
				tree.remove(next);
				next = tree.higherKey(next);
			}
		}

		System.out.println(tree);

		// sum the intervals
		int sum = 0;
		for (Entry<Integer, Integer> i : tree.entrySet()) {
			sum += i.getValue() - i.getKey();
		}

		return sum;
	}

	public static void main(String[] args) {
		List<Integer[]> intervals = new ArrayList<>();
		intervals.add(new Integer[] { 1, 4 });
		intervals.add(new Integer[] { 2, 3 });
		intervals.add(new Integer[] { 2, 4 });
		intervals.add(new Integer[] { 6, 8 });
		intervals.add(new Integer[] { 7, 9 });
		intervals.add(new Integer[] { 10, 15 });
		// Test.equals(calculateTimeCovered(intervals), 11);
		Test.equals(calculateTimeCovered1(intervals), 11);

		List<Integer[]> intervals1 = new ArrayList<>();
		intervals1.add(new Integer[] { 1, 4 });
		intervals1.add(new Integer[] { 2, 3 });
		// Test.equals(calculateTimeCovered(intervals1), 3);
		Test.equals(calculateTimeCovered1(intervals1), 3);

		List<Integer[]> intervals2 = new ArrayList<>();
		intervals2.add(new Integer[] { 4, 6 });
		intervals2.add(new Integer[] { 1, 2 });
		// Test.equals(calculateTimeCovered(intervals2), 3);
		Test.equals(calculateTimeCovered1(intervals2), 3);
	}

}
