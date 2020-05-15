package com.arshrevature.PracticeCar;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class CarDetailed extends CarPart implements Functional {
	
	private String carInfo;
	private boolean isAccidentFreeOnCarfax;
	private String exteriorColor;
	private String interiorColor;
	private BigDecimal startingPrice;
	private LocalDate initialSellDate;
	
	public CarDetailed (int condition, String carInfo, String exterior, String interior, BigDecimal startingPrice, LocalDate dateSold) { //do i even need anything besides default constructor?
		super(condition) ;
		this.carInfo = carInfo;
		this.exteriorColor = exterior;
		this.interiorColor = interior;
		this.startingPrice = startingPrice;
		this.isAccidentFreeOnCarfax = true;
		this.initialSellDate = dateSold;
	}
	
	public void function() {
		System.out.println("This is a " + carInfo + " with a " + exteriorColor + " exterior color and " + interiorColor + " interior.");
		String lux = "";
		if (isLuxury()) {
			lux = "a luxury model, valued at ";
		}
		else lux = "a great price, valued at ";
		
		String priceUpdate = "";
		if (!startingPrice.equals(calculateRetailPrice())) {
			priceUpdate = "Recently lowered price! ";
		}
	
		String accident = "";
		if (isAccidentFreeOnCarfax) {
			accident = "Accident free!";
		}
		System.out.println("It is " + lux + "$" + calculateRetailPrice() + "! " + priceUpdate + accident);
	}


	public boolean getIsAccidentFreeOnCarfax() {
		return isAccidentFreeOnCarfax;
	}

	public void damagePresent(Car thisCar) {
		isAccidentFreeOnCarfax = false;
		System.out.println("Oh no! " + thisCar.getCarDetailed().getCarInfo() + " was in an accident.");
	}
	
	public BigDecimal getStartingPrice() {
		return startingPrice;
	}

	public LocalDate getInitialSellDate() {
		return initialSellDate.withDayOfMonth(1);
	}

	public void setInitialSellDate(LocalDate initialSellDate) {
		this.initialSellDate = initialSellDate.withDayOfMonth(1);
	}

	public String getCarInfo() {
		return carInfo;
	}
	
	public BigDecimal calculateRetailPrice() {
		BigDecimal retailPrice = BigDecimal.valueOf(0);
		int monthsOnLot = (int) Period.between(initialSellDate, LocalDate.now().withDayOfMonth(1)).toTotalMonths();
		if (monthsOnLot <= 1) {
			retailPrice = startingPrice;
		}
		if (monthsOnLot > 1 && monthsOnLot <= 3 && isAccidentFreeOnCarfax) {
			retailPrice = startingPrice.subtract(BigDecimal.valueOf(300));
		}
		if (monthsOnLot > 1 && monthsOnLot <= 3 && !isAccidentFreeOnCarfax) {
			retailPrice = startingPrice.subtract(BigDecimal.valueOf(500));
		}
		if (monthsOnLot > 3 && monthsOnLot <= 5 && isAccidentFreeOnCarfax) {
			retailPrice = startingPrice.subtract(BigDecimal.valueOf(700));
		}
		if (monthsOnLot > 3 && monthsOnLot <= 5 && !isAccidentFreeOnCarfax) {
			retailPrice = startingPrice.subtract(BigDecimal.valueOf(1000));
		}
		if (monthsOnLot > 5) {
			retailPrice = startingPrice.subtract(BigDecimal.valueOf(1200));	
		}
		if (monthsOnLot > 11) {
			retailPrice = startingPrice.multiply(BigDecimal.valueOf(0.75));
		}
		return retailPrice;
	}

	public boolean isLuxury() {
		if (startingPrice.doubleValue() > 49999) {
			return true;
		}
			return false;
	}


	public String getExteriorColor() {
		return exteriorColor;
	}


	public String getInteriorColor() {
		return interiorColor;
	}

}
