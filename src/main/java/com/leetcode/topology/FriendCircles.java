package com.leetcode.topology;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/friend-circles/
 * 
 * There are N students in a class. Some of them are friends, while some are
 * not. Their friendship is transitive in nature. For example, if A is a direct
 * friend of B, and B is a direct friend of C, then A is an indirect friend of
 * C. And we defined a friend circle is a group of students who are direct or
 * indirect friends.
 * 
 * Given a N*N matrix M representing the friend relationship between students in
 * the class. If M[i][j] = 1, then the ith and jth students are direct friends
 * with each other, otherwise not. And you have to output the total number of
 * friend circles among all the students.
 * 
 * Example 1: Input: [[1,1,0], [1,1,0], [0,0,1]] Output: 2 Explanation:The 0th
 * and 1st students are direct friends, so they are in a friend circle. The 2nd
 * student himself is in a friend circle. So return 2.
 * 
 * Example 2: Input: [[1,1,0], [1,1,1], [0,1,1]] Output: 1
 * 
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd
 * students are direct friends, so the 0th and 2nd students are indirect
 * friends. All of them are in the same friend circle, so return 1. Note: N is
 * in range [1,200]. M[i][i] = 1 for all students. If M[i][j] = 1, then M[j][i]
 * = 1
 * 
 * 
 */
public class FriendCircles {

	static class Circle {
		public boolean[] processed;
		public int n;
		int totalProcessed = 0;


		public Circle(int n) {
			this.n = n;
			processed = new boolean[n];
		}

		void collectAllUnprocessedTies(int seed, int[][] m) {
			Queue<Integer> queue = new LinkedList<>();
			queue.add(seed);
			while (!queue.isEmpty()) {
				Integer friend = queue.poll();
				int[] friends = m[friend];
				for (int next = 0; next < n; next++) {
					if (friends[next] == 1 && !processed[next]) {
						processed[next] = true;
						totalProcessed ++;
						queue.add(next);
					}
				}
			}
		}
	}
	

	public static int findCircleNum(int[][] m) {
		int n = m.length;
		
		Circle circle = new Circle(n);

		int circlesCounter = 0;
		while (circle.totalProcessed < n) {
			for (int i = 0; i < n; i++) {
				if (!circle.processed[i]) {
					circle.processed[i] = true;
					circle.totalProcessed ++;
					circle.collectAllUnprocessedTies(i,m);
					break;
				}
			}
			circlesCounter ++;
		}

		return circlesCounter;

	}

	public static void main(String[] arg) {
		
		int[][] m = {
				{1,1,0}, {1,1,0}, {0,0,1}
		};

		System.out.println(findCircleNum(m));

		int[][] m1 = {
				{1,1,0}, {1,1,1}, {0,1,1}
		};

		System.out.println(findCircleNum(m1));
		
		int[][] m2 = {
				{1,0}, {0,1}
		};

		System.out.println(findCircleNum(m2));


	}

}
