package com.arshrevature.PracticeCar;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WheelsTest {
	private Wheels sut;

	@Before
	public void setUp() throws Exception {
		System.out.println("Run the test");
	}

	public static void assertEqualsRelative(double expected, double actual, double relativeError) {
	    assertEquals(expected, actual, relativeError * expected);
	}
	
	@Test
	public void testWheelsConstructor() {
		Wheels sut = new Wheels (49, 17.6);
		assertEquals("The condition of this test Wheels should be at 49% of its original state.", 49, sut.getCondition());
		assertEquals("The tire size is 17.6 inches", 17.6, sut.getTireSize(), 0.1); 
	}
	
	/*
	@Test // how do I test a void method that prints something??
	public void testFunctionWithConditionUnder50AndASpareTire() {
		sut = new Wheels (49, 17.6);
		sut.function();
		Assert.assertSame("The tire size is 17.6 inches, at least one tire needs to be reinflated, and the spare tire is still there.", sut.function());	
	}

	@Test
	public void testFunctionWithConditionOver50AndASpareTire() {
		sut = new Wheels (73, 14);
		sut.function();
		System.out.println("The tire size is 14 inches, all tires are inflated, and the spare tire is still there.");		
	}*/

	@Test
	public void testGetTireSize() {
		sut = new Wheels (98, 9999999);
		double result = sut.getTireSize();
		Assert.assertEquals("The tire size should be 9999999" , 9999999, result, 0.01);
	}

	@Test
	public void testGetIsTirePressureFullWithWheelsInBadCondition() {
		sut = new Wheels (17, 14);
		boolean result = sut.getIsTirePressureFull();
		Assert.assertEquals("Tire pressure should not be full if condition is under 50", false, result);
	}
	
	@Test
	public void testGetIsTirePressureFullWithWheelsInGoodCondition() {
		sut = new Wheels (100, 20.2);
		boolean result = sut.getIsTirePressureFull();
		Assert.assertEquals("Tire pressure should be full if condition is over 50", true, result);
	}

	@Test
	public void testGetSpareLeftBeforeUsingSpare() {
		sut = new Wheels (98, 20);
		int result = sut.getSpareLeft();
		Assert.assertEquals("As the wheels are constructed, the default should be one spare in trunk.", 1, result);
	}

	@Test
	public void testGetSpareLeftAfterUsingSpare() {
		sut = new Wheels (76, 13);
		Car pracCar = new Car(new CarDetailed(45, "test", "test", "test", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01)), new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), sut);
		sut.useSpare(pracCar);
		int result = sut.getSpareLeft();
		Assert.assertEquals("After you use a spare, there should not be any spares left in trunk.", 0, result);
	}

	@Test
	public void testGetSpareLeftAfterReplacingSpare() {
		sut = new Wheels (73, 26.4);
		Car pracCar = new Car(new CarDetailed(45, "test", "test", "test", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01)), new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), sut);
		sut.useSpare(pracCar);
		sut.replaceSpare(pracCar);
		int result = sut.getSpareLeft();
		Assert.assertEquals("After you use a spare and then replace it, there should be one spare left in trunk.", 1, result);
	}

}
