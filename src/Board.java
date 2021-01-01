package src;

import java.util.*;

public class Board {
    private Ship [][] gameBoard;

    private final int boardLength = 8;
    private final int boardHeight = 8;

    private List<String> shipPieces;
    private List<Integer> shipSize;

    public Board(){
        gameBoard = new Ship[boardLength + 1][boardHeight + 1];

        shipPieces = List.of("BattleShip");
        shipSize = List.of(3);

    }

    public List<String> getShipPieces(){
        return shipPieces;
    }
    
    public List<Integer> getShipSize(){
        return shipSize;
    }

    public boolean shipAtCoord(int x, int y){
        if(gameBoard[x][y] == null){
            return false;
        }

        return true;
    }

    public Ship eraseShip(int x, int y){
        Ship s = gameBoard[x][y];
        gameBoard[x][y] = null;

        return s;
    }

    public void setPiece(Ship s){
        int x = s.getXCoord();
        int y = s.getYCoord();

        int length = s.getLength();
        boolean incX = false;

        if(s.getFlip() == 'V'){
            incX = true;
        }

        gameBoard[x][y] = s;
        
        for(int i = 0; i < length -1; i++){
            if(incX){
                x = x + 1;
            }    

            else{
                y = y + 1;
            }

            gameBoard[x][y] = s;
        }
    }

}
