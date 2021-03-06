package org.problems.tree;

import org.problems.model.TreeNode;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/
 * 
 * Given two non-empty binary trees s and t, check whether tree t has exactly
 * the same structure and node values with a subtree of s. A subtree of s is a
 * tree consists of a node in s and all of this node's descendants. The tree s
 * could also be considered as a subtree of itself.
 * 
 * Example 1: Given tree s:
 * 
 *     3 
 *    / \ 
 *   4   5 
 *  / \ 
 * 1   2 
 * 
 * Given tree t: 
 *     4 
 *    / \ 
 *   1   2 
 * 
 * Return true, because t has the same
 * structure and node values with a subtree of s.
 * 
 * 
 * Example 2: Given tree s:
 * 
 *     3 
 *    / \ 
 *   4   5
 *  / \ 
 * 1   2 
 *    / 
 *   0 
 *   
 * Given tree t: 
 * 
 *     4
 *    / \ 
 *   1   2
 * Return false.
 * 
 */
public class SubtreeOfAnotherTree {
	
	static boolean check(TreeNode s, TreeNode t) {
		if (s == null && t != null) {
			return false;
		}
		if (s != null && t == null) {
			return false;
		}
		if (s == null && t == null) {
			return true;
		}
		if (s.val != t.val) {
			return false;
		}
		if (!check(s.left, t.left)) {
			return false;
		}
		if (!check(s.right, t.right)) {
			return false;
		}
		return true;
	}
	
	static boolean collectNodes(TreeNode s, TreeNode t, int val) {
		if (s == null) {
			return false;
		}
		if (s.val == val) {
			if (check(s,t)) {
				return true;
			}
		}
		if (collectNodes(s.left, t, val)) {
			return true;
		}
		if (collectNodes(s.right, t, val)) {
			return true;
		}
		return false;
	}

	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null && t == null) {
			return true;
		}
		if (s == null && t != null) {
			return false;
		}
		
		return collectNodes(s, t, t.val);

	}

	public static void main(String[] arg) {

		System.out.println(true);

	}

}
