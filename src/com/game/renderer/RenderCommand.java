package com.game.renderer;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import com.game.Entity;
import com.game.engine.controller.Application;
import com.game.engine.model.Window.WindowData;
import com.game.time.TimeSteps;

import sandbox.entity.Player;

public class RenderCommand {
	
	private static Graphics2D g2d;
	private static GraphicsConfiguration gc;
	private static TimeSteps ts;
	
	private RenderCommand() {}

	public static void setGraphics(Graphics2D currentG2d) {
		g2d = currentG2d;
	}
	
	public static void setGraphicsConfiguration(GraphicsConfiguration currentGc) {
		gc = currentGc;
	}
	
	public static void setTimeSteps(TimeSteps currentTs) {
		ts = currentTs;
	}
	
	public static void setTimeSteps(double deltaTime) {
		ts.setDeltaTime(deltaTime);
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
	
	public static void drawImg(Image img, int w, int h) {
		g2d.drawImage(img, 0, 0, null);
	}
	
	public static void drawImg(Image img, int x, int y, int w, int h) {
		Graphics2D draw2D = (Graphics2D) g2d.create();
		
	    double translateX = x * ts.getTimeSpeed();
	    double translateY = y * ts.getTimeSpeed();

	    draw2D.translate(translateX, translateY);
			
		draw2D.drawImage(img, 0, 0, w, h, null);
		draw2D.dispose();
	}
	
	public static void drawImg(Entity entity) {
		Graphics2D draw2D = (Graphics2D) g2d.create();
		
	    double translateX = entity.getxPos() * ts.getTimeSpeed();
	    double translateY = entity.getyPos() * ts.getTimeSpeed();

	    draw2D.translate(translateX, translateY);
			
		draw2D.drawImage(entity.getSprite().getImg(), 0, 0, entity.getSprite().getWidth(), entity.getSprite().getHeight(), null);
		draw2D.dispose();
		
		entity.setRelativePosX((int) translateX);
		entity.setRelativePosY((int) translateY);
		entity.setRect(translateX, translateY, entity.getSprite().getImg().getWidth(), entity.getSprite().getImg().getHeight());
		g2d.draw(entity.getRect());
		
	}
	
	public static GraphicsConfiguration getGraphicsConfiguration() {
		return gc;
	}
	
}
