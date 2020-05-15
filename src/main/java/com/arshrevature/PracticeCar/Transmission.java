package com.arshrevature.PracticeCar;

public class Transmission extends CarPart implements Functional {
	private boolean isTransmissionAutomatic;
	private int numberOfSpeeds;
	
	public Transmission (int condition, boolean isTransmissionAutomatic, int numberOfSpeeds) {
		super(condition);
		this.isTransmissionAutomatic = isTransmissionAutomatic;
		this.numberOfSpeeds = numberOfSpeeds;
	}

	public void function() {
		String autoOrManual = "";
		if (isTransmissionAutomatic) {
			autoOrManual = "an automatic";
		}
		else autoOrManual = "a manual";
		System.out.print("This is " + autoOrManual + " " + numberOfSpeeds + "-speed transmission. ");	
		super.status();
	}
	
	public boolean getIsTransmissionAutomatic() {
		return isTransmissionAutomatic;
	}

	public int getNumberOfSpeeds() {
		return numberOfSpeeds;
	}

}