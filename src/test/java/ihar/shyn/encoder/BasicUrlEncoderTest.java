package ihar.shyn.encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasicUrlEncoderTest {

    @Test
    void whenGenerateById_thenReturn62BasedString() {
        URLEncoder encoder = new BasicUrlEncoder();
        Assertions.assertEquals(encoder.encode(1L), "1");
        Assertions.assertEquals(encoder.encode(2L), "2");
        Assertions.assertEquals(encoder.encode(3L), "3");
        Assertions.assertEquals(encoder.encode(33L), "x");
        Assertions.assertEquals(encoder.encode(38L), "C");
        Assertions.assertEquals(encoder.encode(63L), "11");
        Assertions.assertEquals(encoder.encode(100000L), "q0U");
    }

    @Test
    void WhenDecodeByUrl_thenGetCorrectId () {
        URLEncoder encoder = new BasicUrlEncoder();
        Assertions.assertEquals(encoder.decode("1"), 1L);
        Assertions.assertEquals(encoder.decode("2"), 2L);
        Assertions.assertEquals(encoder.decode("3"), 3L);
        Assertions.assertEquals(encoder.decode("x"), 33L);
        Assertions.assertEquals(encoder.decode("C"), 38L);
        Assertions.assertEquals(encoder.decode("11"), 63L);
        Assertions.assertEquals(encoder.decode("q0U"), 100000L);
    }
}
