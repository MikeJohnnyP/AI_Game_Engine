package chess_engine.model.Pieces;

public class Queen extends APiece {

    public Queen(int color) {
        super(Pieces.QUEEN | color);
    }
    
    @Override
    public String toString() {
        return "Queen";
    }
}
