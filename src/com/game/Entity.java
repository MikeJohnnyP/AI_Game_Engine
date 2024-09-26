package com.game;

import java.awt.geom.Rectangle2D;

import com.game.graphics.Sprite;
import com.game.time.TimeSteps;

public interface Entity {
	
	public Sprite getSprite();

	public void setSprite(Sprite sprite);	
	public void setState(int state);
	
	public void setxPos(int xPos);
	public void setyPos(int yPos);

	public int getxPos();
	public int getyPos();
	
	public void setRect(double x, double y, double w, double h);
	public Rectangle2D getRect();

	public boolean isCollide(Rectangle2D rect);
	
	public void setRelativePosX(int relativePosX);
	public void setRelativePosY(int relativePosY);
	
	public int getRelativeXPos();
	public int getRelativeYPos();
	
	public void update(TimeSteps ts);

}
