package com.game.event;

public class KeyTypeEvent implements EventContext {
	private int keyCode;
	
	public KeyTypeEvent(int keyCode) {
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
		return "KeyTypeEvent: " + (char)keyCode;
	}
}