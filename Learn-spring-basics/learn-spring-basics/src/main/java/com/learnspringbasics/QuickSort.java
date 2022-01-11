package com.learnspringbasics;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class QuickSort implements SortingAlgorithm {

	public int[] sort(int[] numbers) {
		System.out.println("Quick Sort implementation : ");
		int low = 0, high = numbers.length - 1;
		sortHelper(numbers, low, high);
		return numbers;
	}

	private void sortHelper(int[] numbers, int low, int high) {
		if (low < high) {
			int pivot = partition(numbers, low, high);
			sortHelper(numbers, low, pivot - 1);
			sortHelper(numbers, pivot + 1, high);
		}
	}

	private int partition(int[] numbers, int low, int high) {
		int pivot = numbers[high];
		int i = low - 1;
		for (int j = low; j <= high - 1; j++) {
			if (numbers[j] <= pivot) {
				i++;
				swapHelper(numbers, i, j);
			}
		}
		swapHelper(numbers, i + 1, high);
		return i + 1;
	}
}
