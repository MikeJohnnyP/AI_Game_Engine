package chess_engine.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import chess_engine.model.Disc;
import chess_engine.model.IBoard;

public class EvilBot implements IBot {
    private Disc disc;

    public EvilBot(Disc disc) {
        this.disc = disc;
    }

    @Override
    public void think(IBoard board) {
        if (board.getColorToMove() != disc)
            return;

        HashMap<Integer, Set<Integer>> listLegaMove = board.getLegalMove();
        if (listLegaMove.isEmpty()) {
            board.switchTurnToMove();
            return;
        }
        int highHeuristic = 0;
        int indexOfSquare = 0;

        for(Map.Entry<Integer, Set<Integer>> square : listLegaMove.entrySet()) {
            if (square.getValue().size() > highHeuristic) {
                highHeuristic = square.getValue().size();
                indexOfSquare = square.getKey();
            } 
        }

        board.makeMove(indexOfSquare, this.disc);
        Set<Integer> capturedSquare = listLegaMove.get(indexOfSquare);
        capturedSquare.forEach((square) -> {
            board.makeMove(square, this.disc);
        });
        board.switchTurnToMove();
        System.out.println("EvilBot Change Square: " + indexOfSquare);
    }

}
