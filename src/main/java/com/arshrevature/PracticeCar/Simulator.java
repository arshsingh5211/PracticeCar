package com.arshrevature.PracticeCar;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Simulator {

	public static void main(String[] args) {
		Car myVan = new Car(new CarDetailed(45, "2015 Honda Odyssey", "black", "charcoal", BigDecimal.valueOf(45000), LocalDate.of(2017, 10, 26)), new Engine(34, 4, 2.4), new FuelTank (46, "regular", 97), new Transmission (43, true, 4), new Wheels(23, 17.6));
		myVan.getFuelTank().getGas(myVan);
		myVan.getWheels().useSpare(myVan);
		myVan.run();
		
		Car mySUV = new Car(new CarDetailed(76, "2018 Toyota Rav4", "silver", "leather", BigDecimal.valueOf(19000), LocalDate.of(2020, 5, 01)), new Engine(99, 6, 3.5), new FuelTank (99, "regular", 46), new Transmission (99, false, 6), new Wheels(99, 19.6));
		mySUV.getCarDetailed().damagePresent(mySUV);
		mySUV.getWheels().useSpare(mySUV);
		mySUV.getWheels().replaceSpare(mySUV);
		mySUV.getWheels().replaceSpare(mySUV);
		mySUV.run();
		
		Car myRogue = new Car(new CarDetailed(99, "2019 Nissan Rogue SL", "black", "leather", BigDecimal.valueOf(18000), LocalDate.of(2019, 12, 05)), new Engine(97, 4, 3.5), new FuelTank(99, "Regular", 99), new Transmission(96, true, 4), new Wheels(100, 17.6));
		myRogue.getCarDetailed().setInitialSellDate(LocalDate.of(2020, 1, 3));
		myRogue.run();
	}
}


