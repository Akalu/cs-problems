package org.problems.favourite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Debtors
 * 
 * Given the group debt records observed for this team (including the borrower
 * name, lender name, and debt amount), who in the group has the smallest
 * negative balance?
 * 
 * Notes: -10 is smaller than -1. If multiple people have the smallest negative
 * balance, return the list in alphabetical order. If nobody has a negative
 * balance, return the list consisting of string "Nobody has a negative
 * balance".
 * 
 * Write an algorithm to find who in the group has the smallest negative
 * balance.
 * 
 * Input: The input to the function/method consists of three arguments: numRows,
 * an integer representing the number of debt records. numCols, an integer
 * representing th enumber of elements in debt records. It is always 3. debts, a
 * list of triplet representing debtRecord consisting of a string borrower, a
 * string lender, and an integer amount, representing the debt record.
 * 
 * Output: Return a list of strings representing an alphabetically ordered list
 * of members with the smallest negative balance. If no team member has a
 * negative balance then return a list containing the string "Nobody has a
 * negative balance".
 * 
 * Constraints: 
 * 1 ≤ numRows ≤ 2*10^5 
 * 1 ≤ amount in debts ≤ 1000 
 * 1 ≤ length of borrower and lender in debts ≤ 20
 * 
 * Example: 
 * 
 * borrower lender amount
 * ======================= 
 * Alice 	Beth 	2 
 * Beth 	Alice 	2 
 * Casey 	Alice 	5 
 * Beth 	Casey 	7 
 * Alice 	Beth 	4 
 * Alice 	Casey 	4 
 * 
 * The first, fifth, and sixth entries
 * decrease Alice's balance because Alice is a borrower. The second and third
 * entries increase because Alice is a lender. So, Alice's balance is (2+5) -
 * (2+4+4) = 7 - 10 = -3. Beth is lender in first and fifth entries and a
 * borrower in the second and fourth entries. Thus, Beth's balance is (2+4) -
 * (2+7) = 6 - 9 = -3. Casey is a borrower in the third entry and a lender in
 * the fourth and sixth entries. Thus, Casey's balance is (7 + 4) - 5 = 11 - 6 =
 * 5. Here Alice and Beth both have the balance of -3, which is the minimum
 * amoung all members.
 * 
 */
public class Debtors {
	
	static class Debtor {
		String borrower;
		String lender;
		int amount;
		
		public Debtor(String borrower, String lender, int amount) {
			this.borrower = borrower;
			this.lender = lender;
			this.amount = amount;
		}
	}
	
	static class Balance {
		String name;
		int balance;

		public Balance(String name) {
			this.name = name;
		}

	}
	
	static List<String> getNames(final List<Balance> lst, final int min){
		return lst.stream().filter(b -> b.balance == min).map(b -> b.name).collect(Collectors.toList());
	}
	
	public static List<String> debtors(List<Debtor> records){
		Map<String, Balance> map = new HashMap<>();
		for (Debtor rec : records) {
			if (!map.containsKey(rec.borrower)) {
				map.put(rec.borrower, new Balance(rec.borrower));
			}
			if (!map.containsKey(rec.lender)) {
				map.put(rec.lender, new Balance(rec.lender));
			}
			map.get(rec.borrower).balance -= rec.amount;
			map.get(rec.lender).balance += rec.amount;
		}
		List<Balance> lst = new ArrayList<>(map.values());
		int min = 0;
		for (Balance b : lst) {
			min = Math.min(min, b.balance);
		}

		if (min == 0) {
			return Arrays.asList("Nobody has a negative balance");
		}
		List<String> res = getNames(lst, min);
		Collections.sort(res);
		return res;
	}

	public static void main(String[] arg) {
		
		List<Debtor> records = new ArrayList<>();
		records.add(new Debtor("Alice", "Beth",	2));
		records.add(new Debtor("Beth","Alice", 2));
		records.add(new Debtor("Casey","Alice", 5));
		records.add(new Debtor("Beth","Casey", 7)); 
		records.add(new Debtor("Alice","Beth", 4)); 
		records.add(new Debtor("Alice","Casey",4)); 
		
		System.out.println(debtors(records));
	}

}
