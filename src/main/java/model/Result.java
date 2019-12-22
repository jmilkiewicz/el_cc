package model;

import lombok.Data;

@Data
public class Result {
    private final PairOfPoints closestPoints;
    private final PairOfPoints farthestPoints;

    public Result merge(PairOfPoints pairOfPoints) {
        var currentClosest = closestPoints;
        var currentFarthest = farthestPoints;

        if (currentClosest.distance() > pairOfPoints.distance()) {
            currentClosest = pairOfPoints;
        }

        if (currentFarthest.distance() < pairOfPoints.distance()) {
            currentFarthest = pairOfPoints;
        }
        return new Result(currentClosest, currentFarthest);
    }

    public Result merge(Result pairOfPoints) {
        Result merge = merge(pairOfPoints.closestPoints);
        return merge.merge(pairOfPoints.farthestPoints);
    }
}
