package chess_engine.model.Pieces;

public class Knight extends APiece {

    public Knight(int color) {
        super(Pieces.KNIGHT | color);
    }

    @Override
    public String toString() {
        return "Knight";
    }    

}
