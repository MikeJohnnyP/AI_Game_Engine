package com.game.event;

public class MouseScrollEvent implements EventContext {
	private int offset;
	

	public MouseScrollEvent(int offset) {
		this.offset = offset;
	}


	public int getOffset() {
		return offset;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "MouseScollEvent: offset: " + offset;
	}
}
