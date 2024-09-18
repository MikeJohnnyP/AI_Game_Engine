package com.game.event;

public class KeyReleasedEvent implements EventContext {
private int keyCode;
	
	public KeyReleasedEvent(int keyCode) {
		this.keyCode = keyCode;
	}
	

	public int getKeyCode() {
		return keyCode;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "KeyReleasedEvent: " + (char)keyCode;
	}
}