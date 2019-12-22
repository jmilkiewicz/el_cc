import model.PairOfPoints;
import model.Point;
import model.Result;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ClosestFarthestPoints {

    public static Optional<Result> calculate(Point... points) {
        if (points.length < 2)
            return Optional.empty();


        Optional<Result> reduce = candidates(points).reduce(Optional.empty(),
                (acc, pair) -> acc.map(current -> current.merge(pair)).or(() -> Optional.of(new Result(pair, pair)))
                , (a, b) -> {
                    if (a.isEmpty()) {
                        return b;
                    }
                    if (b.isEmpty()) {
                        return a;
                    }
                    return a.flatMap(x -> b.map(y -> x.merge(y)));
                });
        return reduce;
    }


    private static Stream<PairOfPoints> candidates(Point[] points) {
        return IntStream.range(0, points.length)
                .mapToObj(index -> IntStream.range(index + 1, points.length)
                        .mapToObj(index2 -> new PairOfPoints(points[index], points[index2])))
                .flatMap(Function.identity());
    }

}
