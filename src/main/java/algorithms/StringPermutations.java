package algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import utils.test.Test;

/**
 * Given a string of unique characters, write a function to calculate all permutations of that string.
 *
 */
public class StringPermutations {

	public static String permutationStrings(String chars) {
		return perm(chars).stream()
				.collect(Collectors.joining(", "));
	}

	public static List<String> perm(String chars) {
		final int n = chars.length();
		final List<String> perms = new LinkedList<>();

		if (n <= 1) {
			perms.add(chars);
			return perms;
		}

		final List<String> subPerm = perm(chars.substring(1));
		for (String curr : subPerm) {
			swap(chars.charAt(0), curr, perms);
		}
		return perms;
	}

	private static void swap(char i, String remaining, List<String> partial) {
		for (int j = 0; j <= remaining.length(); j++) {
			partial.add(remaining.substring(0, j) + i + remaining.substring(j));
		}
	}

	public static void main(String[] args) {
		Test.equals(permutationStrings(""), "");
		Test.equals(permutationStrings(" "), " ");
		Test.equals(permutationStrings("a"), "a");
		Test.equals(permutationStrings("ab"), "ab, ba");
		Test.equals(permutationStrings("abc"), "abc, bac, bca, acb, cab, cba");
		Test.equals(permutationStrings("abcd"),
				"abcd, bacd, bcad, bcda, acbd, cabd, cbad, cbda, acdb, cadb, cdab, cdba, " +
						"abdc, badc, bdac, bdca, adbc, dabc, dbac, dbca, adcb, dacb, dcab, dcba");
		Test.equals(permutationStrings("abcde"),
				"abcde, bacde, bcade, bcdae, bcdea, acbde, cabde, cbade, cbdae, cbdea, acdbe, cadbe, "
						+ "cdabe, cdbae, cdbea, acdeb, cadeb, cdaeb, cdeab, cdeba, abdce, badce, bdace, bdcae, "
						+ "bdcea, adbce, dabce, dbace, dbcae, dbcea, adcbe, dacbe, dcabe, dcbae, dcbea, adceb, "
						+ "daceb, dcaeb, dceab, dceba, abdec, badec, bdaec, bdeac, bdeca, adbec, dabec, dbaec, "
						+ "dbeac, dbeca, adebc, daebc, deabc, debac, debca, adecb, daecb, deacb, decab, decba, "
						+ "abced, baced, bcaed, bcead, bceda, acbed, cabed, cbaed, cbead, cbeda, acebd, caebd, "
						+ "ceabd, cebad, cebda, acedb, caedb, ceadb, cedab, cedba, abecd, baecd, beacd, becad, "
						+ "becda, aebcd, eabcd, ebacd, ebcad, ebcda, aecbd, eacbd, ecabd, ecbad, ecbda, aecdb, "
						+ "eacdb, ecadb, ecdab, ecdba, abedc, baedc, beadc, bedac, bedca, aebdc, eabdc, ebadc, "
						+ "ebdac, ebdca, aedbc, eadbc, edabc, edbac, edbca, aedcb, eadcb, edacb, edcab, edcba");
	}

}
