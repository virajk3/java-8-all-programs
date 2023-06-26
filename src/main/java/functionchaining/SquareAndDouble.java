package functionchaining;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SquareAndDouble {
    public static void main(String[] args) {

        Function<Integer, Integer> doubleFunction  = i -> i * 2;

        Function<Integer, Integer> square = i-> i * i;


        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        integers.stream().map(square.andThen(doubleFunction))
                .forEach(System.out::println);



       // BiFunction<Integer, Integer, Integer> biFunction = square.andThen(doubleFunction);

        //Function<Integer, Integer> square = i -> i * i;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        BiFunction<Integer, Integer, Integer> multiplyAndSquare = multiply.andThen(square);
        //BiFunction<Integer, Integer, Integer> squareAndMultiply = square.andThen(multiply);

        System.out.println(multiplyAndSquare.apply(2, 3)); //36



         BiFunction<Integer, Integer, Integer> addition  = (a,b) -> a + b;

        BiFunction<Integer, Integer, Integer> additionAndThenSquare = addition.andThen(square);
        System.out.println(additionAndThenSquare.apply(2,4 ));


        Function<Integer, Integer> compose = square.compose(doubleFunction);
        System.out.println(compose.apply(5));
    }
}
