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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
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

	public World() {
		isRunning = false;
		keyCodes = new HashSet<>();
		widthSet = false;
		heightSet = false;
		
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

	public World(boolean isRunning, Set<KeyCode> keyCodes, boolean widthSet, boolean heightSet) {
		// initialize variables
		this.isRunning = isRunning;
		this.keyCodes = keyCodes;
		this.widthSet = widthSet;
		this.heightSet = heightSet;

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
		actor.addedToWorld();
	}

	public void remove(Actor actor) {
		getChildren().remove(actor);
	}

	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls) {
		List<A> list = new ArrayList<A>();
		
		for (Node child : getChildren()) {
			if (cls.isInstance(child)) {
				//list.add((A)(child));
				list.add(cls.cast(child));
			}
		}
		
		return list;
	}

	public <A extends Actor> java.util.List<A> getObjectsAt(double x, double y, java.lang.Class<A> cls) {
		List<A> list = new ArrayList<A>();
		
		for (Node child : getChildren()) {
			if (cls.isInstance(child) && child.getBoundsInParent().contains(x,y)) {
				list.add(cls.cast(child));
			}
		}
		
		return list;
	}

	public boolean isKeyPressed(KeyCode code) {
		return keyCodes.contains(code);
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