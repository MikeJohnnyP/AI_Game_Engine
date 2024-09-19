package com.game.layer;

import java.awt.Graphics;

import com.game.event.KeyPressedEvent;
import com.game.event.KeyReleasedEvent;
import com.game.event.MouseMovedEvent;
import com.game.event.MousePressedEvent;
import com.game.event.MouseReleasedEvent;
import com.game.event.MouseScrollEvent;

public abstract class Layer {
	public enum LayerType{
		Standard,
		Overlay
	}
	private String name;
	private LayerType type;
	
	protected Layer(String name, LayerType type) {
		this.name = name;
		this.type = type;
	}
	
	public abstract void onAttach();
	public abstract void onDettach();
	
	public abstract void onUpdate();
	public abstract void onRender(Graphics g);
	
	protected boolean onKeyPressedEvent(KeyPressedEvent e) {return false;}
	protected boolean onKeyReleasedEvent(KeyReleasedEvent e) {return false;}
	protected boolean onMouseMovedEvent(MouseMovedEvent e) {return false;}
	protected boolean onMousePressedEvent(MousePressedEvent e) {return false;}
	protected boolean onMouseReleasedEvent(MouseReleasedEvent e) {return false;}
	protected boolean onMouseScrollEvent(MouseScrollEvent e) {return false;}
	
	@Override
	public String toString() {
		return name;
	}
	
	public LayerType getType() {
		return type;
	}
}
