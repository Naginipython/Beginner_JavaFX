package com.example.shapes;
/*
 * Name: Benjamin McCann
 * Date:2/21/19
 * Course Number:CSC-112
 * Course Name:Intermediate Java
 * Problem Number:HW5 EXTRA
 * Email: bnmccann0001@student.stcc.edu
 * Create GUI for triangle
 */
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateShapesGUI extends Application {
	private static int APP_HEIGHT = 400;
	private static int APP_WIDTH = 700;

	private ChoiceBox<String> cb;
	private Button btnContinue;
	private Button btnEqual;
	private CheckBox chk;
	private FlowPane main;
	private TextField tf1;
	private TextField tf2;
	private TextField tf3;
	private Label txt1;
	private Label txt2;
	private Label txt3;
	private VBox box1;
	private VBox box2;
	private VBox box3;
	private HBox shapeBox;
	private String shape;

	private Label perimeter;
	private Label area;
	private Label draw;

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = setupGUI();

		btnContinue.setOnAction(e -> {
			// resets
			shape = cb.getValue();
			main.getChildren().clear();
			perimeter.setText("");
			area.setText("");
			draw.setText("");
			
			HBox nums = new HBox(10);
			nums.setAlignment(Pos.CENTER);
			VBox vert = new VBox(10);
			vert.setAlignment(Pos.CENTER);
			
			if (shape.equals("Rectangle")) {
				txt1.setText("Length");
				txt2.setText("Width");
				nums.getChildren().addAll(box1, box2);
			} else if (shape.equals("Circle")) {
				txt1.setText("Radius");
				nums.getChildren().addAll(box1);
			} else if (shape.equals("Triangle")) {
				txt1.setText("Side 1");
				txt2.setText("Side 2");
				txt3.setText("Side 3");
				nums.getChildren().addAll(box1, box2, box3);
			}
			vert.getChildren().addAll(shapeBox, nums, btnEqual, perimeter, area, draw);
			main.getChildren().addAll(vert);
		});

		btnEqual.setOnAction(e -> {
			Shape thing = null;
			if (shape.equals("Rectangle")) {
				thing = new Rectangle(Double.parseDouble(tf1.getText()), Double.parseDouble(tf2.getText()));
			} else if (shape.equals("Circle")) {
				thing = new Circle(Double.parseDouble(tf1.getText()));
			} else if (shape.equals("Triangle")) {
				thing = new Triangle(Double.parseDouble(tf1.getText()), Double.parseDouble(tf2.getText()), Double.parseDouble(tf3.getText()));
			}
			
			perimeter.setText("Perimeter: "+thing.getPerimeter());
			area.setText("Area: "+thing.getArea());
			draw.setText("Drawable: "+thing.draw());
		});

		Scene scene = new Scene(bp, APP_WIDTH, APP_HEIGHT);
		primaryStage.setTitle("Complex Numbers App");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}

	private BorderPane setupGUI() {
		tf1 = new TextField();
		tf2 = new TextField();
		tf3 = new TextField();
		txt1 = new Label();
		txt2 = new Label();
		txt3 = new Label();
		chk = new CheckBox("Is Filled?");
		btnEqual = new Button("=");
		perimeter = new Label();
		area = new Label();
		draw = new Label();

		// setting main VBoxes
		box1 = new VBox();
		box1.getChildren().addAll(txt1, tf1);
		box1.setAlignment(Pos.CENTER);
		box2 = new VBox();
		box2.getChildren().addAll(txt2, tf2);
		box2.setAlignment(Pos.CENTER);
		box3 = new VBox();
		box3.getChildren().addAll(txt3, tf3);
		box3.setAlignment(Pos.CENTER);

		VBox colorBox = new VBox(new Label("Color"), new TextField());
		colorBox.setAlignment(Pos.CENTER);
		shapeBox = new HBox(20);
		shapeBox.getChildren().addAll(colorBox, chk);
		shapeBox.setAlignment(Pos.CENTER);

		// Top Choice
		cb = new ChoiceBox<String>(FXCollections.observableArrayList("Rectangle", "Circle", "Triangle"));
		btnContinue = new Button("=>");
		FlowPane choose = new FlowPane(5, 5);
		choose.setAlignment(Pos.TOP_CENTER);
		choose.setPadding(new Insets(5, 5, 10, 5));
		choose.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt");
		choose.getChildren().addAll(cb, btnContinue);

		// MAIN
		main = new FlowPane(50, 50);
		main.setAlignment(Pos.CENTER);
		main.setPadding(new Insets(5, 5, 5, 5));
		main.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt");

		// Taken from Prof. Silvestri's code
		Label lblStatus = new Label("Create Shapes GUI by Ben M");
		FlowPane credit = new FlowPane(5, 5);
		credit.setAlignment(Pos.BASELINE_CENTER);
		credit.setPadding(new Insets(5, 5, 10, 5));
		credit.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt");
		credit.getChildren().addAll(lblStatus);

		BorderPane bp = new BorderPane();
		bp.setTop(choose);
		bp.setCenter(main);
		bp.setBottom(credit);
		return bp;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
