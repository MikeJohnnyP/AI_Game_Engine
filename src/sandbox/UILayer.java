package sandbox;

import com.game.engine.controller.Application;
import com.game.event.KeyPressedEvent;
import com.game.event.MousePressedEvent;
import com.game.layer.Layer;
import com.game.logger.EngineLogger;
import com.game.time.TimeSteps;

public class UILayer extends Layer {

	protected UILayer(String name, Layer.LayerType type) {
		super(name, type);
	}

	@Override
	public void onAttach() {
		EngineLogger.Get().info("UI Layer attach");
		Application.Get().getDispatcher().addEventListener(MousePressedEvent.class, this::onMousePressedEvent);
		Application.Get().getDispatcher().addEventListener(KeyPressedEvent.class, this::onKeyPressedEvent);
		
	}

	@Override
	public void onDettach() {
		
	}

	@Override
	public void onUpdate(TimeSteps ts) {
	}

	@Override
	public void onRender() {
		//RenderCommand.fillRect(0,0, 150, 150);
		
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
