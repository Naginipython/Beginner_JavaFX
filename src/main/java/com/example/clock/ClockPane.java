package com.example.clock;
/*
 * Name: Benjamin McCann
 * Date: 4/15/19
 * Course Number: CSC-112
 * Course Name: Intermediate Java
 * Problem Number: HW-10
 * Email: bnmccann0001@gmail.com
 * Use the clocks from Chapter 14 and make it editable
 */

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class ClockPane extends Pane {
	private int hour;
	private int minute;
	private int second;
	private boolean boolOn;

	/** Construct a default clock with the current time */
	public ClockPane() {
		setCurrentTime();
	}

	/** Construct a clock with specified hour, minute, and second */
	public ClockPane(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.boolOn = true;
	}

	/** Return hour */
	public int getHour() {
		return hour;
	}

	/** Set a new hour */
	public void setHour(int hour) {
		this.hour = hour;
		paintClock();
	}

	/** Return minute */
	public int getMinute() {
		return minute;
	}

	/** Set a new minute */
	public void setMinute(int minute) {
		this.minute = minute;
		paintClock();
	}

	/** Return second */
	public int getSecond() {
		return second;
	}

	/** Set a new second */
	public void setSecond(int second) {
		this.second = second;
		paintClock();
	}

	/** Return boolOn */
	public boolean getBoolOn() {
		return boolOn;
	}

	/** Set a new boolOn */
	public void setBoolOn(boolean boolOn) {
		this.boolOn = boolOn;
		paintClock();
	}

	/* Set the current time for the clock */
	public void setCurrentTime() {
		// Construct a calendar for the current date and time
		Calendar calendar = new GregorianCalendar();

		// Set current hour, minute and second
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		this.boolOn = true;

		paintClock(); // Repaint the clock
	}

	/** Paint the clock */
	private void paintClock() {
		// Initialize clock parameters
		double clockRadius = Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
		double centerX = getWidth() / 2;
		double centerY = getHeight() / 2;

		// Draw circle
		Circle circle = new Circle(centerX, centerY, clockRadius);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
		Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
		Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
		Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");

		// Angle Calculations
		int totalNoOfSeconds = 3600 * (this.hour % 12) + 60 * this.minute + this.second;
		double hourAngle = ((double) totalNoOfSeconds / 3600.0) * 2.0 * Math.PI / 12.0;
		int minuteSeconds = 60 * this.minute + this.second;
		double minuteAngle = ((double) minuteSeconds / 60.0) * 2.0 * Math.PI / 60.0;
		int seconds = this.second;
		double secondAngle = ((double) seconds) * 2.0 * Math.PI / 60.0;

		// Draw second hand
		double sLength = clockRadius * 0.8;
		double secondX = centerX + sLength * Math.sin(secondAngle);
		double secondY = centerY - sLength * Math.cos(secondAngle);
		Line sLine = new Line(centerX, centerY, secondX, secondY);
		sLine.setStroke(Color.RED);

		// Draw minute hand
		double mLength = clockRadius * 0.65;
		double xMinute = centerX + mLength * Math.sin(minuteAngle);
		double minuteY = centerY - mLength * Math.cos(minuteAngle);
		Line mLine = new Line(centerX, centerY, xMinute, minuteY);
		mLine.setStroke(Color.BLUE);

		// Draw hour hand
		double hLength = clockRadius * 0.5;
		double hourX = centerX + hLength * Math.sin(hourAngle);
		double hourY = centerY - hLength * Math.cos(hourAngle);
		Line hLine = new Line(centerX, centerY, hourX, hourY);
		hLine.setStroke(Color.GREEN);

		getChildren().clear();
		getChildren().addAll(circle, t1, t2, t3, t4, sLine, mLine, hLine);
	}

	@Override
	public void setWidth(double width) {
		super.setWidth(width);
		paintClock();
	}

	@Override
	public void setHeight(double height) {
		super.setHeight(height);
		paintClock();
	}

	public void tick() {
		if (this.getBoolOn()) {
			this.second++;
			if (this.second >= 60) {
				this.second = 0;
				this.minute++;
				if (this.minute >= 60) {
					this.minute = 0;
					this.hour++;
					if (this.hour >= 13)
						this.hour %= 12;
				}
			}
			paintClock();
		}
	}

	public void play() {
		this.setBoolOn(true);
	}

	public void pause() {
		this.setBoolOn(false);
	}

	public String toString() {
		return String.format("%d : %d : %d", this.getHour(), this.getMinute(), this.getSecond());
	}

}
