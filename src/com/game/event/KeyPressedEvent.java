package com.game.event;

public class KeyPressedEvent implements EventContext {
	private int keyCode;
	
	public KeyPressedEvent(int keyCode) {
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
		return "KeyPressedEvent: " + (char)keyCode;
	}
}
