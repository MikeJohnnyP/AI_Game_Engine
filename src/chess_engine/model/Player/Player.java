package chess_engine.model.Player;

import chess_engine.controller.GameController;
import chess_engine.helper.MoveGenerator;
import chess_engine.model.Disc;

public class Player {
    private GameController gameController;
    private PlayerType playerType;
    private IBot bot = null;
    private HumanPlayer human = null;
    private Disc playerColor;
    private String playerName;
    private int playerDisc;
    private boolean playerSkipTurn = false;

    public Player(PlayerType playerType, GameController gameController) {
        this.gameController = gameController;
        this.playerColor = Disc.NONE;
        this.playerType = playerType;
        setPlayer();
    }

    public Player(PlayerType playerType, String playerName, Disc playerColor, GameController gameController) {
        this.playerName = playerName;
        this.gameController = gameController;
        this.playerColor = playerColor;
        this.playerType = playerType;
        this.playerDisc = 0;
        setPlayer();
    }
    
    public void update() {
        if(human != null) {
            human.update(this.gameController.getBoard());
        }
        else if(bot != null) {
            bot.think(this.gameController.getBoard());
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
            
            case RandomBot -> {
                this.bot = new RandomBot(playerColor);
                break;
            }
            
            case EvilBot -> {
                this.bot = new EvilBot(playerColor);
                break;
            }
            
            default -> {
            }
        }
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    public int getPlayerDisc() {
        return playerDisc;
    }

    public void setPlayerDisc(int playerDisc) {
        this.playerDisc = playerDisc;
    }

    public boolean isPlayerSkipTurn() {
        return playerSkipTurn;
    }

    public void setPlayerSkipTurn(boolean playerSkipTurn) {
        this.playerSkipTurn = playerSkipTurn;
    }

}
