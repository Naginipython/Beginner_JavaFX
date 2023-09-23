package com.example.cars;/*
 *  Application Code for the Race Car App
 *  
 *  Prof. A.C. Silvestri
 *  CSCI-211 Intermediate Java Programming
 *  1/27/2019
 */

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Platform;

public class NormalRaceCarGUI extends Application {
	private static int DISTANCE = 500;

	private Pane raceCourse;
	private Button btnStart;
	private Button btnClear;
	private Rectangle rectCar1;
	private Rectangle rectCar2;
	private Label raceStatus;

	private Vehicle car1;
	private Vehicle car2;
	
	private Timer timer;
	private Task task;

	@Override
	// Override the start method in the Application class
	public void start(Stage primaryStage) {
		BorderPane bp = setupGUI();

		btnStart.setOnAction(e -> {
			raceStatus.setText("Race in Progress ...");
			car1 = new Vehicle("Ford", "Thunderbird", 2004);
			car2 = new Vehicle("Toyota", "Carolla", 2009);
			runTheRace(car1, car2);
		});
		btnClear.setOnAction(e -> {
			raceStatus.setText("");
			car1 = new Vehicle("Ford", "Thunderbird", 2004);
			car2 = new Vehicle("Toyota", "Carolla", 2009);
			rectCar1.setWidth(0);
			rectCar2.setWidth(0);
		});

		Scene scene = new Scene(bp, 600, 400);
		primaryStage.setTitle("Race Car App");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}

	private BorderPane setupGUI() {
		raceCourse = new Pane();
		raceCourse.setPrefSize(500, 250);
		raceCourse.setStyle(
				"-fx-padding: 10;" + 
		        "-fx-border-style: solid;" + 
				"-fx-border-width: 2;" + 
		        "-fx-border-color: blue;"
				);

		Line start = new Line(50, 0, 50, 250);
		Line finish = new Line(450, 0, 450, 250);
		Text txtCar1 = new Text(5, 80, "Car 1");
		txtCar1.setFill(Color.RED);
		Text txtCar2 = new Text(5, 180, "Car 2");
		txtCar2.setFill(Color.GREEN);

		rectCar1 = new Rectangle(0, 50);
		rectCar1.setStroke(Color.BLACK);
		rectCar1.setFill(Color.RED);
		rectCar1.setX(51);
		rectCar1.setY(50);
		rectCar2 = new Rectangle(0, 50);
		rectCar2.setStroke(Color.BLACK);
		rectCar2.setFill(Color.GREEN);
		rectCar2.setX(51);
		rectCar2.setY(150);
		
		raceCourse.getChildren().addAll(start, txtCar1, txtCar2, rectCar1, rectCar2, finish);

		raceStatus = new Label();
		raceStatus.setPrefSize(500, 40);
		raceStatus.setAlignment(Pos.BASELINE_CENTER);

		btnStart = new Button("Start Race");
		btnClear = new Button("Clear Race");

		FlowPane cpane = new FlowPane(20, 10);
		cpane.setAlignment(Pos.CENTER);
		cpane.setPadding(new Insets(5, 5, 5, 5));
		cpane.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt");
		cpane.getChildren().addAll(raceCourse, raceStatus, btnStart, btnClear);

		Label lblStatus = new Label("Race Car GUI by Ben M");
		FlowPane bpane = new FlowPane(5, 5);
		bpane.setAlignment(Pos.BASELINE_CENTER);
		bpane.setPadding(new Insets(5, 5, 10, 5));
		bpane.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt");
		bpane.getChildren().addAll(lblStatus);

		BorderPane bp = new BorderPane();
		bp.setCenter(cpane);
		bp.setBottom(bpane);
		return bp;
	}
	
	
	private void runTheRace(Vehicle car1, Vehicle car2) {
		raceStatus.setText("Race in Progress ...");
		timer = new Timer();
		task = new Task();
		timer.schedule(task, 0, 50);
	}

	private void updateCarBars(Vehicle car1, Vehicle car2) {
		double distance;
		double barlength;
		distance = car1.getDistance();
		barlength = 400.0 * distance / DISTANCE;
		this.rectCar1.setWidth(barlength);
		distance = car2.getDistance();
		barlength = 400.0 * distance / DISTANCE;
		this.rectCar2.setWidth(barlength);
	}
	
	private void updateGUI(Vehicle car1, Vehicle car2) {
		if (car1.getDistance() >= DISTANCE || car2.getDistance() >= DISTANCE) {
			if (car1.getDistance() > car2.getDistance())
				raceStatus.setText("Car 1: " + car1 + " Wins!!! Loser drove "+car2.getDistance()+" miles.");
			else
				raceStatus.setText("Car 2: " + car2 + " Wins!!! Loser drove "+car1.getDistance()+" miles.");
			task.cancel();
			timer.cancel();
			return;
		}

		car1.adjustCarSpeed();
		car2.adjustCarSpeed();
		car1.setDistance(1);
		car2.setDistance(1);
		updateCarBars(car1, car2);
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	private class Task extends TimerTask {
		public void run() {
			Platform.runLater(() -> {
				updateGUI(car1, car2);
			});
		}
	}
}