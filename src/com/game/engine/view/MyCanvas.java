package com.game.engine.view;


import java.awt.Canvas;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;

import com.game.ObJectRelease;
import com.game.engine.controller.Application;
import com.game.engine.model.WindowSpec;
import com.game.event.EventDispatcher;
import com.game.event.KeyPressedEvent;
import com.game.event.KeyReleasedEvent;
import com.game.event.KeyTypeEvent;
import com.game.event.MouseMovedEvent;
import com.game.event.MousePressedEvent;
import com.game.event.MouseReleasedEvent;
import com.game.event.MouseScrollEvent;
import com.game.input.Mouse;
import com.game.layer.Layer;
import com.game.layer.LayerStack;
import com.game.logger.EngineLogger;
import com.game.renderer.RenderCommand;
import com.game.renderer.Renderer2D;

public class MyCanvas extends Canvas implements MouseListener, KeyListener, MouseMotionListener, MouseWheelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static boolean firstOpen = true;
	private static LayerStack layerStack;
	
	public MyCanvas(WindowSpec spec) {
		super();
		this.setSize(spec.getWidth(), spec.getHeight());
		this.setFocusable(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		this.addKeyListener(this);	
		this.setIgnoreRepaint(true);
		
	}
	
	
	public void paintComponent() {
		//requestFocus(true);
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null) return;
		
		Graphics2D g2d = null;
		try {
			
//	        if (bufferStrategy.getCapabilities().isPageFlipping()) {
//	            System.out.println("Page Flipping is being used.");
//	        } else {
//	            System.out.println("Page Flipping is not supported; using blitting.");
//	        }
			
			g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
			Renderer2D.setRenderHint(g2d);
			RenderCommand.setGraphics(g2d);
			layerStack = Application.Get().getLayerStack();
			
			for(Layer layer : layerStack.Get()) {
				layer.onRender();
			}
			
		} finally {
			g2d.dispose();
			bufferStrategy.show();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		try(ObJectRelease<KeyTypeEvent> ob = new ObJectRelease<KeyTypeEvent>(new KeyTypeEvent(e.getKeyCode()))){
			KeyTypeEvent event = ob.getResource();
			EventDispatcher evntDispatch = Application.Get().getDispatcher();
			evntDispatch.dispatchEventListener(event);
			//System.out.println(event.getKeyCode());
		} catch(Exception ex) {
			EngineLogger.Get().severe(ex.getMessage());
		}	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try(ObJectRelease<KeyPressedEvent> ob = new ObJectRelease<KeyPressedEvent>(new KeyPressedEvent(e.getKeyCode()))){
			KeyPressedEvent event = ob.getResource();
			EventDispatcher evntDispatch = Application.Get().getDispatcher();
			evntDispatch.dispatchEventListener(event);
			//System.out.println(event.getKeyCode());
		} catch(Exception ex) {
			EngineLogger.Get().severe(ex.getMessage());
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try(ObJectRelease<KeyReleasedEvent> ob = new ObJectRelease<KeyReleasedEvent>(new KeyReleasedEvent(e.getKeyCode()))){
			KeyReleasedEvent event = ob.getResource();
			EventDispatcher evntDispatch = Application.Get().getDispatcher();
			evntDispatch.dispatchEventListener(event);
			//System.out.println(event.getKeyCode());
		} catch(Exception ex) {
			EngineLogger.Get().severe(ex.getMessage());
		}	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try(ObJectRelease<MousePressedEvent> ob = new ObJectRelease<MousePressedEvent>(new MousePressedEvent(e.getButton()))){
			MousePressedEvent event = ob.getResource();
			EventDispatcher evntDispatch = Application.Get().getDispatcher();
			Mouse.getMousestate().put(e.getButton(), MouseEvent.MOUSE_PRESSED);
			evntDispatch.dispatchEventListener(event);
		} catch(Exception ex) {
			EngineLogger.Get().severe(ex.getMessage());
		}	
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		try(ObJectRelease<MouseReleasedEvent> ob = new ObJectRelease<MouseReleasedEvent>(new MouseReleasedEvent(e.getButton()))){
			MouseReleasedEvent event = ob.getResource();
			EventDispatcher evntDispatch = Application.Get().getDispatcher();
			Mouse.getMousestate().put(e.getButton(), MouseEvent.MOUSE_RELEASED);
			evntDispatch.dispatchEventListener(event);
		} catch(Exception ex) {
			EngineLogger.Get().severe(ex.getMessage());
		}	
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		try(ObJectRelease<MouseMovedEvent> ob = new ObJectRelease<MouseMovedEvent>(new MouseMovedEvent(e.getX(), e.getY(), 0, 0))){
			MouseMovedEvent event = ob.getResource();
			if(firstOpen) {
				Mouse.setxPos(e.getX());
				Mouse.setyPos(e.getY());
				firstOpen = false;
			}
			Mouse.mouseMoved(e.getX(), e.getY());
			EventDispatcher evntDispatch = Application.Get().getDispatcher();
			evntDispatch.dispatchEventListener(event);
		} catch(Exception ex) {
			EngineLogger.Get().severe(ex.getMessage());
		}
		
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		try(ObJectRelease<MouseScrollEvent> ob = new ObJectRelease<MouseScrollEvent>(new MouseScrollEvent(e.getUnitsToScroll()))){
			MouseScrollEvent event = ob.getResource();
			EventDispatcher evntDispatch = Application.Get().getDispatcher();
			evntDispatch.dispatchEventListener(event);
		} catch(Exception ex) {
			EngineLogger.Get().severe(ex.getMessage());
		}	
		
	}
	
	public void onRender() {
		this.paintComponent();
	}
}
