package chess_engine.model;

import java.util.HashMap;
import java.util.Set;

import chess_engine.chess.Coord;
import chess_engine.helper.MoveGenerator;

public class Board0x88 implements IBoard {
    private Square[] squares = new Square[64]; // Biểu diễn bàn cờ
    private HashMap<Integer, Set<Integer>> listLegalMove = null;
    private Disc colorToMove;

    public Board0x88(Disc colorToMove) {
        this.colorToMove = colorToMove;
        initBoard();
        // this.square = FenUltility.loadPositionFromPen(this.fen);
        squares[35].setDisc(Disc.WHITE);
        squares[36].setDisc(Disc.BLACK);
        squares[27].setDisc(Disc.BLACK);
        squares[28].setDisc(Disc.WHITE);
        this.listLegalMove = MoveGenerator.generateMove(this, colorToMove);
    }

    public Board0x88(IBoard board) {
        initBoard();
        Square[] squares = board.getSquares();
        for(int i = 0; i < squares.length; i++) {
            this.squares[i].setDisc(squares[i].getDisc()); 
            this.squares[i].setCoord( new Coord(squares[i].getCoord()));
        }

    }

    public Board0x88() {}

    void initBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.squares[i * 8 + j] = new Square(Disc.NONE, new Coord(i, j));
            }
        }
    }

    @Override
    public Square[] getSquares() {
        return this.squares;
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
            this.listLegalMove = MoveGenerator.generateMove(this, this.colorToMove);
        } else {
            this.colorToMove = Disc.WHITE;
            this.listLegalMove = MoveGenerator.generateMove(this, this.colorToMove);
        }
    }

    @Override
    public HashMap<Integer, Set<Integer>> getLegalMove() {
        return this.listLegalMove;
    }

    @Override
    public boolean makeMove(int targetSquare, Disc playerDisc) {
        if (targetSquare >= this.squares.length)
            return false;
        this.squares[targetSquare].setDisc(playerDisc);
        return true;
    }

    @Override
    public boolean isGameOver() {
        Disc opponentDisc = colorToMove == Disc.WHITE ? Disc.BLACK : Disc.WHITE;
        int fillBoard = 0;
        for (Square square : this.squares) {
            if (square.getDisc() != Disc.NONE)
                fillBoard++;
        }

        if (fillBoard == this.squares.length)
            return true;

        return MoveGenerator.generateMove(this, colorToMove).isEmpty()
                && MoveGenerator.generateMove(this, opponentDisc).isEmpty();

    }

    @Override
    public IBoard copyBoard() {
        return new Board0x88(this);        
    }

}
