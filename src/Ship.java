package src;

public class Ship {
    
    private String type;
    
    private int length;
    private int health;
    
    private int xCoord;
    private int yCoord;

    private char flip;

    public Ship(String type, int length){
        this.type = type;
        this.length = length;
        health = length;

    }

    public String getType(){
        return type;
    }

    public int getLength(){
        return length;
    }

    public void setXCoord(int x){
        xCoord = x;
    }

    public int getXCoord(){
        return xCoord;
    }

    public void setYCoord(int y){
        yCoord = y;
    }

    public int getYCoord(){
        return yCoord;
    }

    public void setFlip(char f){
        flip = f;
    }

    public char getFlip(){
        return flip;
    }

    public void decHealth(){
        health--;
    }

    public int gethealth(){
        return health;
    }
}
