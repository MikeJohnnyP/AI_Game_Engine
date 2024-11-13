package chess_engine.model.Player;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import chess_engine.model.Disc;
import chess_engine.model.IBoard;

public class RandomBot implements IBot {
    private Disc disc;

    public RandomBot(Disc disc) {
        this.disc = disc;
    }

    @Override
    public void think(IBoard board) {
        if(board.getColorToMove() != disc) return;

        HashMap<Integer, Set<Integer>> listLegaMove = board.getLegalMove();
        if(listLegaMove.isEmpty()) {
            board.switchTurnToMove();
            return;
        } 
        Random r = new Random();
        int randNumber = r.nextInt(board.getSquares().length);
        while (!listLegaMove.containsKey(randNumber)) {
            randNumber = r.nextInt(board.getSquares().length); 
        }
        board.getSquares()[randNumber].setDisc(this.disc);
        Set<Integer> capturedSquare = listLegaMove.get(randNumber);
        capturedSquare.forEach((square) -> {
            board.getSquares()[square].setDisc(this.disc);
        });
        board.switchTurnToMove();
                 
    }
    
}
