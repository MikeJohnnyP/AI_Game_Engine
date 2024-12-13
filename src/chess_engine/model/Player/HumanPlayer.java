package chess_engine.model.Player;

import java.util.HashMap;
import java.util.Set;

import com.game.input.Mouse;

import chess_engine.model.Disc;
import chess_engine.model.IBoard;
import chess_engine.model.Square;
import chess_engine.utility.Vector2;
import chess_engine.view.BoardUI;

public class HumanPlayer {
    private BoardUI boardUI;
    private Disc disc;
    public HumanPlayer(BoardUI boardUI, Disc disc) {
        this.boardUI = boardUI;
        this.disc = disc;
    }

    public void update(IBoard board) {
        if(board.getColorToMove() != disc) return;
        Vector2 mouseCurrentPos = new Vector2(Mouse.getPosX(), Mouse.getPosY()); 
        Square squareIndex = null;
        HashMap<Integer, Set<Integer>> listLegaMove = board.getLegalMove();
        if(listLegaMove.isEmpty()) {
            board.switchTurnToMove();
            System.out.println("Human switch turn");
            return;
        }
        if((squareIndex = boardUI.tryToGetSquareAtPoint(mouseCurrentPos)) != null) {
            int numIndex = squareIndex.getSquareIndex(); 
            if(listLegaMove.containsKey(numIndex)) {
                board.makeMove(numIndex, this.disc);
                Set<Integer> changeSquareColor = listLegaMove.get(numIndex);
                changeSquareColor.forEach((square) -> {
                    board.makeMove(square, this.disc);
                });
                board.switchTurnToMove();
                System.out.println("Human change square: " + numIndex);
            }
        }
        
    } 

    void notifyTurnToMove() {
    }


}
