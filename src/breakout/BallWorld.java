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

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BallWorld extends World {
	private Score score;
	
	public BallWorld() {
		setPrefWidth(800);
		setPrefHeight(600);
		
	}

	@Override
	public void onDimensionsInitialized() {
		Ball ball = new Ball();
		ball.setX(getWidth() / 2 - ball.getWidth()/2);
		ball.setY(getHeight() / 2 - ball.getHeight() / 2);
		add(ball);
		
		Paddle paddle = new Paddle();
		paddle.setX(getWidth() / 2 - paddle.getWidth() / 2);
		paddle.setY(getHeight() * 3 / 4 - paddle.getHeight() / 2);
		add(paddle);
		
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getX() >= paddle.getWidth() / 2 && event.getX() <= getWidth() - paddle.getWidth() / 2) {					
					paddle.setX(event.getX() - paddle.getWidth()/2);
				}
			}
		});
		
		Brick brick1 = new Brick();
		double x = getWidth() / 2 - brick1.getWidth() / 2 - brick1.getWidth() - brick1.getWidth() * 3;
		double y = getHeight() / 4 - brick1.getHeight() / 2;
		
		for (int i = 0; i < 3; i++) {
			Brick b = new Brick();
			b.setX(x);
			b.setY(y);
			add(b);
			
			x += b.getWidth();
			y += b.getHeight();
		}
		
		score = new Score();
		score.setX(getWidth() / 2);
		score.setY(getHeight() * 9 / 10);
		getChildren().add(score);
	}

	@Override
	public void act(long now) {
		
	}
	
	public Score getScore() {
		return score;
	}

}
