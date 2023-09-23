package com.example.lottery;
/*
 * Name: Benjamin McCann
 * Date: 5/1/19
 * Course Number: CSC-112
 * Course Name: Intermediate Java
 * Problem Number: HW-12
 * Email: bnmccann0001@gmail.com
 * Create a GUI Client for the lottery quick picks
 */
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LotteryGUI extends Application {

	public void start(Stage primaryStage) {
		BorderPane bp = mainBp();

		// Create a scene and place it in the stage
		Scene scene = new Scene(bp, 550, 250);
		primaryStage.setTitle("Lottery"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	private BorderPane mainBp() {
		TextField numPicks = new TextField();

		RadioButton rb1 = new RadioButton("PowerBall");
		rb1.setUserData("PowerBall");
		RadioButton rb2 = new RadioButton("MegaMillions");
		rb2.setUserData("MegaMillions");
		RadioButton rb3 = new RadioButton("Luck for Life");
		rb3.setUserData("Luck for Life");
		VBox rbPane = new VBox(5);
		rbPane.getChildren().addAll(rb1, rb2, rb3);
		ToggleGroup rbGroup = new ToggleGroup();
		rb1.setToggleGroup(rbGroup);
		rb2.setToggleGroup(rbGroup);
		rb3.setToggleGroup(rbGroup);

		Button btnGenerate = new Button("Generate Quick Picks");

		HBox topPane = new HBox(10);
		topPane.setAlignment(Pos.CENTER);
		topPane.getChildren().addAll(new Label("Number of Quick Picks: "), numPicks, rbPane, btnGenerate);

		TextArea terminal = new TextArea();

		btnGenerate.setOnAction(e -> {
			Boolean canStart = true;
			terminal.setText("Generation Started...\nNumber of Quick Picks: ");
			int picks = 0;
			String lottery = "";

			try {
				picks = Integer.parseInt(numPicks.getText());
				if (picks >= 10)
					picks = 10;
				terminal.setText(terminal.getText() + picks);
			} catch (Exception e2) {
				terminal.setText(terminal.getText() + "ERROR: Quick Picks is not a number");
				canStart = false;
			}

			terminal.setText(terminal.getText() + "\nLottery Chosen: ");

			if (rbGroup.getSelectedToggle() != null) {
				lottery = rbGroup.getSelectedToggle().getUserData().toString();
				terminal.setText(terminal.getText() + lottery);
			} else {
				terminal.setText(terminal.getText() + "ERROR: Choose a Lottery");
				canStart = false;
			}

			if (canStart) {
				terminal.setText(terminal.getText() + "\nGenerating Quick Picks...");

				for (int i = 0; i < picks; i++) {
					terminal.setText(terminal.getText() + "\nPick " + (i+1) + ":");
					runClient(terminal, lottery);
				}
			} else {
				terminal.setText(terminal.getText() + "\nUnable to Generate Quick Picks.");
			}
		});

		// Place clock and label in border pane
		BorderPane pane = new BorderPane();
		pane.setTop(topPane);
		pane.setBottom(terminal);
		return pane;
	}

	static void runClient(TextArea terminal, String lottery) {
		Socket client = null;
		PrintWriter output = null;
		Scanner input = null;

		try (Scanner sc = new Scanner(System.in);) {
			client = new Socket("localhost", 5004);// hw needs cs.stcc.edu

			input = new Scanner(client.getInputStream());
			output = new PrintWriter(client.getOutputStream());

			output.println(lottery);
			output.flush();

			String lotStr = input.nextLine();
			terminal.setText(terminal.getText() + "\n" + lotStr);
			String lucky = input.nextLine();
			terminal.setText(terminal.getText() + "\nLucky Number: " + lucky);
		} catch (Exception e) {
			e.printStackTrace();
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
				client.close();
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		launch();
	}

}
