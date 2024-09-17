package com.game.engine.controller;

import com.game.engine.model.Window;
import com.game.engine.model.WindowSpec;

public abstract class Application {
	protected boolean isWindowClose;
	protected Window window;
	protected WindowSpec spec;
	
	public Application(WindowSpec spec) {
		this.spec = spec;
		isWindowClose = true;
		window = new Window(spec);
	}
	
	
	public boolean init() {
		window.init();
		
		return true;
	}
	public void run() {}
	public void Shutdown() {}
	public void onEvent() {}
	
	public Window getWindow() {
		return window;
	}
	
	public abstract void ClientInit();
	public abstract void ClienShutdown();
	
	
	
}
