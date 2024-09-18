package com.game.event;

public class KeyHeldEvent implements EventContext {
	private int keyCode;
	
	public KeyHeldEvent(int keyCode) {
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
		return "KeyHeldEvent: " + (char)keyCode;
	}

}
