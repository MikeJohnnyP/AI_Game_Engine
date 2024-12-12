package chess_engine.helper;

import java.util.HashMap;
import java.util.Set;

import chess_engine.model.Disc;
import chess_engine.model.IBoard;
import chess_engine.model.Square;

public class HeuristicHelper {
    private static float WEIGHT_BOARD_VALUE = 0.5f;
    private static float MOBILITY_VALUE = 0.3f;
    private static float PIECE_STABILITY_VALUE = 0.2f;

    private static final int SIZE = 8; // Kích thước bàn cờ (8x8)
    private static final int[] DIRECTIONS = {-1, 1, -8, 8, -9, 9, -7, 7}; // Các hướng di chuyển
    private static final int EMPTY = 0;

    // Các trọng số cho từng giai đoạn
    private static final int[] EARLY_GAME_WEIGHTS = {
        100, -20, 10,  5,  5, 10, -20, 100,
        -20, -50, -2, -2, -2, -2, -50, -20,
         10,  -2,  0,  0,  0,  0,  -2,  10,
          5,  -2,  0,  0,  0,  0,  -2,   5,
          5,  -2,  0,  0,  0,  0,  -2,   5,
         10,  -2,  0,  0,  0,  0,  -2,  10,
        -20, -50, -2, -2, -2, -2, -50, -20,
        100, -20, 10,  5,  5, 10, -20, 100,
    };

    private static final int[] MID_GAME_WEIGHTS = {
        100, -10,  5,  5,  5,  5, -10, 100,
        -10, -20, -1, -1, -1, -1, -20, -10,
          5,  -1,  0,  0,  0,  0,  -1,   5,
          5,  -1,  0,  0,  0,  0,  -1,   5,
          5,  -1,  0,  0,  0,  0,  -1,   5,
          5,  -1,  0,  0,  0,  0,  -1,   5,
        -10, -20, -1, -1, -1, -1, -20, -10,
        100, -10,  5,  5,  5,  5, -10, 100,
    };

    private static final int[] LATE_GAME_WEIGHTS = {
        100,  50,  20, 10, 10, 20,  50, 100,
         50,  50,  10, 10, 10, 10,  50,  50,
         20,  10,   5,  5,  5,  5,  10,  20,
         10,  10,   5,  0,  0,  5,  10,  10,
         10,  10,   5,  0,  0,  5,  10,  10,
         20,  10,   5,  5,  5,  5,  10,  20,
         50,  50,  10, 10, 10, 10,  50,  50,
        100,  50,  20, 10, 10, 20,  50, 100,
    };

    public float evaluate(IBoard board, Disc playerDisc) {
        Disc oppenentPlayer = playerDisc == Disc.WHITE ? Disc.BLACK : Disc.WHITE;
        Square[] squares = board.getSquares();
        int score = 0;

        // // 1. Corner Control
        // int[] corners = { 0, 7, 56, 63 };
        // int corner_score = 25;

        // for(int corner : corners) {
        //     if(board.getSquares()[corner].getDisc() == playerDisc) score += corner_score;
        //     else if (board.getSquares()[corner].getDisc() == oppenentPlayer) score -= corner_score;
        // }

        // 2. Mobility
        HashMap<Integer, Set<Integer>> player_moves = MoveGenerator.generateMove(board, playerDisc);
        HashMap<Integer, Set<Integer>> opponent_moves = MoveGenerator.generateMove(board, oppenentPlayer);
        int mobilityScore = 0;
        mobilityScore += 70 * player_moves.size();
        mobilityScore -= 70 * opponent_moves.size();

        // Static Weight board
        int[] currentWeightBoard = getWeightBoard(board);
        int weightScore = 0;
        for (int i = 0; i < squares.length; i++) {
            if(squares[i].getDisc() == playerDisc) weightScore += currentWeightBoard[i];
            else if(squares[i].getDisc() == oppenentPlayer) weightScore -= currentWeightBoard[i];
        }

        // Piece Stability
        int stabilityScore = calculateStability(squares, playerDisc); 

        return (weightScore * 1) + (mobilityScore * 1) + (stabilityScore * 1);
    }

    private int[] getWeightBoard(IBoard board) {
        int squareEmpty = 0;
        for(Square square : board.getSquares()) {
            if(square.getDisc() == Disc.NONE) {
                squareEmpty++;
            }
        }

        if (squareEmpty > 40){
            WEIGHT_BOARD_VALUE = 0.5f;
            PIECE_STABILITY_VALUE = 0.2f;
            MOBILITY_VALUE = 0.3f;
            return EARLY_GAME_WEIGHTS;
        } 
        else if(squareEmpty > 15){
            WEIGHT_BOARD_VALUE = 0.3f;
            PIECE_STABILITY_VALUE = 0.3f;
            MOBILITY_VALUE = 0.4f;
            return MID_GAME_WEIGHTS;
        }
        else {
            WEIGHT_BOARD_VALUE = 0.1f;
            PIECE_STABILITY_VALUE = 0.5f;
            MOBILITY_VALUE = 0.4f;
            return LATE_GAME_WEIGHTS;
        }

    }

    public static int calculateStability(Square[] squares, Disc player) {
        int stableCount = 0;

        for (int i = 0; i < squares.length; i++) {
            if (squares[i].getDisc() == player && isStable(squares, i, player)) {
                stableCount++;
            }
        }

        return stableCount;
    }

    // Kiểm tra tính ổn định của quân cờ tại vị trí index
    public static boolean isStable(Square[] board, int index, Disc player) {
        int row = index / SIZE;
        int col = index % SIZE;

        // Quân cờ ở góc luôn ổn định
        if ((row == 0 && col == 0) || (row == 0 && col == SIZE - 1) || 
            (row == SIZE - 1 && col == 0) || (row == SIZE - 1 && col == SIZE - 1)) {
            return true;
        }

        // Duyệt qua các hướng để kiểm tra tính ổn định
        for (int direction : DIRECTIONS) {
            int neighbor = index + direction;
            if (isValidNeighbor(index, neighbor) && board[neighbor].getDisc() != player) {
                return false; // Không ổn định nếu có quân đối phương hoặc ô trống lân cận
            }
        }

        return true; // Ổn định nếu tất cả các hướng đều ổn định
    }

    // Kiểm tra vị trí hợp lệ trên bàn cờ
    private static boolean isValidNeighbor(int current, int neighbor) {
        int currentRow = current / SIZE;
        int currentCol = current % SIZE;
        int neighborRow = neighbor / SIZE;
        int neighborCol = neighbor % SIZE;

        // Kiểm tra nếu hàng hoặc cột nằm ngoài phạm vi
        return neighborRow >= 0 && neighborRow < SIZE && 
            neighborCol >= 0 && neighborCol < SIZE &&
            Math.abs(currentRow - neighborRow) <= 1 &&
            Math.abs(currentCol - neighborCol) <= 1;
    }
}
