package chess_engine.model.Pieces;

public class Pawn extends APiece {

    public Pawn(int color) {
        super(Pieces.PAWN | color);
    }

    @Override
    public String toString() {
        return "Pawn";
    }
    
}
