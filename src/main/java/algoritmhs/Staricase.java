package algoritmhs;

/**
 * Consider a staircase of size :
 * 
 * # ## ### ####
 * 
 * Observe that its base and height are both equal to , and the image is drawn using # symbols and spaces. The last line is not preceded by any
 * spaces.
 * 
 * Write a program that prints a staircase of size .
 * 
 * Function Description
 * 
 * Complete the staircase function in the editor below. It should print a staircase as described above.
 * 
 * staircase has the following parameter(s):
 * 
 * n: an integer
 * 
 * Input Format
 * 
 * A single integer, n, denoting the size of the staircase.
 * 
 * Constraints
 * 
 * n <= 100
 * 
 * Output Format
 * 
 * Print a staircase of size using # symbols and spaces.
 * 
 * Note: The last line must have 0 spaces in it.
 * 
 * Sample Input
 * 
 * 6
 * 
 * Sample Output
 * 
 *          # 
 *         ## 
 *        ### 
 *       #### 
 *      ##### 
 *     ######
 * 
 * Explanation
 * 
 * The staircase is right-aligned, composed of # symbols and spaces, and has a height and width of 6.
 * 
 * @author giuseppe.adaldo
 *
 */
public class Staricase {

	static void staircase(int n) {
		String res = "";
		for (int i = 1; i <= n; i++) {
			for (int spaces = 0; spaces < (n - i); spaces++)
				res = res.concat(" ");
			for (int aces = 0; aces < i; aces++)
				res = res.concat("#");
			res = res.concat("\n");
		}
		System.out.println(res);
	}

	public static void main(String[] args) {
		staircase(6);
		staircase(18);
		staircase(100);
	}

}
