package com.game.event;

public class MouseMovedEvent implements EventContext{
	private double xPosition, yPosition;
	private double xOffset, yOffset;
	
	public MouseMovedEvent(double x, double y, double offsetX, double offsetY) {
		xPosition = x;
		yPosition = y;
		xOffset = offsetX;
		yOffset = offsetY;
	}

	public double getxPosition() {
		return xPosition;
	}

	public double getyPosition() {
		return yPosition;
	}

	public double getxOffset() {
		return xOffset;
	}

	public double getyOffset() {
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
