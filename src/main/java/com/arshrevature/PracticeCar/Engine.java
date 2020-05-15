package com.arshrevature.PracticeCar;

public class Engine extends CarPart implements Functional {
	
	private int numberOfCylinders;
	private boolean isTheCheckEngineLightOn;
	private double volumeOfCylinders;
	
	public Engine (int condition, int numberOfCylinders, double volumeOfCylinders) {
		super(condition);
		this.numberOfCylinders = numberOfCylinders;
		this.volumeOfCylinders = volumeOfCylinders;
	}

	public void function() {
		String engineLight = "";
		if (!isTheCheckEngineLightOn) {
			engineLight = "and the check engine light indicates there are no problems with the engine!";
		}
		else engineLight = "and the Check Engine light is on, please get that diagnosed as soon as possible.";
		System.out.println("This engine has " + numberOfCylinders + " cylinders with a " + volumeOfCylinders + "L engine " + engineLight);
		super.status();
	}
	
	public int getNumberOfCylinders () {
		return numberOfCylinders;
	}
	
	public boolean getIsTheCheckEngineLightOn() {
		if (super.getCondition() <= 50) {
			return true;
		}
			return false;
	}

	public double getVolumeOfCylinders() {
		return volumeOfCylinders;
	}

}
