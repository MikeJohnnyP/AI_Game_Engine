package chess_engine.controller;

import com.game.event.MouseMovedEvent;

import chess_engine.model.IBoard;
import chess_engine.view.BoardUI;

public class GameController {
    IBoard board;  
    BoardUI boardUI;
    

    public GameController(IBoard board) {
        this.board = board;
        boardUI = new BoardUI(this);
    }

    public void update() {

    }

    public void draw() {
       boardUI.drawBoard();
       boardUI.drawMousePos();
    }

    public void MouseMovedEvent(MouseMovedEvent e) {
        boardUI.mouseMoved(e);
    }

    public IBoard getBoard() {
        return this.board;
    }

}
