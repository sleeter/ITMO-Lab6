package org.example.data;

import java.io.Serializable;

/**
 * This class represents a point in a two-dimensional space.
 */
public class Coordinates implements Serializable {
    private final float x;
    private final Double y; //Поле не может быть null
    public Coordinates(float x, Double y){
        this.x = x;
        this.y = y;
    }
    /**
     * Returns the y-coordinate of the point.
     *
     * @return The value of the variable y.
     */
    public Double getY() {
        return y;
    }
    /**
     * Get the x-coordinate of the point.
     *
     * @return The value of the variable x.
     */
    public float getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
