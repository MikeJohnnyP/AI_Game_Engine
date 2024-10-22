package chess_engine;

import com.game.engine.controller.Application;
import com.game.engine.model.WindowSpec;
import com.game.layer.Layer.LayerType;

public class App extends Application {
    ChessLayer chess;
    @Override
    public void clientInit() {
        chess = new ChessLayer("Chess", LayerType.Standard);
        pushLayer(chess);
        chess.onAttach();
    }

    public static void main(String[] args) throws Exception {
        Application.setInstance(new App());
		Application.Get().init(new WindowSpec(1280, 720, "My Game", 60));
		Application.Get().run(); 
    }
}
