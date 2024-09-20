package sandbox;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import com.game.engine.controller.Application;
import com.game.event.KeyPressedEvent;
import com.game.event.MousePressedEvent;
import com.game.input.Keyboard;
import com.game.layer.Layer;
import com.game.logger.EngineLogger;
import com.game.renderer.RenderCommand;

public class GameLayer extends Layer {
	private Rectangle2D rect = new Rectangle2D.Double(50, 50, 200, 200);

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
		if(Keyboard.isKeyPressed(KeyEvent.VK_W)) rect.setFrame(rect.getX(), rect.getY() - 1, rect.getWidth(), rect.getHeight());
		if(Keyboard.isKeyPressed(KeyEvent.VK_S)) rect.setFrame(rect.getX(), rect.getY() + 1, rect.getWidth(), rect.getHeight());
		if(Keyboard.isKeyPressed(KeyEvent.VK_A)) rect.setFrame(rect.getX() - 1, rect.getY(), rect.getWidth(), rect.getHeight());
		if(Keyboard.isKeyPressed(KeyEvent.VK_D)) rect.setFrame(rect.getX() + 1, rect.getY(), rect.getWidth(), rect.getHeight());
	}

	@Override
	public void onRender() {
		RenderCommand.clearScreen(Color.black);
		RenderCommand.setColor(Color.green);
		RenderCommand.drawShape(rect);
		
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
