package com.learnspringbasics;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LearnSpringBasics {

	public static void main(String[] args) {

		// BinarySearchImplementation obj = new BinarySearchImplementation(new int[]
		// {90, 23, 12, 67, 82}, new BubbleSort());

		ConfigurableApplicationContext run = SpringApplication.run(LearnSpringBasics.class, args);

		BinarySearchImplementation bean = run.getBean(BinarySearchImplementation.class);
		bean.setNumbers(new int[] { 54, 67, 12, 45, 23 });
		int result = bean.binarySearch(12);
		System.out.println("Sorted array : " + Arrays.toString(bean.getNumbers()));
		System.out.println("Position of 12 in sorted array : " + result);
	}

}
