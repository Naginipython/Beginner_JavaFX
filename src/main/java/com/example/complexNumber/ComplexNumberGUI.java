package com.example.complexNumber;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ComplexNumberGUI extends Application {
	private static int APP_HEIGHT = 600;
	private static int APP_WIDTH = 700;

	private Label complex1;
	private Label complex2;
	private TextField txtReal1;
	private TextField txtImag1;
	private TextField txtReal2;
	private TextField txtImag2;
	private Label plus1;
	private Label plus2;
	private Label i1;
	private Label i2;
	private Button btnEquals;
	
	//Answer fields. I have them set as nothing here for less lines, and because it'll change in answers()
	private Label add = new Label("");
	private Label subtract = new Label("");
	private Label multiply = new Label("");
	private Label divide = new Label("");
	private Label negate1 = new Label("");
	private Label negate2 = new Label("");
	private Label conjugate1 = new Label("");
	private Label conjugate2 = new Label("");
	private Label abs1 = new Label("");
	private Label abs2 = new Label("");
	private Label equals = new Label("");
	private Label distance = new Label("");
	private Label greaterThan = new Label("");
	private Label lessThan = new Label("");
	
	public void start(Stage primaryStage) {
		BorderPane bp = setupGUI();
		
		btnEquals.setOnAction(e -> {
			double real;
			double imag;

			real = Double.parseDouble(txtReal1.getText());
			imag = Double.parseDouble(txtImag1.getText());
			Complex c1 = new Complex(real, imag);

			real = Double.parseDouble(txtReal2.getText());
			imag = Double.parseDouble(txtImag2.getText());
			Complex c2 = new Complex(real, imag);
			answers(c1, c2);
		});

		Scene scene = new Scene(bp, APP_WIDTH, APP_HEIGHT);
		primaryStage.setTitle("Complex Numbers App");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	
	private BorderPane setupGUI() {
		complex1 = new Label("Complex 1");
		txtReal1 = new TextField();
		plus1 = new Label("+");
		txtImag1 = new TextField();
		i1 = new Label("i");
		
		complex2 = new Label("Complex 2");
		txtReal2 = new TextField();
		plus2 = new Label("+");
		txtImag2 = new TextField();
		i2 = new Label("i");

		btnEquals = new Button("=");
		
		HBox equation1 = new HBox();
		equation1.getChildren().addAll(txtReal1, plus1, txtImag1, i1);
		VBox complex1Complete = new VBox();
		complex1Complete.getChildren().addAll(complex1, equation1);
		
		HBox equation2 = new HBox();
		equation2.getChildren().addAll(txtReal2, plus2, txtImag2, i2);
		VBox complex2Complete = new VBox();
		complex2Complete.getChildren().addAll(complex2, equation2);
		
		VBox together = new VBox();
		together.getChildren().addAll(complex1Complete, complex2Complete, btnEquals);
		
		VBox answers = new VBox();
		answers.getChildren().addAll(add, subtract, multiply, divide, negate1, negate2, conjugate1, conjugate2, abs1, abs2, equals, distance, greaterThan, lessThan);
		
		together.getChildren().add(answers);
		together.setAlignment(Pos.CENTER);
		
		//MAIN
		FlowPane main = new FlowPane(20, 10);
		main.setAlignment(Pos.CENTER);
		main.setPadding(new Insets(5, 5, 5, 5));
		main.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt");
		main.getChildren().addAll(together);

		//Taken from Prof. Silvestri's code
		Label lblStatus = new Label("Complex Numbers GUI by Ben M");
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
	
	private void answers(Complex c1, Complex c2) {
		add.setText("c1 + c2 = "+c1.add(c2));
		subtract.setText("c1 - c2 = "+c1.subtract(c2));
		multiply.setText("c1 * c2 = "+c1.multiply(c2));
		divide.setText("c1 / c2 = "+c1.divide(c2));
		negate1.setText("-c1     = "+c1.negate());
		negate2.setText("-c2     = "+c2.negate());
		conjugate1.setText("Conj c1 = "+c1.conjugate());
		conjugate2.setText("Conj c2 = "+c2.conjugate());
		abs1.setText("|c1|    = "+ c1.abs());
		abs2.setText("|c2|    = "+ c2.abs());
		distance.setText("Dist    = "+ c1.distance(c2));
		equals.setText("==?     = " + c1.equals(c2));
		greaterThan.setText(">?      = " + c1.greaterThan(c2));
		lessThan.setText("<?      = " + c1.lessThan(c2));
	}

}