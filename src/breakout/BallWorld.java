/**
 * Ziya Ahmad
 * Period 1, APCS
 * Date: Apr 26, 2026
 * 
 * Is this lab fully working? Yes If not, explain: 
 * 
 * If resubmitting, explain what was wrong and what you fixed.
 */package breakout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BallWorld extends World {
	private Score score;
	private File level1;
	private File level2;
	private int level = 1;
	private Stage stage;
	private Scene titleScene;
	private boolean bricksLoaded = false;

	
	public BallWorld() {
		setPrefWidth(800);
		setPrefHeight(600);
	}
	
	public BallWorld(File level1, File level2, Stage stage, Scene titleScene) {
		setPrefWidth(800);
		setPrefHeight(600);
		
		this.level1 = level1;
		this.level2 = level2;
		this.stage = stage;
	    this.titleScene = titleScene;
	}

	@Override
	public void onDimensionsInitialized() {
		Ball ball = new Ball();
		ball.setX(getWidth() / 2 - ball.getWidth()/2);
		ball.setY(getHeight() / 2 - ball.getHeight() / 2);
		add(ball);
		
		Paddle paddle = new Paddle();
		paddle.setX(getWidth() / 2 - paddle.getWidth() / 2);
		paddle.setY(getHeight() * 5 / 6 - paddle.getHeight() / 2);
		add(paddle);
		
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getX() >= paddle.getWidth() / 2 && event.getX() <= getWidth() - paddle.getWidth() / 2) {					
					paddle.setX(event.getX() - paddle.getWidth()/2);
				}
			}
		});
		
		if (level1 != null) {
			double startX = 0;
			double startY = 0;
			Brick brick1 = new Brick();
			double brickW = brick1.getWidth();
			double brickH = brick1.getHeight();
			
			try {
				Scanner s = new Scanner(level1);
				int totalRows = s.nextInt();
				int totalCols = s.nextInt();
				for (int row = 0; row < totalRows; row ++) {
					for (int col = 0; col < totalCols; col++) {
						int num = s.nextInt();
						if (num == 1) {
							Brick b = new Brick(false);
							b.setX(startX);
							b.setY(startY);
							add(b);
						} else if (num == 2) {
							Brick b = new Brick(true);
							b.setX(startX);
							b.setY(startY);
							add(b);
						}
						startX += brickW;
					}
					startX = 0;
					startY += brickH;
				}
				s.close();
				bricksLoaded = true;
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		score = new Score();
		score.setX(getWidth() / 2);
		score.setY(getHeight() * 9 / 10);
		getChildren().add(score);
	}

	@Override
	public void act(long now) {
		if (bricksLoaded && getObjects(Brick.class).isEmpty()) {
			level++;
			if (level == 2) {
				if (level2 != null) {
					double startX = 0;
					double startY = 0;
					Brick brick1 = new Brick();
					double brickW = brick1.getWidth();
					double brickH = brick1.getHeight();
					
					try {
						Scanner s = new Scanner(level2);
						int totalRows = s.nextInt();
						int totalCols = s.nextInt();
						for (int row = 0; row < totalRows; row ++) {
							for (int col = 0; col < totalCols; col++) {
								int num = s.nextInt();
								if (num == 1) {
									Brick b = new Brick(false);
									b.setX(startX);
									b.setY(startY);
									add(b);
								} else if (num == 2) {
									Brick b = new Brick(true);
									b.setX(startX);
									b.setY(startY);
									add(b);
								}
								startX += brickW;
							}
							startX = 0;
							startY += brickH;
						}
						s.close();
					} catch (FileNotFoundException e) {
						System.out.println(e.getMessage());
					}
					
				}
			} else if (level == 3) {
				stage.setScene(titleScene);
			}
		}

	}
	
	public Score getScore() {
		return score;
	}
}
