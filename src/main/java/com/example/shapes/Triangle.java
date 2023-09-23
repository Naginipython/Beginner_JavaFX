package com.example.shapes;
/*
 * Name: Benjamin McCann
 * Date: 2/21/19
 * Course Number: CSC-112
 * Course Name: Intermediate Java
 * Problem Number: 5
 * Email: bnmccann0001@gmail.com
 * Create a child class of a Geometric shape parent
 */

public class Triangle extends Shape {
	private double side1;
	private double side2;
	private double side3;

	// -----------Getters/Setters----------
	public double getSide1() {
		return side1;
	}

	public void setSide1(double side1) {
		if (side1 >= 0)
			this.side1 = side1;
		else
			throw new RuntimeException("Bad Length Specified " + side1);
	}

	public double getSide2() {
		return side2;
	}

	public void setSide2(double side2) {
		if (side2 >= 0)
			this.side2 = side2;
		else
			throw new RuntimeException("Bad Length Specified " + side2);
	}

	public double getSide3() {
		return side3;
	}

	public void setSide3(double side3) {
		if (side3 >= 0)
			this.side3 = side3;
		else
			throw new RuntimeException("Bad Length Specified " + side3);
	}

	// ----------Constructors---------------
	public Triangle() {
		this(1, 1, 1);
	}

	public Triangle(double side1, double side2, double side3) {
		this(side1, side2, side3, "white", false);
	}

	public Triangle(double side1, double side2, double side3, String color, boolean isFilled) {
		super(color, isFilled);
		this.setSide1(side1);
		this.setSide2(side2);
		this.setSide3(side3);
	}

	// ----------User Methods---------------
	@Override
	public double getPerimeter() {
		if (this.isValid())
			return this.getSide1() + this.getSide2() + this.getSide3();
		else
			return -1;
	}

	@Override
	public double getArea() {
		if (this.isValid()) {
			double side1 = this.getSide1();
			double side2 = this.getSide2();
			double side3 = this.getSide3();
			double s = (side1 + side2 + side3) / 2;
			return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
		} else
			return -1;
	}

	@Override
	public boolean draw() {
		if (this.isValid())
			return true;
		else
			return false;
	}

	public String toString() {
		if (this.isValid())
			return "This TRIANGLE has the sides of " + this.side1 + ", " + this.side2 + ", " + this.side3 + "\n"
					+ super.toString();
		else
			return "This TRIANGLE is invalid";
	}

	private boolean isValid() {
		double side1 = this.getSide1();
		double side2 = this.getSide2();
		double side3 = this.getSide3();
		if (!((side1 + side2) > side3))
			return false;
		if (!((side1 + side3) > side2))
			return false;
		if (!((side3 + side2) > side1))
			return false;
		return true;
	}

}
