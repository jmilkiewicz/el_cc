package model;

import lombok.Data;

import java.util.Objects;


@Data
public final class PairOfPoints {
    private final Point p1;
    private final Point p2;

    public double distance() {
        return p1.distanceFrom(p2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairOfPoints that = (PairOfPoints) o;
        return (Objects.equals(p1, that.p1) &&
                Objects.equals(p2, that.p2)) ||
                (Objects.equals(p1, that.p2) &&
                        Objects.equals(p2, that.p1))
                ;
    }

    @Override
    public int hashCode() {
        if(p1 != null && p2 != null){
            var artificialPoint = new Point(p1.getX()+p2.getX(), p1.getY() + p2.getY());
            return Objects.hash(artificialPoint);
        }
        return Objects.hash(p1,p2);
    }
}
