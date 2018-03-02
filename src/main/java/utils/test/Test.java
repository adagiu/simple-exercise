package utils.test;

public class Test {

	public static <E extends Comparable<E>> void equals(E a, E b) {
		if (a != null && a.equals(b)) {
			System.out.println("expected: " + b + ", given: " + a + "\t\t test OK");
		} else {
			System.err.println("expected: " + b + ", but was: " + a + "\t\t test FAILED");
		}
	}

}
