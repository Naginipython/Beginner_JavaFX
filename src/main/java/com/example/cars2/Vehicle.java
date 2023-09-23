package com.example.cars2;

public class Vehicle {
	private String make;
	private String model;
	private int year;
	private int speed;
	private int distance;
	
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int minutes) {
		int speed = this.getSpeed();
		int currentDistance = this.getDistance();
		int newDistance = speed * minutes;
		newDistance /= 60;
		this.distance = newDistance + currentDistance;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		if (speed > 120)
			this.speed = 120;
		else if (speed < 0)
			this.speed = 0;
		else
			this.speed = speed;
	}

	public Vehicle(String make, String model, int year) {
		this.setMake(make);
		this.setModel(model);
		this.setYear(year);
		this.setSpeed(0);
		this.setDistance(0);
	}
	
	public Vehicle(String make, String model, String year) {
		this(make,model,Integer.parseInt(year));
	}
	
	public void adjustCarSpeed() {
		int random = (int) (Math.random() * 4);
		int currentSpeed = this.getSpeed();
		switch (random) {
			case 0:
			case 1:
				accelerate(currentSpeed);
				break;
			case 2:
				brake(currentSpeed);
				break;
		}
	}
	
	private void accelerate(int currentSpeed) {
		this.setSpeed(currentSpeed+10);
	}
	
	private void brake(int currentSpeed) {
		this.setSpeed(currentSpeed-10);
	}
	
	public void reset() {
		this.distance = 0;
		this.speed = 0;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", this.make, this.model);
	}


}
