package algoritmhs;

import utils.test.Test;

/**
 * Given two int value a and b, create a solution that returns a number c which is done by the digits of the two given number as following:
 * <p>
 * c = a[1], b[1], a[2], b[2] ... a[n], b[m]
 * <p>
 * Considering that the two numbers can have different length.
 * <p>
 * The solution must returns -1 is the number is > 100000000.
 *
 * @author gadaldo
 */
public class ZipNumber {

    public int solution(int a, int b) {
        if (a == 0 && b == 0)
            return 0;

        final String stringA = ("" + a).replace("-", "");
        final String stringB = ("" + b).replace("-", "");

        int minLength;
        final StringBuilder builder = new StringBuilder(stringA.length() + stringB.length());
        String toConcat = "";

        if (stringA.length() <= stringB.length()) {
            minLength = stringA.length();
            toConcat += stringB.substring(minLength, stringB.length());
        } else {
            minLength = stringB.length();
            toConcat += stringA.substring(minLength, stringA.length());
        }

        for (int i = 0; i < minLength; i++) {
            builder.append(stringA.charAt(i)).append(stringB.charAt(i));
        }

        builder.append(toConcat);

        final Long tempNumber = Long.parseLong(builder.toString());
        return tempNumber < 100000000 ? tempNumber.intValue() : -1;
    }

    public static void main(String[] args) {
        ZipNumber zipNumber = new ZipNumber();
        Test.equals(zipNumber.solution(0, 0), 0);
        Test.equals(zipNumber.solution(12, 56), 1526);
        Test.equals(zipNumber.solution(12398, 34598), -1);
        Test.equals(zipNumber.solution(432, 876), 483726);
        Test.equals(zipNumber.solution(43234, 22), 4232234);
        Test.equals(zipNumber.solution(42, 876057), 48276057);
        Test.equals(zipNumber.solution(0101, 000), 605);
        Test.equals(zipNumber.solution(Integer.MAX_VALUE, 0), -1);
        Test.equals(zipNumber.solution(0, Integer.MAX_VALUE), -1);
        Test.equals(zipNumber.solution(0, Integer.MIN_VALUE), -1);
    }
}
