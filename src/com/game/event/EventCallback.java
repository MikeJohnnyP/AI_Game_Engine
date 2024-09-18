package com.game.event;

public interface EventCallback<T> {
	public boolean execute(T callback);
}
