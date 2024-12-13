package chess_engine.model;

import java.util.HashMap;
import java.util.Set;

public interface IBoard {
    Square[] getSquares();
    void setColorToMove(Disc color);
    Disc getColorToMove();
    void switchTurnToMove();
    boolean makeMove(int targetSquare, Disc playerDisc);
    boolean isGameOver();
    IBoard copyBoard();
    HashMap<Integer, Set<Integer>> getLegalMove();
}
