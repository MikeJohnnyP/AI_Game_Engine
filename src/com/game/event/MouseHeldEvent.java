package com.game.event;

public class MouseHeldEvent implements EventContext {
	private int button;
	

	public MouseHeldEvent(int button) {
		this.button = button;
	}


	public int getButton() {
		return button;
	}

	@Override
	public void close() throws Exception {
		
	}

	@Override
	public String toString() {
		return "MouseHeldEvent: " + (char)button;
	}
	
}
