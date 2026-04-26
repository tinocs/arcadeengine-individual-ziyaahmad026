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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import engine.Sound;
import engine.World;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BallWorld extends World {
	private Ball ball;
	private Score score;
	private File level1;
	private File level2;
	private int level = 1;
	private Stage stage;
	private Scene titleScene;
	private boolean bricksLoaded = false;
	private int lives = 3;
	private Label livesLbl;
	private boolean isPaused = true;
	private Label pauseLbl = new Label("Press SPACE to start.");
	private Sound game_lost = new Sound("ballbounceresources/game_lost.wav");
	private Sound game_won = new Sound("ballbounceresources/game_won.wav");
	private boolean gameOverSoundPlayed = false;
	

	public BallWorld() {
		setPrefWidth(800);
		setPrefHeight(600);
		pauseLbl.setFont(new Font("Algerian", 30));
	}

	public BallWorld(File level1, File level2, Stage stage, Scene titleScene) {
		setPrefWidth(800);
		setPrefHeight(600);

		pauseLbl.setFont(new Font("Algerian", 40));
		pauseLbl.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		pauseLbl.setLayoutX(220);
	    pauseLbl.setLayoutY(270);
	    
		this.level1 = level1;
		this.level2 = level2;
		this.stage = stage;
		this.titleScene = titleScene;
	}

	@Override
	public void onDimensionsInitialized() {
		// ball
		ball = new Ball();
		ball.setX(getWidth() / 2 - ball.getWidth()/2);
		ball.setY(getHeight() / 2 - ball.getHeight() / 2);
		add(ball);

		// paddle
		Paddle paddle = new Paddle();
		paddle.setX(getWidth() / 2 - paddle.getWidth() / 2);
		paddle.setY(getHeight() * 5 / 6 - paddle.getHeight() / 2);
		add(paddle);

		// move paddle w/ mouse
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (!isPaused()) {
					if (event.getX() >= paddle.getWidth() / 2 && event.getX() <= getWidth() - paddle.getWidth() / 2) {					
						paddle.setX(event.getX() - paddle.getWidth()/2);
					}					
				}
			}
		});

		// show score
		score = new Score();
		score.setX(getWidth() / 2);
		score.setY(getHeight() * 9 / 10);
		getChildren().add(score);

		// show lives
		livesLbl = new Label("Lives: " +lives);
		livesLbl.setFont(new Font("Algerian", 25));
		livesLbl.setLayoutX(20);
		livesLbl.setLayoutY(20);
		
		getChildren().add(livesLbl);
		
		// load level 1
		if (level1 != null) {
			loadBricks(level1);
			bricksLoaded = true;
		}
	}

	@Override
	public void act(long now) {
		if (isPaused) {
			if (!getChildren().contains(pauseLbl)) { // add pauseLbl if not alr there
				getChildren().add(pauseLbl);				
			} else if (isKeyPressed(KeyCode.SPACE)) { // unpause on space
				isPaused = false;
				getChildren().remove(pauseLbl);
			}
		}
		
		if (lives == 0) { // if we lost all lives go to menu
			if (!gameOverSoundPlayed) {
				game_lost.play();	
				gameOverSoundPlayed = true;
			}
			stage.setScene(titleScene);
			stop();
		}
		
		// check if we are done with this level
		if (bricksLoaded && getObjects(Brick.class).isEmpty()) {
			level++; // move to next level
			if (level == 2 && level2 != null) {
				loadBricks(level2); // load next level
			} else if (level == 3) {
				// if we're done w/ all levels go to menu
				if (!gameOverSoundPlayed) {					
					game_won.play();
					gameOverSoundPlayed = true;
				}
				stage.setScene(titleScene);
				stop();
			}
		}
	}

	public void loadBricks(File file) {
		double startX = 0;
		double startY = 0;
		Brick dummy = new Brick(); // placeholder brick for dimensions
		double brickW = dummy.getWidth();
		double brickH = dummy.getHeight();

		try {
			Scanner s = new Scanner(file);
			
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

	public boolean isPaused() {
		return isPaused;
	}
	
	public void setIsPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
	
	public int getLives() {
		return lives;
	}

	public void setLives(int i) {
		lives = i;
		livesLbl.setText("Lives: "+i);
	}

	public Score getScore() {
		return score;
	}
}
