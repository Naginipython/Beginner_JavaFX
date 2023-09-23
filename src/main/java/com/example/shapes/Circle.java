package com.example.shapes;

public class Circle extends Shape {
	private double radius;

	// -----------Getters/Setters----------
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		if (radius >= 0)
			this.radius = radius;
		else
			throw new RuntimeException("Bad Radius Specified " + radius);
	}

	// ----------Constructors---------------
	public Circle() {
		this(1);
	}

	public Circle(double radius) {
		this(radius, "white", false);
	}

	public Circle(double radius, String color, boolean isFilled) {
		super(color, isFilled);
		this.setRadius(radius);
	}

	// ----------User Methods---------------
	@Override
	public double getPerimeter() {
		return (2 * Math.PI * this.radius);
	}

	@Override
	public double getArea() {
		return Math.PI * this.radius * this.radius;
	}

	@Override
	public boolean draw() {
		return true;
	}

	public String toString() {
		return "This CIRCLE has a radius of " + this.radius+"\n"+super.toString();
	}

}
