package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;

/**
 * Given a dictionary and a string of random letters, find the longest word(s) from the dictionary that you can get from the letters.
 *
 */
public class DictionaryLongestWords {

	private static final List<String> DICTIONARY = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("casa");
			add("aglio");
			add("cassa");
			add("cassata");
			add("coniglio");
			add("gelato");
			add("patata");
			add("sacca");
			add("sacah");
			add("sacha");
			add("carni");
			add("crina");
			add("crani");
			add("ranci");
			add("rhanci");
			add("rancio");
			add("aranci");
			add("aracni");
		}
	};

	public static List<String> getLongestWord(char[] letters) {
		// initializing frequency for current string
		int[] inputStringFreq = calculateFrequency(letters);

		List<String> result = new ArrayList<>();

		// min length to accept
		int minLenght = letters.length;
		for (String word : DICTIONARY) {
			int countTotFreq = 0;

			// calculating frequency for current word from dictionary
			int[] currentWordFreq = calculateFrequency(word);

			// calculating if the dictionary's word chars have frequency in the given one
			for (char c : letters) {
				if (inputStringFreq[c - 'a'] > 0) {
					countTotFreq += currentWordFreq[c - 'a'];
				}
			}
			// adding to result only word whose frequency matches the length (means all letters had a match)
			if (countTotFreq >= minLenght && countTotFreq == word.length()) {
				result.add(word);
				minLenght = word.length();
			}
		}
		final int minWordLenght = minLenght;

		return result.stream().filter(s -> s.length() == minWordLenght).collect(Collectors.toList());
	}

	private static int[] calculateFrequency(char[] string) {
		int[] currentStringFreq = new int[26];
		for (char c : string)
			currentStringFreq[c - 'a']++;

		return currentStringFreq;
	}

	private static int[] calculateFrequency(String s) {
		return calculateFrequency(s.toCharArray());
	}

	public static void main(String[] args) {
		String[] expected = new String[] { "cassa", "sacca" };
		List<String> calculated = getLongestWord(new char[] { 'a', 'c', 's' });
		calculated.forEach(System.out::println);
		Assert.assertTrue(Arrays.equals(expected, calculated.toArray()));

		expected = new String[] { "aranci", "aracni" };
		calculated = getLongestWord(new char[] { 'a', 'r', 'c', 'n', 'i' });
		calculated.forEach(System.out::println);
		Assert.assertTrue(Arrays.equals(expected, calculated.toArray()));

	}

}
