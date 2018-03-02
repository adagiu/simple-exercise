package threading;

import utils.test.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * giuseppe.adaldo
 */
public class ListStringReverser {

	private static final int MIN_LIMIT = 4;

	/**
	 * Executes reversing of the string.
	 *
	 * @param words
	 * @return
	 */
	public List<String> execute(List<String> words) {
		if (words == null || words.isEmpty())
			return words;

		final ForkJoinPool pool = new ForkJoinPool();
		final StringReverserTask task = new StringReverserTask(words, MIN_LIMIT);
		final List<String> reversed = pool.invoke(task);

		System.out.println("reversed words are: " + reversed);

		return reversed;
	}

	/**
	 * Sample usage.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		final List<String> wordsToReverse = Arrays.asList("word1", "word2", "anotherWord",
				"some", "any", "etc", "word1", "word2", "anotherWord", "some", "any", "etc", "word1", "word2",
				"anotherWord", "some", "any", "etc", "word1", "word2", "anotherWord", "some", "any", "etc",
				"word1", "word2", "anotherWord", "some", "any", "etc", "also", "this", "is", "a", "test");

		final List<String> reversed = new ListStringReverser().execute(wordsToReverse);
		Test.equals(reversed.size(), wordsToReverse.size());
	}
}
