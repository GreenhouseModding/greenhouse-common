package dev.greenhouseteam.greenhouse_common.registry.impl.neoforge;

import dev.greenhouseteam.greenhouse_common.registry.api.RegistryContext;
import dev.greenhouseteam.greenhouse_common.registry.impl.GreenhouseRegistryConstants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(GreenhouseRegistryConstants.MOD_ID)
public class GreenhouseRegistryNeoforge {
    public GreenhouseRegistryNeoforge(IEventBus bus) {
        bus.addListener(this::registerToRegistries);
    }

    @SubscribeEvent
    public void registerToRegistries(RegisterEvent event) {
        RegistryContext.listen((registry, lazy, id) ->
                event.register(registry.key(), id, lazy)
        );
    }
}
