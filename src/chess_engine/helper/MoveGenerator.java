package chess_engine.helper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import chess_engine.model.Disc;
import chess_engine.model.IBoard;
import chess_engine.model.Square;

public class MoveGenerator {
    private MoveGenerator() {}

    public static HashMap<Integer, Set<Integer>> generateMove(IBoard board, boolean isWhiteToMove) {
        HashMap<Integer, Set<Integer>> listMove = new HashMap<>();
        Square[] square = board.getSquares();
        for(int i = 0; i < square.length; i++) {
            if(square[i].getDisc() == Disc.NONE) {
                legalMove(listMove, square, i, isWhiteToMove);
            }         
        }
        return listMove;
    }

    private static void legalMove(HashMap<Integer, Set<Integer>> listMove, Square[] square, Integer indexToCheck, boolean isWhiteToMove) {
        Set<Integer> leftSet = new HashSet<>();
        Set<Integer> rightSet = new HashSet<>();
        Set<Integer> upSet = new HashSet<>();
        Set<Integer> downSet = new HashSet<>(); 
        Set<Integer> northeast = new HashSet<>();
        Set<Integer> southeast = new HashSet<>();
        Set<Integer> northwest = new HashSet<>();
        Set<Integer> southwest = new HashSet<>(); 
        Disc discToMove =isWhiteToMove ? Disc.WHITE : Disc.BLACK; 
        int[] pseudo1 = { -1, 1, 8, -8};
        int[] pseudo2 = { 9, -7, -9, 7 };

        int row = indexToCheck / 8;
        int col = indexToCheck % 8;

        int lowestHorizontal = col;
        int highestHorizontal = 7 * 8 + col;
        int highestRight = row * 8 + 7;
        int highestLeft = row * 8 + 0;

        boolean stopLeft = false;
        boolean stopRight = false;
        boolean stopUp = false;
        boolean stopDown = false;

        if(indexToCheck == highestHorizontal) stopUp = true;
        if(indexToCheck == lowestHorizontal) stopDown = true;
        if(indexToCheck == highestRight) stopRight = true;
        if(indexToCheck == highestLeft) stopLeft = true;

        // DN
        int steps = Math.min(row, 8 - col - 1);
        int final_row = row - steps;
        int final_col = col + steps;
        int maxSoutheastIndex = final_row * 8 + final_col;
        
        // DB 
        steps = Math.min(8 - row - 1, 8 - col - 1);
        final_row = row + steps;
        final_col = col + steps;
        int maxNortheastIndex = final_row * 8 + final_col;
        
        // TN 
        steps = Math.min(row, col);
        final_row = row - steps;
        final_col = col - steps;
        int maxSouthwestIndex = final_row * 8 + final_col;
        
        // TB 
        steps = Math.min(8 - row - 1, col);
        final_row = row + steps;
        final_col = col - steps;
        int maxNorthwestIndex = final_row * 8 + final_col;

        boolean stopNortheast = false;
        boolean stopSoutheast = false;
        boolean stopNorthwest = false;
        boolean stopSouthwest = false;
        if(indexToCheck == maxNortheastIndex) stopNortheast = true;
        if(indexToCheck == maxSoutheastIndex) stopSoutheast = true;
        if(indexToCheck == maxNorthwestIndex) stopNorthwest = true;
        if(indexToCheck == maxSouthwestIndex) stopSouthwest = true;

        for(int i = 0; i < 8; i++) {
            int checkIndex = indexToCheck + pseudo1[0] * (i + 1);

            if(!stopLeft){
                if(checkIndex >= highestLeft) {
                    Square squareToCheck = square[checkIndex]; 
                    stopLeft = toAddSet(squareToCheck, discToMove, checkIndex, leftSet);
                } else { stopLeft = true; leftSet.clear(); }
            }

            checkIndex = indexToCheck + pseudo1[1] * (i + 1);
            if(!stopRight){
                if(checkIndex <= highestRight) {
                    Square squareToCheck = square[checkIndex]; 
                    stopRight = toAddSet(squareToCheck, discToMove, checkIndex, rightSet);
                } else { stopRight = true; rightSet.clear(); } 
            }

            checkIndex = indexToCheck + pseudo1[2] * (i + 1);
            if(!stopUp) {
                if(checkIndex <= highestHorizontal) {
                    Square squareToCheck = square[checkIndex]; 
                    stopUp = toAddSet(squareToCheck, discToMove, checkIndex, upSet);
                } else { stopUp = true; upSet.clear(); } 
            }

            checkIndex = indexToCheck + pseudo1[3] * (i + 1);
            if(!stopDown) {
                if(checkIndex >= lowestHorizontal) {
                    Square squareToCheck = square[checkIndex]; 
                    stopDown = toAddSet(squareToCheck, discToMove, checkIndex, downSet);
                } else { stopDown = true; downSet.clear(); }
            }

            // DB
            checkIndex = indexToCheck + pseudo2[0] * (i + 1);
            if(!stopNortheast){
                if(checkIndex <= maxNortheastIndex) {
                    Square squareToCheck = square[checkIndex]; 
                    stopNortheast = toAddSet(squareToCheck, discToMove, checkIndex, northeast);
                } else { stopNortheast = true; northeast.clear(); }
            }

            //DN
            checkIndex = indexToCheck + pseudo2[1] * (i + 1);
            if(!stopSoutheast){
                if(checkIndex >= maxSoutheastIndex) {
                    Square squareToCheck = square[checkIndex]; 
                    stopSoutheast = toAddSet(squareToCheck, discToMove, checkIndex, southeast);
                } else { stopSoutheast = true; southeast.clear(); }
            }


            //TN
            checkIndex = indexToCheck + pseudo2[2] * (i + 1);
            if(!stopSouthwest) {
                if(checkIndex >= maxSouthwestIndex) {
                    Square squareToCheck = square[checkIndex]; 
                    stopSouthwest = toAddSet(squareToCheck, discToMove, checkIndex, southwest);
                } else { stopSouthwest = true; southwest.clear(); }
            }


            //TB
            checkIndex = indexToCheck + pseudo2[3] * (i + 1);
            if(!stopNorthwest) {
                if(checkIndex <= maxNorthwestIndex) {
                    Square squareToCheck = square[checkIndex]; 
                    stopNorthwest = toAddSet(squareToCheck, discToMove, checkIndex, northwest);
                } else { stopNorthwest = true; northwest.clear(); }
            }

            if(stopLeft && stopRight && stopUp && stopDown && stopNortheast && stopSoutheast && stopNorthwest && stopSouthwest) break;
        }
        
        if((leftSet.size() | rightSet.size() | upSet.size() | downSet.size() | northeast.size() | southeast.size() | northwest.size() | southwest.size()) != 0)  {
            listMove.put(indexToCheck, leftSet);
            listMove.get(indexToCheck).addAll(rightSet);
            listMove.get(indexToCheck).addAll(upSet);
            listMove.get(indexToCheck).addAll(downSet);
            listMove.get(indexToCheck).addAll(northeast);
            listMove.get(indexToCheck).addAll(southeast);
            listMove.get(indexToCheck).addAll(northwest);
            listMove.get(indexToCheck).addAll(southwest);
        }
    }

    // private static void diagonalMove(HashMap<Integer, Set<Integer>> listMove, Square[] square, Integer indexToCheck, boolean isWhiteToMove) {
    //     Set<Integer> northeast = new HashSet<>();
    //     Set<Integer> southeast = new HashSet<>();
    //     Set<Integer> northwest = new HashSet<>();
    //     Set<Integer> southwest = new HashSet<>(); 
    //     Disc discToMove =isWhiteToMove ? Disc.WHITE : Disc.BLACK; 
    //     int[] pseudo = { 9, -7, -9, 7 };
    //     int row = indexToCheck / 8;
    //     int col = indexToCheck % 8;

    //     // DN
    //     int steps = Math.min(row, 8 - col - 1);
    //     int final_row = row - steps;
    //     int final_col = col + steps;
    //     int maxSoutheastIndex = final_row * 8 + final_col;
        
    //     // DB 
    //     steps = Math.min(8 - row - 1, 8 - col - 1);
    //     final_row = row + steps;
    //     final_col = col + steps;
    //     int maxNortheastIndex = final_row * 8 + final_col;
        
    //     // TN 
    //     steps = Math.min(row, col);
    //     final_row = row - steps;
    //     final_col = col - steps;
    //     int maxSouthwestIndex = final_row * 8 + final_col;
        
    //     // TB 
    //     steps = Math.min(8 - row - 1, col);
    //     final_row = row + steps;
    //     final_col = col - steps;
    //     int maxNorthwestIndex = final_row * 8 + final_col;

    //     boolean stopNortheast = false;
    //     boolean stopSoutheast = false;
    //     boolean stopNorthwest = false;
    //     boolean stopSouthwest = false;
    //     if(indexToCheck == maxNortheastIndex) stopNortheast = true;
    //     if(indexToCheck == maxSoutheastIndex) stopSoutheast = true;
    //     if(indexToCheck == maxNorthwestIndex) stopNorthwest = true;
    //     if(indexToCheck == maxSouthwestIndex) stopSouthwest = true;
    //     for(int i = 0; i < 8; i++) {
            
    //         // DB
    //         int checkIndex = indexToCheck + pseudo[0] * (i + 1);
    //         if(!stopNortheast){
    //             if(checkIndex <= maxNortheastIndex) {
    //                 Square squareToCheck = square[checkIndex]; 
    //                 if(squareToCheck.getDisc() == Disc.NONE) {
    //                     stopNortheast = true;
    //                     northeast.clear();
    //                 }
    //                 else if(squareToCheck.getDisc() == discToMove) {
    //                     stopNortheast = true;
    //                 } else northeast.add(checkIndex);
    //             } else stopNortheast = true;
    //         }

    //         //DN
    //         checkIndex = indexToCheck + pseudo[1] * (i + 1);
    //         if(!stopSoutheast){
    //             if(checkIndex >= maxSoutheastIndex) {
    //                 Square squareToCheck = square[checkIndex]; 
    //                 if(squareToCheck.getDisc() == Disc.NONE) {
    //                     stopSoutheast = true;
    //                     southeast.clear();
    //                 }
    //                 else if(squareToCheck.getDisc() == discToMove) {
    //                     stopSoutheast = true;
    //                 } else southeast.add(checkIndex);
    //             } else stopSoutheast = true; 
    //         }

    //         //TN
    //         checkIndex = indexToCheck + pseudo[2] * (i + 1);
    //         if(!stopSouthwest) {
    //             if(checkIndex >= maxSouthwestIndex) {
    //                 Square squareToCheck = square[checkIndex]; 
    //                 if(squareToCheck.getDisc() == Disc.NONE) {
    //                     stopSouthwest = true;
    //                     southwest.clear();
    //                 }
    //                 else if(squareToCheck.getDisc() == discToMove) {
    //                     stopSouthwest = true;
    //                 } else southwest.add(checkIndex);
    //             } else stopSouthwest = true; 
    //         }

    //         //TB
    //         checkIndex = indexToCheck + pseudo[3] * (i + 1);
    //         if(!stopNorthwest) {
    //             if(checkIndex <= maxNorthwestIndex) {
    //                 Square squareToCheck = square[checkIndex]; 
    //                 if(squareToCheck.getDisc() == Disc.NONE) {
    //                     stopNorthwest = true;
    //                     northwest.clear();
    //                 }
    //                 else if(squareToCheck.getDisc() == discToMove) {
    //                     stopNorthwest = true;
    //                 } else northwest.add(checkIndex);
    //             } else stopNorthwest = true; 
    //         }

    //         if(stopNortheast && stopSoutheast && stopNorthwest  && stopSouthwest) break;
    //     }
        
    //     if((northeast.size() | southeast.size() | northwest.size() | southwest.size()) != 0)  {
    //         if(listMove.containsKey(indexToCheck)) {
    //             listMove.get(indexToCheck).addAll(northeast);
    //             listMove.get(indexToCheck).addAll(southeast);
    //             listMove.get(indexToCheck).addAll(northwest);
    //             listMove.get(indexToCheck).addAll(southwest);
    //         } else {
    //             listMove.put(indexToCheck, northeast);
    //             listMove.get(indexToCheck).addAll(southeast);
    //             listMove.get(indexToCheck).addAll(northwest);
    //             listMove.get(indexToCheck).addAll(southwest);
    //         }
    //     }
    // }


    private static boolean toAddSet(Square squareToCheck, Disc discToMove, int checkIndex, Set<Integer> setList) {
        if(squareToCheck.getDisc() == Disc.NONE) {
            setList.clear();
            return true;
        }
        else if(squareToCheck.getDisc() == discToMove) {
            return true;
        } else {
            setList.add(checkIndex);
            return false;
        } 
           
    }
}
