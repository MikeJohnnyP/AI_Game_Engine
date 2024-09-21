package com.game.graphics;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.game.logger.EngineLogger;

public class Texture {
	
	private BufferedImage img;
	private int width;
	private int height;
	private String texName;
	
	public Texture(BufferedImage img) {
		this.img = img;
		this.width = img.getWidth();
		this.height = img.getHeight();
	}
	
	public Texture(BufferedImage img, String name) {
		this.img = img;
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.texName = name;
	}
	
	public Texture(String path) {
		this.img = loadPath(path);
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.texName = null;
	}
	
	public Texture(String path, String name) {
		this.img = loadPath(path);
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.texName = name;
	}
	
	public BufferedImage loadPath(String path) {
		   BufferedImage sprite = null;
	        try {
	        	EngineLogger.Get().info("Load texture: " + path);
	            //sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
	        	sprite = ImageIO.read(new File("src/res/" + path));
	        } catch (Exception e) {
	            EngineLogger.Get().warning("ERROR: could not load texture: " + path + "\n" + e.getMessage());
	        }
	        return sprite;
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public int getTextureWidth() {
		return width;
	}
	
	public int getTextureHeight() {
		return height;
	}
	
	public String getName() {
		return texName;
	}
	
}
