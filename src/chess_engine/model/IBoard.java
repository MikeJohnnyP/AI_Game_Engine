package chess_engine.model;

import java.util.HashMap;
import java.util.Set;

public interface IBoard {
    Square[] getSquares();
    String getFen();
    void setColorToMove(Disc color);
    Disc getColorToMove();
    void switchTurnToMove();
    HashMap<Integer, Set<Integer>> getLegalMove();
}
