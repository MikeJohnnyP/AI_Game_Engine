package com.game.time;

public class TimeSteps {
	private double deltaTime;
	private float timeScale;
	
	public TimeSteps(float deltaTime, float timeScale) {
		this.deltaTime = deltaTime;
		this.timeScale = timeScale;
	}
	
	public TimeSteps() {
		this.deltaTime = 0.f;
		this.timeScale = 1.0f;
	}

	public double getDeltaTime() {
		return deltaTime;
	}

	public void setDeltaTime(float deltaTime) {
		this.deltaTime = deltaTime;
	}
	
	public void setDeltaTime(double deltaTime) {
		this.deltaTime = deltaTime;
	}

	public float getTimeScale() {
		return timeScale;
	}

	public void setTimeScale(float timeScale) {
		this.timeScale = timeScale;
	}
	
	public float getTimeSpeed() {
		return (float) (timeScale * deltaTime);
	}
	
	@Override
	public String toString() {
		return String.valueOf(deltaTime);
	}

}
