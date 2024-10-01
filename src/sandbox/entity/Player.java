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
	
	public Rectangle2D rectCollide = new Rectangle2D.Float();

	public Player(StateMachine stateMachine, float posX, float posY) {
		this.stateMachine = stateMachine;
		Sprite temp = stateMachine.getState().getCurrentSprite();
		this.rectCollide.setFrame(posX, posY, temp.getWidth(), temp.getHeight());
	}

	@Override
	public Sprite getSprite() {
		return stateMachine.getState().getCurrentSprite();
	}

	@Override
	public void setSprite(Sprite sprite) {
		
	}

	@Override
	public float getxPos() {
		return (float) rectCollide.getX();
	}
	@Override
	public void setxPos(float xPos) {
		this.rectCollide.setFrame(xPos, rectCollide.getY(), rectCollide.getWidth(), rectCollide.getHeight());	
	}
	@Override
	public float getyPos() {
		return (float) rectCollide.getY();
	}
	@Override
	public void setyPos(float yPos) {
		this.rectCollide.setFrame(rectCollide.getX(), yPos, rectCollide.getWidth(), rectCollide.getHeight());	
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
	public boolean isCollide(Rectangle2D rect) {
		return rectCollide.intersects(rect);
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
