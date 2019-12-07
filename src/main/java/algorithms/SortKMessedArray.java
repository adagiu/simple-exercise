package algorithms;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

/**
 * Given an array of integers arr where each element is at most k places away from its sorted position,
 * code an efficient function sortKMessedArray that sorts arr. For instance, for an input array of size 10 and k = 2,
 * an element belonging to index 6 in the sorted array will be located at either index 4, 5, 6, 7 or 8 in the input array.
 */
public class SortKMessedArray {

    static int[] sortKMessedArray(int[] a, int k) {
        if (a == null || a.length < k)
            return a;

        final int n = a.length;

        final PriorityQueue<Integer> slidingWindow = IntStream.range(0, k + 1)
                .boxed()
                .map(i -> a[i])
                .collect(toCollection(PriorityQueue::new));

        int index = 0;
        for (int i = k + 1; i < n; i++) {
            a[index++] = slidingWindow.poll();
            slidingWindow.add(a[i]);
        }
        for (; index < n; a[index++] = slidingWindow.poll()) ;
        return a;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.equals(sortKMessedArray(new int[]{1, 4, 5, 2, 3, 6, 8, 7, 10, 9}, 2),
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}
