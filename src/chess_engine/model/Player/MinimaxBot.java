package chess_engine.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import chess_engine.helper.HeuristicHelper;
import chess_engine.helper.MoveGenerator;
import chess_engine.model.Disc;
import chess_engine.model.IBoard;

public class MinimaxBot implements IBot {
    private Disc disc;

    public MinimaxBot(Disc disc) {
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
            float moveScore = minimax(newBoard, 3, false, this.disc);
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

    float minimax(IBoard board, int depth, boolean maximizingPlayer, Disc player) {
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
                float eval = minimax(board, depth - 1, false, oppenentDisc);
                maxEval = Math.max(eval, maxEval);
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
                float eval = minimax(board, depth - 1, true, oppenentDisc);
                minEval = Math.min(eval, minEval);
            }
            return minEval;
        }
    }

}
