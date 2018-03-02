package algoritmhs;

import utils.test.Test;

/**
 * This is a demo task. You can read about this task and its solutions in this blog post.
 * <p>
 * A zero-indexed array A consisting of N integers is given. An equilibrium index of this array is any integer P such that 0 ≤ P < N and the sum of elements of lower indices is equal to the sum of elements of higher
 * indices, i.e.
 * <p>
 * A[0] + A[1] + ... + A[P−1] = A[P+1] + ... + A[N−2] + A[N−1].
 * <p>
 * Sum of zero elements is assumed to be equal to 0. This can happen if P = 0 or if P = N−1.
 * <p>
 * For example, consider the following array A consisting of N = 8 elements:
 * <p>
 * A[0] = -1 A[1] = 3 A[2] = -4 A[3] = 5 A[4] = 1 A[5] = -6 A[6] = 2 A[7] = 1
 * <p>
 * P = 1 is an equilibrium index of this array, because:
 * <p>
 * A[0] = −1 = A[2] + A[3] + A[4] + A[5] + A[6] + A[7]
 * <p>
 * P = 3 is an equilibrium index of this array, because:
 * <p>
 * A[0] + A[1] + A[2] = −2 = A[4] + A[5] + A[6] + A[7]
 * <p>
 * P = 7 is also an equilibrium index, because:
 * <p>
 * A[0] + A[1] + A[2] + A[3] + A[4] + A[5] + A[6] = 0
 * <p>
 * and there are no elements with indices greater than 7.
 * <p>
 * P = 8 is not an equilibrium index, because it does not fulfill the condition 0 ≤ P < N.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given a zero-indexed array A consisting of N integers, returns any of its equilibrium indices. The function should return −1 if no equilibrium index exists.
 * <p>
 * For example, given array A shown above, the function may return 1, 3 or 7, as explained above.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [0..100,000]; each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 * <p>
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N); expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * <p>
 * Elements of input arrays can be modified.
 *
 * @author gadaldo
 */
public class EquilibriumNumber {

    public int solution(int array[]) {
        if (array == null || array.length == 0)
            return -1;

        if (array.length == 1)
            return 0;

        long sumTot = 0, sum2 = array[0];

        for (int i = 2; i < array.length; sumTot += (long) array[i++]) ;

        if (sumTot == sum2)
            return 1;

        for (int i = 2; i < array.length; i++) {
            sumTot -= (long) array[i];
            sum2 += (long) array[i - 1];
            if (sumTot == sum2)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        EquilibriumNumber equilibriumPoint = new EquilibriumNumber();
        Test.equals(equilibriumPoint.solution(new int[]{}), -1);
        Test.equals(equilibriumPoint.solution(new int[]{1}), 0);
        Test.equals(equilibriumPoint.solution(new int[]{0}), 0);
        Test.equals(equilibriumPoint.solution(new int[]{0, 0}), 1);
        Test.equals(equilibriumPoint.solution(new int[]{1, 2, 3, 54, 1, 2, 3}), 3);
        Test.equals(equilibriumPoint.solution(new int[]{1, 2, 3, 2, 6}), 3);
        Test.equals(equilibriumPoint.solution(new int[]{1121299899, 234599878, 234599878, 234599878, 1, 234599878, 234599878, 1121299899, 234599878}), 4);
        Test.equals(equilibriumPoint.solution(new int[]{Integer.MAX_VALUE, 234599878, 234599878, 234599878, 1, 234599878, 234599878, Integer.MAX_VALUE, 234599878}), 4);
        Test.equals(equilibriumPoint.solution(new int[]{Integer.MIN_VALUE, 234599878, 234599878, 234599878, 1, 234599878, 234599878, Integer.MIN_VALUE, 234599878}), 4);
        Test.equals(equilibriumPoint.solution(new int[]{-1, 2, 3, 7, 4}), 3);
        Test.equals(equilibriumPoint.solution(new int[]{-1, 2, 3, -5, 4}), 3);
        Test.equals(equilibriumPoint.solution(new int[]{-1, 2, 3, 4, -45, 3, 5}), 4);
        Test.equals(equilibriumPoint.solution(new int[]{1, -2, 3, -4, 2}), 1);
        Test.equals(equilibriumPoint.solution(new int[]{1, -2, 3, 3, 4, -2}), 3);
        Test.equals(equilibriumPoint.solution(new int[]{-1, 3, -4, 5, 1, -6, 2, 1}), 1);
    }

}
