package com.arshrevature.PracticeCar;

abstract class CarPart implements Functional {
	private int condition = 100;
	private Car thisCar;
	
	public CarPart (int condition) {
		this.condition = condition;
	}
	
	public void status() {
		String rec = "";
		if (condition < 50) {
			rec = "Our recommendation is to take your car to the mechanic to get your " + CarPart.this.getClass().getSimpleName().toLowerCase() + " looked at immediately!";
		}
		else rec = "General maintenance is recommended at least once per year.";
		System.out.println(CarPart.this.getClass().getSimpleName() + " at " + condition + "% of original condition." + " " + rec);
	}

	public abstract void function();

	public int getCondition() {
		return condition;
	}

	public Car getThisCar() {
		return thisCar;
	}
}
