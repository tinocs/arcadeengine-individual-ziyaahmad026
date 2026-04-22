/**
 * Ziya Ahmad
 * Period 1, APCS
 * Date: Apr 21, 2026
 * 
 * Is this lab fully working? No If not, explain: Have made the Ball and BallWorld subclasses
 * but still need to add the paddle behavior & need to fix bug with Ball staying in the BallWorld
 * boundaries. Worked for ~40min on 4/21, would have spent longer but need to study for a math test tmrw :(
 * 
 * If resubmitting, explain what was wrong and what you fixed.
 */
package breakout;

import engine.World;

public class BallWorld extends World {
	
	public BallWorld() { // why doesnt this work?
		setWidth(600);
		setHeight(800);
		
		
	}

	@Override
	public void onDimensionsInitialized() {
		Ball ball = new Ball();
		add(ball);
		
	}

	@Override
	public void act(long now) {
		
	}

}
