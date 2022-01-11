package com.learnspringbasics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BinarySearchImplementation {

	private int[] numbers;

	@Autowired
	private SortingAlgorithm algorithm;

	public int[] getNumbers() {
		return this.numbers;
	}

	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	public int binarySearch(int elementToBeSearched) {

		this.algorithm.sort(numbers);

		int low = 0, high = this.numbers.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (this.numbers[mid] == elementToBeSearched)
				return mid + 1;
			else if (this.numbers[mid] > elementToBeSearched)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}

}
