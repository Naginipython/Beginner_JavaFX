package com.example.shapes;

public class Rectangle extends Shape {
	private double length;
	private double width;

	// -----------Getters/Setters----------
	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		if (length >= 0)
			this.length = length;
		else
			throw new RuntimeException("Bad Length Specified " + length);
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		if (width >= 0)
			this.width = width;
		else
			throw new RuntimeException("Bad Width Specified " + width);
	}

	// ----------Constructors---------------
	public Rectangle() {
		this(1, 1);
	}

	public Rectangle(double length, double width) {
		this(length, width, "white", false);
	}

	public Rectangle(double length, double width, String color, boolean isFilled) {
		super(color, isFilled);
		this.setLength(length);
		this.setWidth(width);
	}

	// ----------User Methods---------------
	@Override
	public double getPerimeter() {
		return (this.length * 2) + (this.width * 2);
	}

	@Override
	public double getArea() {
		return this.length * this.width;
	}

	@Override
	public boolean draw() {
		return true;
	}

	public String toString() {
		return "This RECTANGLE has a length of " + this.length + " and a width of " + this.width+
				"\n"+super.toString();
	}
}
