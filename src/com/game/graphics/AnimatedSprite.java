package com.game.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimatedSprite extends Sprite {
	private ArrayList<BufferedImage> imgArray = new ArrayList<>();
	private int numOfCol, numOfRow;
	
	private int spritePerWidth, spritePerHeight;
	
	private int currentFrame;
	private int lastFrame;
	
	private int quantitySprite;
	
	public AnimatedSprite(Texture texture, int numOfRow, int numOfCol, int spritePerWidth, int spritePerHeight) {
		super(texture);
		this.numOfCol = numOfCol;
		this.numOfRow = numOfRow;
		this.quantitySprite = 0;
		this.currentFrame = 0;
		this.spritePerWidth = spritePerWidth;
		this.spritePerHeight = spritePerHeight;
		imgArray = getImgArray();
		
	}
	
	public AnimatedSprite(Texture texture, int numOfRow, int numOfCol, int spritePerWidth, int spritePerHeight, int scale) {
		super(texture, 0, 0, scale);
		this.numOfCol = numOfCol;
		this.numOfRow = numOfRow;
		this.quantitySprite = 0;
		this.currentFrame = 0;
		this.spritePerWidth = spritePerWidth * scale;
		this.spritePerHeight = spritePerHeight * scale;
		System.out.println(this.w);
		System.out.println(this.h);
		imgArray = getImgArray();
		
	}
	
	private ArrayList<BufferedImage> getImgArray() {
		ArrayList<BufferedImage> result = new ArrayList<BufferedImage>();
		for(int x = 0; x <= numOfCol; x++) {
			for(int y = 0; y <= numOfRow; y++) {
				result.add(scaledImg.getSubimage(x, y, spritePerWidth, spritePerHeight));
				quantitySprite++;
			}
		}
		return result;
	}
	
	private ArrayList<BufferedImage> getImgScaleArray(int value) {
		ArrayList<BufferedImage> result = new ArrayList<BufferedImage>();
		for(int x = 0; x <= numOfCol; x++) {
			for(int y = 0; y <= numOfRow; y++) {
				BufferedImage temp = scaleImg(masterImg, value);
				System.out.println(masterImg.getWidth() + " " + masterImg.getHeight());
				System.out.println(temp.getWidth() + " " + temp.getHeight());
				result.add(temp.getSubimage(y, x, spritePerWidth, spritePerHeight));
				quantitySprite++;
			}
		}
		return result;
	}
	
	@Override
	public BufferedImage getImg() {
		return imgArray.get(currentFrame);
	}
	
	@Override
	public int getWidth() {
		return spritePerWidth;
	}
	
	@Override
	public int getHeight() {
		return spritePerHeight;
	}
	
	public BufferedImage getImg(int index) {
		if(index >= 0 && index < quantitySprite) return imgArray.get(index);
		
		return null;
	}
	
	public void setCurrentFrame(int frame) {
		this.currentFrame = frame;
	}
	
	public void setLastFrame(int frame) {
		this.lastFrame = frame;
	}
	
	public int getCurrentFrame() {
		return currentFrame;
	}
	
	public int getLastFrame() {
		return lastFrame;
	}

}
