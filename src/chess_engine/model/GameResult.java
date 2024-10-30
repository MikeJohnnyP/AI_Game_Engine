package chess_engine.model;

import chess_engine.model.Player.Player;

public class GameResult {
    private boolean isGameOver; 
    private Player playerWhite;
    private Player playerBlack;
    
    public GameResult(Player playerWhite, Player playerBlack) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
    }

    public Player getPlayerBlack() {
        return playerBlack;
    }
    public void setPlayerBlack(Player playerBlack) {
        this.playerBlack = playerBlack;
    }
    public Player getPlayerWhite() {
        return playerWhite;
    }
    
    public void setPlayerWhite(Player playerWhite) {
        this.playerWhite = playerWhite;
    }
    
    public void notitfyGameResult() {
        // if(discPlayerBlack + discPlayerWhite == 64) 
        // if(playerBlackSkipTurn == 1 && playerWhiteSkipTurn == 1)
    }

    // public boolean checkGameover() {
    //     // if(this.discPlayerBlack == this.discPlayerWhite) re
    // } 
    
    public int getDiscPlayerWhite() {
        return discPlayerWhite;
    }

    public void setDiscPlayerWhite(int discPlayerWhite) {
        this.discPlayerWhite = discPlayerWhite;
    }

    public int getDiscPlayerBlack() {
        return discPlayerBlack;
    }

    public void setDiscPlayerBlack(int discPlayerBlack) {
        this.discPlayerBlack = discPlayerBlack;
    }

    public int getPlayerWhiteSkipTurn() {
        return playerWhiteSkipTurn;
    }

    public void setPlayerWhiteSkipTurn(int playerWhiteSkipTurn) {
        this.playerWhiteSkipTurn = playerWhiteSkipTurn;
    }

    public int getPlayerBlackSkipTurn() {
        return playerBlackSkipTurn;
    }

    public void setPlayerBlackSkipTurn(int playerBlackSkipTurn) {
        this.playerBlackSkipTurn = playerBlackSkipTurn;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
}
