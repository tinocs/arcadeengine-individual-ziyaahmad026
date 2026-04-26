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

public class Ball extends Actor {

	double dx;
	double dy;
	
	double ballW;
	double ballH;
	
	public Ball() {
		dx = 5.0;
		dy = 5.0;
		
		// set image to the ball
		String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
		Image img = new Image(path);
		setImage(img);
		
		ballW = getWidth();
		ballH = getHeight();
	}
	
	@Override
	public void act(long now) {
		Score score = ((BallWorld)getWorld()).getScore();
		int lives = ((BallWorld)getWorld()).getLives();
		
		if (score == null) {
			return;
		}
		move(dx,dy);
		
		//bounce off world edges
		if (getX() <= 0 || getX() + ballW >= getWorld().getWidth()) {
			dx *=-1;
		}
		
		if (getY() <= 0 || getY() + ballH >= getWorld().getHeight()) {
			if (!(getY() <= 0)) { // - 1000 when ball hits floor
				((BallWorld)getWorld()).setLives(lives - 1);
				score.setScore(score.getScore() - 1000);
			}
			dy *=-1;
		}
		
		// bounce off paddle
		if (getOneIntersectingObject(Paddle.class) != null) {
			dy *= -1;
		}
		
		// bounce off brick
		Brick potentialBrick = getOneIntersectingObject(Brick.class);
		if (potentialBrick != null) {
			// + 100 when ball hits brick
			score.setScore(score.getScore() + 100);
			
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
			
			getWorld().getChildren().remove(potentialBrick);
		}
	}

}
