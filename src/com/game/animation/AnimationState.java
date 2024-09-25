package com.game.animation;

import java.util.ArrayList;
import java.util.List;

import com.game.graphics.Sprite;

public class AnimationState {
	public String title;
	
	private List<Frame> animationFrames = new ArrayList<>();
	
	private static Sprite defaultSprite;
	private float timeTracker = 0.0f;
	private int currentSprite = 0;
	private boolean doesLoop = false;
	
	public void addFrame(Sprite sprite, float frameTime) {
		animationFrames.add(new Frame(sprite, frameTime));
	}
	
	public void setLoop(boolean doesLoop) {
		this.doesLoop = doesLoop;
	}
	
	public void update(float dt) {
		if(currentSprite < animationFrames.size()) {
			timeTracker -= dt;
			if(timeTracker <= 0) {
				if(!(currentSprite == animationFrames.size() - 1 && !doesLoop)) {
					currentSprite = (currentSprite + 1) % animationFrames.size();
				}
				timeTracker = animationFrames.get(currentSprite).frameTime;
			}			
		}
	}
	
	public Sprite getCurrentSprite() {
		if(currentSprite < animationFrames.size()) {
			return animationFrames.get(currentSprite).sprite;
		}
		
		return defaultSprite;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Frame> getAnimationFrames() {
		return animationFrames;
	}

	public void setAnimationFrames(List<Frame> animationFrames) {
		this.animationFrames = animationFrames;
	}

	public static Sprite getDefaultSprite() {
		return defaultSprite;
	}

	public static void setDefaultSprite(Sprite defaultSprite) {
		AnimationState.defaultSprite = defaultSprite;
	}

	public float getTimeTracker() {
		return timeTracker;
	}

	public void setTimeTracker(float timeTracker) {
		this.timeTracker = timeTracker;
	}

	public boolean isDoesLoop() {
		return doesLoop;
	}

	public void setDoesLoop(boolean doesLoop) {
		this.doesLoop = doesLoop;
	}

	public void setCurrentSprite(int currentSprite) {
		this.currentSprite = currentSprite;
	}
}
