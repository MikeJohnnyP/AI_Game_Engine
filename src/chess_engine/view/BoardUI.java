package chess_engine.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Rectangle2D;

import com.game.engine.controller.Application;
import com.game.engine.model.WindowSpec;
import com.game.event.KeyPressedEvent;
import com.game.event.MouseMovedEvent;
import com.game.renderer.Renderer2D;

import chess_engine.chess.Coord;
import chess_engine.controller.GameController;
import chess_engine.model.Square;
import chess_engine.utility.Vector2;

public class BoardUI {
    Vector2 worldPos;
    private static int SQUARE_SIZE = 100;
    private static boolean whitePerspective = true;
    private static Theme theme = new Theme();
    private GameController gameController;
    Font font = new Font("Georgia", Font.PLAIN, 14);

    public BoardUI(GameController gameController) {
        SQUARE_SIZE = calcSquareSize();
        worldPos = new Vector2(0, 0);
        this.gameController = gameController;
    } 

    void drawSquare(Coord coord) {
        drawSquare(coord.getFile(), coord.getRank());
    }

    void drawSquare(int file, int rank) {
        Coord coord = new Coord(file, rank);
        Color col = coord.isLightSquare() ? theme.LightCol : theme.DarkCol;         

        Vector2 pos = getSquarePos(file, rank, whitePerspective);
        Rectangle2D rec = new Rectangle2D.Float();
        rec.setFrame(pos.x, pos.y, SQUARE_SIZE, SQUARE_SIZE);
        Renderer2D.draw(rec, col);
    }

    public void drawMousePos() {
        String mousePos = worldPos.x + ", " + worldPos.y;  
        Renderer2D.drawString(mousePos, (int)worldPos.x + 10, (int)worldPos.y + 5, font);
    }

    public static Vector2 getSquarePos(int file, int rank, boolean whitepPerspective) {
        // int boardStartX = SQUARE_SIZE * 4;
        // int boardStartY = SQUARE_SIZE * 4;

        int boardStartX = 0;
        int boardStartY = 0;
        
        if (!whitePerspective)
        {
            file = 7 - file;
            rank = 7 - rank;
        }
        
        float posX = boardStartX + file * SQUARE_SIZE;
        float posY = boardStartY + (7 - rank) * SQUARE_SIZE;
        return new Vector2(posX, posY); 
    }

    public void drawBoard() {
        Square[][] squares = gameController.getBoard().getSquares();
        for(int i = 0; i < squares.length; i++) {
            for(int y = 0; y < squares[i].length; y++) {
                drawSquare(squares[i][y].getCoord());
            }
        }
    }

    public static int calcSquareSize() {
        WindowSpec data = Application.Get().getWindow().getWindowData();
        int width = data.getWidth()/8;
        int height = data.getHeight()/8;
        return Math.min(width, height);
    }

    public static int getSquareSize() {
        return SQUARE_SIZE;
    }

    public void mouseMoved(MouseMovedEvent e) {
        worldPos.x = (float) e.getxPosition();
        worldPos.y = (float) e.getyPosition();
    }

    public void keyPressed(KeyPressedEvent e) {

    }
}
