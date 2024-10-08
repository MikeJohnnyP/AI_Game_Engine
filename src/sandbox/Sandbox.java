package sandbox;

import com.game.AssetPool;
import com.game.engine.controller.Application;
import com.game.engine.model.WindowSpec;
import com.game.layer.Layer;

public class Sandbox extends Application {
	private Layer uiLayer;
	private Layer gameLayer;
	private Layer enimiesLayer;

	@Override
	public void clientInit() {
		AssetPool.Get().loadAsset("WitchIdle", "Blue_witch/B_witch_idle.png");
		AssetPool.Get().loadAsset("WitchRun", "Blue_witch/B_witch_run.png");
		AssetPool.Get().loadAsset("WitchAttack", "Blue_witch/B_witch_attack.png");
		AssetPool.Get().loadAsset("BlueBackground", "Background/Background.png");
		
		uiLayer = new UILayer("Ui Layer", Layer.LayerType.Overlay);
		gameLayer = new GameLayer("Game layer", Layer.LayerType.Standard);
		enimiesLayer = new EnimiesLayer("Enimies Layer", Layer.LayerType.Standard);
		pushLayer(gameLayer);
		pushOverlay(uiLayer);
		pushLayer(enimiesLayer);
		
		uiLayer.onAttach(); 
		gameLayer.onAttach();
		enimiesLayer.onAttach();
		
	}

	@Override
	public void clientShutdown() {
		
	}
	
	public static void main(String[] args) {
		Application.setInstance(new Sandbox());
		Application.Get().init(new WindowSpec(1280, 720, "My Game", 60));
		Application.Get().run();
	}
}
