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
import engine.Actor;
import javafx.scene.image.Image;

public class Paddle extends Actor {

	public Paddle() {
		// set image to the paddle
		String path = getClass().getClassLoader().getResource("breakoutresources/paddle.png").toString();
		Image img = new Image(path);
		setImage(img);
	}

	@Override
	public void act(long now) {

	}

}
