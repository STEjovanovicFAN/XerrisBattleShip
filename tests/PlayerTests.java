package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.*;

import src.Player;
import src.Ship;

public class PlayerTests {
    @Test 
    public void testValidCoordsInput(){
        Player p = new Player("Player 1");

        assertEquals(true, p.isValidCoord("A1"));
        assertEquals(true, p.isValidCoord("A8"));
        assertEquals(true, p.isValidCoord("H1"));
        assertEquals(true, p.isValidCoord("A8"));
        assertEquals(true, p.isValidCoord("C4"));
        assertEquals(true, p.isValidCoord("a1"));
    }

    @Test
    public void testInValidCoordsInput(){
        Player p = new Player("Player 1");

        assertEquals(false, p.isValidCoord(""));
        
        assertEquals(false, p.isValidCoord("A"));
        assertEquals(false, p.isValidCoord("1"));
        
        assertEquals(false, p.isValidCoord("A0"));
        assertEquals(false, p.isValidCoord("A9"));
        assertEquals(false, p.isValidCoord("H0"));
        assertEquals(false, p.isValidCoord("H9"));
        
        assertEquals(false, p.isValidCoord("11"));
        assertEquals(false, p.isValidCoord("HH"));
        assertEquals(false, p.isValidCoord("00"));
        assertEquals(false, p.isValidCoord("99"));

        assertEquals(false, p.isValidCoord("12322"));
        assertEquals(false, p.isValidCoord("A1233"));
    }

    @Test 
    public void testValidFlipInput(){
        Player p = new Player("Player 1");

        assertEquals(true, p.isValidFlip("V"));
        assertEquals(true, p.isValidFlip("v"));

        assertEquals(true, p.isValidFlip("h"));
        assertEquals(true, p.isValidFlip("H"));
    }

    @Test 
    public void testInValidFlipInput(){
        Player p = new Player("Player 1");

        assertEquals(false, p.isValidFlip("1"));
        assertEquals(false, p.isValidFlip("a"));
        
        assertEquals(false, p.isValidFlip("123435"));
        assertEquals(false, p.isValidFlip(""));
    }

    @Test 
    public void testValidFlipBounds(){
        Player p = new Player("Player 1");
        Ship s = new Ship("battleship", 3);

        assertEquals(true, p.isValidFlipBounds("V", "A1", s));
        assertEquals(true, p.isValidFlipBounds("H", "A1", s));
    }

    @Test 
    public void testInValidFlipBounds(){
        Player p = new Player("Player 1");
        Ship s = new Ship("battleship", 3);

        assertEquals(false, p.isValidFlipBounds("V", "H8", s));
        assertEquals(false, p.isValidFlipBounds("H", "H8", s));

        assertEquals(false, p.isValidFlipBounds("H", "A8", s));
        assertEquals(false, p.isValidFlipBounds("V", "H1", s));
    }

    @Test
    public void testValidPoints(){
        Player p = new Player("Player 1");

        assertEquals(true, p.isValidPoint(1, 1));
        assertEquals(true, p.isValidPoint(1, 8));
        assertEquals(true, p.isValidPoint(8, 1));
        assertEquals(true, p.isValidPoint(8, 8));
        assertEquals(true, p.isValidPoint(5, 3));
    }

    @Test
    public void testInValidPoints(){
        Player p = new Player("Player 1");

        assertEquals(false, p.isValidPoint(0, 0));
        assertEquals(false, p.isValidPoint(9, 9));
        assertEquals(false, p.isValidPoint(-1, -8));
    }

    @Test
    public void testGuessCoords(){
        Player p = new Player("Player 1");
        Ship s = new Ship("BattleShip", 3);
        p.setShipCoords("A1", "V", s);
        p.placeShip(s);

        assertEquals("Hit!", p.guessCoords("A1"));
        assertEquals("Hit!", p.guessCoords("B1"));
        assertEquals("Hit!", p.guessCoords("C1"));

        assertEquals("Miss!", p.guessCoords("A2"));
        assertEquals("Miss!", p.guessCoords("B2"));
        assertEquals("Miss!", p.guessCoords("C2"));
        assertEquals("Miss!", p.guessCoords("D1"));
        assertEquals("Miss!", p.guessCoords("D2"));

        Ship s2 = new Ship("BattleShip", 3);
        p.setShipCoords("H1", "H", s2);
        p.placeShip(s2);

        assertEquals("Hit!", p.guessCoords("H1"));
        assertEquals("Hit!", p.guessCoords("H2"));
        assertEquals("Hit!", p.guessCoords("H3"));

        assertEquals("Miss!", p.guessCoords("G1"));
        assertEquals("Miss!", p.guessCoords("G2"));
        assertEquals("Miss!", p.guessCoords("G3"));
        assertEquals("Miss!", p.guessCoords("G4"));
        assertEquals("Miss!", p.guessCoords("H4"));
    }

    @Test
    public void testAllDestroyed(){
        Player p = new Player("Player 1");
        List<Ship> shipLst = p.getPlayerShips();
        p.setShipCoords("A1", "V", shipLst.get(0));
        p.placeShip(shipLst.get(0));

        assertEquals(false, p.allShipsDestroyed());

        p.guessCoords("A1");
        p.guessCoords("B1");
        p.guessCoords("C1");
        
        assertEquals(true, p.allShipsDestroyed());
    }
}
