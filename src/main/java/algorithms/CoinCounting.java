package algorithms;

import utils.test.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.Comparator.reverseOrder;

/**
 * Given an array of coin denominations and a integer representing a monetary value, return the minimum array of integers that where the sum of its
 * elements is the monetary value. <br>
 * <br>
 * Return array[0] in case this calculation is not possible. <br>
 * <br>
 * sample: <br>
 * given -> [20, 10, 5, 1] and 53 <br>
 * return -> [20, 20, 10, 1, 1, 1]
 *
 * @author giuseppe.adaldo
 */
public class CoinCounting {

    private static List<Integer> countCoins(List<Integer> coinDenominations, int monetaryValue) {
        if (coinDenominations == null || coinDenominations.isEmpty())
            return singletonList(0);

        coinDenominations.sort(reverseOrder());

        List<Integer> toReturn = new ArrayList<>();
        int partial = monetaryValue;
        int i = 0;
        while (partial > 0) {
            while (i < coinDenominations.size() && partial < coinDenominations.get(i))
                i++;

            if (i >= coinDenominations.size())
                break;

            partial -= coinDenominations.get(i);

            if (partial >= 0)
                toReturn.add(coinDenominations.get(i));
        }

        if (toReturn.isEmpty() || partial > 0)
            return singletonList(0);
        return toReturn;
    }

    public static void main(String[] args) {
        Test.equals(countCoins(Arrays.asList(20, 10, 5, 1), 53).equals(Arrays.asList(20, 20, 10, 1, 1, 1)), true);
        Test.equals(countCoins(Arrays.asList(20, 10, 5, 1), 13).equals(Arrays.asList(10, 1, 1, 1)), true);
        Test.equals(countCoins(Arrays.asList(20, 10, 5, 2), 19).equals(Arrays.asList(10, 5, 2, 2)), true);
        Test.equals(countCoins(Arrays.asList(5, 10, 20, 2), 19).equals(Arrays.asList(10, 5, 2, 2)), true);
        Test.equals(countCoins(Arrays.asList(5, 10, 20, 3), 34).equals(Arrays.asList(0)), true);
        Test.equals(countCoins(Arrays.asList(34, 40, 8, 9), 9).equals(Arrays.asList(9)), true);
        Test.equals(countCoins(Arrays.asList(34, 40, 8, 9), 7).equals(Arrays.asList(0)), true);
    }

}
