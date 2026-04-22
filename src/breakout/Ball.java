/**
 * Ziya Ahmad
 * Period 1, APCS
 * Date: Apr 21, 2026
 * 
 * Is this lab fully working? No If not, explain: Have made the Ball and BallWorld subclasses
 * but still need to add the paddle behavior & need to fix bug with Ball staying in the BallWorld
 * boundaries. Worked for ~40min on 4/21, would have spent longer but need to study for a math test tmrw :(
 * 
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
//		if (getWorld().getWidth() / 2 - getX() <= this.getWidth() / 2) {
//			dx *= -1;
//		}
//		
//		if (getWorld().getHeight() / 2 - getY() <= this.getHeight() / 2) {
//			dy *= -1;
//		}
	}

}
