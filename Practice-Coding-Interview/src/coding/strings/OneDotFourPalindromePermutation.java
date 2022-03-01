package coding.strings;

import java.io.IOException;
import java.util.ArrayList;

import helper.fast.io.Reader;

/*
 * Given a string, write a function to check if it is a permutation of 
a palindrome. A palindrome is a word or phrase that is the same forwards and backwards. A 
permutation is a rearrangement of letters. The palindrome does not need to be limited to just 
dictionary words. 
EXAMPLE 
Input: Tact Coa 
Output: True (permutations: "taco cat'; "atco eta·; etc.)
 */
public class OneDotFourPalindromePermutation {

	private static boolean checkPalindrome(String str) {
		int first = 0, last = str.length() - 1;
		while (first++ < last--) {
			if (str.charAt(first) != str.charAt(last)) {
				return false;
			}
		}
		return true;
	}

	private static void generatePalindrome(String str, String prefix, ArrayList<String> permutations) {
		if (str.length() == 0) {
			permutations.add(prefix);
		} else {
			for (int i = 0; i < str.length(); i++) {
				String first = str.substring(0, i);
				String last = str.substring(i + 1);
				generatePalindrome(first + last, prefix + str.charAt(i), permutations);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Reader reader = new Reader();
		String str = reader.readLine();
		str = str.toLowerCase();

		/*
		 * Method 1 = O(n^2 * n!) boolean ans = false; ArrayList<String> list = new
		 * ArrayList<>(); generatePalindrome(str, "", list); for (String s : list) { //
		 * System.out.println(s); if (checkPalindrome(s)) { ans = true; break; } }
		 * System.out.print(ans);
		 */

		// Method 2 - O(n) plus constant space of a-z
		/*
		 * int countOfWordsHavingOddNumber = 0; int[] characters = new int[26]; for (int
		 * i = 0; i < str.length(); i++) { char ch = str.charAt(i); if (ch > 96 && ch <
		 * 123) characters[ch - 'a']++; } for (int i = 0; i < characters.length; i++) {
		 * if (characters[i] % 2 != 0) countOfWordsHavingOddNumber++; } if
		 * (countOfWordsHavingOddNumber > 1) System.out.println(false); else
		 * System.out.println(true);
		 */

		// Method 3 - O(n) with extra space = one int var
		int bitVector = 0;
		for (char c : str.toCharArray()) {
			if (c >= 97 && c <= 122) {
				int cint = c - 97;
				int mask = 1 << cint;
				if ((bitVector & mask) == 0)
					bitVector |= mask;
				else {
					bitVector &= ~mask;
				}
			}
		}
		if ((bitVector == 0) || (bitVector & (bitVector - 1)) == 0)
			System.out.print(true);
		else {
			System.out.print(false);
		}
	}

}
