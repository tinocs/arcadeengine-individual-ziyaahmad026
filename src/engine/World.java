/**
 * Ziya Ahmad
 * Period 1, APCS
 * Date: Apr 10, 2026
 * 
 * Is this lab fully working? No If not, explain: Spent significant time trying to understand how to properly
 * implement the act() method with the AnimationTimer. Still need to finish the methods in Actor, two of the methods
 * in World, and test my code.
 * 
 * If resubmitting, explain what was wrong and what you fixed. Resubmitting b/c I forgot to update the class comment headers
 */

// https://docs.google.com/presentation/d/1hs-gURHR-FC5nuksjJQW8PzqXNj9kHJE8umMr8sOAY0/edit?slide=id.g3934f50146_0_23#slide=id.g3934f50146_0_23
package engine;

import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {
	// calls act() on the world first, then on each actor in the world every frame
	private AnimationTimer myTimer;

	// keeps track of whether the timer is running
	private boolean isRunning;

	// holds all the keys that are currently being pressed
	private Set<KeyCode> keyCodes;

	// keep track of whether dimensions of world have been set
	private boolean widthSet;
	private boolean heightSet;


	public World(boolean isRunning, Set<KeyCode> keyCodes, boolean widthSet, boolean heightSet) {
		// initialize variables
		this.isRunning = isRunning;
		this.keyCodes = keyCodes;
		this.widthSet = widthSet;

		// listeners
		widthProperty().addListener(new WidthListener());
		heightProperty().addListener(new HeightListener());
		sceneProperty().addListener(new SceneListener());
		setOnKeyPressed(new KeyPressedHandler());
		setOnKeyReleased(new KeyReleasedHandler());
		
		// animation timer
		myTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				act(now);
				for (Actor actor: getObjects(Actor.class)) {
					if (getObjects(Actor.class).contains(actor)) {
						actor.act(now);
					}
				}
			}
		};
	}

	public void start() {
		isRunning = true;
		myTimer.start();
	}

	public void stop() {
		isRunning = false;
		myTimer.stop();
	}

	public boolean isStopped() {
		return !isRunning;
	}

	public void add(Actor actor) {
		getChildren().add(actor);
	}

	public void remove(Actor actor) {
		getChildren().remove(actor);
	}

	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls) {
		return null;
	}

	public <A extends Actor> java.util.List<A> getObjectsAt(double x, double y, java.lang.Class<A> cls) {
		return null;
	}

	public boolean isKeyPressed(KeyCode code) {
		return false;
	}

	public abstract void onDimensionsInitialized();

	public abstract void act(long now);
	
	private class KeyReleasedHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			keyCodes.remove(event.getCode());
		}
	}

	private class KeyPressedHandler implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent arg0) {
			keyCodes.add(arg0.getCode());
		}
		
	}
	
	private class SceneListener implements ChangeListener<Scene> {
		@Override
		public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
			if (newValue != null) {
				requestFocus();
			}
		}
	}
	
	private class WidthListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
			if (!widthSet && arg2.doubleValue() > 0) {
				widthSet = true;
				if (heightSet) {
					onDimensionsInitialized();						
				}
			}
		}
	}

	private class HeightListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
			if (!heightSet&& arg2.doubleValue() > 0) {
				heightSet = true;
				if (widthSet) {
					onDimensionsInitialized();
				}
			}

		}
	}
}