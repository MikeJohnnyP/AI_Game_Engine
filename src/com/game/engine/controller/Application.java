package com.game.engine.controller;

import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.ImageCapabilities;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.time.Instant;

import com.game.engine.model.PerFrameData;
import com.game.engine.model.Window;
import com.game.engine.model.WindowSpec;
import com.game.engine.view.AView;
import com.game.engine.view.MyCanvas;
import com.game.engine.view.ViewComponent;
import com.game.event.EventDispatcher;
import com.game.event.KeyPressedEvent;
import com.game.event.KeyReleasedEvent;
import com.game.event.MouseMovedEvent;
import com.game.event.MousePressedEvent;
import com.game.event.MouseReleasedEvent;
import com.game.input.Keyboard;
import com.game.input.Mouse;
import com.game.layer.Layer;
import com.game.layer.LayerStack;
import com.game.logger.EngineLogger;
import com.game.renderer.RenderCommand;
import com.game.time.TimeSteps;

public class Application {
	private boolean isWindowClose;
	private Window window;
	private WindowSpec spec;
	private AView view;
	private MyCanvas canvas;
	private EventDispatcher eventDispatcher;
	private LayerStack layerStack;
	private TimeSteps ts;
	private PerFrameData frameData;
	
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
		layerStack = new LayerStack();
		window = new Window(spec);
		//view = new ViewComponent();
		
		if(!window.init()) {
			EngineLogger.Get().severe("Failed to init window");
			return false;
		} else
			EngineLogger.Get().info("Init Window Sucess");
		//window.setComponent(view);
		canvas = new MyCanvas(spec);
		 window.setCanvas(canvas);
		BufferCapabilities bufferCapabilities = new BufferCapabilities(
                new ImageCapabilities(true), 
                new ImageCapabilities(true), 
                BufferCapabilities.FlipContents.UNDEFINED 
        );

        try {
            canvas.createBufferStrategy(2, bufferCapabilities);
        } catch (AWTException e) {
            System.err.println("Page flipping not support.");
            canvas.createBufferStrategy(2);
        }
		EngineLogger.Get().info("Init Application Sucess");
		
		eventDispatcher.addEventListener(MouseMovedEvent.class, this::mouseMoved);
		eventDispatcher.addEventListener(KeyPressedEvent.class, this::keyPressed);
		eventDispatcher.addEventListener(KeyReleasedEvent.class, this::keyReleased);
		eventDispatcher.addEventListener(MousePressedEvent.class, this::mousePressed);
		eventDispatcher.addEventListener(MouseReleasedEvent.class, this::mouseReleased);
		ts = new TimeSteps(0.f, 1.f);
		RenderCommand.setGraphicsConfiguration(canvas.getGraphicsConfiguration());
		frameData = new PerFrameData();
		
		return true;
	}
	public void shutdown() {}
	
	public void clientInit() {}
	public void clientShutdown() {}
	
	public void pushLayer(Layer layer) {
		layerStack.pushLayer(layer);
	}
	
	public void pushOverlay(Layer overlay) {
		layerStack.pushOverlay(overlay);
	}
	
	public void popLayer(Layer layer) {
		layerStack.popLayer(layer);
	}
	
	public void popOverlay(Layer overlay) {
		layerStack.popOverlay(overlay);
	}
	
	public EventDispatcher getDispatcher() {
		return eventDispatcher;
	}
	
	public Window getWindow() {
		return window;
	}
	
	public LayerStack getLayerStack() {
		return layerStack;
	}
	
	public TimeSteps getTimeSteps() {
		return ts;
	}
	
	public void run() {
		clientInit();
		//final float MAX_DELTA_TIME = 0.05f;
		final double FIXED_TIME_STEP = 1.0 / spec.getMaxFPS();
		double accumulator = 0.0;
		long lastFrameTime = System.nanoTime();
		
		while(isWindowClose && true) {
			//RenderCommand.setGraphicsConfiguration(canvas.getGraphicsConfiguration());
			//while (System.nanoTime() - lastFrameTime < FIXED_TIME_STEP);
			long currentFrameTime = System.nanoTime();
			
			double elapsedTime = (currentFrameTime - lastFrameTime)/1_000_000_000.0;
			ts.setDeltaTime(elapsedTime); 
			
			accumulator += elapsedTime;
			
			lastFrameTime = currentFrameTime;
			
//			while (ts.getDeltaTime() > MAX_DELTA_TIME) {
//				frameData.IsCatchUpPhase = true;
//
//				for (Layer layer : layerStack.Get()) {
//					layer.onUpdate(MAX_DELTA_TIME);
//				}
//				ts.setDeltaTime(ts.getDeltaTime() - MAX_DELTA_TIME);
//				System.out.println("CatchUp Fps" + ts);
//				RenderCommand.setTimeSteps(MAX_DELTA_TIME);
//				canvas.onRender();
//				
//			}
//			frameData.IsCatchUpPhase = false;
			
			if(accumulator >= 3) {
				accumulator = FIXED_TIME_STEP;
			}
			
			while(accumulator >= FIXED_TIME_STEP) {
				for(Layer layer : layerStack.Get()) {
					ts.setDeltaTime(FIXED_TIME_STEP);
					layer.onUpdate(ts);
				}
				accumulator -= FIXED_TIME_STEP;
			}
			frameData.FrameIndex++;
			RenderCommand.setTimeSteps(ts);
			RenderCommand.setTimeSteps(FIXED_TIME_STEP);
			canvas.onRender();
		}
		clientShutdown();
	}
	
	public boolean mouseMoved(MouseMovedEvent e) {
		return false;
	}
	
	boolean keyPressed(KeyPressedEvent e) {
		return false;
	}
	
	boolean keyReleased(KeyReleasedEvent e) {
		return false;
	}
	
	boolean mousePressed(MousePressedEvent e) {
		return false;
	}
	
	boolean mouseReleased(MouseReleasedEvent e) {
		return false;
	}
	
	
}
