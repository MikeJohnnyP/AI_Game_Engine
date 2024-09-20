package sandbox;

import java.awt.Color;
import java.awt.Graphics;

import com.game.engine.controller.Application;
import com.game.event.KeyPressedEvent;
import com.game.event.MousePressedEvent;
import com.game.layer.Layer;
import com.game.logger.EngineLogger;
import com.game.renderer.RenderCommand;

public class UILayer extends Layer {

	protected UILayer(String name, Layer.LayerType type) {
		super(name, type);
	}

	@Override
	public void onAttach() {
		System.out.println("UI Layer attach");
		Application.Get().getDispatcher().addEventListener(MousePressedEvent.class, this::onMousePressedEvent);
		Application.Get().getDispatcher().addEventListener(KeyPressedEvent.class, this::onKeyPressedEvent);
		
	}

	@Override
	public void onDettach() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate() {
	}

	@Override
	public void onRender() {
		RenderCommand.fillRect(0,0, 150, 150);
		
	}
	
	@Override
	protected boolean onKeyPressedEvent(KeyPressedEvent e) {
		EngineLogger.Get().info("Ui Layer keyPressed");
		return false;
	}
	
	@Override
	protected boolean onMousePressedEvent(MousePressedEvent e) {
		EngineLogger.Get().info("Ui Layer mousePressed");
		return false;
	}

}
