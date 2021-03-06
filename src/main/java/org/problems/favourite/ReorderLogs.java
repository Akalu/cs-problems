package org.problems.favourite;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Reorder Data in Log Files
 * 
 * You have an array of logs. Each log is a space delimited string of words.
 * 
 * For each log, the first word in each log is an alphanumeric identifier. Then,
 * either:
 * 
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * 
 */
public class ReorderLogs {
	
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

	public String[] reorderLogFiles(String[] logs) {
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

		System.out.println("D");

	}

}
