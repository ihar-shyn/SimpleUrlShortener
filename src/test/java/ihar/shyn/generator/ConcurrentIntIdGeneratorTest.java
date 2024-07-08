package ihar.shyn.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentIntIdGeneratorTest {

    @Test
    void whenDefaultConstructor_thenReturn1_thenReturn2_thenReturn3() {
        IdGenerator generator = new ConcurrentIdGenerator();
        Assertions.assertEquals(generator.generateId(), 1L);
        Assertions.assertEquals(generator.generateId(), 2L);
        Assertions.assertEquals(generator.generateId(), 3L);
    }

    @Test
    void whenConstructorWith1000_thenReturn1000_thenReturn1001_thenReturn1002() {
        IdGenerator generator = new ConcurrentIdGenerator(1000L);
        Assertions.assertEquals(generator.generateId(), 1000L);
        Assertions.assertEquals(generator.generateId(), 1001L);
        Assertions.assertEquals(generator.generateId(), 1002L);
    }

    @Test
    void whenGenerate1000IdsInDifferentThread_thenNextIDIs1001AndGenerated1000DifferentValues() throws InterruptedException {
        int generateCount = 1000;
        Map<Long, Long> map = new ConcurrentHashMap<>(generateCount);
        IdGenerator generator = new ConcurrentIdGenerator();
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < generateCount; i++) {
            service.execute(() -> {
                long id = generator.generateId();
                map.put(id, id);
            });
        }
        service.shutdown();
        service.awaitTermination(100, TimeUnit.SECONDS);
        Assertions.assertEquals(map.size(), generateCount);
        Assertions.assertEquals(generator.generateId(), generateCount+1);
    }
}
