package com.example.cars2;
/*
 *  Application Code for the Race Car App
 *  
 *  Ben M and Brian F
 *  CSCI-211 Intermediate Java Programming
 *  2/5/2019
 */

import java.util.Arrays;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Platform;

public class RaceCarGUI extends Application {
	private static int DISTANCE = 500;

	// JavaFX Objects
	private Pane raceCourse;
	private Button btnStart;
	private Button btnClear;
	private Label raceStatus;

	// Ny new array Objects
	private Rectangle[] rectCar;
	private Vehicle[] cars;

	private Timer timer;
	private Task task;

	// -------------------------Start but for
	// real------------------------------------

	@Override
	// Override the start method in the Application class
	public void start(Stage primaryStage) {
		BorderPane bp = setupGUI();

		// Start Button Action
		btnStart.setOnAction(e -> {
			raceStatus.setText("Race in Progress ...");

			runTheRace(cars);
		});
		// Clear Button Action
		btnClear.setOnAction(e -> {
			raceStatus.setText("");
			for (int x = 0; x < rectCar.length; x++) {
				rectCar[x].setWidth(0);
				cars[x].reset();
			}
		});

		// Creates app screen [I think]
		Scene scene = new Scene(bp, 600, 400);
		primaryStage.setTitle("Race Car App");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}

	// --------------------SETTING UP AREA--------------------------------------

	private BorderPane setupGUI() {
		// Prompts user for car amount, 2 < x < 33
		TextInputDialog dialog = new TextInputDialog("2");
		dialog.setTitle("Car number");
		dialog.setHeaderText("Choose the amount of cars in the race (2-33)");
		dialog.setContentText("Car total:");
		Optional<String> result = dialog.showAndWait();
		int carNum = Integer.parseInt(result.get());

		if (carNum > 33) { // If more than 33 cars
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText(null);
			alert.setContentText("That's kinda a big race, bud.");

			alert.showAndWait();
			return null;
		} else if (carNum < 2) {// if less than 2
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText(null);
			alert.setContentText("That's a boring race, try again.");

			alert.showAndWait();
			return null;
		} else { // ------------------------- Now thats the ticket---------------------
			// Sets the arrays with a number
			rectCar = new Rectangle[carNum];
			cars = new Vehicle[carNum];
			Text[] txtCars = new Text[carNum];
			cars = promptCarType(carNum);

			// RaceCourse setup
			raceCourse = new Pane();
			raceCourse.setPrefSize(500, 251);
			raceCourse.setStyle("-fx-padding: 10;" + "-fx-border-style: solid;" + "-fx-border-width: 2;"
					+ "-fx-border-color: blue;");

			// Adds lines
			Line start = new Line(50, 0, 50, 250);
			Line finish = new Line(450, 0, 450, 250);

			// setup for all things cars
			for (int x = 0; x < carNum; x++) {
				// Car text positioning/Naming
				txtCars[x] = new Text(3, (250 / carNum) * x + 16, "Car " + (x+1));

				// Car rectangle positioning
				rectCar[x] = new Rectangle(0, 250 / carNum);
				rectCar[x].setStroke(Color.BLACK);
				rectCar[x].setX(51);
				rectCar[x].setY((250 / carNum) * x + 1);

				switch (x % 5) { // dont question I wanna have different colors god damn it
				case 0:
					txtCars[x].setFill(Color.RED);
					rectCar[x].setFill(Color.RED);
					break;
				case 1:
					txtCars[x].setFill(Color.LIME);
					rectCar[x].setFill(Color.LIME);
					break;
				case 2:
					txtCars[x].setFill(Color.BLUE);
					rectCar[x].setFill(Color.BLUE);
					break;
				case 3:
					txtCars[x].setFill(Color.VIOLET);
					rectCar[x].setFill(Color.VIOLET);
					break;
				case 4:
					txtCars[x].setFill(Color.AQUA);
					rectCar[x].setFill(Color.AQUA);
					break;
				}
			}

			// Adds elements to the Plane [I think]
			raceCourse.getChildren().addAll(start, finish);
			for (int x = 0; x < carNum; x++) { // puts in rectCars and txtCars
				raceCourse.getChildren().add(rectCar[x]);
				raceCourse.getChildren().add(txtCars[x]);
			}

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

			Label lblStatus = new Label("Race Car GUI by Ben M & Brian F");
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
	}

	// Times & starts race
	private void runTheRace(Vehicle[] cars) {
		raceStatus.setText("Race in Progress ...");
		timer = new Timer();
		task = new Task();
		timer.schedule(task, 0, 50);
	}

	// updates bars as after move [After updateGUI]
	private void updateCarBars(Vehicle[] cars) {
		double distance;
		double barlength;
		for (int x = 0; x < cars.length; x++) {
			distance = cars[x].getDistance();
			barlength = 400.0 * distance / DISTANCE;
			this.rectCar[x].setWidth(barlength);
		}
	}

	// [Start of task]
	private void updateGUI(Vehicle[] cars) {
		for (int x = 0; x < cars.length; x++) {
			if (cars[x].getDistance() >= DISTANCE) {
				raceStatus.setText("Car " + (x+1) + ": " + cars[x] + " Wins!!!");
				rectCar[x].setFill(Color.GOLD);
				task.cancel();
				timer.cancel();
				return;
			}
			cars[x].adjustCarSpeed();
			cars[x].setDistance(1);
		}
		updateCarBars(cars);
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
				updateGUI(cars);
			});
		}
	}

	public Vehicle[] promptCarType(int carNum) {
		Vehicle[] carTemp = new Vehicle[carNum];
		String[] getStrings = new String[carNum * 3];
		int count = 0;

		// Allows user to type in every make model and year, puts onto array
		for (int x = 0; x < carNum; x++) {
			for (int y = 0; y < 3; y++) {
				String text = "error";
				switch (y) {
				case 0:
					text = "Make";
					break;
				case 1:
					text = "Model";
					break;
				case 2:
					text = "Year";
					break;
				}
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Car" + (x + 1) + " " + text);
				dialog.setHeaderText("Car " + text+" (NOTE: type 'skip' to auto assign)");
				dialog.setContentText("Please type in a car " + text + ":");

				Optional<String> result = dialog.showAndWait();
				getStrings[count] = result.get();
				if (Arrays.stream(getStrings).anyMatch("skip"::equals)) {
					return defaultCars();
				}
				count++;
			}
		}

		// Puts array as multiple Vehicles
		count = 0;
		for (int x = 0; x < carNum; x++) {
			carTemp[x] = new Vehicle(getStrings[count], getStrings[count + 1], getStrings[count + 2]);
			count += 3;
		}

		return carTemp;

	}

	private Vehicle[] defaultCars() {
		Vehicle[] defaultCars = new Vehicle[cars.length];
		for (int x = 0; x < cars.length; x++) {
			defaultCars[x] = new Vehicle("Car", "" + (x+1) + "", 0);
		}

		return defaultCars;
	}
}