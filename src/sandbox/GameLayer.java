package sandbox;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import com.game.engine.controller.Application;
import com.game.event.KeyPressedEvent;
import com.game.event.MousePressedEvent;
import com.game.graphics.AnimatedSprite;
import com.game.graphics.Sprite;
import com.game.graphics.Texture;
import com.game.input.Keyboard;
import com.game.layer.Layer;
import com.game.logger.EngineLogger;
import com.game.renderer.RenderCommand;
import com.game.renderer.Renderer2D;
import com.game.time.TimeSteps;

public class GameLayer extends Layer {
	private Rectangle2D rect = new Rectangle2D.Double(50.67, 50.75, 200.65, 200.54);
	Texture tex2;
	Texture tex3;
	Sprite attack;
	Sprite background;

	protected GameLayer(String name, Layer.LayerType type) {
		super(name, type);
	}

	@Override
	public void onAttach() {
		tex3 = new Texture("Blue_witch/B_witch_idle.png", "Player");
		attack = new AnimatedSprite(tex3, 5, 0, 32, 48, 2);
		tex2 = new Texture("Background/Background.png", "background");
		background = new Sprite(tex2, 0, 0, 1280, 720);
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
		if(Keyboard.isKeyPressed(KeyEvent.VK_W)) attack.setYPos(attack.getYpos() - 500);
		if(Keyboard.isKeyPressed(KeyEvent.VK_S)) attack.setYPos(attack.getYpos() + 500);
		if(Keyboard.isKeyPressed(KeyEvent.VK_A)) attack.setXPos(attack.getXpos() - 500);
		if(Keyboard.isKeyPressed(KeyEvent.VK_D)) attack.setXPos(attack.getXpos() + 500);
		
		System.out.println(attack.getXpos());
		System.out.println(attack.getYpos());
	}

	@Override
	public void onRender() {
		RenderCommand.clearScreen(Color.black);
		Renderer2D.draw(background);
		Renderer2D.draw(attack);
		
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
