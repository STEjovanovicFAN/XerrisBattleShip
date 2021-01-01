package src;

import java.util.*;

public class Controller {

    private List<Player> playerPool;
    private Scanner scan;

    private void initState(){
        playerPool = new ArrayList<Player>();
        scan = new Scanner(System.in);

        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 2");

        playerPool.add(p1);
        playerPool.add(p2);

        System.out.println("Welcome to the game of BattleShip! \nBoards have been initalized!\n");
    }

    private void placeShips(){
        System.out.println("Begin by placing your ships on your respective Boards!" + 
        "\nRows are A-H while Columns are 1-8 (eg. \'A1\')!" +
        "\nAfter you've chosen your Ships coordinates flip your Ships either (V)ertically or (H)orizontally (eg. 'v')!");

        int playerSize = playerPool.size();

        for(int i = 0; i < playerSize; i++){
            Player currPlayer = playerPool.remove(0);
            List<Ship> playerShips = currPlayer.getPlayerShips();
            
            for(int j = 0; j < playerShips.size(); j++){
                String coords = "";
                String flip = "";
                Ship currShip = playerShips.get(j);

                boolean validPlace = false;
                do{
                    boolean validCoords = false;
                    do{
                        System.out.print("\n" + currPlayer.getPlayerId() + " place your " + currShip.getType() 
                        + " of length " + currShip.getLength() + ":");

                        coords = scan.nextLine();
                        validCoords = currPlayer.isValidCoord(coords);
                        if(!validCoords){
                            System.out.println("Coordinates invalid, please input valid coordinates.");
                        } 

                    } while(!validCoords);

                    System.out.print(currPlayer.getPlayerId() + " would you like to flip your " + currShip.getType()
                         + " (V)ertically or (H)orizontally:");
                    
                    flip = scan.nextLine();
                    validPlace = currPlayer.isValidFlip(flip) && currPlayer.isValidFlipBounds(flip, coords, currShip);

                    if(!validPlace){
                        System.out.println("Flip is invalid because input is incorrect, ship is out of bounds, or another ship is in that location, please input valid coordinates and flip.");
                    }

                } while(!validPlace);

                currPlayer.setShipCoords(coords, flip, currShip);
                currPlayer.placeShip(currShip);
            }

            playerPool.add(currPlayer);
        }

    }

    private void gameLoop(){
        System.out.println("\nBeginning Game!\nReminder Rows are A-H while Columns are 1-8 (eg. 'A1')!");

        while(true){
            Player guessingPlayer = playerPool.remove(0);
            Player opponentPlayer = playerPool.remove(0);

            String coord = "";

            boolean validGuess = false;
            do{
                System.out.print("\n" + guessingPlayer.getPlayerId() + " guess where " + opponentPlayer.getPlayerId() 
                    + " placed their ship:");

                coord = scan.nextLine();
                validGuess = opponentPlayer.isValidCoord(coord);
                if(!validGuess){
                    System.out.println("Invalid guessing coordinate, please try again.");
                }

            } while(!validGuess);

            String action = opponentPlayer.guessCoords(coord);
            System.out.println(action);

            if(action.equals("Hit!") && opponentPlayer.allShipsDestroyed()){
                System.out.println("\n" + guessingPlayer.getPlayerId() + " has destroyed all " + opponentPlayer.getPlayerId() + " ships!" +
                    "\n" + guessingPlayer.getPlayerId() + " wins!");
                break;
            }

            playerPool.add(opponentPlayer);
            playerPool.add(guessingPlayer);
        }

    }

    public void startGame(){
        initState();
        placeShips();
        gameLoop();
    }

}