package chess_engine.model.Player;

import com.game.event.MousePressedEvent;

import chess_engine.controller.GameController;
import chess_engine.model.Disc;

public class Player {
    private GameController gameController;
    private PlayerType playerType;
    private IChessBot bot = null;
    private HumanPlayer human = null;
    private Disc playerColor;

    public Player(PlayerType playerType, GameController gameController) {
        this.gameController = gameController;
        this.playerColor = Disc.NONE;
        this.playerType = playerType;
        setPlayer();
    }

    public Player(PlayerType playerType, Disc playerColor, GameController gameController) {
        this.gameController = gameController;
        this.playerColor = playerColor;
        this.playerType = playerType;
        setPlayer();
    }

    public void update(MousePressedEvent e) {
        if(human != null) {
            human.update(this.gameController.getBoard(), e);
        }
        else if(bot != null) {
            bot.update();
        }
    }

    public boolean isBot() { return bot != null; }
    public boolean isHuman() { return human != null; }
    public PlayerType getPlayerType() { return this.playerType; }
    public Disc getPlayerColor() { return this.playerColor; }
    public void setPlayerColor(Disc color) { this.playerColor = color; }

    public void setPlayer() {
        switch (this.playerType) {
            case Human -> {
                this.human = new HumanPlayer(this.gameController.getBoardUI(), playerColor);
                break;
            }
            default -> {
                break;
            }
        }
    }

}
