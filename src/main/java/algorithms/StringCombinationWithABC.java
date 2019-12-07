package algorithms;

import utils.test.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a length n, count the number of strings of length n that can be made using 'a' at most 1 'b' and 1 or 2 'c'
 */
public class StringCombinationWithABC {

    static int countWords(int n) {
        String a = fillWord(n - 2, 'a') + "bc";
        String b = fillWord(n - 3, 'a') + "bcc";

        final Set<String> set = new HashSet<>();
        set.add(a);
        set.add(b);
        int res = countStrings(a, n, set);
        res += countStrings(b, n, set);

        System.out.println(set);

        return res;
    }

    static int countStrings(String s, int n, Set<String> set) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                char[] stringArray = s.toCharArray();
                if (stringArray[i] != stringArray[j]) {
                    char tmp = stringArray[j];
                    stringArray[j] = stringArray[i];
                    stringArray[i] = tmp;
                    set.add(new String(stringArray));
                    set.add(new StringBuilder(new String(stringArray)).reverse().toString());
                }
            }
        }
        return set.size();
    }

    static String fillWord(int n, char c) {
        String s = "";
        for (int i = 0; i < n; i++, s += c) ;
        return s;
    }

    public static void main(String[] args) {
        Test.equals(countWords(5), 80);
    }

}
