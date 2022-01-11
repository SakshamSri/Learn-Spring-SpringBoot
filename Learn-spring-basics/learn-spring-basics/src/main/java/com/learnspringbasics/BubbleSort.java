package com.learnspringbasics;

import org.springframework.stereotype.Component;

@Component
public class BubbleSort implements SortingAlgorithm {

	public int[] sort(int[] numbers) {
		System.out.println("Bubble Sort implementation : ");
		sortHelper(numbers);
		return numbers;
	}

	private void sortHelper(int[] numbers) {
		for (int i = 0; i < numbers.length - 1; i++) {
			boolean flag = false;
			for (int j = 0; j < numbers.length - i - 1; j++) {
				if (numbers[j] > numbers[j + 1]) {
					swapHelper(numbers, j, j + 1);
					flag = true;
				}
			}
			if (flag == false)
				break;
		}
	}

}
