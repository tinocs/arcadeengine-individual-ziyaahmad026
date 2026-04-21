/**
 * Ziya Ahmad
 * Period 1, APCS
 * Date: Apr 21, 2026
 * 
 * Is this lab fully working?  If not, explain:
 * If resubmitting, explain what was wrong and what you fixed.
 */
package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Ball extends Actor {

	double dx;
	double dy;
	
	public Ball() {
		dx = 5.5;
		dy = 5.5;
		
		// set image to the ball
		String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
		Image img = new Image(path);
		setImage(img);
	}
	
	@Override
	public void act(long now) {
		move(dx,dy);
		
		//bounce off world edges
	}

}
