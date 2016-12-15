import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestFlatMap {

    @Test
    public void testJavaOptional() {
        List<Integer> hashCodes = Stream.of(
                null,
                "some stuff",
                "other stuff",
                null)
                .flatMap(this::toByteArray)
                .collect(Collectors.toList());

        assertThat(hashCodes.size(), is(2));
    }

    public Stream<Integer> toByteArray(String s) {
        if (s == null) {
            return Stream.empty();
        }
        return Stream.of(s.hashCode());
    }
}
