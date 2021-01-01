package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import src.Board;
import src.Player;
import src.Ship;

public class BoardTests {
    @Test 
    public void testShipAtCoord(){
        Player p = new Player("Player 1");
        Ship s = new Ship("BattleShip", 3);
        p.setShipCoords("A1", "V", s);

        Board b = new Board();

        assertEquals(false, b.shipAtCoord(1, 1));
        assertEquals(false, b.shipAtCoord(2, 1));
        assertEquals(false, b.shipAtCoord(3, 1));

        b.setPiece(s);

        assertEquals(true, b.shipAtCoord(1, 1));
        assertEquals(true, b.shipAtCoord(2, 1));
        assertEquals(true, b.shipAtCoord(3, 1));
    }

    @Test 
    public void testEraseShip(){
        Player p = new Player("Player 1");
        Ship s = new Ship("BattleShip", 3);
        p.setShipCoords("A1", "V", s);

        Board b = new Board();
        b.setPiece(s);

        assertEquals(true, b.shipAtCoord(1, 1));
        assertEquals(true, b.shipAtCoord(2, 1));
        assertEquals(true, b.shipAtCoord(3, 1));

        b.eraseShip(1, 1);
        assertEquals(false, b.shipAtCoord(1, 1));
    }

    @Test 
    public void testSetPiece(){
        Player p = new Player("Player 1");
        Ship s = new Ship("BattleShip", 3);
        p.setShipCoords("A1", "V", s);

        Board b = new Board();

        assertEquals(false, b.shipAtCoord(1, 1));
        assertEquals(false, b.shipAtCoord(2, 1));
        assertEquals(false, b.shipAtCoord(3, 1));

        b.setPiece(s);

        assertEquals(true, b.shipAtCoord(1, 1));
        assertEquals(true, b.shipAtCoord(2, 1));
        assertEquals(true, b.shipAtCoord(3, 1));
    }
}
