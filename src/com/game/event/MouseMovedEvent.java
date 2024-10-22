package com.game.event;

public class MouseMovedEvent implements EventContext{
	private float xPosition, yPosition;
	private float xOffset, yOffset;
	
	public MouseMovedEvent(float x, float y, float offsetX, float offsetY) {
		xPosition = x;
		yPosition = y;
		xOffset = offsetX;
		yOffset = offsetY;
	}

	public float getxPosition() {
		return xPosition;
	}

	public float getyPosition() {
		return yPosition;
	}

	public float getxOffset() {
		return xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}
	

	@Override
	public void close() throws Exception {
	}
	
	@Override
	public String toString() {
		return "MouseMovedEvent: x: " + xPosition + " y: " + yPosition + " offsetX: " + xOffset + " offsetY: " + yOffset;
	}
	
	
}
