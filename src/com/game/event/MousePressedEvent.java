package com.game.event;

public class MousePressedEvent implements EventContext {
	private int button;
	

	public MousePressedEvent(int button) {
		this.button = button;
	}


	public int getButton() {
		return button;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "MousePressedEvent: " + (char)button;
	}
	
}