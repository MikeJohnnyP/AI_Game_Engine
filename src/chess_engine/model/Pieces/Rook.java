package chess_engine.model.Pieces;

public class Rook extends APiece {

    public Rook(int color) {
        super(Pieces.ROOK | color);
    }

    @Override
    public String toString() {
        return "Rook";
    }
    
}
