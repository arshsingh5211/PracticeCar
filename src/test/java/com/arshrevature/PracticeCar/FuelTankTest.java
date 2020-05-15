package com.arshrevature.PracticeCar;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FuelTankTest {
	private FuelTank sut;

	@Before
	public void setUp() throws Exception {
		System.out.println("Run the test");
	}
	
	@Test
	public void testFuelTankConstructor() {
		sut = new FuelTank (99, "test fuel", 50);
		assertEquals("The condition of this test Fuel Tank should be at 99% of its original state.", 99, sut.getCondition());
		assertEquals("Tank uses 'test fuel'.", "test fuel", sut.getFuelType());
		assertEquals("Gas tank is half-empty.", 50, sut.getGasLeftInTank());
	}
	
	@Test
	public void testGetGasLeftInTankFullGas() {
		sut = new FuelTank(100, "test", 100);
		assertEquals("Gas tank is full.", 100, sut.getGasLeftInTank());
	}

	@Test
	public void testGetFuelTypeRegular() {
		sut = new FuelTank(0, "Regular", 0);
		assertEquals("Fuel is of regular type.", "Regular", sut.getFuelType());
	}
	
	@Test
	public void testGetIsItSafeToDriveCapIsSecureAndNoFuelLeaking() {
		sut = new FuelTank(100, "test", 100);
		boolean result = sut.getIsItSafeToDrive(true, true);
		assertEquals(("Cap is secure and there is no fuel leaking, so fuel tank is safe to drive!"), true, result); 
	}

	@Test
	public void testGetIsItSafeToDriveFuelIsLeaking() {
		sut = new FuelTank(100, "test", 100);
		boolean result = sut.getIsItSafeToDrive(true, false);
		assertEquals(("Fuel tank is leaking so it is not safe to drive!"), false, result); 
	}
	
	@Test
	public void testGetIsItSafeToDriveCapNotSecure() {
		sut = new FuelTank(100, "test", 100);
		boolean result = sut.getIsItSafeToDrive(false, true);
		assertEquals(("Fuel tank's cap not secure so it is not safe to drive!"), false, result); 
	}

	@Test
	public void testGetGasLeftInTankAfterGettingGas() {
		sut = new FuelTank(100, "test", 13);
		Car pracCar = new Car(new CarDetailed(45, "test", "test", "test", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01)), new Engine(9, 9, 9.9), sut, new Transmission (99, true, 9999), new Wheels (99, 99));
		pracCar.getFuelTank().getGas(pracCar);
		int result = sut.getGasLeftInTank();
		Assert.assertEquals("After getting gas, the amount in tank should be 100.", 100, result);
	}

}
