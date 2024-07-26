package dev.greenhouseteam.greenhouse_common.registry.impl.fabric;

import dev.greenhouseteam.greenhouse_common.registry.api.RegistryContext;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;

public class GreenhouseRegistryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RegistryContext.listen((registry, lazy, id) ->
                Registry.register(registry, id, lazy.get())
        );
    }
}
