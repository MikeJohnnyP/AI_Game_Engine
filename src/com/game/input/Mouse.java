package com.game.input;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class Mouse {
	private static final Map<Integer, Integer> MouseState = new HashMap<>();
	private static int xPos, yPos;
	private static int xOffset, yOffset;
	private static int LastPosX, LastPosY;
	
	public static Map<Integer, Integer> getMousestate() {
		return MouseState;
	}

	public static void setxPos(int xPos) {
		Mouse.xPos = xPos;
	}

	public static void setyPos(int yPos) {
		Mouse.yPos = yPos;
	}

	public static void setxOffset(int xOffset) {
		Mouse.xOffset = xOffset;
	}

	public static void setyOffset(int yOffset) {
		Mouse.yOffset = yOffset;
	}

	public static void setLastPosX(int lastPosX) {
		LastPosX = lastPosX;
	}

	public static void setLastPosY(int lastPosY) {
		LastPosY = lastPosY;
	}

	public static int getPosX() {
		return xPos;
	}
	
	public static int getPosY() {
		return yPos;
	}
	
	public static int getXOffset() {
		return xOffset;
	}
	
	public static int getYOffset() {
		return yOffset;
	}
	
	public static int[] getMousePos() {
		int[] result = {xPos, yPos};
		return result;
	}
	
	public static int[] getMouseOffset() {
		int[] result = {xOffset, yOffset};
		return result;
	}
	
	public static void mouseMoved(int newX, int newY) {
	    xOffset = newX - xPos;
	    yOffset = newY - yPos;
	    
	    LastPosX = xPos;
	    LastPosY = yPos;
	    xPos = newX;
	    yPos = newY;
	}
	
	public static boolean isMousePressed(int button) {
		if(MouseState.get(button) == MouseEvent.MOUSE_PRESSED) 
			return true;
		
		return false;
	}
	
	public static boolean isMouseReleased(int button) {
		if(MouseState.get(button) == MouseEvent.MOUSE_RELEASED) 
			return true;
		
		return false;
	}
	
}