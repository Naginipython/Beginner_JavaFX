/*\
 * Name: Benjamin McCann
 * Date: 3/27/19
 * Course Number: CSC-112
 * Course Name: Intermediate Java
 * Problem Number: 8
 * Email: bnmccann0001@gmail.com
 * Create the american flag in javafx
 */
package com.example.flag;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AmericanFlag extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		final double PANEWIDTH = 619;
		final double PANEHEIGHT = 330;
		
		for (int i = 0; i < 7; i++) {
			Rectangle r = new Rectangle();
			r.setX(10);
			r.heightProperty().bind(pane.heightProperty().subtract(20).divide(13.0));
			r.setFill(Color.RED);
			//attempting to variable
			r.yProperty().bind(pane.heightProperty().subtract(20).divide(13).multiply(2*i).add(10));
			r.widthProperty().bind(pane.heightProperty().multiply(1.9).subtract(30));
			pane.getChildren().addAll(r);
		}
		
		Rectangle blue = new Rectangle();
		blue.setX(10);
		blue.setY(10);
		blue.widthProperty().bind(pane.heightProperty().multiply(0.76));
		blue.heightProperty().bind(pane.heightProperty().subtract(20).divide(13).multiply(7));
		blue.setFill(Color.BLUE);
		pane.getChildren().addAll(blue);
		
		for(int i=0;i<9;i++) {
			int num = (i%2==0) ? 6 : 5;
			for(int j=0;j<num;j++) { //The H width
				Image star = new Image("resources/flagstar.png");
				ImageView starView = new ImageView(star);
				int even = i%2+1;
				starView.xProperty().bind((pane.heightProperty().multiply(0.063*2).multiply(j)).add(pane.heightProperty().multiply(0.063).multiply(even)));
				starView.yProperty().bind(pane.heightProperty().multiply(0.05).multiply(i+1));
				//Diameter
				starView.fitWidthProperty().bind(pane.heightProperty().multiply(0.0616));
				starView.fitHeightProperty().bind(pane.heightProperty().multiply(0.0616));
				pane.getChildren().addAll(starView); //i%2+1
			}
		}

		// Create a scene and place it in the stage
		StackPane test = new StackPane();
		test.getChildren().addAll(pane);
		Scene scene = new Scene(test, PANEWIDTH, PANEHEIGHT);
		primaryStage.setTitle("American Flag"); 
		primaryStage.setScene(scene); 
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}