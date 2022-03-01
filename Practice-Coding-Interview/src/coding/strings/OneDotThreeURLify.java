package coding.strings;

import java.io.IOException;

import helper.fast.io.Reader;

/*
Write a method to replace all spaces in a string with '%20'. You may assume that the string 
has sufficient space at the end to hold the additional characters, and that you are given the "true" 
length of the string. (Note: if implementing in Java, please use a character array so that you can 
perform this operation in place.) 

EXAMPLE 
Input:  "Mr John Smith    ", 13 
Output: "Mr%20John%20Smith"
 */

public class OneDotThreeURLify {

	public static void main(String[] args) throws IOException {
		Reader reader = new Reader();
		char[] inputArray = reader.readLine().toCharArray();
		int trueLength = reader.nextInt();

		int countSpaces = 0;
		for (int i = 0; i < trueLength; i++)
			if (inputArray[i] == ' ')
				countSpaces++;

		int index = trueLength + countSpaces * 2;

		for (int i = trueLength - 1; i >= 0; i--) {
			if (inputArray[i] == ' ') {
				inputArray[index - 1] = '0';
				inputArray[index - 2] = '2';
				inputArray[index - 3] = '%';
				index = index - 3;
			} else {
				inputArray[index - 1] = inputArray[i];
				index--;
			}
		}
		System.out.println(inputArray);
	}

}
