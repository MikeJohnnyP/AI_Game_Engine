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
        Disc oppenentDisc = this.disc == Disc.WHITE ? Disc.BLACK : Disc.WHITE;
        int indexTomove = 0;
        float bestScore = -Float.MAX_VALUE;

        HashMap<Integer, Set<Integer>> listLegaMove = board.getLegalMove();
        if (listLegaMove.isEmpty()) {
            board.switchTurnToMove();
            System.out.println("Minimax Bot switch turn");
            return;
        }

        for(Map.Entry<Integer, Set<Integer>> entry : listLegaMove.entrySet()) {
            IBoard newBoard = board.copyBoard();
            newBoard.makeMove(entry.getKey(), this.disc);
            for(int flipDisc : entry.getValue()) {
                newBoard.makeMove(flipDisc, this.disc);
            }
            float moveScore = minimax(newBoard, 3, !(this.disc == Disc.WHITE), this.disc == Disc.WHITE ? oppenentDisc : this.disc);
            if(moveScore > bestScore) {
                bestScore = moveScore;
                indexTomove = entry.getKey();
            }
        }

        
        Set<Integer> capturedSquare = listLegaMove.get(indexTomove);
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

        HashMap<Integer, Set<Integer>> moves = MoveGenerator.generateMove(board, player);
        if (moves.isEmpty()) {
            return minimax(board, depth - 1, !maximizingPlayer, oppenentDisc);
        }

        if (maximizingPlayer) {
            float maxEval = -Float.MAX_VALUE;
            for (Map.Entry<Integer, Set<Integer>> entry : moves.entrySet()) {
                IBoard newBoard = board.copyBoard();
                newBoard.makeMove(entry.getKey(), player);
                for(int flipDisc : entry.getValue()) {
                    newBoard.makeMove(flipDisc, player);
                }
                float eval = minimax(newBoard, depth - 1, false, oppenentDisc);
                maxEval = Math.max(eval, maxEval);
            }
            return maxEval;

        } else {
            float minEval = Float.MAX_VALUE;
            for (Map.Entry<Integer, Set<Integer>> entry : moves.entrySet()) {
                IBoard newBoard = board.copyBoard();
                newBoard.makeMove(entry.getKey(), player);
                for(int flipDisc : entry.getValue()) {
                    newBoard.makeMove(flipDisc, player);
                }
                float eval = minimax(newBoard, depth - 1, true, oppenentDisc);
                minEval = Math.min(eval, minEval);
            }
            return minEval;
        }
    }

}
