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

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score extends Text {
	private int score;
	
	public Score() {
		score = 0;
		this.setFont(new Font(25.0));
		updateDisplay();
	}
	
	public void updateDisplay() {
		this.setText("" + score);
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
		updateDisplay();
	}
}
