import model.Point;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class DistanceBetween2PointsTest {

    @Test
    public void shallCalculateDistanceBetweenTwoPoints() {
        var p1 = new Point(1, 1);
        var p2 = new Point(4, 4);
        assertThat(p1.distanceFrom(p2), Matchers.is(p2.distanceFrom(p1)));
        assertThat(p1.distanceFrom(p2), Matchers.closeTo(4.242, 0.003));
    }

}
