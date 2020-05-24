package org.problems.datastructures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.problems.utils.Utils;

/**
 * https://leetcode.com/problems/reorder-data-in-log-files/
 * 
 * You have an array of logs. Each log is a space delimited string of words.
 * 
 * For each log, the first word in each log is an alphanumeric identifier. Then,
 * either:
 * 
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits. We will call
 * these two varieties of logs letter-logs and digit-logs. It is guaranteed that
 * each log has at least one word after its identifier.
 * 
 * Reorder the logs so that all of the letter-logs come before any digit-log.
 * The letter-logs are ordered lexicographically ignoring identifier, with the
 * identifier used in case of ties. The digit-logs should be put in their
 * original order.
 * 
 * Return the final order of the logs.
 * 
 * Example 1:
 * 
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit
 * dig","let3 art zero"] 
 * 
 * Output: ["let1 art can","let3 art zero","let2 own kit
 * dig","dig1 8 1 5 1","dig2 3 6"]
 * 
 * 
 * Constraints:
 * 
 * 0 <= logs.length <= 100 
 * 3 <= logs[i].length <= 100 
 * logs[i] is guaranteed to
 * have an identifier, and a word after the identifier.
 * 
 * 
 */
public class ReorderData {
	
	public static int letterLogs = 0;
	public static int digitLogs = 1;
	
	static class Log {

		public String[] parts;
		public int type; 
		public String orig;
		public String key;
		public String suffix;
		
		public Log(String str) {
			this.orig = str;
			this.parts = str.split(" ");
			if (parts[1].charAt(0) >= '0' && parts[1].charAt(0) <= '9') {
				type = digitLogs;
			}else {
				type = letterLogs;
			}
			key = parts[0];
			parts[0] = "";
			suffix = String.join(" ", parts);
		}
		
		public String getSuffix() {
			return suffix;
		}
		
	}

	public static String[] reorderLogFiles(String[] logs) {
		List<Log> digital = new ArrayList<>();
		
		List<Log> letter = new ArrayList<>();
		
		for (String s : logs) {
			Log log = new Log(s);
			if (log.type == digitLogs) {
				digital.add(log);
			}else {
				letter.add(log);
			}
		}
		Comparator<Log> byWord = (o,p) -> o.getSuffix().compareToIgnoreCase(p.getSuffix());
		Comparator<Log> byKey = (o,p) -> o.key.compareToIgnoreCase(p.key);
		letter.sort(byWord.thenComparing(byKey));
		String[] res = new String[logs.length];
		int idx = 0;
		for (Log log : letter) {
			res[idx++] = log.orig;
		}
		for (Log log : digital) {
			res[idx++] = log.orig;
		}
		
		return res;
        
    }

	public static void main(String[] arg) {

		String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
		Utils.print(reorderLogFiles(logs));

		String[] logs1 = {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo","a2 act car"};
		Utils.print(reorderLogFiles(logs1));

	}

}
