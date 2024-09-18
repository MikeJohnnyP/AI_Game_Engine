package com.game.engine.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.game.engine.model.Window;
import com.game.engine.model.WindowSpec;
import com.game.engine.view.AView;
import com.game.engine.view.ViewComponent;
import com.game.event.EventDispatcher;
import com.game.event.KeyPressedEvent;
import com.game.event.KeyReleasedEvent;
import com.game.event.MouseMovedEvent;
import com.game.event.MousePressedEvent;
import com.game.event.MouseReleasedEvent;
import com.game.input.Keyboard;
import com.game.input.Mouse;
import com.game.logger.EngineLogger;

public class Application {
	private boolean isWindowClose;
	private Window window;
	private WindowSpec spec;
	private AView view;
	private EventDispatcher eventDispatcher;
	
	private static Application instance;
	
	public Application() {
		
	}
	
	public static Application Get() {
		if(instance == null) {
			EngineLogger.Get().severe("Application instance is null");
			return instance;
		} else return instance;
	}
	
	public static void setInstance(Application application) {
		instance = application;
	}
	
	public boolean init(WindowSpec spec) {
		if(instance == null) {
			EngineLogger.Get().severe("Application instance is null");
			return false;
		}
		this.spec = spec;
		isWindowClose = true;
		eventDispatcher = new EventDispatcher();
		window = new Window(spec);
		view = new ViewComponent();
		if(!window.init()) {
			EngineLogger.Get().severe("Failed to init window");
			return false;
		} else
			EngineLogger.Get().info("Init Window Sucess");
		window.setComponent(view);
			
		EngineLogger.Get().info("Init Application Sucess");
		
		
		eventDispatcher.addEventListener(MouseMovedEvent.class, this::mouseMoved);
		eventDispatcher.addEventListener(KeyPressedEvent.class, this::keyPressed);
		eventDispatcher.addEventListener(KeyReleasedEvent.class, this::keyReleased);
		eventDispatcher.addEventListener(MousePressedEvent.class, this::mousePressed);
		eventDispatcher.addEventListener(MouseReleasedEvent.class, this::mouseReleased);
		
		return true;
	}
	
	public void run() {
		while(isWindowClose && true) {
			view.repaint();
		}
	}
	public void shutdown() {}
	public void onEvent() {}
	
	public Window getWindow() {
		return window;
	}
	
	public void clientInit() {}
	public void clientShutdown() {}
	
	public EventDispatcher getDispatcher() {
		return eventDispatcher;
	}
	
	public boolean mouseMoved(MouseMovedEvent e) {
//		System.out.println("xPos: " + Mouse.getPosX());
//		System.out.println("yPos: " + Mouse.getPosY());
//		System.out.println("xOffset: " + Mouse.getXOffset());
//		System.out.println("yOffset: " + Mouse.getYOffset());
		return false;
	}
	
	boolean keyPressed(KeyPressedEvent e) {
		if(Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			System.out.println("Escape is pressed");
		}
		if(Keyboard.isKeyPressed(KeyEvent.VK_1)) {
			System.out.println("1 is pressed");
		}
		return false;
	}
	
	boolean keyReleased(KeyReleasedEvent e) {
		if(Keyboard.isKeyReleased(KeyEvent.VK_ESCAPE)) {
			System.out.println("Escape is released");
		}
		if(Keyboard.isKeyReleased(KeyEvent.VK_1)) {
			System.out.println("1 is released");
		}
		return false;
	}
	
	boolean mousePressed(MousePressedEvent e) {
		if(Mouse.isMousePressed(MouseEvent.BUTTON1)) {
			System.out.println("Mouse left is pressed");
		}
		return false;
	}
	
	boolean mouseReleased(MouseReleasedEvent e) {
		return false;
	}
	
	
}
