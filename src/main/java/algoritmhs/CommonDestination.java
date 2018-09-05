package algoritmhs;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utils.test.Test;

/**
 * 
 * 
 * Find the Most Popular Destination:
 * 
 * Your task is to find the most popular holiday destination from a list of destinations searched for by users. You are given as standard input the
 * integer size of the list, followed by the names of the destinations themselves. The input has the following format:
 * 
 * on the first line, the count of items in the list on the subsequent lines, the name of each destination searched for, one per line (each
 * destination is a single word with no spaces, destinations can be searched for and appear more than once) The input is correct. There is at least
 * one destination in the input. Write a program that reads the input from stdin and then outputs out the name of the most searched for destination
 * i.e. the destination that appears most in the list. One destination is guaranteed to be the outright winner in the input.
 * 
 * Examples:
 * 
 * Input:
 * 
 * 6 Barcelona Edinburgh Barcelona Miami Miami Barcelona
 * 
 * Output:
 * 
 * Barcelona
 * 
 * Input:
 * 
 * 5 Singapore Bangkok Singapore Bangkok Singapore
 * 
 * Output:
 * 
 * Singapore
 * 
 * 
 * @author giuseppe.adaldo
 *
 */
public class CommonDestination {

	private static String commonDestination(String[] destinations) {
		return Stream.of(destinations)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet()
				.stream()
				.max(Comparator.comparing(Entry::getValue))
				.map(Entry::getKey)
				.get();
	}

	public static void main(String[] args) {
		Test.equals("Barcelona", commonDestination(new String[] { "Barcelona", "Edinburgh", "Barcelona", "Miami", "Miami", "Barcelona" }));
		Test.equals("Singapore", commonDestination(new String[] { "Singapore", "Bangkok", "Singapore", "Bangkok", "Singapore", "Barcelona" }));
	}

}
