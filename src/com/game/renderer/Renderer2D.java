package com.game.renderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;

import com.game.Entity;
import com.game.graphics.Sprite;

public class Renderer2D{

	public static final Font DEFAULT_FONT = new Font("Georgia", Font.BOLD, 20); 
	public static final Color DEFAULT_COLOR = Color.white;


	public static void setRenderHint(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);		
	}

	
	public static void beginScene() {
		
	}

	public static void draw(Sprite sprite) {
		RenderCommand.drawImg(sprite.getImg(), sprite.getXpos(), sprite.getYpos(), sprite.getWidth(), sprite.getHeight());
	}

	public static void drawRect(int x, int y, int width, int height) {
		RenderCommand.setColor(DEFAULT_COLOR);
		RenderCommand.drawRect(x, y, width, height);
	}

	public static void drawRect(int x, int y, int width, int height, Color color) {
		RenderCommand.setColor(color);
		RenderCommand.drawRect(x, y, width, height);
	}

	public static void fillRect(int x, int y, int width, int height) {
		RenderCommand.setColor(DEFAULT_COLOR);
		RenderCommand.drawRect(x, y, width, height);
	}

	public static void fillRect(int x, int y, int width, int height, Color color) {
		RenderCommand.setColor(color);
		RenderCommand.drawRect(x, y, width, height);
	}
	
	public static void draw(Entity entity) {
		RenderCommand.drawImg(entity);
	}

	public static void draw(Shape shape){
		RenderCommand.setColor(DEFAULT_COLOR);
		RenderCommand.drawShape(shape);
	}

	public static void draw(Shape shape, Color color){
		RenderCommand.setColor(color);
		RenderCommand.drawShape(shape);
	}

	public static void drawString(String string, int x, int y){
		RenderCommand.setColor(DEFAULT_COLOR);
		RenderCommand.setFont(DEFAULT_FONT);
		RenderCommand.drawString(string, x, y);
	}

	public static void drawString(String string, int x, int y, Color color){
		RenderCommand.setColor(color);
		RenderCommand.setFont(DEFAULT_FONT);
		RenderCommand.drawString(string, x, y);
	}	

	public static void drawString(String string, int x, int y, Font font){
		RenderCommand.setColor(DEFAULT_COLOR);
		RenderCommand.setFont(font);
		RenderCommand.drawString(string, x, y);
	}	

	public static void drawString(String string, int x, int y, Font font, Color color){
		RenderCommand.setColor(color);
		RenderCommand.setFont(font);
		RenderCommand.drawString(string, x, y);
	}	

	public static void endScenne() {
		
	}

}
