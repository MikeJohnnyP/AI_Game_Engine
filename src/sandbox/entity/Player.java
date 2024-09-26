package sandbox.entity;

import java.awt.geom.Rectangle2D;

import com.game.Entity;
import com.game.animation.StateMachine;
import com.game.graphics.Sprite;
import com.game.time.TimeSteps;

public class Player implements Entity {
	StateMachine stateMachine;
	
	boolean isAttack = false;
	boolean isRun = false;
	boolean isIdle = false;
	
	private int xPos;
	private int yPos;
	
	private int relativePosX;
	private int relativePosY;
	
	
	public Rectangle2D rectCollide = new Rectangle2D.Double();

	public Player(StateMachine stateMachine, int posX, int posY) {
		this.stateMachine = stateMachine;
		this.xPos = posX;
		this.yPos = posY;
		this.relativePosX = posX;
		this.relativePosY = posY;
	}

	@Override
	public Sprite getSprite() {
		return stateMachine.getState().getCurrentSprite();
	}

	@Override
	public void setSprite(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getxPos() {
		return xPos;
	}
	@Override
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	@Override
	public int getyPos() {
		return yPos;
	}
	@Override
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	@Override
	public void setState(int state) {
		stateMachine.triggerState(state);
	}

	@Override
	public void update(TimeSteps ts) {
		stateMachine.update((float) ts.getTimeSpeed());
		
	}

	@Override
	public int getRelativeXPos() {
		return relativePosX;
	}

	@Override
	public int getRelativeYPos() {
		return relativePosY;
	}

	@Override
	public boolean isCollide(Rectangle2D rect) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRelativePosX(int relativePosX) {
		this.relativePosX = relativePosX;
		
	}

	@Override
	public void setRelativePosY(int relativePosY) {
		this.relativePosY = relativePosY;
	}

	@Override
	public void setRect(double x, double y, double w, double h) {
		this.rectCollide.setFrame(x, y, w, h);
		
	}

	@Override
	public Rectangle2D getRect() {
		return rectCollide;
	}
	
	
	
	
}
