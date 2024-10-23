package chess_engine.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Rectangle2D;

import com.game.AssetPool;
import com.game.engine.controller.Application;
import com.game.engine.model.WindowSpec;
import com.game.event.KeyPressedEvent;
import com.game.event.MouseMovedEvent;
import com.game.graphics.Sprite;
import com.game.renderer.Renderer2D;

import chess_engine.chess.Coord;
import chess_engine.controller.GameController;
import chess_engine.model.Square;
import chess_engine.model.Pieces.APiece;
import chess_engine.utility.Vector2;

public class BoardUI {
    private Vector2 worldPos;
    private static int SQUARE_SIZE = 100;
    private static boolean whitePerspective = false;
    private static Theme theme = new Theme();
    private Sprite[] whitePieces;
    private Sprite[] blackPieces;
    private GameController gameController;
    Font font = new Font("Georgia", Font.PLAIN, 14);

    public BoardUI(GameController gameController) {
        SQUARE_SIZE = calcSquareSize();
        worldPos = new Vector2(0, 0);
        loadPieces();
        this.gameController = gameController;
    } 

    void drawSquare(Coord coord) {
        drawSquare(coord.getFile(), coord.getRank());
    }

    void drawSquare(int file, int rank) {
        Coord coord = new Coord(file, rank);
        Color col = coord.isLightSquare() ? theme.LightCol : theme.DarkCol;         

        Square[][] squares = this.gameController.getBoard().getSquares();
        Square thisSquare = squares[rank][file];

        Vector2 pos = getSquarePos(file, rank, whitePerspective);
        Rectangle2D rec = new Rectangle2D.Float();
        rec.setFrame(pos.x, pos.y, SQUARE_SIZE, SQUARE_SIZE);
        Renderer2D.draw(rec, col);
        if(thisSquare.getPiece().getPieceType() == APiece.Pieces.NONE || thisSquare.getPiece() == null) return;
        Sprite spriteOfPiece = getPieceAtSquare(thisSquare.getPiece());
        spriteOfPiece.setXPos((int)pos.x);
        spriteOfPiece.setYPos((int)pos.y);
        Renderer2D.draw(spriteOfPiece);
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
        // float posY = boardStartY + rank * SQUARE_SIZE;
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

    private void loadPieces() {
        this.whitePieces = new Sprite[6]; 
        this.blackPieces = new Sprite[6];

        /*
         * Load Pieces Sprite from AssetPool
         * Scale of pieces was size of each square
         */
        whitePieces[APiece.Pieces.KING] = new Sprite(AssetPool.Get().getAsset("White_King"), 0, 0, SQUARE_SIZE, SQUARE_SIZE); // White King
        whitePieces[APiece.Pieces.QUEEN] = new Sprite(AssetPool.Get().getAsset("White_Queen"), 0, 0, SQUARE_SIZE, SQUARE_SIZE); // White Queen 
        whitePieces[APiece.Pieces.ROOK] = new Sprite(AssetPool.Get().getAsset("White_Rook"), 0, 0, SQUARE_SIZE, SQUARE_SIZE); // White Rook 
        whitePieces[APiece.Pieces.KNIGHT] = new Sprite(AssetPool.Get().getAsset("White_Knight"), 0, 0, SQUARE_SIZE, SQUARE_SIZE); // White Knight 
        whitePieces[APiece.Pieces.BISHOP] = new Sprite(AssetPool.Get().getAsset("White_Bishop"), 0, 0, SQUARE_SIZE, SQUARE_SIZE); // White Bishop 
        whitePieces[APiece.Pieces.PAWN] = new Sprite(AssetPool.Get().getAsset("White_Pawn"), 0, 0, SQUARE_SIZE, SQUARE_SIZE); // White Pawn 

        blackPieces[APiece.Pieces.KING] = new Sprite(AssetPool.Get().getAsset("Black_King"), 0, 0, SQUARE_SIZE, SQUARE_SIZE); // Black King
        blackPieces[APiece.Pieces.QUEEN] = new Sprite(AssetPool.Get().getAsset("Black_Queen"), 0, 0, SQUARE_SIZE, SQUARE_SIZE); // Black Queen 
        blackPieces[APiece.Pieces.ROOK] = new Sprite(AssetPool.Get().getAsset("Black_Rook"), 0, 0, SQUARE_SIZE, SQUARE_SIZE); // Black Rook 
        blackPieces[APiece.Pieces.KNIGHT] = new Sprite(AssetPool.Get().getAsset("Black_Knight"), 0, 0, SQUARE_SIZE, SQUARE_SIZE); // Black Knight 
        blackPieces[APiece.Pieces.BISHOP] = new Sprite(AssetPool.Get().getAsset("Black_Bishop"), 0, 0, SQUARE_SIZE, SQUARE_SIZE); // Black Bishop 
        blackPieces[APiece.Pieces.PAWN] = new Sprite(AssetPool.Get().getAsset("Black_Pawn"), 0, 0, SQUARE_SIZE, SQUARE_SIZE); // Black Pawn 

    }

    Sprite getPieceAtSquare(APiece piece) {
        int pieceType = piece.getPieceType();
        int pieceColour = piece.getPieceColour();

        switch (pieceColour) {
            case APiece.Pieces.BLACK -> {
                return blackPieces[pieceType];
            }
            case APiece.Pieces.WHITE -> {
                return whitePieces[pieceType];
            }
            default -> { return null; }
        }

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
