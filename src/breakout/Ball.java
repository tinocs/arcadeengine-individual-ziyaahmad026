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
import engine.Sound;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Ball extends Actor {

	private double dx;
	private double dy;
	
	private double ballW;
	private double ballH;
	
	private Sound ball_bounce = new Sound("ballbounceresources/ball_bounce.wav");
	private Sound brick_hit = new Sound("ballbounceresources/brick_hit.wav");
	private Sound lose_life = new Sound("ballbounceresources/lose_life.wav");
	
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
		
		if (!((BallWorld)getWorld()).isPaused()) {
			move(dx,dy);
			
			//bounce off world edges
			if (getX() <= 0 || getX() + ballW >= getWorld().getWidth()) {
				dx *=-1;
				ball_bounce.play();
			}
			
			if (getY() <= 0 || getY() + ballH >= getWorld().getHeight()) {
				if (!(getY() <= 0)) { // - 1000 when ball hits floor
					((BallWorld)getWorld()).setLives(lives - 1); // inc lives
					lose_life.play();
					
					score.setScore(score.getScore() - 1000); // inc score
					
					setX(getWorld().getWidth() / 2 - ballW/2); // move to the center
					setY(getWorld().getHeight() / 2 - ballH/2);
					((BallWorld)getWorld()).setIsPaused(true); // pause game
				} else {
					ball_bounce.play();
				}
				dy *=-1;
			}
			
			// bounce off paddle
			if (getOneIntersectingObject(Paddle.class) != null) {
				dy *= -1;
				ball_bounce.play();
			}
			
			// bounce off brick
			Brick potentialBrick = getOneIntersectingObject(Brick.class);
			if (potentialBrick != null && !potentialBrick.isFading()) {
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
				
				brick_hit.play();
				
				FadeTransition fade = new FadeTransition(new Duration(300), potentialBrick);
				fade.setFromValue(1.0);
				fade.setToValue(0.0);
				fade.play();
				fade.setOnFinished((e) ->{
					getWorld().getChildren().remove(potentialBrick);
					
				});
			}
		}
		
	}

}
