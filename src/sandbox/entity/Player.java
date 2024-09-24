package sandbox.entity;

import com.game.graphics.AnimatedSprite;
import com.game.graphics.Sprite;

public class Player implements Entity {
	AnimatedSprite run;
	AnimatedSprite jumpStart;
	AnimatedSprite jumpEnd;
	AnimatedSprite dead;
	AnimatedSprite idle;
	AnimatedSprite attack;
	
	private int currentState;

	public Player() {}

	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSprite(Sprite sprite) {
		// TODO Auto-generated method stub
		
	}
	

}
