package sandbox;

import java.awt.Graphics;

import com.game.engine.controller.Application;
import com.game.event.KeyPressedEvent;
import com.game.event.MousePressedEvent;
import com.game.layer.Layer;
import com.game.logger.EngineLogger;

public class GameLayer extends Layer {

	protected GameLayer(String name, Layer.LayerType type) {
		super(name, type);
	}

	@Override
	public void onAttach() {
		System.out.println("Game Layer attach");
		Application.Get().getDispatcher().addEventListener(MousePressedEvent.class, this::onMousePressedEvent);
		Application.Get().getDispatcher().addEventListener(KeyPressedEvent.class, this::onKeyPressedEvent);
		
	}

	@Override
	public void onDettach() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate() {
		System.out.println("Game layer update");
		
	}

	@Override
	public void onRender(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected boolean onKeyPressedEvent(KeyPressedEvent e) {
		EngineLogger.Get().info("Game Layer keyPressed");
		return false;
	}
	
	@Override
	protected boolean onMousePressedEvent(MousePressedEvent e) {
		EngineLogger.Get().info("Game Layer mousePressed");
		return false;
	}

}
