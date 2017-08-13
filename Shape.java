package tetris.game;

import java.awt.Color;

public class Shape{

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
