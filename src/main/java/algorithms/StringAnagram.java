package algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given a n array of string containing words, return a set of group of anagram.
 * 
 */
public class StringAnagram {

	/**
	 * Uses word character's frequency as key to check if it already exists or not.
	 * 
	 * @param strs
	 * @return
	 */
	public static Set<Set<String>> groupAnagrams(String[] strs) {
		if (strs.length == 0)
			return Collections.emptySet();

		final Map<String, Set<String>> anagrams = new HashMap<>();
		for (String s : strs) {
			String key = getKey(s);
			if (!anagrams.containsKey(key))
				anagrams.put(key, new HashSet<>());

			anagrams.get(key).add(s);
		}
		return new HashSet<>(anagrams.values());
	}

	private static String getKey(String word) {
		int[] freq = getCharsFreq(word);

		StringBuilder sb = new StringBuilder(freq.length);
		Arrays.stream(freq).forEach(sb::append);

		return sb.toString();
	}

	private static int[] getCharsFreq(String s) {
		int freq[] = new int[26];
		for (char c : s.toCharArray()) {
			freq[c - 'a']++;
		}
		return freq;
	}

	private static Map<String, Long> toFreqMap(String s) {
		return s.chars()
				.mapToObj(c -> (char) c)
				.filter(Character::isAlphabetic)
				.collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));
	}

	public static void main(String[] args) {
		System.out.println(groupAnagrams(new String[] {}).isEmpty());
		System.out.println(groupAnagrams(new String[] { "dog", "god" }).contains(new HashSet<>(Arrays.asList(new String[] { "dog", "god" }))));

		Set<String> set = new HashSet<>(Arrays.asList(new String[] { "dog", "god" }));
		Set<String> set1 = new HashSet<>(Arrays.asList(new String[] { "cat", "act" }));
		Set<String> set2 = new HashSet<>(Arrays.asList(new String[] { "banana" }));
		Set<String> set3 = new HashSet<>(Arrays.asList(new String[] { "hello", "holle" }));
		Set<Set<String>> result = groupAnagrams(new String[] { "dog", "cat", "act", "banana", "hello", "god", "holle" });
		System.out.println(result.contains(set));
		System.out.println(result.contains(set1));
		System.out.println(result.contains(set2));
		System.out.println(result.contains(set3));

		System.out.println(toFreqMap("HelloMarylinoBrian"));
	}

}
