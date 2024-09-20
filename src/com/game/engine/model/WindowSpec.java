package com.game.engine.model;

public class WindowSpec {
	private int width, height;
	private String title;
	private int maxFPS;
	
	public WindowSpec(int width, int height, String title, int maxFPS) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.maxFPS = maxFPS;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMaxFPS() {
		return maxFPS;
	}

	public void setMaxFPS(int maxFPS) {
		this.maxFPS = maxFPS;
	}
}
