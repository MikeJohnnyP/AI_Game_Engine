package sandbox;

import com.game.engine.controller.Application;
import com.game.engine.model.WindowSpec;
import com.game.layer.Layer;

public class Sandbox extends Application {
	private Layer uiLayer;
	private Layer gameLayer;

	@Override
	public void clientInit() {
		uiLayer = new UILayer("Ui Layer", Layer.LayerType.Overlay);
		gameLayer = new GameLayer("Game layer", Layer.LayerType.Standard);
		pushLayer(gameLayer);
		pushOverlay(uiLayer);
		
//		popLayer(gameLayer);
//		popLayer(gameLayer);
		
		uiLayer.onAttach(); 
		gameLayer.onAttach();
		
	}

	@Override
	public void clientShutdown() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Application.setInstance(new Sandbox());
		Application.Get().init(new WindowSpec(640, 480, "My Game"));
		Application.Get().run();
	}
}
