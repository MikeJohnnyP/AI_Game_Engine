package chess_engine.model;


import chess_engine.chess.Coord;

public class Square {
    private Coord coord;
    private Disc disc;

    public Square(Disc disc) {
        this.disc = disc;
        this.coord.setFile(0);
        this.coord.setRank(0);
    }

    public Square(Disc disc, Coord coord) {
        this.disc = disc;
        this.coord = coord;
    }

    public Coord getCoord() {
        return coord;
    }

    public boolean isBlack() {
        return this.disc == Disc.BLACK;
    }

    public boolean isWhite() {
        return this.disc == Disc.WHITE;
    }

    public boolean isNone() {
        return this.disc == Disc.NONE;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    } 

    public Disc getDisc() {
        return this.disc;
    }

    public void setDisc(Disc disc) {
        this.disc = disc;
    }

    public int getSquareIndex() {
        return this.coord.getIndex();
    }

    @Override
    public String toString() {
        return disc + " " + coord; 
    }
}
