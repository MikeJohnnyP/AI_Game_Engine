package sandbox.entity;

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

	public Player(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
		this.xPos = 0;
		this.yPos = 0;
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
	
	
	
	
}
