package com.game.engine.model;

import java.awt.Canvas;

import javax.swing.JFrame;

import com.game.engine.view.AView;
import com.game.logger.EngineLogger;

public class Window {
	private WindowSpec windowData;
	private JFrame frame;
	
	public Window(WindowSpec spec) {
		this.windowData = spec;
		EngineLogger.Get().info("WindowData: " + spec.getWidth() + ", " + spec.getHeight() + ", "+ spec.getTitle());
	}
	
	public boolean init() {
		System.setProperty("sun.java2d.opengl", "True");
		//System.setProperty("sun.java2d.trace", "log");
		frame = new JFrame();
		frame.setSize(windowData.getWidth(), windowData.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		return true;
	}
	
	public WindowSpec getWindowData() {
		return windowData;
	}
	
	public JFrame getWindow() {
		return frame;
	}

	public void setComponent(AView view) {
		try {
			frame.add(view);
			view.setFocusable(true);
			view.requestFocusInWindow();
			//System.out.println(view.isRequestFocusEnabled());
			EngineLogger.Get().info("Add Component: " + view.getClass().getName());
		} catch (Exception e) {
			EngineLogger.Get().severe("Exception: " + e.getMessage());
		}
		frame.pack();
		frame.revalidate();
		frame.repaint();	
	}
	
	public void setCanvas(Canvas canvas) {
		try {
			frame.add(canvas);
			canvas.setFocusable(true);
			canvas.requestFocusInWindow();
			//System.out.println(view.isRequestFocusEnabled());
			EngineLogger.Get().info("Add Canvas: " + canvas.getClass().getName());
		} catch (Exception e) {
			EngineLogger.Get().severe("Exception: " + e.getMessage());
		}
		frame.pack();
		frame.revalidate();
		frame.repaint();	
	}
	
}
