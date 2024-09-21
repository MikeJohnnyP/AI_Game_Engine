package com.game.renderer;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.game.graphics.Sprite;

public class Renderer2D{

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
		// TODO Auto-generated method stub
		
	}

	public static void draw(Sprite sprite) {
		RenderCommand.drawImg(sprite.getImg(), sprite.getXpos(), sprite.getYpos(), sprite.getWidth(), sprite.getHeight());
	}
	
	public static void endScenne() {
		// TODO Auto-generated method stub
		
	}

}
