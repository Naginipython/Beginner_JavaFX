package com.example.javafx;/*
 * Name: Benjamin McCann
 * Date: 4/09/19
 * Course Number: CSC-112
 * Course Name: Intermediate Java
 * Problem Number: Test 2
 * Email: bnmccann0001@gmail.com
 * Create Hangman
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Hangman extends Application {

    private static Circle head;
    private static Rectangle body;
    private static Rectangle armL;
    private static Rectangle armR;
    private static Rectangle legL;
    private static Rectangle legR;
    private static Pane human;
    private static Button btnGuess;
    private static TextField txtGuess;
    private static String word = "test";
    private static String mysteryWord = "____";
    private int count = 0;
    private static Label lblMystery;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane bp = main();

        btnGuess.setOnAction(e -> {
            String guess = txtGuess.getText();
            guess = guess.toLowerCase();
            char guessChar = guess.charAt(0);
            if (compare(guessChar)) { //If it isn't right
                addPart(count);
                count++;
            } else
            if (mysteryWord.equalsIgnoreCase(word))
                gameEnd("You Win");
            txtGuess.setText("");
        });

        Scene scene = new Scene(bp, 470, 380);
        primaryStage.setTitle("Hangman by Ben McCann");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static BorderPane main() {
        // Main Hangman Area
        Pane main = new Pane();
        Line lineMid = new Line(100, 285, 100, 30);
        lineMid.setStrokeWidth(10);
        Line lineTop = new Line(75, 29, 300, 30);
        lineTop.setStrokeWidth(10);
        Line lineBot = new Line(75, 286, 125, 286);
        lineBot.setStrokeWidth(10);
        Line lineRope = new Line(300, 30, 300, 75);
        lineRope.setStrokeWidth(10);

        Image bg = new Image("resources/hangman_background.jpg");
        ImageView bgView = new ImageView(bg);

        // human
        human = new Pane();
        head = new Circle(300, 100, 30);
        setPart(head);
        body = new Rectangle(288, 100, 25, 125);
        setPart(body);
        armL = new Rectangle(275, 110, 10, 75);
        setPart(armL);
        armL.setRotate(30);
        armR = new Rectangle(317, 110, 10, 75);
        setPart(armR);
        armR.setRotate(-30);
        legL = new Rectangle(275, 200, 10, 75);
        setPart(legL);
        legL.setRotate(20);
        legR = new Rectangle(317, 200, 10, 75);
        setPart(legR);
        legR.setRotate(-20);

        //Guess Area
        btnGuess = new Button("Guess a character");
        txtGuess = new TextField();
        lblMystery = new Label(mysteryWord);

        human.getChildren().addAll(legR, legL, armR, armL, body, head);
        main.getChildren().addAll(bgView, lineMid, lineTop, lineBot, lineRope, human);

        FlowPane guessA = new FlowPane(5, 5);
        guessA.getChildren().addAll(txtGuess, btnGuess, lblMystery);
        guessA.setPadding(new Insets(5, 5, 10, 5));
        lblMystery.setStyle("-fx-font-weight: bold; -fx-font-size: 20pt");
        guessA.setAlignment(Pos.BASELINE_CENTER);

        BorderPane bp = new BorderPane();
        bp.setCenter(main);
        bp.setBottom(guessA);
        return bp;
    }

    public static void main(String[] args) {
        launch();
    }

    private static void setPart(Shape shape) {
        shape.setFill(Color.WHITE);
        shape.setStroke(Color.BLACK);
        shape.setStrokeWidth(5);
        shape.setOpacity(0);
    }

    public void addPart(int count) {
        switch (count) {
            case 0:
                head.setOpacity(100);
                break;
            case 1:
                body.setOpacity(100);
                break;
            case 2:
                armL.setOpacity(100);
                break;
            case 3:
                armR.setOpacity(100);
                break;
            case 4:
                legL.setOpacity(100);
                break;
            case 5:
                legR.setOpacity(100);
                gameEnd("You Lose");
                break;

        }
    }
    private boolean compare (char c) {
        boolean result = true;
        for (int i = 0; i < word.length(); i++) {
            if (c == word.charAt(i)) {
                changeMystery(i, c);
                result = false;
            }
        }
        return result;
    }
    private void changeMystery(int num, char c) {
        String mystery = "";
        for (int i = 0; i < mysteryWord.length(); i++) {
            if (i == num) {
                mystery += c;
            } else {
                mystery += mysteryWord.charAt(i);
            }
        }
        mysteryWord = mystery;
        lblMystery.setText(mysteryWord);
    }
    private void gameEnd(String cond) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(cond);
        alert.setHeaderText(cond);
        alert.setContentText(cond);
        alert.showAndWait();
    }


}