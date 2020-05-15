package com.arshrevature.PracticeCar;

public class Wheels extends CarPart implements Functional {
	private double tireSize;
	private boolean isTirePressureFull;
	private int spareLeft;
	
	public Wheels (int condition, double tireSize) {
		super(condition);
		this.tireSize = tireSize;
		spareLeft = 1;
		isTirePressureFull = true;
	}
	
	public void function() {
		String newSpare = "";
		if (spareLeft > 0) {
			newSpare = " and the spare tire is still there.";
		}
		else newSpare = " and no spare tires left in trunk!";
		String tirePressureUpdate = "";
		if (isTirePressureFull) {
			tirePressureUpdate = " all tires are inflated,";
		}
		else tirePressureUpdate = " at least one tire needs to be reinflated,";
		
		System.out.println("The tire size is " + tireSize + " inches," + tirePressureUpdate + newSpare);		
	}

	public double getTireSize() {
		return tireSize;
	}

	public boolean getIsTirePressureFull() {
		if (super.getCondition() < 50) {
			return !isTirePressureFull;
		}
			return isTirePressureFull;
	}
	
	public void useSpare(Car thisCar) {
		if (spareLeft == 1) {
			spareLeft -= 1;
			System.out.println(thisCar.getCarDetailed().getCarInfo().toString() + ": Tire changed! Replace spare tire ASAP!");
		}
		else System.out.println(thisCar.getCarDetailed().getCarInfo().toString() + ": No spare left in trunk :( Call AAA for assistance.");
	}

	public int getSpareLeft() {
		return spareLeft;
	}
	
	public void replaceSpare(Car thisCar) {
		if (spareLeft == 0) {
			this.spareLeft++;
			System.out.println(thisCar.getCarDetailed().getCarInfo().toString() + ": Spare replaced!");
		}
		else System.out.println(thisCar.getCarDetailed().getCarInfo().toString() + " already has a spare in trunk. No more room!");
	}

}
