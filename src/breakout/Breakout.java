/**
 * Ziya Ahmad
 * Period 1, APCS
 * Date: Apr 23, 2026
 * 
 * Is this lab fully working? Yes If not, explain: 
 * 
 * If resubmitting, explain what was wrong and what you fixed.
 * Forgot to delete the Brick from the World when it touches a Ball.
 * Also I accidentally put the Paddle at the top of the world because
 * I haven't played this game before.
 * 
 * Resubmitted, added Paddle, Brick, & Score subclasses. Also fixed
 * the bug relating to the ball staying in the World's boundaries.
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
