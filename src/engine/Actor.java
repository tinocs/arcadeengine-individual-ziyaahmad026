/**
 * Ziya Ahmad
 * Period 1, APCS
 * Date: Apr 13, 2026
 * 
 * Is this lab fully working? Yes If not, explain: 
 * 
 * If resubmitting, explain what was wrong and what you fixed. Resubmitted,
 * finished testing all my code.
 */

package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView {
	private static final Random rand = new Random();
	
	public Actor() {
		
	}
	
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}
	
	public World getWorld() {
		return (World)(getParent());
	}

	public double getWidth() {
		//return this.getBoundsInLocal().getWidth();
		return this.getBoundsInParent().getWidth();
	}
	
	public double getHeight() {
		return this.getBoundsInParent().getHeight();
	}
	
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		List<A> list = new ArrayList<>();
		
		// if actor isn't yet added to world
		if (getWorld() == null) {
			return list;
		}
		
		for (A obj : getWorld().getObjects(cls)) {
			// getboundsinparent -> gives positions relative to the world's coord system
			// getboundsinlocla -> gives positions relative to each obj's own origin
			if (obj != this && this.getBoundsInParent().intersects(obj.getBoundsInParent())) {
				list.add(obj);
			}
			
		}
		
		return list;
	}
	
	public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {
		List<A> list = getIntersectingObjects(cls);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(rand.nextInt(list.size()));
	}
	
	public abstract void act(long now);
	
	public void addedToWorld() {}; // meant to be overriden
}
