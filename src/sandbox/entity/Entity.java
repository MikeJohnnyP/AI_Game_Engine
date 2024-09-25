package sandbox.entity;


import com.game.graphics.Sprite;
import com.game.time.TimeSteps;

public interface Entity {
	
	public Sprite getSprite();

	public void setSprite(Sprite sprite);
	
	public void setState(int state);
	
	public int getxPos();
	public void setxPos(int xPos);

	public int getyPos();

	public void setyPos(int yPos);
	
	public void update(TimeSteps ts);

}
