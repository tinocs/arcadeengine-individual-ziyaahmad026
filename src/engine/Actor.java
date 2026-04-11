/**
 * Ziya Ahmad
 * Period 1, APCS
 * Date: Apr 7, 2026
 * 
 * Is this lab fully working?  If not, explain:
 * If resubmitting, explain what was wrong and what you fixed.
 */
package engine;

import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView {
	
	public Actor() {
		
	}
	
	public void move(double dx, double dy) {
		
	}
	
	public World getWorld() {
		return (World)(getParent());
	}

	public double getWidth() {
		return 0;
	}
	
	public double getHeight() {
		return 0;
	}
	
	public <A extends Actor> java.util.List<A> getIntersectingObject(java.lang.Class<A> cls) {
		return null;
	}
	
	public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {
		return null;
	}
	
	public abstract void act(long now);
	
	public void addedToWorld() {}; // meant to be overriden
}
