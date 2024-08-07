package dev.greenhouseteam.greenhouse_common.registry.api;

import dev.greenhouseteam.greenhouse_common.event.api.Event;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record RegistryContext<TValue>(Registry<TValue> registry, String namespace) {
    public static final List<RegistryPair<?>> PAIRS = new ArrayList<>();

    private static final Event<RegistryEvent<?>> EVENT = new Event<>(
            listeners ->
                    (registry, lazy, id) ->
                            listeners.forEach(it -> ((RegistryEvent<Object>) it).register(registry, lazy, id))
    );

    public static void listen(RegistryEvent<?> listener) {
        PAIRS.forEach(it -> ((RegistryEvent<Object>) listener).register((Registry<Object>) it.registry, (Lazy<Object>) it.lazy, it.id));
        EVENT.listen(listener);
    }

    @SuppressWarnings("unchecked")
    public <TRegister extends TValue> Lazy<TRegister> register(Supplier<TRegister> sup, String path) {
        var lazy = Lazy.of(sup);
        var loc = ResourceLocation.fromNamespaceAndPath(namespace, path);
        PAIRS.add(new RegistryPair<>(
                registry,
                (Lazy<TValue>) lazy,
                loc
        ));

        ((RegistryEvent<TValue>) EVENT.invoker()).register(registry, (Lazy<TValue>) lazy, loc);

        return lazy;
    }

    public record RegistryPair<TValue>(Registry<TValue> registry, Lazy<TValue> lazy, ResourceLocation id) {}

    public interface RegistryEvent<TValue> {
        void register(Registry<TValue> registry, Lazy<TValue> lazy, ResourceLocation id);
    }
}
