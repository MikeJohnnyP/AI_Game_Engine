package chess_engine.helper;

import java.util.HashMap;
import java.util.Set;

import chess_engine.model.Disc;
import chess_engine.model.IBoard;
import chess_engine.model.Square;

public class HeuristicHelper {
    private static float WEIGHT_BOARD_VALUE = 0.5f;
    private static float MOBILITY_VALUE = 0.3f;
    private static float EDGE_STABILITY_VALUE = 0.2f;
    private static float FRONTIER_DISC_VALUE = 0.2f;
    private static float CORNER_CONTROL_VALUE = 0.2f;
    private static float PARITY_VALUE = 0.1f;

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
          //1. Corner Control
         int[] corners = { 0, 7, 56, 63 };
         int corner_score = 0;

         for(int corner : corners) {
             if(board.getSquares()[corner].getDisc() == playerDisc) corner_score += 70;
             else if (board.getSquares()[corner].getDisc() == oppenentPlayer) corner_score -= 70;
         }

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

        int edgeStability = calculateEdgeStability(squares, playerDisc);
        int frontierDiscs = calculateFrontierDiscs(squares, playerDisc);

        // Parity
        float parityScore = evaluateParity(board, playerDisc) * 100;

        return (weightScore * WEIGHT_BOARD_VALUE) +
                (mobilityScore * MOBILITY_VALUE) +
                ((edgeStability * EDGE_STABILITY_VALUE) - (frontierDiscs * FRONTIER_DISC_VALUE)) +
                (parityScore * PARITY_VALUE) +
                (corner_score * CORNER_CONTROL_VALUE);
    }

    private int[] getWeightBoard(IBoard board) {
        int squareEmpty = 0;
        for(Square square : board.getSquares()) {
            if(square.getDisc() == Disc.NONE) {
                squareEmpty++;
            }
        }

        if (squareEmpty > 40){
            MOBILITY_VALUE = 0.5f;
            WEIGHT_BOARD_VALUE = 0.4f;
            FRONTIER_DISC_VALUE = 0.2f;
            EDGE_STABILITY_VALUE = 0.1f;
            CORNER_CONTROL_VALUE = 0.1f;
            PARITY_VALUE = 0.0f;
            return EARLY_GAME_WEIGHTS;
        } 
        else if(squareEmpty > 15){
            MOBILITY_VALUE = 0.4f;
            WEIGHT_BOARD_VALUE = 0.3f;
            FRONTIER_DISC_VALUE = 0.3f;
            EDGE_STABILITY_VALUE = 0.3f;
            CORNER_CONTROL_VALUE = 0.2f;
            PARITY_VALUE = 0.1f;
            return MID_GAME_WEIGHTS;
        }
        else {
            MOBILITY_VALUE = 0.1f;
            WEIGHT_BOARD_VALUE = 0.2f;
            FRONTIER_DISC_VALUE = 0.1f;
            EDGE_STABILITY_VALUE = 0.4f;
            CORNER_CONTROL_VALUE = 0.4f;
            PARITY_VALUE = 0.2f;
            return LATE_GAME_WEIGHTS;
        }

    }

    public static int calculateEdgeStability(Square[] squares, Disc playerDisc) {
        int edgeStability = 0;


        int[] corners = {0, 7, 56, 63}; // Góc của bàn cờ 8x8
        int[] edges = {1, 2, 3, 4, 5, 6, 8, 16, 24, 32, 40, 48, 57, 58, 59, 60, 61, 62};


        for (int pos : corners) {
            if (squares[pos].getDisc() == playerDisc) {
                edgeStability += 5; // Góc có giá trị cao
            }
        }


        for (int pos : edges) {
            if (squares[pos].getDisc() == playerDisc) {

                if (isEdgeStable(squares, pos, playerDisc)) {
                    edgeStability += 2;
                }
            }
        }

        return edgeStability;
    }


    private static boolean isEdgeStable(Square[] squares, int pos, Disc playerDisc) {
        int row = pos / 8;
        int col = pos % 8;


        if (row == 0 || row == 7 || col == 0 || col == 7) {
            return isStableInDirection(squares, pos, playerDisc, -1) &&
                    isStableInDirection(squares, pos, playerDisc, 1);
        }
        return false;
    }


    private static boolean isStableInDirection(Square[] squares, int pos, Disc playerDisc, int step) {
        int next = pos + step;
        while (next >= 0 && next < squares.length) {
            if (squares[next].getDisc() != playerDisc) {
                return false; // Không ổn định nếu gặp quân khác màu
            }
            next += step;
        }
        return true;
    }

    public static int calculateFrontierDiscs(Square[] squares, Disc playerDisc) {
        int frontierDiscs = 0;


        for (int i = 0; i < squares.length; i++) {
            if (squares[i].getDisc() == playerDisc && isFrontierDisc(squares, i)) {
                frontierDiscs++;
            }
        }

        return frontierDiscs;
    }


    private static boolean isFrontierDisc(Square[] squares, int pos) {
        int[] directions = {-1, 1, -8, 8, -9, -7, 7, 9};
        for (int dir : directions) {
            int neighbor = pos + dir;
            if (neighbor >= 0 && neighbor < squares.length && squares[neighbor].getDisc() == Disc.NONE) {
                return true;
            }
        }
        return false;
    }

    public static float evaluateParity(IBoard board, Disc playerDisc) {
        int emptySquares = 0;
        Square[] squares = board.getSquares();
        for (Square square : squares) {
            if (square.getDisc() == Disc.NONE) {
                emptySquares++;
            }
        }
        boolean isEven = (emptySquares % 2 == 0);

        return isEven ? 1.0f : -1.0f;
    }
}
