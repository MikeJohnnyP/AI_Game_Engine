package com.game.engine.model;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	class WindowData {
		int width, height;
		String title;
	}
	
	private WindowData windowData;
	private JFrame frame;
	
	public Window(WindowSpec spec) {
		windowData = new WindowData();
		windowData.width = spec.getWidth();
		windowData.height = spec.getHeight();
		windowData.title = spec.getTitle();
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
		frame.setVisible(true);
		return true;
	}
	
	public WindowData getWindowData() {
		return windowData;
	}
	
	public JFrame getWindow() {
		return frame;
	}
	
}
