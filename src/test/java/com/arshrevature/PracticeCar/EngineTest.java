package com.arshrevature.PracticeCar;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EngineTest {
	private Engine sut;

	@Before
	public void setUp() throws Exception {
		System.out.println("Run the test");
	}

	public static void assertEqualsRelative(double expected, double actual, double relativeError) {
	    assertEquals(expected, actual, relativeError * expected);
	}
	

	@Test
	public void testEngineConstructor() {
		sut = new Engine (99, 6, 2.4);
		assertEquals("The condition of this test Engine should be at 99% of its original state.", 99, sut.getCondition());
		assertEquals("Engine has 6 cylinders", 6, sut.getNumberOfCylinders());
		assertEquals("Total volume of all cylinders is 2.4.", 2.4, sut.getVolumeOfCylinders(), 0.1);
	}

	@Test
	public void testGetNumberOfCylinders() {
		sut = new Engine (99, 4, 2.4);
		int result = sut.getNumberOfCylinders();
		assertEquals("Engine has 4 cylinders", 4, result);
	}

	@Test
	public void testGetIsTheCheckEngineLightOnWhileInGoodCondition() {
		sut = new Engine (99, 6, 3.5);
		boolean result = sut.getIsTheCheckEngineLightOn();
		assertEquals("Check engine light is off.", false, result);
	}
	
	@Test
	public void testGetIsTheCheckEngineLightOnWhileInPoorCondition() {
		sut = new Engine (19, 6, 3.5);
		boolean result = sut.getIsTheCheckEngineLightOn();
		assertEquals("Check engine light is on!", true, result);
	}

	@Test
	public void testGetVolumeOfCylinders() {
		sut = new Engine (99, 6, 2.4);
		double result = sut.getVolumeOfCylinders();
		assertEquals("Total volume of all cylinders is 2.4.", 2.4, result, 0.1);

	}

}
