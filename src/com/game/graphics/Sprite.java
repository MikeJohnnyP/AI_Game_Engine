package com.game.graphics;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

import com.game.renderer.RenderCommand;

public class Sprite {
	private int xPos = 0;
	private int yPos = 0;
	private int w;
	private int h;
	private String texName;
	private BufferedImage img;

    public Sprite(Texture texture) {
		this.w = texture.getTextureWidth();
		this.h = texture.getTextureHeight();
		this.texName = texture.getName();
		this.img = texture.getImg();
	}
	
	public int getWidth() { return w; }
	public int getHeight() { return h; }
	public String getName() { return texName; }
	public BufferedImage getImg() { return img; }
	public int getXpos() { return xPos; }
	public int getYpos() { return yPos; }
	public void setXPos(int xPos) { this.xPos = xPos; } 
	public void setYPos(int yPos) { this.yPos = yPos; } 

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
