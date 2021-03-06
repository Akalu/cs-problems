package org.problems.topology;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/employee-importance/
 * 
 * You are given a data structure of employee information, which includes the
 * employee's unique id, his importance value and his direct subordinates' id.
 * 
 * For example, employee 1 is the leader of employee 2, and employee 2 is the
 * leader of employee 3. They have importance value 15, 10 and 5, respectively.
 * Then employee 1 -> [1, 15, [2]], 
 * and employee 2 -> [2, 10, [3]], 
 * and employee 3 -> [3, 5, []].
 * Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.
 * 
 * Now given the employee information of a company, and an employee id, you need
 * to return the total importance value of this employee and all his
 * subordinates.
 * 
 * Example 1:
 * 
 * Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1 Output: 11 
 * 
 * Explanation:
 * Employee 1 has importance value 5, and he has two direct subordinates:
 * employee 2 and employee 3. They both have importance value 3. So the total
 * importance value of employee 1 is 5 + 3 + 3 = 11.
 * 
 * 
 * Note:
 * 
 * One employee has at most one direct leader and may have several subordinates.
 * The maximum number of employees won't exceed 2000.
 * 
 * 
 */
public class EmployeeImportance {

	static class Employee {
		public int id;
		public int importance;
		public List<Integer> subordinates;

		public Employee(int id, int importance, List<Integer> subordinates) {
			this.id = id;
			this.importance = importance;
			this.subordinates = subordinates;
		}

	};
	
	public static int getImportance(int id, Map<Integer,Employee> emplMap) {
		int totalImportance = 0;
		Employee e = emplMap.get(id);
		if (e != null) {
			totalImportance += e.importance;
			for (Integer emplId : e.subordinates) {
				totalImportance += getImportance(emplId, emplMap);
			}
		}
		return totalImportance;
	}
	

	public static int getImportance(List<Employee> employees, int id) {
		
		Map<Integer,Employee> emplMap = new HashMap<>();
		for (Employee e : employees) {
			emplMap.put(e.id, e);
		}
		return getImportance(id, emplMap);
	}

	public static void main(String[] arg) {
		
		Employee e1 = new Employee(1, 5, Arrays.asList(2,3));
		Employee e2 = new Employee(2, 3, Arrays.asList());
		Employee e3 = new Employee(3, 3, Arrays.asList());
		List<Employee> employees = Arrays.asList(e1,e2,e3);

		System.out.println(getImportance(employees, 1));

	}

}
