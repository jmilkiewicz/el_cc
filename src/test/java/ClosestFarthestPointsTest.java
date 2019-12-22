import com.github.npathai.hamcrestopt.OptionalMatchers;
import model.PairOfPoints;
import model.Point;
import model.Result;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class ClosestFarthestPointsTest {

    @Test
    public void shallReturnEmptyWhenInputIsEmpty() {
        Optional<Result> result = ClosestFarthestPoints.calculate();

        assertThat(result, isEmpty());
    }

    @Test
    public void shallReturnEmptyWhenOnlyOnePoint() {
        Optional<Result> result = ClosestFarthestPoints.calculate(new Point(1, 2));

        assertThat(result, isEmpty());
    }

    @Test
    public void whenInputHas2PointsThenTheseAreClosesAndFurthest() {
        Point p1 = new Point(2, 6);
        Point p2 = new Point(4, 1);
        Optional<Result> result = ClosestFarthestPoints.calculate(p1, p2);

        var pair = new PairOfPoints(p1, p2);
        assertThat(result, OptionalMatchers.isPresentAnd(is(new Result(pair, pair))));
    }

    @Test
    public void twoPointsAtTheSameLocationAreAlwaysTheClosest() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(3, 3);
        Optional<Result> result = ClosestFarthestPoints.calculate(p1, p2, p3);

        assertThat(result, OptionalMatchers.isPresentAnd(is(new Result(new PairOfPoints(p1, p2), new PairOfPoints(p3, p1)))));
    }

    @Test
    public void shallCalculateFor3Points() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(4, 4);
        Optional<Result> result = ClosestFarthestPoints.calculate(p1, p2, p3);

        var expectedCloses = new PairOfPoints(p1, p2);
        var expectedFarthest = new PairOfPoints(p1, p3);
        assertThat(result, OptionalMatchers.isPresentAnd(is(new Result(expectedCloses, expectedFarthest))));
    }

    @Test
    public void canHaveMultipleClosestPairs() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(3, 3);
        Optional<Result> result = ClosestFarthestPoints.calculate(p1, p2, p3);
        var expectedCloses1 = new PairOfPoints(p2, p3);
        var expectedCloses2 = new PairOfPoints(p1, p2);

        PairOfPoints closestPoints = result.get().getClosestPoints();

        assertThat(closestPoints, anyOf(is(expectedCloses1), is(expectedCloses2)));
    }

}
