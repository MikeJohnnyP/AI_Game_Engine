package chess_engine.model;

import chess_engine.chess.Coord;
import chess_engine.model.Pieces.APiece;

public class Square {
    private APiece piece;
    private Coord coord;

    public Square(APiece piece) {
        this.piece = piece;
        this.coord.setFile(0);
        this.coord.setRank(0);
    }

    public Square(APiece piece, Coord coord) {
        this.piece = piece;
        this.coord = coord;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    } 

    public APiece getPiece() {
        return this.piece;
    }

    public void setPiece(APiece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return piece + " " + coord; 
    }
}
