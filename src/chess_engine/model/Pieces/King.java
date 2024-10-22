package chess_engine.model.Pieces;

public class King extends APiece {

    public King(int color) {
        super(Pieces.KING | color);
    }

    @Override
    public String toString() {
        return "King";
    }
    
}
