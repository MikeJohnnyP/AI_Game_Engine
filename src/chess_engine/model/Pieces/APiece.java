package chess_engine.model.Pieces;

public abstract class APiece {

    public class Pieces {
        public static final int NONE = 0;
        public static final int KING = 1;
        public static final int PAWN = 2;
        public static final int KNIGHT = 3; 
        public static final int BISHOP = 4;
        public static final int ROOK = 5;
        public static final int QUEEN = 6;

        public static final int WHITE = 8;
        public static final int BLACK = 16;
    }

    protected final int typeMask = 0b0111;
    protected final int colourMask = 0b1000;
    protected int pieceCode;

    protected APiece(int pieceCode) {
        this.pieceCode = pieceCode;
    }

    public int getPieceCode() {
        return pieceCode;
    }

    public int getPieceType(){
        return pieceCode & typeMask;
    }

    public int getPieceColour() {
        return pieceCode & colourMask;
    }

    @Override
    public String toString() {
        return " ";
    }
}
