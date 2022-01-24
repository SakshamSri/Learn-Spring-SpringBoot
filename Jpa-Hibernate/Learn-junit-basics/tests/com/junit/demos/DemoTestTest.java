package com.junit.demos;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DemoTestTest {

	@BeforeClass
	public static void beforeAllTests() {
		System.out.println("Before all tests");
	}

	@AfterClass
	public static void afterAllTests() {
		System.out.println("After all tests");
	}

	@Before
	public void beforeEachTest() {
		System.out.println("Before each test");
	}

	@After
	public void afterEachTest() {
		System.out.println("After each test");
	}

	@Test
	public void test() {
		System.out.println("test");
		DemoTest demoTest = new DemoTest();
		assertEquals(demoTest.squareroot(9), 3, 0);
	}
}
