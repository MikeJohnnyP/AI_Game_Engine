package chess_engine.model.Pieces;

public class Bishop extends APiece {

    public Bishop(int color) {
        super(Pieces.BISHOP | color);
    }

    @Override
    public String toString() {
        return "Bishop";
    }

}
