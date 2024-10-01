package com.game;

import java.awt.geom.Rectangle2D;

import com.game.graphics.Sprite;
import com.game.time.TimeSteps;

public interface Entity {
	
	public Sprite getSprite();

	public void setSprite(Sprite sprite);	
	public void setState(int state);
	
	public void setxPos(float xPos);
	public void setyPos(float yPos);

	public float getxPos();
	public float getyPos();
	
	public void setRect(double x, double y, double w, double h);
	public Rectangle2D getRect();

	public boolean isCollide(Rectangle2D rect);
	
	public void update(TimeSteps ts);

}
