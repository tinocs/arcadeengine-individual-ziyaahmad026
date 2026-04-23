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

public class Ball extends Actor {

	double dx;
	double dy;
	
	double ballW;
	double ballH;
	
	public Ball() {
		dx = 4.5;
		dy = 4.5;
		
		// set image to the ball
		String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
		Image img = new Image(path);
		setImage(img);
		
		ballW = getWidth();
		ballH = getHeight();
	}
	
	@Override
	public void act(long now) {
		move(dx,dy);
		
		//bounce off world edges
		if (getX() <= 0 || getX() + ballW >= getWorld().getWidth()) {
			dx *=-1;
		}
		
		if (getY() <= 0 || getY() + ballH >= getWorld().getHeight()) {
			dy *=-1;
		}
		
		// bounce off paddle
		if (getOneIntersectingObject(Paddle.class) != null) {
			dy *= -1;
		}
		
		// bounce off brick
		Brick potentialBrick = getOneIntersectingObject(Brick.class);
		if (potentialBrick != null) {
			double leftX = potentialBrick.getX() - potentialBrick.getWidth() / 2;
			double rightX = potentialBrick.getX() + potentialBrick.getWidth() / 2;
			double upY = potentialBrick.getY() + potentialBrick.getHeight() / 2;
			double downY = potentialBrick.getY() - potentialBrick.getHeight() / 2;
			
			if (leftX < getX() && getX() < rightX) {
				dy *= -1;
			} else if (downY < getY() && getY() < upY) {
				dx *= -1;
			} else {
				dy *= -1;
				dx *= -1;
			}
		}
	}

}
