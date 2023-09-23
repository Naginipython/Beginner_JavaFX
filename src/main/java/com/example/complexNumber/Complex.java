/*
 * Name: Ben McCann
 * Date: 2/10/19
 * Course Number: CSC-112
 * Course Name: Intermediate Java
 * Problem Number: 3
 * Email: bnmccann0001@student.stcc.edu
 * Create a complex number calculator
 */

package com.example.complexNumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Complex {

	// Makes the numbers unchangeable. Not sure if you want this, but email me if
	// I'm wrong.
	final private double REAL;
	final private double IMAG;

	// No setter since I don't wanna change doubles.
	public double getReal() {
		return REAL;
	}

	public double getImag() {
		return IMAG;
	}

	// Standard constructors
	public Complex(double real, double imag) {
		this.REAL = real;
		this.IMAG = imag;
	}

	public Complex(double real) {
		this(real, 0);
	}

	public Complex() {
		this(0, 0);
	}

	public Complex(String complex) {
		String regex = "[+-]?(\\d+(\\.\\d*)?|\\.\\d+)";
		Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher m = p.matcher(complex);
		int count = 0;
		String real = null;
		String imag = null;
		while (m.find()) {
			System.out.println("Full match: " + m.group(0));
			if (count == 0)
				real = m.group(0);
			else
				imag = m.group(0);
			count++;
		}
		this.REAL = Double.parseDouble(real);
		this.IMAG = Double.parseDouble(imag);
	}

	// Finds the Real and Imag of a complex if they were added
	public Complex add(Complex c) {
		double real = this.getReal() + c.getReal();
		double imag = this.getImag() + c.getImag();
		return new Complex(real, imag);
	}

	// Finds the Real and Imag of a complex if they were subtracted
	public Complex subtract(Complex c) {
		double real = this.getReal() - c.getReal();
		double imag = this.getImag() - c.getImag();
		return new Complex(real, imag);
	}

	// Finds the Real and Imag of a complex if they were multiplied
	public Complex multiply(Complex c) {
		double real = (this.getReal() * c.getReal()) - (this.getImag() * c.getImag());
		double imag = (this.getReal() * c.getImag()) + (c.getReal() * this.getImag());
		return new Complex(real, imag);
	}

	// Finds the Real and Imag of a complex if they were divided
	public Complex divide(Complex c) {
		double real = ((this.getReal() * c.getReal()) + (this.getImag() * c.getImag()))
				/ ((c.getReal() * c.getReal()) + (c.getImag() + c.getImag()));
		double imag = ((this.getImag() * c.getReal()) - (this.getReal() * c.getImag()))
				/ ((c.getReal() * c.getReal()) + (c.getImag() + c.getImag()));
		return new Complex(real, imag);
	}

	// Finds the Real and Imag of a complex if they were negated
	public Complex negate() {
		double real = -this.getReal();
		double imag = -this.getImag();
		return new Complex(real, imag);
	}

	// Finds the conjugate Real and Imag of a complex
	public Complex conjugate() {
		double imag = -this.getImag();
		return new Complex(this.getReal(), imag);
	}

	// Finds the absolute value Real and Imag of a complex
	public double abs() {
		double abs = Math.sqrt(this.getReal() * this.getReal() + this.getImag() * this.getImag());
		return abs;
	}

	// Finds the distance Real and Imag of a complex
	public double distance(Complex c) {
		double dist = Math
				.sqrt(Math.pow((this.getReal() - c.getReal()), 2) + Math.pow((this.getImag() - c.getImag()), 2));
		return dist;
	}

	// Finds if two complexes are nearly equal.
	// Not really sure if works, to be honest
	public boolean equals(Complex c) {
		Complex greatest = this.findGreatest(c);
		if ((this.distance(c) / greatest.abs()) < (Math.pow(10, -6)))
			return true;
		return false;
	}

	// Finds the greater one for above method
	public Complex findGreatest(Complex c) {
		if (this.greaterThan(c))
			return this;
		else {
			return c;
		}
	}

	// Finds if one complex is greater than the other
	public boolean greaterThan(Complex c) {
		double c1 = this.abs();
		double c2 = c.abs();
		if (c1 > c2)
			return true;
		else
			return false;
	}

	// Finds if one complex is less than the other
	public boolean lessThan(Complex c) {
		double c1 = this.abs();
		double c2 = c.abs();
		if (c1 < c2)
			return true;
		else
			return false;
	}

	// Forms the complex into a string
	public String toString() {
		if (this.getImag() < 0)
			return String.format("%.1f - %.1fi", this.getReal(), -this.getImag());
		else
			return String.format("%.1f + %.1fi", this.getReal(), this.getImag());
	}
}
