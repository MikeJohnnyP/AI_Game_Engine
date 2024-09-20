package com.game.renderer;

import java.awt.*;
import java.awt.Graphics2D;

import com.game.engine.controller.Application;
import com.game.engine.model.Window.WindowData;

public class RenderCommand {
	
	private static Graphics2D g2d;
	
	private RenderCommand() {}

	public static void setGraphics(Graphics2D currentG2d) {
		g2d = currentG2d;
	}
	
	public static void setColor(Color color) {
			g2d.setColor(color);
	}
	
	public static void clearScreen(Color color) {
		setColor(color);
		WindowData data = Application.Get().getWindow().getWindowData();
		g2d.fillRect(0, 0, data.width, data.height);
	}
	
	public static void drawRect(int x, int y, int sizeX, int sizeY) {
		setColor(Color.white);
		g2d.drawRect(x, y, sizeX, sizeY);
	}
	
	public static void drawColorRect(int x, int y, int sizeX, int sizeY, Color color) {
		setColor(color);
		g2d.drawRect(x, y, sizeX, sizeY);
	}
	
	public static void fillRect(int x, int y, int sizeX, int sizeY) {
		setColor(Color.white);
		g2d.fillRect(x, y, sizeX, sizeY);
	}
	
	public static void fillColorRect(int x, int y, int sizeX, int sizeY, Color color) {
		setColor(color);
		g2d.fillRect(x, y, sizeX, sizeY);
	}
	
	public static void drawCircle(int x, int y, int sizeX, int sizeY) {
		setColor(Color.white);
		g2d.drawOval(x, y, sizeX, sizeY);
	}
	
	public static void drawColorCircle(int x, int y, int sizeX, int sizeY, Color color) {
		setColor(color);
		g2d.drawOval(x, y, sizeX, sizeY);
	}
	
	public static void drawShape(Shape shape) {
		g2d.fill(shape);
	}
	
	
}
