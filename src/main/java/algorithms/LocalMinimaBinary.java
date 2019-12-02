package algorithms;

import utils.test.Test;

import java.util.Arrays;

public class LocalMinimaBinary {

    int findLocalMin(int[] a, int l, int r) {
        if (l > r)
            return Integer.MIN_VALUE;

        int mid = (l + r) / 2;

        if (isLocalMin(a, mid))
            return a[mid];

        if (isLocalMin(a, l))
            return a[l];

        if (isLocalMin(a, r))
            return a[r];

        int min = Integer.MIN_VALUE;
        if (l < mid)
            min = findLocalMin(a, l + 1, mid - 1);

        if (min == Integer.MIN_VALUE && mid < r)
            min = findLocalMin(a, mid + 1, r - 1);

        return min;
    }

    private boolean isLocalMin(int[] a, int i) {
        if (i == 0)
            return a[i] < a[i + 1];

        if (i == a.length - 1)
            return a[i] < a[i - 1];

        return a[i] < a[i + 1] && a[i] < a[i - 1];
    }

    int findLocalMin(int[] a) {
        System.out.println("local minima array=" + Arrays.toString(a));
        if (a.length == 0)
            return Integer.MIN_VALUE;
        if (a.length == 1)
            return a[0];

        return findLocalMin(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        LocalMinimaBinary l = new LocalMinimaBinary();

        int[] arrA = {11, 4, 2, 5, 11, 13, 5};
        Test.equals(l.findLocalMin(arrA), 5);

        arrA = new int[]{11, 4, 2, 5, 11, 13, 13};
        Test.equals(l.findLocalMin(arrA), 2);

        arrA = new int[]{0};
        Test.equals(l.findLocalMin(arrA), 0);

        arrA = new int[]{1};
        Test.equals(l.findLocalMin(arrA), 1);

        arrA = new int[]{1, 2};
        Test.equals(l.findLocalMin(arrA), 1);

        arrA = new int[]{2, 1};
        Test.equals(l.findLocalMin(arrA), 1);

        arrA = new int[]{7, 7};
        Test.equals(l.findLocalMin(arrA), Integer.MIN_VALUE);

        arrA = new int[]{7, 7, 8, 5, 3, 4};
        Test.equals(l.findLocalMin(arrA), 3);

        arrA = new int[]{2, 2, 3, 5, 4};
        Test.equals(l.findLocalMin(arrA), 4);

        arrA = new int[]{2, 2, 3, 4, 5, 6, 4, 7, 8, 9};
        Test.equals(l.findLocalMin(arrA), 4);

        arrA = new int[]{2, 2, 3, 2, 2, 2, 2, 2};
        Test.equals(l.findLocalMin(arrA), Integer.MIN_VALUE);

        arrA = new int[]{2, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 17, 33, 21, 100};
        Test.equals(l.findLocalMin(arrA), 21);

        arrA = new int[]{2, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 17, 33, 34, 100, 150, 160, 200, 300, 400, 401};
        Test.equals(l.findLocalMin(arrA), Integer.MIN_VALUE);

        arrA = new int[]{2, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 17, 33, 34, 100, 150, 160, 200, 300, 400, 401, 2};
        Test.equals(l.findLocalMin(arrA), 2);

        arrA = new int[]{2, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 17, 33, 34, 100, 150, 160, 200, 300, 400, 401, 2000, 2001, 2002, 2001, 2010, 2008};
        Test.equals(l.findLocalMin(arrA), 2008);

        arrA = new int[]{2, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 17, 33, 34, 100, 150, 160, 200, -160, 400, 401, 2000, 2001, 2002, 2001, 2010, 2018};
        Test.equals(l.findLocalMin(arrA), -160);
    }
}
