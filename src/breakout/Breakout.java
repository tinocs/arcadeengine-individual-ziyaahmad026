/**
 * Ziya Ahmad
 * Period 1, APCS
 * Date: Apr 26, 2026
 * 
 * Is this lab fully working? Yes If not, explain: 
 * 
 * If resubmitting, explain what was wrong and what you fixed.
 */
package breakout;

import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Breakout extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Breakout");

		// title page
		VBox titleRoot = new VBox();

		Label titleLbl = new Label("BREAKOUT");	
		titleLbl.setFont(new Font("Algerian",70));
		titleLbl.setPadding(new Insets(30));

		Button playBtn = new Button("PLAY");
		playBtn.setFont(new Font("Algerian", 40));

		titleRoot.getChildren().addAll(titleLbl, playBtn);	
		titleRoot.setPadding(new Insets(30));
		titleRoot.setPrefHeight(600);
		titleRoot.setPrefWidth(800);

		titleRoot.setAlignment(Pos.CENTER);

		// game
		BorderPane levelRoot = new BorderPane();

		Scene scene1 = new Scene(titleRoot);
		//BallWorld world = new BallWorld(new File("level1.txt"), new File ("level2.txt"), stage, scene1);
		BallWorld world = new BallWorld(new File("test1.txt"), new File ("test2.txt"), stage, scene1);
		
		levelRoot.setCenter(world);

		Scene scene2 = new Scene(levelRoot);
		playBtn.setOnMousePressed(e -> {
			stage.setScene(scene2);
		});
		stage.setScene(scene1);

		world.start();
		stage.show();
	}

}
