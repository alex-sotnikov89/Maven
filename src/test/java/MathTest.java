package Lesson6
        ;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class MathTest {

    @ParameterizedTest
    @MethodSource("actualAndExpectedResults")
    void shouldReturnArrayAfterLastValues(int[] array, int[] expected) {
        Assertions.assertArrayEquals(expected, Math.returnArrayAfterLastValue(array, 4));
    }

    private static Stream<Arguments> actualAndExpectedResults() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{5, 6, 7, 8}),
                Arguments.arguments(new int[]{1, 2, 3, 4, 7, 8}, new int[]{7, 8}),
                Arguments.arguments(new int[]{1, 2, 3, 4}, new int[]{})
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("wrongParametersProvider")
    void shouldThrowRuntimeExceptionWhenValueNotFound(int[] parameters) {
        assertThatThrownBy(() -> Math.returnArrayAfterLastValue(parameters, 4)
        ).isInstanceOf(RuntimeException.class);
        //        Assertions.assertNotNull(
//                Assertions.assertThrows(
//                        RuntimeException.class,
//                        () -> Math.returnArrayAfterLastValue(parameters, 4)
//                )
//        );
    }

    private static Stream<int[]> wrongParametersProvider() {
        return Stream.of(new int[]{1, 2, 3});
    }

    @ParameterizedTest
    @MethodSource("actualAndExpectedResultsForMethodIsArrayContainValues")
    void shouldReturnTrueIsIsArrayContainValues(int[] array, boolean expected, int[] values){
        boolean result = Math.isArrayContainValues(array, values);
        Assertions.assertEquals(expected,result);
    }


    private static Stream<Arguments> actualAndExpectedResultsForMethodIsArrayContainValues() {
        return Stream.of(
                Arguments.arguments(new int[]{2, 2, 3,5}, false, new int[]{5}),
                Arguments.arguments(new int[]{1, 2, 3,5}, true, new int[]{1,4,5}),
                Arguments.arguments(new int[]{2, 2, 3,4}, true, new int[]{1,4}),
                Arguments.arguments(new int[]{4}, true, new int[]{1,4}),
                Arguments.arguments(new int[]{}, false, new int[]{1,4})
        );
    }
}
