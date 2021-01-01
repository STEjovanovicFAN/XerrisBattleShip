package src;

import java.util.ArrayList;
import java.util.List;

public class Player {
    
    private String id;
    private Board playerBoard;
    private List<Ship> playerShips;

    public Player(String id){
        this.id = id;

        playerBoard = new Board();
        playerShips = new ArrayList<Ship>();

        initShips();
    }

    private void initShips(){
        List<String> typeOfShips= playerBoard.getShipPieces();
        List<Integer> lengthOfShips = playerBoard.getShipSize();

        for(int i = 0; i < typeOfShips.size(); i++){

            Ship s = new Ship(typeOfShips.get(i), lengthOfShips.get(i));
            playerShips.add(s);

        }
    }

    public boolean isValidCoord(String coord){
        if(coord.length() != 2){
            return false;
        }
        
        //65 = A, 72 = H
        else if(Character.toUpperCase(coord.charAt(0)) < 65 || Character.toUpperCase(coord.charAt(0)) > 72){
            return false;
        }

        //49 = 1, 56 = 8
        else if(coord.charAt(1) < 49 || coord.charAt(1) > 56){
            return false;
        }

        return true;
    }

    public boolean isValidFlip(String flip){
        if(flip.length() != 1){
            return false;
        }

        else if(Character.toUpperCase(flip.charAt(0)) == 'V' || Character.toUpperCase(flip.charAt(0)) == 'H'){
            return true;
        }

        return false;
    }

    public boolean isValidPoint(int x, int y){
        if(x < 1 || x > 8){
            return false;
        }

        if(y < 1 || y > 8){
            return false;
        }

        return true;
    }

    private int convertXCoord(String coords){
        return Character.toUpperCase(coords.charAt(0)) - 64;
    }

    private int convertYCoord(String coords){
        return coords.charAt(1) - 48;
    }

    public boolean isValidFlipBounds(String flip, String coords, Ship s){
        int x = convertXCoord(coords);
        int y = convertYCoord(coords);        

        int length = s.getLength();
        boolean incX = false;

        if(Character.toUpperCase(flip.charAt(0)) == 'V'){
            incX = true;
        }

        for(int i = 0; i < length; i++){
            if(incX){
                x = x + 1;
            }    

            else{
                y = y + 1;
            }

            if(!isValidPoint(x,y)){
                return false;
            }

            if(playerBoard.shipAtCoord(x,y)){
                return false;
            }
        }

        return true;
    }

    public void placeShip(Ship s){
        playerBoard.setPiece(s);
    }

    public void setShipCoords(String coords, String flip, Ship s){
        int x = convertXCoord(coords);
        int y = convertYCoord(coords);
        s.setXCoord(x);
        s.setYCoord(y);
        s.setFlip(Character.toUpperCase(flip.charAt(0)));
    }

    public String guessCoords(String coords){
        int x = convertXCoord(coords);
        int y = convertYCoord(coords);

        if(playerBoard.shipAtCoord(x,y)){
            Ship s = playerBoard.eraseShip(x, y);
            s.decHealth();
            return "Hit!";
        }
        
        return "Miss!";
    }

    public boolean allShipsDestroyed(){
        for(int i = 0; i < playerShips.size(); i++){
            if(playerShips.get(i).gethealth() != 0){
                return false;
            }
        }

        return true;
    }

    public List<Ship> getPlayerShips(){
        return playerShips;
    }

    public String getPlayerId(){
        return id;
    }

}
