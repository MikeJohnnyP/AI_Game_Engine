package chess_engine.controller;

import com.game.event.MouseMovedEvent;
import com.game.event.MousePressedEvent;

import chess_engine.model.Disc;
import chess_engine.model.IBoard;
import chess_engine.model.Player.Player;
import chess_engine.model.Player.PlayerType;
import chess_engine.view.BoardUI;

public class GameController {
    IBoard board;
    BoardUI boardUI;
    Player playerWhite;
    Player playerBlack;
    Player playerToMove = null;

    public GameController(IBoard board) {
        this.board = board;
        boardUI = new BoardUI(this);
        this.playerWhite = new Player(PlayerType.Human, Disc.WHITE, this);
        this.playerBlack = new Player(PlayerType.Human, Disc.BLACK, this);
    }

    public void update() {
    }

    public void draw() {
        boardUI.drawBoard();
        boardUI.drawMousePos();
        boardUI.drawLegalMove();
        boardUI.drawHover();
    }

    public void MouseMovedEvent(MouseMovedEvent e) {
        boardUI.mouseMoved(e);
    }

    public void MousePressedEvent(MousePressedEvent e) {
        this.playerToMove = getPlayerToMove();
        this.playerToMove.update(e);
    }

    public IBoard getBoard() {
        return this.board;
    }

    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    Player getPlayerToMove() {
        boolean isWhiteMove = board.getColorToMove() == Disc.WHITE ? true : false;
        return isWhiteMove ? playerWhite : playerBlack;
    }
}
