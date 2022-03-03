package coding.strings;

import java.io.IOException;

import helper.fast.io.Reader;

/*
 *  There are three types of edits that can be performed on strings: insert a character, 
remove a character, or replace a character. Given two strings, write a function to check if they are 
one edit (or zero edits) away. 
EXAMPLE 
pale, ple -> true 
pales, pale -> true 
pale, bale -> true 
pale, bake -> false
 */
public class OneDotFiveOneAway {

	private static boolean oneReplaceAway(String str1, String str2) {
		boolean diffChars = false;
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				if (diffChars) {
					return false;
				}
				diffChars = true;
			}
		}
		return true;
	}

	private static boolean oneInsertAway(String str1, String str2) {
		int ind1 = 0, ind2 = 0;
		while (ind1 < str1.length() && ind2 < str2.length()) {
			if (str1.charAt(ind1) != str2.charAt(ind2)) {
				if (ind1 != ind2) {
					return false;
				}
				ind2++;
			} else {
				ind1++;
				ind2++;
			}
		}
		return true;
	}

	private static boolean doOperation(String str1, String str2) {
		if (str1.length() == str2.length()) {
			return oneReplaceAway(str1, str2);
		} else if (str1.length() + 1 == str2.length()) {
			return oneInsertAway(str1, str2);
		} else if (str1.length() == str2.length() + 1) {
			return oneInsertAway(str2, str1);
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		Reader reader = new Reader();
		// edit
		// replace = ~insert
		String string1 = reader.readLine();
		String string2 = reader.readLine();

		boolean res = doOperation(string1, string2);
		System.out.println(res);
	}

}
