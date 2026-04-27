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
import javafx.scene.input.KeyCode;

public class Paddle extends Actor {
	public Paddle() {
		// set image to the paddle
		String path = getClass().getClassLoader().getResource("breakoutresources/paddle.png").toString();
		Image img = new Image(path);
		setImage(img);
	}

	@Override
	public void act(long now) {
		if (!((BallWorld)getWorld()).isPaused()) {
			// if both keys are pressed, prioritize the left key
		    if (getWorld().isKeyPressed(KeyCode.LEFT)) {
		    	if (getX() > getWorld().getScene().getX() + getWorld().getScene().getWidth()/2) {
			    	((BallWorld)getWorld()).scroll(-12);	
		    	}
		    	if (getX() > 0) {
		            move(-12.0, 0);
		        }
		    } else if (getWorld().isKeyPressed(KeyCode.RIGHT)) {
		    	if (getX() < getWorld().getScene().getX() + getWorld().getScene().getWidth()/2) {
			    	((BallWorld)getWorld()).scroll(12);		
		    	}
		    	if (getX() + getWidth() < getWorld().getWidth()) {
		            move(12.0, 0);
		        }
		    }
			
		}
	}
}
