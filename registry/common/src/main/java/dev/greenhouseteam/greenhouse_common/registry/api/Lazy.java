package dev.greenhouseteam.greenhouse_common.registry.api;

import java.util.Optional;
import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {
    private final Supplier<T> supplier;
    private Optional<T> value = Optional.empty();

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public static <T> Lazy<T> of(Supplier<T> sup) {
        if (sup instanceof Lazy<T> lazy)
            return lazy;
        return new Lazy<>(sup);
    }

    @Override
    public T get() {
        if (value.isEmpty())
            value = Optional.of(supplier.get());
        return value.get();
    }
}
