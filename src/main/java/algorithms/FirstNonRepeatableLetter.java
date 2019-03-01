package algorithms;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Assert;

import utils.test.Test;

/**
 * Find first non-repeating letter in a string. e.g: For "apple" its 'a'.
 *
 */
public class FirstNonRepeatableLetter {

	public static char firstNonRepeatableLetter(String s) {
		// calculating array of frequencies for each letter
		final int freq[] = new int[256];

		// LinkedHashSet preserves insertion order, has O(1) add() and remove() complexity
		final Set<Character> nonRepeatableChars = new LinkedHashSet<>(256);
		
		for (char c : s.toCharArray()) {
			freq[c]++;

			// if frequency of current char is 1, we add to nonRepeatableCharsMap,
			// otherwise we avoid inserting and then removing in next step. This will reduce map resizing
			if (freq[c] == 1)
				nonRepeatableChars.add(c);

			// removing current char from non repeatable hash map characters if it's frequency it's > then 1
			if (freq[c] > 1)
				nonRepeatableChars.remove(c);
		}
		if (nonRepeatableChars.isEmpty())
			throw new IllegalArgumentException("non repeatable letter not found");

		return nonRepeatableChars.iterator().next(); // returns the first inserted object
	}

	public static void main(String[] args) {
		Test.equals(firstNonRepeatableLetter("apple"), 'a');
		Test.equals(firstNonRepeatableLetter("applea"), 'l');
		Test.equals(firstNonRepeatableLetter("appllea"), 'e');
		try {
			firstNonRepeatableLetter("applleaaaae");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("non repeatable letter not found", e.getMessage());
		}
		Test.equals(firstNonRepeatableLetter("abcdefghija"), 'b');
		Test.equals(firstNonRepeatableLetter("simplest"), 'i');
		Test.equals(firstNonRepeatableLetter("kjdhivuhidue87837468t7kgdwivnenuoigdSDJJOFEHj"), '3');
		Test.equals(firstNonRepeatableLetter("kjdhhSGK!ITVO878j3d74SG68tK7g09823&()$(@)EHk"), '!');
		Test.equals(firstNonRepeatableLetter(
				"jkbkcdjshicn84cn9SPFKODPOgjri  rh$)(R)$83p9u9u0w9 9wu9v0p]q9 cw94ut9 w9hfoiw'diho uqneg97vy48wnpny[2983yc49nr48yb398 y98fynuxxiowehfip oh\\ewj]ptgukjdhhSGK!ITVO878j3d74SG68tK7g09823&()$(@)EHk"),
				's');
	}

}
