package com.game.event;

public class MouseReleasedEvent implements EventContext {
	private int button;
	

	public MouseReleasedEvent(int button) {
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
		return "MouseReleasedEvent: " + (char)button;
	}
}
