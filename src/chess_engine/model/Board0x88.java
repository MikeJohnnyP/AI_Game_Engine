package chess_engine.model;

import chess_engine.utility.FenUltility;

public class Board0x88 implements IBoard {
    private String fen; 
    private Square[][] square = new Square[8][8]; // Biểu diễn bàn cờ
  
    public Board0x88(String fen){
        this.fen = fen; 
        this.square = FenUltility.loadPositionFromPen(this.fen);
    }

    @Override
    public Square[][] getSquares() {
        return square;
    }

    @Override
    public String getFen() {
        return fen;
    }

}
