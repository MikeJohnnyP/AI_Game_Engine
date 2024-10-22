package chess_engine;
import java.util.Arrays;

public class BoardGeneration {
    public static void initiateStandardChess(){
        long WP=0L, WN=0L, WR=0L, WB=0L, WQ=0L, WK=0L, BP=0L, BN=0L, BB=0L, BQ=0L, BK=0L, BR=0L;
        String chessBoard[][] ={
            {"r", "n", "b", "q", "k", "b", "n", "r"},
            {"p", "p", "p", "p", "p", "p", "p", "p"},
            {" ", " ", " ", " ", " ", " ", " ", " "}, 
            {" ", " ", " ", " ", " ", " ", " ", " "}, 
            {" ", " ", " ", " ", " ", " ", " ", " "}, 
            {" ", " ", " ", " ", " ", " ", " ", " "}, 
            {"P", "P", "P", "P", "P", "P", "P", "P"},
            {"R", "N", "B", "Q", "K", "B", "N", "R"},
        };

        arrayToBitboards(chessBoard, WP, WN, WR, WB, WQ, WK, BP, BN, BR, BB, BQ, BK);
    }

    private static void arrayToBitboards(String[][] chessBoard, long WP, long WN, long WR, long WB, long WQ, long WK, long BP, long BN, long BR, long BB, long BQ, long BK) {
        String binary;
        for(int i = 0; i<64; i++){
            binary = "0000000000000000000000000000000000000000000000000000000000000000";
            binary = binary.substring(i + 1) +"1"+ binary.substring(0, i);
            switch (chessBoard[i/8][i%8]) {
                case "P": WP += convertStringToBitboard(binary); break;
                case "N": WN += convertStringToBitboard(binary); break;
                case "B": WB += convertStringToBitboard(binary); break;
                case "R": WR += convertStringToBitboard(binary); break;
                case "Q": WQ += convertStringToBitboard(binary); break;
                case "K": WK += convertStringToBitboard(binary); break;
                case "p": BP += convertStringToBitboard(binary); break;
                case "n": BN += convertStringToBitboard(binary); break;
                case "b": BB += convertStringToBitboard(binary); break;
                case "r": BR += convertStringToBitboard(binary); break;
                case "q": BQ += convertStringToBitboard(binary); break;
                case "k": BK += convertStringToBitboard(binary); break;
                default:
                    break;
            }
        }
        drawArray(WP, WN, WR, WB, WQ, WK, BP, BN, BR, BB, BQ, BK);
    }

    public static long convertStringToBitboard(String binary) {
        if(binary.charAt(0) == 0){
            return Long.parseLong(binary, 2);
        } else {
            return Long.parseLong("1"+binary.substring(2), 2) * 2;
        }
    }

    public static void drawArray(long WP, long WN, long WR, long WB, long WQ, long WK, long BP, long BN, long BR, long BB, long BQ, long BK) {
        String chessBoard[][] = new String[8][8];
        for (int i = 0; i < 64; i++) {
           chessBoard[i/8][i%8] = " "; 
        }

        for (int i = 0; i < 64; i++) {
           if(((WP>>i)&1) == 1)  chessBoard[i/8][i%8] = "P";
           if(((WN>>i)&1) == 1)  chessBoard[i/8][i%8] = "N";
           if(((WR>>i)&1) == 1)  chessBoard[i/8][i%8] = "R";
           if(((WB>>i)&1) == 1)  chessBoard[i/8][i%8] = "B";
           if(((WQ>>i)&1) == 1)  chessBoard[i/8][i%8] = "Q";
           if(((WK>>i)&1) == 1)  chessBoard[i/8][i%8] = "K";
           if(((BP>>i)&1) == 1)  chessBoard[i/8][i%8] = "p";
           if(((BN>>i)&1) == 1)  chessBoard[i/8][i%8] = "n";
           if(((BR>>i)&1) == 1)  chessBoard[i/8][i%8] = "r";
           if(((BB>>i)&1) == 1)  chessBoard[i/8][i%8] = "b";
           if(((BQ>>i)&1) == 1)  chessBoard[i/8][i%8] = "q";
           if(((BK>>i)&1) == 1)  chessBoard[i/8][i%8] = "k";
        }

        for (int i = 0; i < 8; i++) {
           System.out.println(Arrays.toString(chessBoard[i])); 
        }
    }
}
