package chess_engine.model;

import chess_engine.model.Player.Player;

public class GameResult {
   public static class GameinforState {
        public int winMatches = 0;
        public int drawMatches = 0;
        public int looseMatches = 0;
    }
    private Player playerWhite;
    private Player playerBlack;

    private final GameinforState playerWhiteState;
    private final GameinforState playerBlackState;
    public static int countMatches = 0;


    public GameResult(Player playerWhite, Player playerBlack) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
        this.playerWhiteState = new GameinforState();
        this.playerBlackState = new GameinforState();
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

    public GameinforState getPlayerWhiteState() {
        return playerWhiteState;
    }

    public GameinforState getPlayerBlackState() {
        return playerBlackState;
    }

    public void overGame() {
        int playerWhiteDisc = playerWhite.getPlayerDisc();
        int playerBlackDisc = playerBlack.getPlayerDisc();

        if(playerWhiteDisc == playerBlackDisc) {
            playerWhiteState.drawMatches++;
            playerBlackState.drawMatches++;
        } else if(playerBlackDisc > playerWhiteDisc) {
            playerBlackState.winMatches++;
            playerWhiteState.looseMatches++;
        } else {
            playerWhiteState.winMatches++;
            playerBlackState.looseMatches++;
        }

        countMatches++;
    }

}
