package sandbox;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.game.AssetPool;
import com.game.animation.AnimationState;
import com.game.animation.State;
import com.game.animation.StateMachine;
import com.game.engine.controller.Application;
import com.game.event.KeyPressedEvent;
import com.game.event.MouseMovedEvent;
import com.game.event.MousePressedEvent;
import com.game.graphics.SpriteSheet;
import com.game.graphics.Sprite;
import com.game.graphics.Texture;
import com.game.input.Keyboard;
import com.game.input.Mouse;
import com.game.layer.Layer;
import com.game.logger.EngineLogger;
import com.game.renderer.RenderCommand;
import com.game.renderer.Renderer2D;
import com.game.time.TimeSteps;

import sandbox.entity.Player;

public class GameLayer extends Layer {
	SpriteSheet run;
	SpriteSheet idle;
	SpriteSheet attack;
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
		run = new SpriteSheet(AssetPool.Get().getAsset("WitchRun"), 7, 0, 32, 48, 2);
		idle = new SpriteSheet(AssetPool.Get().getAsset("WitchIdle"), 5, 0, 32, 48, 2);
		attack = new SpriteSheet(AssetPool.Get().getAsset("WitchAttack"), 8, 0, 104, 46);
		
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
		
		player = new Player(stateMachine, 640, 350);
		
		background = new Sprite(AssetPool.Get().getAsset("BlueBackground"), 0, 0, 1280, 720);
		Application.Get().getDispatcher().addEventListener(MousePressedEvent.class, this::onMousePressedEvent);
		Application.Get().getDispatcher().addEventListener(KeyPressedEvent.class, this::onKeyPressedEvent);
		Application.Get().getDispatcher().addEventListener(MouseMovedEvent.class, this::onMouseMovedEvent);
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
		Renderer2D.draw(background);
		Renderer2D.draw(player);
		
	}
	
	@Override
	protected boolean onKeyPressedEvent(KeyPressedEvent e) {
		return false;
	}
	
	@Override
	protected boolean onMousePressedEvent(MousePressedEvent e) {
		EngineLogger.Get().info("MouseX: " + Mouse.getPosX() + " MouseY: " + Mouse.getPosY());
		return false;
	}
	
	@Override
	protected boolean onMouseMovedEvent(MouseMovedEvent e) {
		return false;
	}

}
