package chess_engine.model;

public interface IBoard {
    Square[][] getSquares();
    String getFen();
}
