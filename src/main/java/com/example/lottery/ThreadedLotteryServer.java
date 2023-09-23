package com.example.lottery;

/*
 * Name: Benjamin McCann
 * Date: 5/1/19
 * Course Number: CSC-112
 * Course Name: Intermediate Java
 * Problem Number: HW-13
 * Email: bnmccann0001@gmail.com
 * Create a Server for the lottery quick picks
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ThreadedLotteryServer {
	private static final int PORTNUMBER = 5004;

	static void pStr(String p) {
		System.out.println(p);
	}

	static void runServer() {
		ServerSocket server;
		Socket connection;

		try {
			pStr("Creating Server Socket " + PORTNUMBER + " . . . ");
			server = new ServerSocket(PORTNUMBER);
			pStr("SUCCESS!!!");

			while (true) {
				pStr("Waiting for connection.");
				connection = server.accept();
				pStr("Done");
				ThreadConnect t = new ThreadConnect(connection);
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class ThreadConnect extends Thread {
		private Socket connection;

		ThreadConnect(Socket x) {
			connection = x;
		}

		public void run() {
			PrintWriter output = null;
			Scanner input = null;
			try {
				// Get input and output streams.
				input = new Scanner(connection.getInputStream());
				output = new PrintWriter(connection.getOutputStream());

				String lottery;
				int[] numbers;
				int lucky;
				lottery = input.nextLine();
				if (lottery.equalsIgnoreCase("PowerBall")) {
					numbers = getNumbers(69);
					numbers = shuffleNum(numbers);
					lucky = 26;
				} else if (lottery.equalsIgnoreCase("MegaMillions")) {
					numbers = getNumbers(70);
					numbers = shuffleNum(numbers);
					lucky = 25;
				} else if (lottery.equalsIgnoreCase("Luck for Life")) {
					numbers = getNumbers(48);
					numbers = shuffleNum(numbers);
					lucky = 18;
				} else {
					numbers = getNumbers(1);
					lucky = -1;
				}
				
				//display
				int[] winNums = {numbers[0], numbers[1], numbers[2], numbers[3], numbers[4]};
				winNums = organizeNum(winNums);
				String winStr = printNum(winNums);
				output.println(winStr);
				output.flush();
				int random = (int) (Math.random() * lucky);
				output.println(random);
				output.flush();
			} catch (Exception e) {
				pStr(e.getMessage());
			} finally {
				try {
					input.close();
				} catch (Exception e) {
				}
				try {
					output.close();
				} catch (Exception e) {
				}
				try {
					connection.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	private static int[] getNumbers(int num) {
		int[] numbers = new int[num];
		for (int i=1; i <= num; i++)
			numbers[i-1] = i;
		return numbers;
	}

	private static int[] shuffleNum(int[] num) {
		int index = 0;
		int temp = 0;
		for (int i = 0; i < num.length; i++) {
			index = (int) (Math.random() * 43);
			temp = num[i];
			num[i] = num[index];
			num[index] = temp;
		}
		return num;
	}

	private static int[] organizeNum(int[] num) {
		int prev = 0;
		for (int i = 0; i < num.length; i++) {
			for (int j = i + 1; j < num.length; j++) {
				if (num[i] > num[j]) {
					prev = num[i];
					num[i] = num[j];
					num[j] = prev;
				}
			}
		}
		return num;
	}

	private static String printNum(int[] num) {
		String str = "";
		for (int i = 0; i < num.length; i++) {
			if (i > 0)
				str += ", ";
			str += num[i];
		}
		return str;
	}

	public static void main(String args[]) {
		runServer();
	}
}
