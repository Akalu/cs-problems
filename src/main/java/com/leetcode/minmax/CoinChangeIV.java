package com.leetcode.minmax;

/**
 * https://leetcode.com/problems/lemonade-change/
 * 
 * At a lemonade stand, each lemonade costs $5.
 * 
 * Customers are standing in a queue to buy from you, and order one at a time
 * (in the order specified by bills).
 * 
 * Each customer will only buy one lemonade and pay with either a $5, $10, or
 * $20 bill. You must provide the correct change to each customer, so that the
 * net transaction is that the customer pays $5.
 * 
 * Note that you don't have any change in hand at first.
 * 
 * Return true if and only if you can provide every customer with correct
 * change.
 * 
 * Example 1:
 * 
 * Input: [5,5,5,10,20] Output: true 
 * 
 * Explanation: From the first 3 customers, we
 * collect three $5 bills in order. From the fourth customer, we collect a $10
 * bill and give back a $5. From the fifth customer, we give a $10 bill and a $5
 * bill. Since all customers got correct change, we output true. Example 2:
 * 
 * Input: [5,5,10] Output: true Example 3:
 * 
 * Input: [10,10] Output: false Example 4:
 * 
 * Input: [5,5,10,10,20] Output: false 
 * 
 * Explanation: From the first two customers
 * in order, we collect two $5 bills. For the next two customers in order, we
 * collect a $10 bill and give back a $5 bill. For the last customer, we can't
 * give change of $15 back because we only have two $10 bills. Since not every
 * customer received correct change, the answer is false
 *  
 */
public class CoinChangeIV {
	
	static class Change {
		public int five = 0;
		public int ten = 0;

	    boolean update(int bill) {
	    	if (bill == 5) {
	    		five ++;
	    		return true;
	    	}else if (bill == 10) {
	    		ten ++;
	    		if (five > 0) {
	    			five --;
	    			return true;
	    		}
	    	}else {// bill = 20
	    		if (ten > 0) {
	    			ten --;
	    			if (five > 0) {
	    				five --;
	    				return true;
	    			}
	    		}else {
	    			if (five > 2) {
	    				five -= 3;
	    				return true;
	    			}
	    		}
	    	}
			return false;
		}
	}
	

	public static boolean lemonadeChange(int[] bills) {
		Change change = new Change();
		for (int bill : bills) {
			if (!change.update(bill)) {
				return false;
			}
		}
		
		return true;

	}

	public static void main(String[] arg) {

		int[] bills = {5,5,5,10,20};
		System.out.println(lemonadeChange(bills));

		int[] bills1 = {5,5,10};
		System.out.println(lemonadeChange(bills1));

		int[] bills2 = {10,10};
		System.out.println(lemonadeChange(bills2));

		int[] bills3 = {5,5,10,10,20};
		System.out.println(lemonadeChange(bills3));

	}

}
