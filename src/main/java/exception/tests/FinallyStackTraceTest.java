package exception.tests;

public class FinallyStackTraceTest {

	/**
	 * hello 1, then RuntimeException and at the end Finally
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("hello 1");
			throwsSomething();
			System.out.println("hello 2");
		} finally {
			System.out.println("Finally");
		}
	}

	public static void throwsSomething() {
		throw new RuntimeException();
	}

}
