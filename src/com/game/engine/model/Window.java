package com.game.engine.model;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.game.engine.view.AView;
import com.game.event.EventDispatcher;
import com.game.logger.EngineLogger;

public class Window {
	public class WindowData {
		public int width, height;
		public String title;
		public int maxFPS;
	}
	
	private WindowData windowData;
	private JFrame frame;
	
	public Window(WindowSpec spec) {
		windowData = new WindowData();
		windowData.width = spec.getWidth();
		windowData.height = spec.getHeight();
		windowData.title = spec.getTitle();
		windowData.maxFPS = spec.getMaxFPS();
		EngineLogger.Get().info("WindowData: " + spec.getWidth() + ", " + spec.getHeight() + ", "+ spec.getTitle());
	}
	
	public boolean init() {
		frame = new JFrame();
		Dimension dimension = new Dimension(windowData.width, windowData.height);
		frame.setMinimumSize(dimension);
		frame.setMaximumSize(dimension);
		frame.setPreferredSize(dimension);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		return true;
	}
	
	public WindowData getWindowData() {
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
		frame.revalidate();
		frame.repaint();	
	}
	
}
