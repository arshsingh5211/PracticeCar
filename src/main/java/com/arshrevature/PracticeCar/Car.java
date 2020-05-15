package com.arshrevature.PracticeCar;

import java.util.ArrayList;
import java.util.List;

public class Car {
	private CarDetailed carDet;
	private Engine e;
	private FuelTank ft;
	private Transmission tm;
	private Wheels wh;
	private ArrayList<CarPart> partList = new ArrayList<CarPart>();
	

	public Car(CarDetailed CarDetailed, Engine Engine, FuelTank FuelTank, Transmission Transmission, Wheels Wheels) {
		this.carDet = CarDetailed;
		this.e = Engine;
		this.ft = FuelTank;
		this.tm = Transmission;
		this.wh = Wheels;
		
		partList.add(CarDetailed);
		partList.add(Engine);
		partList.add(FuelTank);
		partList.add(Transmission);
		partList.add(Wheels);
		
	}
	
	public void run() {
		System.out.println("----------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < partList.size(); i++) {
			partList.get(i).function();
		}
	}

	public CarDetailed getCarDetailed() {
		return carDet;
	}

	public Engine getEngine() {
		return e;
	}

	public FuelTank getFuelTank() {
		return ft;
	}

	public Transmission getTransmission() {
		return tm;
	}

	public Wheels getWheels() {
		return wh;
	}

	public List <CarPart> getPartList() {
		return partList;
	}
}
