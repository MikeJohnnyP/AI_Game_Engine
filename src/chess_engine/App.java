package chess_engine;

import com.game.AssetPool;
import com.game.engine.controller.Application;
import com.game.engine.model.WindowSpec;
import com.game.layer.Layer.LayerType;

public class App extends Application {
    ChessLayer chess;
    @Override
    public void clientInit() {
        // Load Pieces Asset
		AssetPool.Get().loadAsset("Black_King", "pieces/Black_King.png");
		AssetPool.Get().loadAsset("Black_Knight", "pieces/Black_Knight.png");
		AssetPool.Get().loadAsset("Black_Bishop", "pieces/Black_Bishop.png");
		AssetPool.Get().loadAsset("Black_Rook", "pieces/Black_Rook.png");
		AssetPool.Get().loadAsset("Black_Queen", "pieces/Black_Queen.png");
		AssetPool.Get().loadAsset("Black_Pawn", "pieces/Black_Pawn.png");

		AssetPool.Get().loadAsset("White_King", "pieces/White_King.png");
		AssetPool.Get().loadAsset("White_Knight", "pieces/White_Knight.png");
		AssetPool.Get().loadAsset("White_Bishop", "pieces/White_Bishop.png");
		AssetPool.Get().loadAsset("White_Rook", "pieces/White_Rook.png");
		AssetPool.Get().loadAsset("White_Queen", "pieces/White_Queen.png");
		AssetPool.Get().loadAsset("White_Pawn", "pieces/White_Pawn.png");

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
