package com.game.renderer;

import java.awt.*;
import java.awt.Graphics2D;

import com.game.Entity;
import com.game.engine.controller.Application;
import com.game.engine.model.WindowSpec;


public class RenderCommand {
	
	private static Graphics2D g2d;
	private static GraphicsConfiguration gc;
	
	private RenderCommand() {}

	public static void setGraphics(Graphics2D currentG2d) {
		g2d = currentG2d;
	}
	
	public static void setGraphicsConfiguration(GraphicsConfiguration currentGc) {
		gc = currentGc;
	}
	
	public static void setColor(Color color) {
			g2d.setColor(color);
	}

	public static void setFont(Font font) {
		g2d.setFont(font);	
	}
	
	public static void clearScreen(Color color) {
		setColor(color);
		WindowSpec data = Application.Get().getWindow().getWindowData();
		g2d.fillRect(0, 0, data.getWidth(), data.getHeight());
	}
	
	public static void drawRect(int x, int y, int sizeX, int sizeY) {
		g2d.drawRect(x, y, sizeX, sizeY);
	}
	
	public static void drawColorRect(int x, int y, int sizeX, int sizeY, Color color) {
		setColor(color);
		g2d.drawRect(x, y, sizeX, sizeY);
	}
	
	public static void fillRect(int x, int y, int sizeX, int sizeY) {
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
	
	public static void drawImg(Image img, int x, int y, int w, int h) {
		Graphics2D draw2D = (Graphics2D) g2d.create();
		
	    draw2D.translate(x, y);
			
		draw2D.drawImage(img, 0, 0, w, h, null);
		draw2D.dispose();
	}

	public static void drawImg(Image img, float x, float y, int w, int h) {
		Graphics2D draw2D = (Graphics2D) g2d.create();
		
	    draw2D.translate(x, y);
			
		draw2D.drawImage(img, 0, 0, w, h, null);
		draw2D.dispose();
	}
	
	public static void drawImg(Entity entity) {
		Graphics2D draw2D = (Graphics2D) g2d.create();
		
	    draw2D.translate(entity.getxPos(), entity.getyPos());
			
		draw2D.drawImage(entity.getSprite().getImg(), 0, 0, entity.getSprite().getWidth(), entity.getSprite().getHeight(), null);
		draw2D.dispose();
		g2d.draw(entity.getRect());
	}

	public static void drawString(String text, int xPos, int yPos){
		g2d.drawString(text, xPos, yPos);
	}
	
	public static GraphicsConfiguration getGraphicsConfiguration() {
		return gc;
	}
	
}
