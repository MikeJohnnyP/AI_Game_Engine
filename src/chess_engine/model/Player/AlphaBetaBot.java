package chess_engine.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import chess_engine.helper.HeuristicHelper;
import chess_engine.helper.MoveGenerator;
import chess_engine.model.Disc;
import chess_engine.model.IBoard;

public class AlphaBetaBot implements IBot{
       private Disc disc;

    public AlphaBetaBot(Disc disc) {
        this.disc = disc;
    }

    @Override
    public void think(IBoard board) {

        int indexTomove = 0;
        float bestScore = Float.MIN_VALUE;

        HashMap<Integer, Set<Integer>> listLegaMove = board.getLegalMove();
        if (listLegaMove.isEmpty()) {
            board.switchTurnToMove();
            System.out.println("Minimax Bot switch turn");
            return;
        }
        
        for(Map.Entry<Integer, Set<Integer>> entry : listLegaMove.entrySet()) {
            IBoard newBoard = board.copyBoard();
            float moveScore = minimax(newBoard, 5, Float.MIN_VALUE, Float.MAX_VALUE,  false, this.disc);
            if(moveScore >= bestScore) {
                bestScore = moveScore;
                indexTomove = entry.getKey();
            }
        }

        
        Set<Integer> capturedSquare = listLegaMove.get(indexTomove);
        if (capturedSquare == null || capturedSquare.isEmpty()) {
            board.makeMove(indexTomove, this.disc);
            System.out.println("MinimaxBot Change Square: " + indexTomove + ", best score: " + bestScore);
            board.switchTurnToMove(); 
            return;
        }
        capturedSquare.forEach((square) -> {
            board.makeMove(square, this.disc);
        });
        board.makeMove(indexTomove, this.disc);
        board.switchTurnToMove();
        System.out.println("MinimaxBot Change Square: " + indexTomove + ", best score: " + bestScore);
    }

    float minimax(IBoard board, int depth, float alpha, float beta, boolean maximizingPlayer, Disc player) {
        Disc oppenentDisc = player == Disc.WHITE ? Disc.BLACK : Disc.WHITE;
        if (depth == 0 || board.isGameOver()) {
            HeuristicHelper heuristic = new HeuristicHelper();
            return heuristic.evaluate(board, player);
        }

        if (maximizingPlayer) {
            float maxEval = Float.MIN_VALUE;
            for (Map.Entry<Integer, Set<Integer>> entry : MoveGenerator.generateMove(board, player).entrySet()) {
                IBoard newBoard = board.copyBoard();
                newBoard.makeMove(entry.getKey(), player);
                for(int flipDisc : entry.getValue()) {
                    newBoard.makeMove(flipDisc, player);
                }
                float eval = minimax(board, depth - 1, alpha, beta, false, oppenentDisc);
                maxEval = Math.max(eval, maxEval);
                alpha = Math.max(alpha, eval);
                if(beta <= alpha) {
                    break;
                }
            }
            return maxEval;

        } else {
            float minEval = Float.MAX_VALUE;
            for (Map.Entry<Integer, Set<Integer>> entry : MoveGenerator.generateMove(board, player).entrySet()) {
                IBoard newBoard = board.copyBoard();
                newBoard.makeMove(entry.getKey(), player);
                for(int flipDisc : entry.getValue()) {
                    newBoard.makeMove(flipDisc, player);
                }
                float eval = minimax(board, depth - 1, alpha, beta, true, oppenentDisc);
                minEval = Math.min(eval, minEval);
                beta = Math.min(beta, eval);
                if(beta <= alpha) {
                    break;
                }
            }
            return minEval;
        }
    }

 
}
