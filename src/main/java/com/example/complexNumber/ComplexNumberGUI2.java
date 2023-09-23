package com.example.complexNumber;
/*
 * Name: Ben McCann
 * Date: 3/2/19
 * Course Number: CSC-112
 * Course Name: Intermediate Java
 * Problem Number: 6
 * Email: bnmccann0001@student.stcc.edu
 * Make a GUI for Complex Number akin to how Prof. Silvestri did
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ComplexNumberGUI2 extends Application {
	private static int APP_HEIGHT = 200;
	private static int APP_WIDTH = 900;

	private Label complexLb1;
	private Label complexLb2;
	private TextField txt1;
	private TextField txt2;
	private Button btnEquals;
	private ChoiceBox<String> cb;
	private TextField answer;
	private Button btnClear;

	public void start(Stage primaryStage) {
		BorderPane bp = setupGUI();

		btnEquals.setOnAction(e -> {
			Complex c1 = new Complex(txt1.getText());
			Complex c2 = new Complex(txt2.getText());
			answers(c1, c2, cb.getValue());
		});

		btnClear.setOnAction(e -> {
			txt1.setText("");
			txt2.setText("");
			answer.setText("");
		});

		Scene scene = new Scene(bp, APP_WIDTH, APP_HEIGHT);
		primaryStage.setTitle("Complex Numbers App");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}

	private BorderPane setupGUI() {
		complexLb1 = new Label("A");
		txt1 = new TextField();

		complexLb2 = new Label("B");
		txt2 = new TextField();

		btnEquals = new Button("=");
		btnClear = new Button("Clear");

		cb = new ChoiceBox<String>(FXCollections.observableArrayList("+", "-", "*", "/", "==", "dist", "<", ">"));
		cb.setValue("+");
		answer = new TextField();
		answer.setEditable(false);

		HBox together = new HBox(10);
		together.getChildren().addAll(complexLb1, txt1, cb, complexLb2, txt2, btnEquals, answer);
		together.setAlignment(Pos.CENTER);

		// this means Together, (for real) Now
		VBox togetherFRNow = new VBox(10);
		togetherFRNow.getChildren().addAll(together, btnClear);
		togetherFRNow.setAlignment(Pos.CENTER);

		// MAIN
		FlowPane main = new FlowPane(20, 10);
		main.setAlignment(Pos.CENTER);
		main.setPadding(new Insets(5, 5, 5, 5));
		main.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt");
		main.getChildren().addAll(togetherFRNow);

		// Taken from Prof. Silvestri's code
		Label lblStatus = new Label("Complex Numbers GUI v2.0 by Ben M");
		FlowPane credit = new FlowPane(5, 5);
		credit.setAlignment(Pos.BASELINE_CENTER);
		credit.setPadding(new Insets(5, 5, 10, 5));
		credit.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt");
		credit.getChildren().addAll(lblStatus);

		BorderPane bp = new BorderPane();
		bp.setCenter(main);
		bp.setBottom(credit);
		return bp;
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void answers(Complex c1, Complex c2, String value) {
		if (value.equals("+"))
			answer.setText("" + c1.add(c2));
		if (value.equals("-"))
			answer.setText("" + c1.subtract(c2));
		if (value.equals("*"))
			answer.setText("" + c1.multiply(c2));
		if (value.equals("/"))
			answer.setText("" + c1.divide(c2));
		if (value.equals("dist"))
			answer.setText("" + c1.distance(c2));
		if (value.equals("=="))
			answer.setText("" + c1.equals(c2));
		if (value.equals("<"))
			answer.setText("" + c1.greaterThan(c2));
		if (value.equals(">"))
			answer.setText("" + c1.lessThan(c2));
	}

}