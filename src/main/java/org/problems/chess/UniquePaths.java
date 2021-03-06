package org.problems.chess;

/**
 * https://leetcode.com/problems/unique-paths/
 * 
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
 * the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
 * the diagram below).
 * 
 * How many possible unique paths are there?
 * 
 * Note: m and n will be at most 100.
 * 
 * Example 1:
 * 
 * Input: m = 3, n = 2 Output: 3 
 * 
 * Explanation: From the top-left corner, there
 * are a total of 3 ways to reach the bottom-right corner:
 * 
 * 1. Right -> Right -> Down 2. Right -> Down -> Right 3. Down -> Right -> Right
 * 
 * 
 * Example 2:
 * 
 * Input: m = 7, n = 3 Output: 28
 */
public class UniquePaths {

	public static int uniquePaths(int m, int n) {
		
		int paths = 0;
		if (m == 0 || n == 0) {
			return 0;
		}
		
		int[][] dt = new  int[n][m];
		for (int i = 0; i < m; i++) {
			dt[0][i] = 1;
		}
		for (int i = 0; i < n; i++) {
			dt[i][0] = 1;
		}
		for (int row = 1; row < n; row++) { 
			for (int col = 1; col < m; col++) { 
				dt[row][col] = dt[row][col-1] + dt[row-1][col]; 
			}
		}
		
		return dt[n-1][m-1];
    }

	public static void main(String[] arg) {

		System.out.println(uniquePaths(1,1));
		System.out.println(uniquePaths(3,1));
		System.out.println(uniquePaths(1,3));
		System.out.println(uniquePaths(3,2));
		System.out.println(uniquePaths(7,3));

	}
}
