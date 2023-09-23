package com.example.polygon;
/*
 * Name: Benjamin McCann
 * Date: 4/04/19
 * Course Number: CSC-112
 * Course Name: Intermediate Java
 * Problem Number: 9
 * Email: bnmccann0001@gmail.com
 * Create a program that adds and subtracts sides of a polygon
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class CreatePolygon extends Application {
	private static int APP_HEIGHT = 400;
	private static int APP_WIDTH = 500;
	private FlowPane main;
	private Button btnPlus;
	private Button btnMinus;
	private int sideNum;

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = setupGUI();

		btnPlus.setOnAction(e -> {
			sideNum++;
			paint(sideNum);
		});
		btnMinus.setOnAction(e -> {
			if((sideNum-1)>1) {
				sideNum--;
				paint(sideNum);
			}
		});

		Scene scene = new Scene(bp, APP_WIDTH, APP_HEIGHT);
		primaryStage.setTitle("Create Polygon App");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}

	private BorderPane setupGUI() {
		// MAIN
		main = new FlowPane(500, 500);
		main.setAlignment(Pos.CENTER);
		main.setPadding(new Insets(5, 5, 5, 5));
		main.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt");
		sideNum = 3;
		paint(sideNum);

		FlowPane test = new FlowPane(5, 5);
		test.setAlignment(Pos.CENTER);
		btnPlus = new Button("+");
		btnMinus = new Button("-");
		test.getChildren().addAll(btnPlus, btnMinus);

		// Taken from Prof. Silvestri's code
		Label lblStatus = new Label("Create Polygon GUI by Ben M");
		FlowPane credit = new FlowPane(5, 5);
		credit.setAlignment(Pos.BASELINE_CENTER);
		credit.setPadding(new Insets(5, 5, 10, 5));
		credit.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt");
		credit.getChildren().addAll(lblStatus);

		VBox vert = new VBox(20);
		vert.getChildren().addAll(main, test);
		vert.setAlignment(Pos.CENTER);

		BorderPane bp = new BorderPane();
		bp.setCenter(vert);
		bp.setBottom(credit);
		return bp;
	}

	public static void main(String[] args) {
		launch();
	}

	private void paint(int sides) {
		main.getChildren().clear();
		Polygon polygon = new Polygon();
		polygon.setFill(Color.WHITE);
		polygon.setStroke(Color.BLACK);
		double radius = 125;
		double xCenter = 200;
		double yCenter = 250 / 2;

		polygon.getPoints().addAll(xCenter + radius, yCenter);
		polygon.getPoints().addAll((xCenter + radius * Math.cos(2 * Math.PI / sides)),
				(yCenter - radius * Math.sin(2 * Math.PI / sides)));
		for (int polygonPoint = 2; polygonPoint <= sides; polygonPoint++) {
			polygon.getPoints().addAll((xCenter + radius * Math.cos(polygonPoint * 2 * Math.PI / sides)),
					(yCenter - radius * Math.sin(polygonPoint * 2 * Math.PI / sides)));
		}
		main.getChildren().addAll(polygon);
	}

}
