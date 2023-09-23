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

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DisplayClock extends Application {

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		BorderPane bp = mainBp();

		// Create a scene and place it in the stage
		Scene scene = new Scene(bp, 300, 250);
		primaryStage.setTitle("DisplayClock"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	private BorderPane mainBp() {
		// Create a clock and a label
		ClockPane clock = new ClockPane();
		Label lblCurrentTime = new Label("" + clock);

		// Tick-tock
		EventHandler<ActionEvent> tickHandler = e -> {
			clock.tick();
			lblCurrentTime.setText("" + clock);
		};

		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), tickHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();

		// Buttons & Boxes for Bot pane
		Button btnPlay = new Button("Play Watch");
		Button btnPause = new Button("Pause Watch");
		Button btnCurrent = new Button("Set Time: Current");

		// Bot Pane
		VBox botPane = new VBox(10);
		HBox btnPane = new HBox(10);
		btnPane.getChildren().addAll(btnPlay, btnPause, btnCurrent);
		btnPane.setAlignment(Pos.CENTER);
		botPane.getChildren().addAll(btnPane, lblCurrentTime);
		botPane.setAlignment(Pos.CENTER);
		botPane.setPadding(new Insets(5, 5, 10, 5));

		HBox editableTime = new HBox(10);
		editableTime.setAlignment(Pos.CENTER);
		TextField hour = new TextField();
		hour.setMaxWidth(30);
		hour.setPromptText("" + clock.getHour());
		TextField minute = new TextField();
		minute.setMaxWidth(30);
		minute.setPromptText("" + clock.getMinute());
		TextField second = new TextField();
		second.setMaxWidth(30);
		second.setPromptText("" + clock.getSecond());
		Button btnLoad = new Button("Set Time");
		editableTime.getChildren().addAll(hour, new Label(":"), minute, new Label(":"), second, btnLoad);

		btnPlay.setOnAction(e -> {
			clock.play();
			// make time un-editable
			botPane.getChildren().clear();
			botPane.getChildren().addAll(btnPane, lblCurrentTime);
		});

		btnPause.setOnAction(e -> {
			clock.pause();
			hour.setPromptText("" + clock.getHour());
			minute.setPromptText("" + clock.getMinute());
			second.setPromptText("" + clock.getSecond());
			// make the time editable
			botPane.getChildren().clear();
			botPane.getChildren().addAll(btnPane, editableTime);
		});

		btnCurrent.setOnAction(e -> {
			clock.setCurrentTime();
			// make time un-editable
			botPane.getChildren().clear();
			botPane.getChildren().addAll(btnPane, lblCurrentTime);
		});

		btnLoad.setOnAction(e -> {
			if (isInt(hour.getText()) && isInt(minute.getText()) && isInt(second.getText())) {
				clock.setHour(Integer.parseInt(hour.getText()));
				clock.setMinute(Integer.parseInt(minute.getText()));
				clock.setSecond(Integer.parseInt(second.getText()));
				hour.setPromptText("" + clock.getHour());
				minute.setPromptText("" + clock.getMinute());
				second.setPromptText("" + clock.getSecond());
			}
			hour.setText("");
			minute.setText("");
			second.setText("");
		});

		// Place clock and label in border pane
		BorderPane pane = new BorderPane();
		pane.setCenter(clock);
		pane.setTop(botPane);
		BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);
		return pane;
	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}