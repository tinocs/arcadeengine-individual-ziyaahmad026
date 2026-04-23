/**
 * Ziya Ahmad
 * Period 1, APCS
 * Date: Apr 22, 2026
 * 
 * Is this lab fully working? No If not, explain: 
 * 
 * If resubmitting, explain what was wrong and what you fixed.
 */
package breakout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Breakout extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Breakout");
//		stage.setWidth(800);
//		stage.setHeight(600);
//		
		BorderPane root = new BorderPane();
		BallWorld world = new BallWorld();
		
		root.setCenter(world);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		world.start();
		stage.show();
	}

}
