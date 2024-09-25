package sandbox;


import java.awt.Color;
import java.awt.event.KeyEvent;

import com.game.animation.AnimationState;
import com.game.animation.State;
import com.game.animation.StateMachine;
import com.game.engine.controller.Application;
import com.game.event.KeyPressedEvent;
import com.game.event.MousePressedEvent;
import com.game.graphics.AnimatedSprite;
import com.game.graphics.Sprite;
import com.game.graphics.Texture;
import com.game.input.Keyboard;
import com.game.layer.Layer;
import com.game.renderer.RenderCommand;
import com.game.renderer.Renderer2D;
import com.game.time.TimeSteps;

import sandbox.entity.Player;

public class GameLayer extends Layer {
	Texture runTexture;
	Texture idleTexture;
	Texture attackTexture;
	AnimatedSprite run;
	AnimatedSprite idle;
	AnimatedSprite attack;
	Texture backgroundTexture;
	Sprite background;
	Player player;
	AnimationState arcRun = new AnimationState();
	AnimationState arcIdle = new AnimationState();
	StateMachine stateMachine = new StateMachine();

	protected GameLayer(String name, Layer.LayerType type) {
		super(name, type);
	}

	@Override
	public void onAttach() {
		idleTexture = new Texture("Blue_witch/B_witch_idle.png", "PlayerIdle");
		runTexture = new Texture("Blue_witch/B_witch_run.png", "PlayerRun");
		attackTexture = new Texture("Blue_witch/B_witch_attack.png", "PlayerAttack");
		run = new AnimatedSprite(runTexture, 7, 0, 32, 48, 2);
		idle = new AnimatedSprite(idleTexture, 5, 0, 32, 48, 2);
		attack = new AnimatedSprite(attackTexture, 8, 0, 104, 46);
		backgroundTexture = new Texture("Background/Background.png", "Background");
		
		arcRun.title = "run";
		float deltaTime = 0.1f;
		arcRun.addFrame(run.getSpriteIndex(0), deltaTime);
		arcRun.addFrame(run.getSpriteIndex(1), deltaTime);
		arcRun.addFrame(run.getSpriteIndex(2), deltaTime);
		arcRun.addFrame(run.getSpriteIndex(3), deltaTime);
		arcRun.addFrame(run.getSpriteIndex(4), deltaTime);
		arcRun.addFrame(run.getSpriteIndex(5), deltaTime);
		arcRun.addFrame(run.getSpriteIndex(6), deltaTime);
		arcRun.addFrame(run.getSpriteIndex(7), deltaTime);
		arcRun.setLoop(true);
		
		arcIdle.title = "idle";
		arcIdle.addFrame(idle.getSpriteIndex(0), deltaTime);
		arcIdle.addFrame(idle.getSpriteIndex(1), deltaTime);
		arcIdle.addFrame(idle.getSpriteIndex(2), deltaTime);
		arcIdle.addFrame(idle.getSpriteIndex(3), deltaTime);
		arcIdle.addFrame(idle.getSpriteIndex(4), deltaTime);
		arcIdle.addFrame(idle.getSpriteIndex(5), deltaTime);
		arcIdle.setLoop(true);
		
		stateMachine.addState(State.IDLE, arcIdle);
		stateMachine.addState(State.RUN, arcRun);
		stateMachine.setDefaultState(State.IDLE);
		
		player = new Player(stateMachine);
		
		
		background = new Sprite(backgroundTexture, 0, 0, 1280, 720);
		Application.Get().getDispatcher().addEventListener(MousePressedEvent.class, this::onMousePressedEvent);
		Application.Get().getDispatcher().addEventListener(KeyPressedEvent.class, this::onKeyPressedEvent);
		System.out.println("Game Layer attach");
		
	}

	@Override
	public void onDettach() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(TimeSteps ts) {
		player.update(ts);
		player.setState(State.IDLE);
			
		if(Keyboard.isKeyPressed(KeyEvent.VK_W)) { 
			player.setyPos(player.getyPos() - 500);
			player.setState(State.RUN);
		}
		if(Keyboard.isKeyPressed(KeyEvent.VK_S)) {
			player.setyPos(player.getyPos() + 500);
			player.setState(State.RUN);
		}
		if(Keyboard.isKeyPressed(KeyEvent.VK_A)) { 
			player.setxPos(player.getxPos() - 500);
			player.setState(State.RUN);
		}
		if(Keyboard.isKeyPressed(KeyEvent.VK_D)) {
			player.setxPos(player.getxPos() + 500);
			player.setState(State.RUN);
		}		
	}

	@Override
	public void onRender() {
		RenderCommand.clearScreen(Color.black);
		//Renderer2D.draw(background);
		Renderer2D.draw(player);
		
	}
	
	@Override
	protected boolean onKeyPressedEvent(KeyPressedEvent e) {
		return false;
	}
	
	@Override
	protected boolean onMousePressedEvent(MousePressedEvent e) {
		return false;
	}

}
