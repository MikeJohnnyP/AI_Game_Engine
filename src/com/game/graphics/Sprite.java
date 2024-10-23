package com.game.graphics;


import java.awt.image.BufferedImage;

import com.game.logger.EngineLogger;
import com.game.scale.Scalr;
import com.game.scale.Scalr.Method;

public class Sprite {
	protected int xPos = 0;
	protected int yPos = 0;
	protected int w;
	protected int h;
	protected String texName;
	protected BufferedImage masterImg;
	protected BufferedImage scaledImg;

    public Sprite(Texture texture) {
		EngineLogger.Get().info("Created Sprite: " + texture.getName());
		this.w = texture.getTextureWidth();
		this.h = texture.getTextureHeight();
		this.texName = texture.getName();
		this.masterImg = texture.getImg();
		this.scaledImg = this.masterImg;
	}
    
    public Sprite(Texture texture, int xPos, int yPos) {
		EngineLogger.Get().info("Created Sprite: " + texture.getName());
    	this.xPos = xPos;
    	this.yPos = yPos;
		this.w = texture.getTextureWidth();
		this.h = texture.getTextureHeight();
		this.texName = texture.getName();
		this.masterImg = texture.getImg();
		this.scaledImg = this.masterImg;
	}
    
    public Sprite(Texture texture, int xPos, int yPos, int scale) {
		EngineLogger.Get().info("Created Sprite: " + texture.getName());
    	this.xPos = xPos;
    	this.yPos = yPos;
		this.w = texture.getTextureWidth();
		this.h = texture.getTextureHeight();
		this.texName = texture.getName();
		this.masterImg = texture.getImg();
		this.scaledImg = scaleImg(this.masterImg, scale);
	}
    
    public Sprite(Texture texture, int xPos, int yPos, int scaleX, int scaleY) {
		EngineLogger.Get().info("Created Sprite: " + texture.getName());
    	this.xPos = xPos;
    	this.yPos = yPos;
		this.w = texture.getTextureWidth();
		this.h = texture.getTextureHeight();
		this.texName = texture.getName();
		this.masterImg = texture.getImg();
		this.scaledImg = scaleImg(this.masterImg, scaleX, scaleY);
	}
    
    public Sprite(BufferedImage img) {
		EngineLogger.Get().info("Created Sprite: " + this.getClass().getName());
    	this.xPos = 0;
    	this.yPos = 0;
    	this.w = img.getWidth();
    	this.h = img.getHeight();
    	this.texName = null;
    	this.masterImg = img;
    	this.scaledImg = img;
    }
	
	public int getWidth() { return w; }
	public int getHeight() { return h; }
	public String getName() { return texName; }
	public BufferedImage getImg() { return scaledImg; }
	public int getXpos() { return xPos; }
	public int getYpos() { return yPos; }
	public void setXPos(int xPos) { this.xPos = xPos; } 
	public void setYPos(int yPos) { this.yPos = yPos; } 
	
	protected BufferedImage scaleImg(BufferedImage masterImg, int value) {
		BufferedImage result = Scalr.resize(masterImg, Method.ULTRA_QUALITY, masterImg.getWidth() * value, masterImg.getHeight() * value);
		if (result != null) {
			this.w = result.getWidth();
			this.h = result.getHeight();
			EngineLogger.Get().info("Scale Image Success: " + this.texName);
		} else EngineLogger.Get().info("Scale Image Failed: " + this.texName);
		return result;
	}
	
	public BufferedImage scaleImg(BufferedImage masterImg, int targetWidth, int targetHeight) {
		BufferedImage result = Scalr.resize(masterImg, Method.ULTRA_QUALITY, targetWidth, targetHeight);
		if (result != null) {
			this.w = targetWidth;
			this.h = targetHeight;
			EngineLogger.Get().info("Scale Image Success: " + this.texName);
		} else EngineLogger.Get().info("Scale Image Failed: " + this.texName);
		return result;
	}

//    public Sprite getSubimage(int x, int y, int w, int h) {
//        return new Sprite(image.getSubimage(x, y, w, h));
//	}
	
//	public Sprite getNewSubimage(int x, int y, int w, int h) {
//		BufferedImage temp = image.getSubimage(x, y, w, h);
//		BufferedImage newImage = new BufferedImage(image.getColorModel(), image.getRaster().createCompatibleWritableRaster(w,h), image.isAlphaPremultiplied(), null);
//		temp.copyData(newImage.getRaster());
//        return new Sprite(newImage);
//	}

//	public Sprite getNewSubimage() {
//		return getNewSubimage(0, 0, this.w, this.h);
//	}
	
//	private VolatileImage createVolatileImage(BufferedImage img, int width, int height, int transparency) { 
//		GraphicsConfiguration gc = RenderCommand.getGraphicsConfiguration(); 
//
//		
//        VolatileImage vImage = gc.createCompatibleVolatileImage(width, height, transparency);
//
//        
//        do {
//            if (vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
//             
//                vImage = gc.createCompatibleVolatileImage(width, height, transparency);
//            }
//            Graphics2D g2d = vImage.createGraphics();
//       
//            g2d.drawImage(img, 0, 0, null);
//            g2d.dispose();
//        } while (vImage.contentsLost());
//        return vImage;
//	} 
//	
//	private VolatileImage createVolatileImage(BufferedImage img) { 
//		GraphicsConfiguration gc = RenderCommand.getGraphicsConfiguration(); 
//
//        VolatileImage vImage = gc.createCompatibleVolatileImage(w, h, Transparency.TRANSLUCENT);
//
//       
//        do {
//            if (vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
//                
//                vImage = gc.createCompatibleVolatileImage(w, h, Transparency.TRANSLUCENT);
//            }
//            Graphics2D g2d = vImage.createGraphics();
//            
//            g2d.drawImage(img, 0, 0, null);
//            g2d.dispose();
//        } while (vImage.contentsLost()); 
//
//        return vImage;
//	} 
//	
//	private VolatileImage createVolatileImage(Texture texture) {
//        return createVolatileImage(texture.getImg(), texture.getTextureWidth(), texture.getTextureHeight(), Transparency.TRANSLUCENT);
//	} 
//	
	
}
