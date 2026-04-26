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
import engine.Actor;
import javafx.scene.image.Image;

public class Brick extends Actor {

	public Brick() {
		String path = getClass().getClassLoader().getResource("breakoutresources/brick.png").toString();
		Image img = new Image(path);
		setImage(img);
	}
	
	public Brick(boolean isYellowBrick) {
		String path;
		if (isYellowBrick) {
			path = getClass().getClassLoader().getResource("breakoutresources/brick2.png").toString();
			
		} else {
			path = getClass().getClassLoader().getResource("breakoutresources/brick.png").toString();
		}
		Image img = new Image(path);
		setImage(img);
	}
	
	@Override
	public void act(long now) {
		
	}
}
