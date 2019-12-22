package model;

import lombok.Data;

@Data
public class Point {
    private final int x;
    private final int y;

    public double distanceFrom(Point otherPoint) {
        return Math.sqrt((otherPoint.getX() - x) * (otherPoint.getX() - x) + (otherPoint.getY() - y) * (otherPoint.getY() - y));
    }
}
