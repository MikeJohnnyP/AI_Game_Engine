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
import com.game.time.TimeSteps;

public class GameLayer extends Layer {
	private Rectangle2D rect = new Rectangle2D.Double(50.67, 50.75, 200.65, 200.54);
	double x ,y;

	protected GameLayer(String name, Layer.LayerType type) {
		super(name, type);
	}

	@Override
	public void onAttach() {
		System.out.println("Game Layer attach");
		x = 0;
		y = 0;
		Application.Get().getDispatcher().addEventListener(MousePressedEvent.class, this::onMousePressedEvent);
		Application.Get().getDispatcher().addEventListener(KeyPressedEvent.class, this::onKeyPressedEvent);
		
	}

	@Override
	public void onDettach() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(float deltaTime) {
		double speed = 1000 * deltaTime; 
		if(Keyboard.isKeyPressed(KeyEvent.VK_W)) rect.setFrame(rect.getX(), (rect.getY() - speed), rect.getWidth(), rect.getHeight());
		if(Keyboard.isKeyPressed(KeyEvent.VK_S)) rect.setFrame(rect.getX(), (rect.getY() + speed), rect.getWidth(), rect.getHeight());
		if(Keyboard.isKeyPressed(KeyEvent.VK_A)) rect.setFrame((rect.getX() - speed), rect.getY() , rect.getWidth(), rect.getHeight());
		if(Keyboard.isKeyPressed(KeyEvent.VK_D)) rect.setFrame((rect.getX() + speed), rect.getY(), rect.getWidth(), rect.getHeight());
	}

	@Override
	public void onRender() {
		RenderCommand.clearScreen(Color.black);
		RenderCommand.setColor(Color.green);
		RenderCommand.drawShape(rect);
		
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
