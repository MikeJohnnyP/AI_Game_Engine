package chess_engine.controller;

import com.game.event.MouseMovedEvent;
import com.game.event.MousePressedEvent;

import chess_engine.helper.MoveGenerator;
import chess_engine.model.Disc;
import chess_engine.model.GameResult;
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
    Player playerNotToMove = null;
    GameResult result;

    public GameController(IBoard board) {
        this.board = board;
        boardUI = new BoardUI(this);
        this.playerWhite = new Player(PlayerType.EvilBot, "EvilBot",Disc.WHITE, this);
        this.playerBlack = new Player(PlayerType.Human, "Humanity",Disc.BLACK, this);
        this.result = new GameResult(this.playerWhite, this.playerBlack);
    }

    public void update() {
        this.playerWhite.setPlayerDisc(MoveGenerator.countDiscOfPlayer(this.playerWhite.getPlayerColor(), this.board));
        this.playerBlack.setPlayerDisc(MoveGenerator.countDiscOfPlayer(this.playerBlack.getPlayerColor(), this.board));
    }

    public void draw() {
        boardUI.drawBoard();
        boardUI.drawMousePos();
        boardUI.drawLegalMove();
        boardUI.drawHover();
        boardUI.drawPlayerInfo();
    }

    public void MouseMovedEvent(MouseMovedEvent e) {
        boardUI.mouseMoved(e);
    }

    public void MousePressedEvent(MousePressedEvent e) {
        this.playerToMove = getPlayerToMove();
        this.playerNotToMove = getPlayerNotToMove();
        this.playerToMove.update();
        if(playerNotToMove.isBot()) this.playerNotToMove.update();
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

    Player getPlayerNotToMove() {
        boolean isWhiteMove = board.getColorToMove() == Disc.WHITE ? true : false;
        return isWhiteMove ? playerBlack : playerWhite;
    }

    public GameResult getGameResult() {
        return this.result;
    }

    public Player getPlayerWhite() {
        return this.playerWhite;
    }

    public Player getPlayerBlack() {
        return this.playerBlack;
    }
}
