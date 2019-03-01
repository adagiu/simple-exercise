package algorithms;

import utils.test.Test;

import java.util.regex.Pattern;

/**
 * Sum all the numbers of a random string, considering negative numbers:
 * <p>
 * 'cjidnei 6 ejdnfkjheo 456 djewoin [2] njode39 eow9 -4jfiore' must print 508
 * <p>
 * Test: https://www.linkedin.com/grp/post/3983267-6009553344324997123
 *
 * @author gadaldo
 */
public class SumNumbersOfString {

    public int solution(String string) {
        return string != null && !string.trim().isEmpty() ?
                Pattern.compile("[^\\d-]").
                        splitAsStream(string).
                        filter(item -> !item.isEmpty()).
                        mapToInt(Integer::parseInt).
                        sum() : -1;
    }

    public static void main(String[] args) {
        SumNumbersOfString calculator = new SumNumbersOfString();
        Test.equals(calculator.solution(""), -1);
        Test.equals(calculator.solution(null), -1);
        Test.equals(calculator.solution("kidjhau osuhef ooshdfovhreoh dsa"), 0);

        Test.equals(calculator.solution("cjidnei 6  ejdnfkjheo 456 djewoin [2] njode39 eow9 -4jfiore"), 508);

        Test.equals(calculator.solution("32 34 5 6 a      8 4 5   5 "), 99);

        Test.equals(calculator.solution(" abc 2 de 3 4 juk      45"), 54);

        Test.equals(calculator.solution("iruhgf pw7e5y8t76327c ny49w874=3'#��$ -902 ur02 c4rhjicof"), 76377);
    }
}
