package com.arshrevature.PracticeCar;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class CarDetailedTest {
	private CarDetailed sut;
//	(condition, carInfo, exterior, interior, startingPrice) {

	@Before
	public void setUp() throws Exception {
		System.out.println("Run the test");
	}
	
	@Test
	public void testCarDetailedConstructor() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2020, 05, 01));
		assertEquals("The condition of this test CarDetailed should be at 76% of its original state.", 76, sut.getCondition());
		assertEquals("The car info should be '2020 Test Car'.","2020 Test Car", sut.getCarInfo());
		assertEquals("Exterior color should be white", "white", sut.getExteriorColor());
		assertEquals("Interior color should be white", "white", sut.getInteriorColor());
		assertEquals("Starting price should be $99,999.", BigDecimal.valueOf(99999), sut.getStartingPrice());
		assertEquals("The car was put on sale on May 1, 2020.", LocalDate.of(2020, 05, 01), sut.getInitialSellDate());
	}

	@Test
	public void testGetIsAccidentFreeOnCarfaxAfterConstructed() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2020, 05, 01));
		boolean result = sut.getIsAccidentFreeOnCarfax();
		assertEquals("Car has not been in an accident.", true, result);

	}
	
	@Test
	public void testGetIsAccidentFreeOnCarfaxAfterDamagePresent() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().damagePresent(pracCar);
		boolean result = sut.getIsAccidentFreeOnCarfax();
		assertEquals("Car has been in an accident!", false, result);
	}

	@Test
	public void testGetStartingPrice() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		BigDecimal result = sut.getStartingPrice();
		assertEquals("Car's initial retail price is $99,999.", BigDecimal.valueOf(99999), result);
	}

	@Test
	public void testGetInitialSellDate() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2020, 05, 01));
		LocalDate result = sut.getInitialSellDate();
		assertEquals("The car went on sale on 5/1/2020", LocalDate.of(2020, 05, 01), result);
	}

	@Test
	public void testGetCarInfo() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		String result = sut.getCarInfo();
		assertEquals("The car info should be '2020 Test Car'.","2020 Test Car", result);
	}

	@Test
	public void testCalculateRetailPriceIfSellDateWasTodayWithNoAccidentHistory() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.now());
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Starting price should be $99,999.", BigDecimal.valueOf(99999), result);
	}
	
	@Test
	public void testCalculateRetailPriceAtSellDateWithDamagePresent() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().setInitialSellDate(LocalDate.of(2020, 4, 1));
		pracCar.getCarDetailed().damagePresent(pracCar);
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Starting price should be $99,999.", BigDecimal.valueOf(99999), result);
	}
	
	@Test
	public void testCalculateRetailPriceAtExactly1MonthWithNoAccidentHistory() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().setInitialSellDate(LocalDate.of(2020, 4, 1));
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Current retail price should be the same as starting price if it's been on the lot for 1 month and has no accident history.", BigDecimal.valueOf(99999), result);
	}
	
	@Test
	public void testCalculateRetailPriceAtExactly1MonthWithDamagePresent() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().setInitialSellDate(LocalDate.of(2020, 4, 1));
		pracCar.getCarDetailed().damagePresent(pracCar);
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Current retail price should be the same as starting price if it's been on the lot for 1 month even with an accident history.", BigDecimal.valueOf(99999), result);
	}
	
	@Test
	public void testCalculateRetailPriceAtOver1MonthAndLessThan3MonthsWithNoAccidentHistory() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().setInitialSellDate(LocalDate.of(2020, 2, 14));
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Current retail price should be $300 less than starting price if it's been on the lot over 1 month and has no accident history.", BigDecimal.valueOf(99699), result);
	}
	
	@Test
	public void testCalculateRetailPriceAt3MonthsWithNoAccidentHistory() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().setInitialSellDate(LocalDate.of(2020, 2, 1));
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Current retail price should be $300 less than starting price if it's been on the lot for 3 months and has no accident history.", BigDecimal.valueOf(99699), result);
	}
	
	@Test
	public void testCalculateRetailPriceAtOver1MonthAndLessThan3MonthsWithDamagePresent() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().setInitialSellDate(LocalDate.of(2020, 2, 14));
		pracCar.getCarDetailed().damagePresent(pracCar);
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Current retail price should be $500 less than starting price if it's been on the lot over 1 month and has no accident history.", BigDecimal.valueOf(99499), result);
	}
	
	@Test
	public void testCalculateRetailPriceAt3MonthsWithDamagePresent() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().setInitialSellDate(LocalDate.of(2020, 2, 1));
		pracCar.getCarDetailed().damagePresent(pracCar);
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Current retail price should be $500 less than starting price if it's been on the lot for 3 months and has no accident history.", BigDecimal.valueOf(99499), result);
	}
	
	@Test
	public void testCalculateRetailPriceAtOver3MonthsAndLessThan5MonthsWithNoAccidentHistory() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().setInitialSellDate(LocalDate.of(2020, 1, 4));
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Current retail price should be $700 less than starting price if it's been on the lot for over 3 months and has no accident history.", BigDecimal.valueOf(99299), result);
	}
	
	@Test
	public void testCalculateRetailPriceAt5MonthsWithNoAccidentHistory() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().setInitialSellDate(LocalDate.of(2019, 12, 1));
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Current retail price should be $700 less than starting price if it's been on the lot for 3 months and has no accident history.", BigDecimal.valueOf(99299), result);
	}
	
	@Test
	public void testCalculateRetailPriceAtOver3MonthsAndLessThan5MonthsWithDamagePresent() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().setInitialSellDate(LocalDate.of(2020, 1, 4));
		pracCar.getCarDetailed().damagePresent(pracCar);
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Current retail price should be $1000 less than starting price if it's been on the lot for over 3 months and has no accident history.", BigDecimal.valueOf(98999), result);
	}
	
	@Test
	public void testCalculateRetailPriceAt5MonthsWithDamagePresent() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().setInitialSellDate(LocalDate.of(2019, 12, 1));
		pracCar.getCarDetailed().damagePresent(pracCar);
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Current retail price should be $1000 less than starting price if it's been on the lot for 3 months and has no accident history.", BigDecimal.valueOf(98999), result);
	}
	
	@Test
	public void testCalculateRetailPriceAtOverManyYearsWithNoAccidentHistory() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2017, 06, 13));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().setInitialSellDate(LocalDate.of(2012, 12, 7));
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Current retail price should be 75% of starting price if it's been on the lot over a year.", BigDecimal.valueOf(74999.25), result);
	}
	
	@Test
	public void testCalculateRetailPriceAtOverManyYearsWithDamagePresent() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2017, 10, 26));
		Car pracCar = new Car(sut, new Engine(9, 9, 9.9), new FuelTank (99, "test", 99), new Transmission (99, true, 9999), new Wheels(49, 17.6));
		pracCar.getCarDetailed().setInitialSellDate(LocalDate.of(2012, 12, 7));
		pracCar.getCarDetailed().damagePresent(pracCar);
		BigDecimal result = sut.calculateRetailPrice();
		assertEquals("Current retail price should be 75% of starting price if it's been on the lot over a year even with an accident history.", BigDecimal.valueOf(74999.25), result);
	}
	

	@Test
	public void testIsLuxuryWithStartingPriceOver50K() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		boolean result = sut.isLuxury();
		assertEquals("Since the car's starting price is over $49,999, it is a luxury car.", true, result);
	}
	
	@Test
	public void testIsLuxuryWithStartingPriceUnder50K() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(48999), LocalDate.of(2015, 01, 01));
		boolean result = sut.isLuxury();
		assertEquals("Since the car's starting price is under $49,999, it is NOT a luxury car.", false, result);
	}
	
	@Test
	public void testIsLuxuryWithStartingPriceAtCutOffPrice() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "white", BigDecimal.valueOf(49999), LocalDate.of(2015, 01, 01));
		boolean result = sut.isLuxury();
		assertEquals("Since the car's starting price is exactly $49,999, it is NOT a luxury car.", false, result);
	}

	@Test
	public void testGetExteriorColor() {
		sut = new CarDetailed (76, "2020 Test Car", "black", "white", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		String result = sut.getExteriorColor();
		assertEquals("Exterior color should be black", "black", result);
	}

	@Test
	public void testGetInteriorColor() {
		sut = new CarDetailed (76, "2020 Test Car", "white", "black", BigDecimal.valueOf(99999), LocalDate.of(2015, 01, 01));
		String result = sut.getInteriorColor();
		assertEquals("Interior color should be black", "black", result);
	}

}
