package sandbox;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import com.game.engine.controller.Application;
import com.game.event.KeyPressedEvent;
import com.game.event.MousePressedEvent;
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
	Texture tex;
	Sprite player;
	

	protected GameLayer(String name, Layer.LayerType type) {
		super(name, type);
	}

	@Override
	public void onAttach() {
		tex = new Texture("Exit.png", "player");
		player = new Sprite(tex);
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
//		if(Keyboard.isKeyPressed(KeyEvent.VK_W)) rect.setFrame(rect.getX(), (rect.getY() - speed), rect.getWidth(), rect.getHeight());
//		if(Keyboard.isKeyPressed(KeyEvent.VK_S)) rect.setFrame(rect.getX(), (rect.getY() + speed), rect.getWidth(), rect.getHeight());
//		if(Keyboard.isKeyPressed(KeyEvent.VK_A)) rect.setFrame((rect.getX() - speed), rect.getY() , rect.getWidth(), rect.getHeight());
//		if(Keyboard.isKeyPressed(KeyEvent.VK_D)) rect.setFrame((rect.getX() + speed), rect.getY(), rect.getWidth(), rect.getHeight());
		
		if(Keyboard.isKeyPressed(KeyEvent.VK_W)) player.setYPos(player.getYpos() - 1000);
		if(Keyboard.isKeyPressed(KeyEvent.VK_S)) player.setYPos(player.getYpos() + 1000);
		if(Keyboard.isKeyPressed(KeyEvent.VK_A)) player.setXPos(player.getXpos() - 1000);
		if(Keyboard.isKeyPressed(KeyEvent.VK_D)) player.setXPos(player.getXpos() + 1000);
	}

	@Override
	public void onRender() {
		RenderCommand.clearScreen(Color.black);
		//RenderCommand.setColor(Color.green);
		//RenderCommand.drawShape(rect);
		//RenderCommand.drawImg(player.getImg(), player.getXpos(), player.getYpos(), player.getWidth(), player.getHeight());
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
