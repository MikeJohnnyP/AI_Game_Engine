package chess_engine.model;

import java.util.HashMap;
import java.util.Set;

import chess_engine.chess.Coord;
import chess_engine.helper.MoveGenerator;

public class Board0x88 implements IBoard {
    private String fen;
    private Square[] square = new Square[64]; // Biểu diễn bàn cờ
    private HashMap<Integer, Set<Integer>> listLegalMove = null;
    private Disc colorToMove;

    public Board0x88(String fen, Disc colorToMove) {
        this.fen = fen;
        this.colorToMove = colorToMove;
        initBoard();
        // this.square = FenUltility.loadPositionFromPen(this.fen);
        square[35].setDisc(Disc.WHITE);
        square[36].setDisc(Disc.BLACK);
        square[27].setDisc(Disc.BLACK);
        square[28].setDisc(Disc.WHITE);
        this.listLegalMove = MoveGenerator.generateMove(this, colorToMove == Disc.WHITE ? true : false);
    }

    void initBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                square[i * 8 + j] = new Square(Disc.NONE, new Coord(i, j));
            }
        }
    }

    @Override
    public Square[] getSquares() {
        return this.square;
    }

    @Override
    public String getFen() {
        return fen;
    }

    @Override
    public void setColorToMove(Disc color) {
        this.colorToMove = color;
    }

    @Override
    public Disc getColorToMove() {
        return this.colorToMove;
    }

    @Override
    public void switchTurnToMove() {
        if (this.colorToMove == Disc.WHITE) {
            this.colorToMove = Disc.BLACK;
            this.listLegalMove = MoveGenerator.generateMove(this, false);
        } else {
            this.colorToMove = Disc.WHITE;
            this.listLegalMove = MoveGenerator.generateMove(this, true);
        }
    }

    @Override
    public HashMap<Integer, Set<Integer>> getLegalMove() {
        return this.listLegalMove;
    }

}
