package ihar.shyn.generator;

import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentIdGenerator implements IdGenerator {
    private final AtomicLong id;

    public ConcurrentIdGenerator(Long initValue) {
        this.id = new AtomicLong(initValue);
    }

    public ConcurrentIdGenerator() {
        this(1L);
    }

    @Override
    public long generateId() {
        return id.getAndIncrement();
    }
}
