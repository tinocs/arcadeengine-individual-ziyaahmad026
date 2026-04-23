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
import engine.Actor;
import javafx.scene.image.Image;

public class Brick extends Actor {

	public Brick() {
		String path = getClass().getClassLoader().getResource("breakoutresources/brick.png").toString();
		Image img = new Image(path);
		setImage(img);
	}
	
	@Override
	public void act(long now) {
		
	}

}
