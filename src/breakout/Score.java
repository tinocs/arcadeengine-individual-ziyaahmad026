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

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score extends Text {
	private int score;
	
	public Score() {
		score = 0;
		this.setFont(new Font("Algerian", 25.0));
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
