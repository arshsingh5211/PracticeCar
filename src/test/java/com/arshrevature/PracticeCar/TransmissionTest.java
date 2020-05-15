package com.arshrevature.PracticeCar;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.arshrevature.PracticeCar.Transmission;


public class TransmissionTest {
	private Transmission sut;

	@Before
	public void setUp() throws Exception {
		System.out.println("Run the test");
	}

	public static void assertEqualsRelative(double expected, double actual, double relativeError) {
	    assertEquals(expected, actual, relativeError * expected);
	}
	

	@Test
	public void testTransmissionConstructor() {
		sut = new Transmission (49, true, 4);
		assertEquals("The condition of this test Transmission should be at 49% of its original state.", 49, sut.getCondition());
		assertEquals("The transmission is automatic.", true, sut.getIsTransmissionAutomatic());
		assertEquals("This is a 4-speed transmission.", 4, sut.getNumberOfSpeeds());
	}
	
	@Test
	public void testIsTransmissionAutomaticReturnsTrue() {
		sut = new Transmission (99, true, 9);
		boolean result = sut.getIsTransmissionAutomatic();
		assertEquals("This is an automatic transmission.", true, result);
	}

	@Test
	public void testIsTransmissionAutomaticReturnsFalse() {
		sut = new Transmission (98, false, 6);
		boolean result = sut.getIsTransmissionAutomatic();
		assertEquals("This is a manual transmission.", false, result);
	}

	@Test
	public void testGetNumberOfSpeeds() {
		sut = new Transmission (8, true, 4);
		int result = sut.getNumberOfSpeeds();
		assertEquals("This is a 4-speed transmission.", 4, result);
	}

	@Test
	public void testGetCondition() {
		sut = new Transmission (76, false, 6);
		int result = sut.getCondition();
		assertEquals("The condition of this test Transmission should be at 76% of its original state.", 76, result);
	}


}
