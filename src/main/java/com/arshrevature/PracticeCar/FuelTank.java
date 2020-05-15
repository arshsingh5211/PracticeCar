package com.arshrevature.PracticeCar;

public class FuelTank extends CarPart implements Functional {
	private String fuelType;
	private boolean isSafeToDrive;
	private int gasLeftInTank;

	public FuelTank(int condition, String fuelType, int gasLeftInTank) {
		super(condition);
		this.gasLeftInTank = gasLeftInTank;
		this.fuelType = fuelType;
	}
	
	public void function() {
		String gasMeter = "";
		if (gasLeftInTank == 100) {
			gasMeter = " is completely full";
		}
		if (gasLeftInTank < 100 && gasLeftInTank > 75) {
			gasMeter = " is almost full";
		}
		if (gasLeftInTank < 75 && gasLeftInTank > 50) {
			gasMeter = " is half-full";
		}
		if (gasLeftInTank < 50 && gasLeftInTank > 25) {
			gasMeter = " is less than half-full";
		}
		if (gasLeftInTank < 25) {
			gasMeter = " is almost empty";
		}
		System.out.println("The fuel tank uses " + fuelType.toLowerCase() + " fuel, the tank" + gasMeter + ".");
	}
	
	
	int getGasLeftInTank() {
		return gasLeftInTank;
	}
	
	public String getFuelType() {
		return fuelType;
	}
	

	public boolean getIsItSafeToDrive(boolean capIsSecure, boolean noFuelLeaking ) {
		if (capIsSecure && noFuelLeaking) {
			isSafeToDrive = true;
		}
		else isSafeToDrive = false;
		return isSafeToDrive;
	} 
	
	public void getGas(Car thisCar) {
		if (gasLeftInTank < 100) {
			gasLeftInTank = 100;
			System.out.println(thisCar.getCarDetailed().getCarInfo().toString() + ": Fuel tank is now full!");
		}
		else System.out.println(thisCar.getCarDetailed().getCarInfo().toString() + ": Fuel tank is already full. No gas needed!");
	}

}
