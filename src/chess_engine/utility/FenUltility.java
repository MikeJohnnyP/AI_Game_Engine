package chess_engine.utility;


public class FenUltility {

    // public static Square[][] loadPositionFromPen(String fen) {
    //     Square[][] squares;
    //     // HashMap<Character, Integer> pieceTypeFromSymbol = new HashMap<>();

    //     // pieceTypeFromSymbol.put('k', Pieces.KING);
    //     // pieceTypeFromSymbol.put('p', Pieces.PAWN);
    //     // pieceTypeFromSymbol.put('n', Pieces.KNIGHT);
    //     // pieceTypeFromSymbol.put('r', Pieces.ROOK);
    //     // pieceTypeFromSymbol.put('b', Pieces.BISHOP);
    //     // pieceTypeFromSymbol.put('q', Pieces.QUEEN);

    //     String[] fenBoard = fen.split(" ");
    //     int file = 0, rank = 7;

    //     squares = new Square[8][8];

    //     for (String type : fenBoard) {
    //         for (int i = 0; i < type.length(); i++) {
    //             if (type.charAt(i) == '/') {
    //                 file = 0;
    //                 rank--;
    //             } else {
    //                 if (Character.isDigit(type.charAt(i))) {
    //                     int skipToNone = Integer.parseInt(Character.toString(type.charAt(i)));
    //                     for (int j = file; j < skipToNone; j++) {
    //                         // squares[rank][j] = new Square(new None(0), new Coord(rank, j));
    //                     }
    //                     file += skipToNone;
    //                 } else {
    //                     int pieceColor = (Character.isUpperCase(type.charAt(i))) ? APiece.Pieces.WHITE
    //                             : APiece.Pieces.BLACK;
    //                     // int pieceType =
    //                     // pieceTypeFromSymbol.get(Character.toLowerCase(type.charAt(i)));
    //                     // square[rank][file] = pieceType | pieceColor;
    //                     // Square temp = new Square(getPiecesFromChar(Character.toLowerCase(type.charAt(i)), pieceColor),
    //                     //         new Coord(rank, file));
    //                     // squares[rank][file] = temp;
    //                     file++;
    //                 }
    //             }
    //         }
    //     }

    //     return squares;

    // }

    // private static APiece getPiecesFromChar(char pieceChar, int color) {
    //     switch (pieceChar) {
    //         case 'k' -> {
    //             return new King(color);
    //         }
    //         case 'q' -> {
    //             return new Queen(color);
    //         }
    //         case 'p' -> {
    //             return new Pawn(color);
    //         }
    //         case 'n' -> {
    //             return new Knight(color);
    //         }
    //         case 'b' -> {
    //             return new Bishop(color);
    //         }
    //         case 'r' -> {
    //             return new Rook(color);
    //         }
    //         default -> {
    //             return null;
    //         }
    //     }
    // }
}
