package algorithms;

import utils.test.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static java.util.Collections.singletonList;

/**
 * Given a LinkedList of tasks and a coolDown period, count the executionTime of given tasks list. The coolDown adds more time
 * if previous k tasks are matching.
 * <p>
 * A,B,C - k = 2 => 3
 * A,A,B,C - k = 2 => A,_,_A,B,C => 6
 * A,A,B,A,C - k = 2 => A,_,_,A,B,_,A,C => 8
 * A, A, B, A, B, C - k = 2 => A,_,_AB,_A,_,B,C
 */
public class StepCounterInTaskExecutor {

    private static int countSteps(LinkedList<String> tasks, int k) {
        String cache = "";
        int steps = 0;

        for (String t : tasks) {
            steps += getCoolDown(cache, t, k);
            cache = cache.concat(t);
        }
        return steps;
    }

    private static int getCoolDown(String cache, String t, int k) {
        if (cache.isEmpty())
            return 1;

        int n = cache.length() - 1;
        int c = k + 1;

        for (int i = n; i >= 0; i--, c--) {
            if (cache.charAt(i) == t.toCharArray()[0]) {
                return c;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<>();
        Test.equals(countSteps(ll, 2), 0);

        ll = new LinkedList<>(Arrays.asList("A", "B"));
        Test.equals(countSteps(ll, 2), 2);

        ll = new LinkedList<>(Arrays.asList("A", "B", "C"));
        Test.equals(countSteps(ll, 2), 3);

        ll = new LinkedList<>(Arrays.asList("A", "A", "B", "C"));
        Test.equals(countSteps(ll, 2), 6);

        ll = new LinkedList<>(Arrays.asList("A", "A", "B", "A", "C"));
        Test.equals(countSteps(ll, 2), 8);

        ll = new LinkedList<>(Arrays.asList("A", "A", "B", "A", "B", "C"));
        Test.equals(countSteps(ll, 2), 10);

        ll = new LinkedList<>(Arrays.asList("A", "A", "B", "A", "B", "C"));
        Test.equals(countSteps(ll, 3), 13);
    }

}
