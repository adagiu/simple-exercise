package switchcase.tests;

public class SwitchCase {

	/**
	 * prints two due to break absence.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		final String[] string = { "one", "two", "three" };

		int num = 2;
		int pos = 0;
		switch (num) {
		case 2:
			pos = 3;
		case 4:
		case 6:
		case 9:
		case 11:
			pos = 2;
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			pos = 1;
		}
		System.out.println(string[pos]);
	}

}
