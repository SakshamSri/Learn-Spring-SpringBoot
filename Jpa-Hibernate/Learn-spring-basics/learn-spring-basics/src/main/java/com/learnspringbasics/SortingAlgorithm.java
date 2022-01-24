package com.learnspringbasics;

public interface SortingAlgorithm {
	
	public int[] sort(int[] numbers);
	
	default void swapHelper(int[] array, int i, int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
