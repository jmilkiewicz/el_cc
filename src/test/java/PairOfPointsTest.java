import model.PairOfPoints;
import model.Point;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class PairOfPointsTest {
    private final Point p1 = new Point(1, 1);
    private final Point p2 = new Point(2, 2);

    @Test
    public void symetric(){
        PairOfPoints pairOfPoints = new PairOfPoints(p1,p2);
        PairOfPoints pairOfPoints2 = new PairOfPoints(p2,p1);

        assertThat(pairOfPoints, Matchers.equalTo(pairOfPoints2));
        assertThat(pairOfPoints2, Matchers.equalTo(pairOfPoints));
    }

    @Test
    public void equalsTest(){
        EqualsVerifier.forClass(PairOfPoints.class)
                .verify();
    }
}
