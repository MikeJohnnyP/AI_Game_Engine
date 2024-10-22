package chess_engine.chess;

import chess_engine.model.Square;

public class Coord {
    private int rankIndex;
    private int fileIndex;

    public Coord(int rankIndex, int fileIndex) {
        this.rankIndex = rankIndex;
        this.fileIndex = fileIndex;
    }

    public Coord(Square square) {
        this.rankIndex = square.getCoord().getRank();
        this.fileIndex = square.getCoord().getFile();
    }

    public Coord(Coord other) {
        this.fileIndex = other.getFile();
        this.rankIndex = other.getRank();
    }

    public int getRank() {
        return rankIndex;
    }

    public void setRank(int rankIndex) {
        this.rankIndex = rankIndex;
    }

    public void setFile(int fileIndex) {
        this.fileIndex = fileIndex;
    }

    public int getFile() {
        return fileIndex;
    }

    public boolean isLightSquare() {
        return (fileIndex + rankIndex) % 2 != 0;
    }

    @Override
    public boolean equals(Object obj) {
        Coord other = (Coord) obj; 
        return (this.rankIndex == other.getRank() && this.fileIndex == other.getFile());
    }

    @Override
    public String toString() {
        return rankIndex + " " + fileIndex;
    }

}
