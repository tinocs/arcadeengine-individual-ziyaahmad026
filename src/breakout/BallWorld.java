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

import engine.Actor;
import engine.Sound;
import engine.World;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BallWorld extends World {
	private File level1;
	private File level2;
	
	private Stage stage;
	private Scene titleScene;
	private Ball ball;
	private Score score;
	
	private ImageView imgv;
	private Image img;
	
	private boolean bricksLoaded = false;
	private int lives = 3;
	private int level = 1;
	
	private Label livesLbl;
	private Label pauseLbl = new Label("Press SPACE to start.");
	
	private Sound game_lost = new Sound("ballbounceresources/game_lost.wav");
	private Sound game_won = new Sound("ballbounceresources/game_won.wav");
	
	private boolean gameOverSoundPlayed = false;
	private boolean isPaused = true;
	

	public BallWorld() {		
		setPrefWidth(800);
		setPrefHeight(600);
		pauseLbl.setFont(new Font("Algerian", 30));
		
		// background
		String path = getClass().getClassLoader().getResource("breakoutresources/background.png").toString();
		img = new Image(path);
		imgv = new ImageView(img);
		imgv.setY(0);
		imgv.setX((800 - img.getWidth()) / 2);
		getChildren().add(imgv);

	}

	public BallWorld(File level1, File level2, Stage stage, Scene titleScene) {
		setPrefWidth(800);
		setPrefHeight(600);
		
		// background
		String path = getClass().getClassLoader().getResource("breakoutresources/background.png").toString();
		img = new Image(path);
		imgv = new ImageView(img);
		imgv.setY(0);
		imgv.setX((800 - img.getWidth()) / 2);
		getChildren().add(imgv);

		// pauselbl
		pauseLbl.setFont(new Font("Algerian", 40));
		pauseLbl.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		pauseLbl.setLayoutX(220);
	    pauseLbl.setLayoutY(270);
	    
	    // other vars
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
//		this.setOnMouseMoved(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				if (!isPaused()) {
//					if (event.getX() >= paddle.getWidth() / 2 && event.getX() <= getWidth() - paddle.getWidth() / 2) {					
//						paddle.setX(event.getX() - paddle.getWidth()/2);
//					}					
//				}
//			}
//		});

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
			stop();
			Platform.runLater(() -> {
		        Alert a = new Alert(AlertType.ERROR, "Game Over. You lost!", ButtonType.OK);
		        a.showAndWait();
		        stage.setScene(titleScene);
		    });
			stage.setScene(titleScene);
			
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
				stop();
				Platform.runLater(() -> {
			        Alert a = new Alert(AlertType.ERROR, "Congratulations! You Won!", ButtonType.OK);
			        a.showAndWait();
			        stage.setScene(titleScene);
			    });
				stage.setScene(titleScene);
				
			}
		}
	}

	public void scroll(double dx) {
		if (imgv.getX() - dx >= getWidth() - img.getWidth() && imgv.getX() - dx <= 0) {
			for (Actor actor : getObjects(Actor.class)) {
				actor.setX(actor.getX() - dx);
			}
			imgv.setX(imgv.getX() - dx);			
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
