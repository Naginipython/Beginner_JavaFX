package com.example.shapes;

import java.util.Date;

public class Shape {
	private boolean isFilled;
	private String color;
	private Date dateCreated;
	// private String shape;

	// ------Getters and setters-------
	public boolean isFilled() {
		return isFilled;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		if (!color.equals("") && color != null)
			this.color = color;
		else
			throw new RuntimeException("Bad Color Specified " + color);
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	// ---------Constructors-------------
	public Shape() {
		this("white", false);
	}

	public Shape(String color, boolean isFilled) {
		this.setColor(color);
		this.setFilled(isFilled);
		this.dateCreated = new Date();
	}

	// ----------User Methods---------------
	public double getPerimeter() {
		return -1;
	}

	public double getArea() {
		return -1;
	}

	public boolean draw() {
		return false;
	}

	public String toString() {
		return "This SHAPE is " + this.color + " color and has isFilled set as " + this.isFilled + ", set at "
				+ this.dateCreated;
	}

	public double perimeter() {
		// TODO Auto-generated method stub
		return 0;
	}
}
