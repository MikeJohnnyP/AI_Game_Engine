package genetic_algorithm;

import com.game.engine.controller.Application;
import com.game.engine.model.WindowSpec;
import com.game.layer.Layer;


public class App extends Application {
    ShowLayer layer;     

    @Override
    public void clientInit() {
        layer = new ShowLayer("ShowLayer", Layer.LayerType.Standard, "Hom Nay An Gi", 200, 0.01f);
        pushLayer(layer);
        layer.onAttach();
    }

    @Override
    public void clientShutdown() {
    }

    public static void main(String[] args) {
        Application.setInstance(new App());
		Application.Get().init(new WindowSpec(960, 720, "My Game", 60));
		Application.Get().run(); 
    }
}
