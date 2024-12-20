package chess_engine;

import com.game.engine.controller.Application;
import com.game.event.KeyPressedEvent;
import com.game.event.MouseMovedEvent;
import com.game.event.MousePressedEvent;
import com.game.layer.Layer;
import com.game.time.TimeSteps;

import chess_engine.controller.GameController;
import chess_engine.model.Board0x88;
import chess_engine.model.Disc;

public class ChessLayer extends Layer {

    GameController gControl ;

    public ChessLayer(String name, LayerType type) {
        super(name, type);
    }

    @Override
    public void onAttach() {
        gControl = new GameController(new Board0x88(Disc.BLACK));
		Application.Get().getDispatcher().addEventListener(MousePressedEvent.class, this::onMousePressedEvent);
		Application.Get().getDispatcher().addEventListener(KeyPressedEvent.class, this::onKeyPressedEvent);
		Application.Get().getDispatcher().addEventListener(MouseMovedEvent.class, this::onMouseMovedEvent);
    }

    @Override
    public void onDettach() {
    }

    @Override
    public void onUpdate(TimeSteps ts) {
        gControl.update();
    }

    @Override
    public void onRender() {
        gControl.draw();
    }

   	@Override
	protected boolean onKeyPressedEvent(KeyPressedEvent e) {
		return false;
	}
	
	@Override
	protected boolean onMousePressedEvent(MousePressedEvent e) {
        gControl.MousePressedEvent(e);
		return false;
	}
	
	@Override
	protected boolean onMouseMovedEvent(MouseMovedEvent e) {
        gControl.MouseMovedEvent(e);
		return false;
	} 
    
}
