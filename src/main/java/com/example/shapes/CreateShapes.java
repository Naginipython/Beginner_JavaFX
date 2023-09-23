package com.example.shapes;

/*
 * Name:
 * Date:
 * Course Number:
 * Course Name:
 * Problem Number:
 * Email: 
 * Short Description of the Problem
 */

import java.util.Scanner;

public class CreateShapes {
	
	
	//**********************************************
	
	private static void process(Scanner sc, String args[]) {
//		System.out.print("What color?: ");
//		String color = sc.next();
//		System.out.print("Is it Filled?(t/f): ");
//		String filled = sc.next();
//		
//		boolean isFilled;
//		if (filled.equalsIgnoreCase("t"))
//			isFilled = true;
//		else if (filled.equalsIgnoreCase("f"))
//			isFilled = false;
//		else {
//			System.out.println("Okay, your filled is false.");
//			isFilled = false;
//		}
//		sc.nextLine();
//		Shape test = new Shape(color, isFilled);
		Shape test = new Triangle(3, 4, 100);
		System.out.println(test);
		System.out.println(test.getPerimeter());
		System.out.println(test.getArea());
		System.out.println(test.draw());
	}
	
	//**********************************************
	
	private static boolean doThisAgain(Scanner sc, String prompt) {
		System.out.print(prompt);
		String doOver = sc.nextLine();
		return doOver.equalsIgnoreCase("Y");
	}
	
	//**********************************************
	
	public static void main(String args[]) {
		final String TITLE = "Ben's Shape Creator";
		final String CONTINUE_PROMPT = "Do this again? [y/N] ";
		
		System.out.println("Welcome to " + TITLE);
		Scanner sc = new Scanner(System.in);
		do {
			process(sc, args);
		} while (doThisAgain(sc, CONTINUE_PROMPT));
		sc.close();
		System.out.println("Thank you for using " + TITLE);
	}

}