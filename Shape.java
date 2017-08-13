package tetris.game;

import java.awt.Color;

public class Shape{

   /* public static void main(String[] args){
        Shape s = new Shape(Color.GREEN,new Coordinates(1,0),new Coordinates(0,0),new Coordinates(2,0), new Coordinates(3,0));
        System.out.println(s.getColor());
        for(Coordinates c : s.getCoordinates()){
            System.out.println(c.getX() + ", " + c.getY());
        }
    }*/

    private static final int NUM_OF_COORDINATES = 4;

    private final Color color;
    private Coordinates [] coordinates;

    public Shape(Color color, Coordinates ... coordinates){

        this.color = color;
        
        this.coordinates = new Coordinates[NUM_OF_COORDINATES];
        for(int i = 0; i < NUM_OF_COORDINATES; i++)
            this.coordinates[i] = coordinates[i];
        
    }

    public Color getColor(){
        return color;
    }

    public Coordinates [] getCoordinates(){
        return coordinates;
    }
}