package com.game.engine.controller;

import com.game.engine.model.Window;
import com.game.engine.model.WindowSpec;
import com.game.engine.view.AView;
import com.game.engine.view.ViewComponent;
import com.game.logger.EngineLogger;

public abstract class Application {
	protected boolean isWindowClose;
	protected Window window;
	protected WindowSpec spec;
	protected AView view;
	
	public Application(WindowSpec spec) {
		this.spec = spec;
		isWindowClose = true;
		window = new Window(spec);
		view = new ViewComponent(spec);
	}
	
	
	public boolean init() {
		if(!window.init()) {
			EngineLogger.Get().severe("Failed to init window");
			return false;
		} else
			EngineLogger.Get().info("Init Window Sucess");
		window.setComponent(view);
			
		EngineLogger.Get().info("Init Application Sucess");
		return true;
	}
	
	public void run() {
		while(true) {
			view.repaint();
		}
	}
	public void shutdown() {}
	public void onEvent() {}
	
	public Window getWindow() {
		return window;
	}
	
	public abstract void clientInit();
	public abstract void clientShutdown();
	
}
