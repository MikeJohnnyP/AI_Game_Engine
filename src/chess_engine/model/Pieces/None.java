package chess_engine.model.Pieces;

public class None extends APiece {

    public None(int color) {
        super(Pieces.NONE | color);
    }
    
    @Override
    public String toString() {
        return "None";
    }
}
