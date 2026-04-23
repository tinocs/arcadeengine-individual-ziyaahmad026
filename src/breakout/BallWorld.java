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

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BallWorld extends World {
	
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
		paddle.setY(getHeight() / 4 - paddle.getHeight() / 2);
		add(paddle);
		
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getX() >= paddle.getWidth() / 2 && event.getX() <= getWidth() - paddle.getWidth() / 2) {					
					paddle.setX(event.getX() - paddle.getWidth()/2);
				}
			}
		});
		
		Brick brick = new Brick();
		brick.setX(getWidth() / 2 - brick.getWidth() / 2);
		brick.setY(getHeight() * 3 / 4 - brick.getHeight() / 2);
		add(brick);
	}

	@Override
	public void act(long now) {
		
	}

}
