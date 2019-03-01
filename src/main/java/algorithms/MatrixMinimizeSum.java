package algorithms;

import utils.test.Test;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its
 * path.
 * 
 * Note: You can only move either down or right at any point in time.
 * 
 * Example:
 * 
 * Input: [ [1,3,1], [1,5,1], [4,2,1] ] Output: 7 Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MatrixMinimizeSum {

	public static void main(String[] args) {
		int[][] grid = new int[][] {
				{ 1, 3, 1 },
				{ 1, 5, 1 },
				{ 4, 2, 1 } };
		Test.equals(minSumPath(grid), 7); // 1 -> 3 -> 1 -> 1 -> 1

		grid = new int[][] {
				{ 1, 3, 1 },
				{ 1, 4, 1 },
				{ 4, 2, 1 } };
		Test.equals(minSumPath(grid), 7); // 1 -> 3 -> 1 -> 1 -> 1

		grid = new int[][] {
				{ 1, 3, 1 },
				{ 1, 2, 1 },
				{ 4, 2, 1 } };
		Test.equals(minSumPath(grid), 6); // 1 -> 1 -> 2 -> 1 -> 1

		grid = new int[][] {
				{ 1, 3, 1, 1 },
				{ 1, 2, 1, 2 },
				{ 4, 2, 1, 1 } };
		Test.equals(minSumPath(grid), 7); // 1 -> 1 -> 2 -> 1 -> 1 -> 1

		grid = new int[][] {
				{ 1, 3, 8, 1 },
				{ 1, 2, 1, 2 },
				{ 4, 5, 9, 1 },
				{ 6, 2, 1, 4 } };
		Test.equals(minSumPath(grid), 12); // 1 -> 1 -> 2 -> 1 -> 2 -> 1 -> 4
	}

	// private static int minSumPath(int[][] grid) {
	// int m = grid.length, n = grid[0].length;
	// int[][] mem = new int[m][n];
	// return minSumPath(grid, mem, m - 1, n - 1);
	// }
	//
	// private static int minSumPath(int[][] grids, int[][] mem, int m, int n) {
	// if (m == 0 && n == 0)
	// return grids[0][0];
	// if (mem[m][n] != 0)
	// return mem[m][n];
	// if (m == 0) {
	// mem[0][n] = grids[0][n] + minSumPath(grids, mem, 0, n - 1);
	// return mem[0][n];
	// }
	// if (n == 0) {
	// mem[m][0] = grids[m][0] + minSumPath(grids, mem, m - 1, 0);
	// return mem[m][0];
	// }
	// mem[m][n] = grids[m][n] + Math.min(minSumPath(grids, mem, m - 1, n), minSumPath(grids, mem, m, n - 1));
	// // printMatrix(mem);
	// // System.out.println(m + " " + n + " " + mem[m][n]);
	// return mem[m][n];
	// }

	public static int minSumPath(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		int[][] mem = new int[m][n];

		mem[0][0] = grid[0][0];

		for (int i = 1; i < n; i++)
			mem[0][i] = mem[0][i - 1] + grid[0][i];
		for (int i = 1; i < m; i++)
			mem[i][0] = mem[i - 1][0] + grid[i][0];

		printMatrix(mem);

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				mem[i][j] = Math.min(mem[i - 1][j] + grid[i][j], mem[i][j - 1] + grid[i][j]);
			}
		}

		printMatrix(mem);
		return mem[m - 1][n - 1];
	}

	private static void printMatrix(int[][] m) {
		System.out.println();
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}

}
