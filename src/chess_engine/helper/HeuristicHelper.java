package chess_engine.helper;

import java.util.HashMap;
import java.util.Set;

import chess_engine.model.Disc;
import chess_engine.model.IBoard;

public class HeuristicHelper {

    public int evaluate(IBoard board, Disc playerDisc) {
        Disc oppenentPlayer = playerDisc == Disc.WHITE ? Disc.BLACK : Disc.WHITE;
        int score = 0;

        // 1. Corner Control
        int[] corners = { 0, 7, 56, 63 };
        int corner_score = 25;
        // for x, y in corners:
        // if board[x][y] == player:
        // score += corner_score
        // elif board[x][y] == -player:
        // score -= corner_score

        for(int corner : corners) {
            if(board.getSquares()[corner].getDisc() == playerDisc) score += corner_score;
            else if (board.getSquares()[corner].getDisc() == oppenentPlayer) score -= corner_score;
        }

        // 2. Mobility
        HashMap<Integer, Set<Integer>> player_moves = MoveGenerator.generateMove(board, playerDisc);
        HashMap<Integer, Set<Integer>> opponent_moves = MoveGenerator.generateMove(board, oppenentPlayer);
        int mobility_score = 2;
        score += mobility_score * player_moves.size();
        score -= mobility_score * opponent_moves.size();
        return score;
    }
}
