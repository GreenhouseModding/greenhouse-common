package dev.greenhouseteam.greenhouse_common.neoforge;


import dev.greenhouseteam.greenhouse_common.GreenhouseCommon;
import dev.greenhouseteam.greenhouse_common.api.RegistryContext;
import dev.greenhouseteam.greenhouse_common.neoforge.platform.NeoforgePlatformHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(GreenhouseCommon.MOD_ID)
public class GreenhouseCommonNeoForge {
    public GreenhouseCommonNeoForge(IEventBus eventBus) {
        GreenhouseCommon.init(new NeoforgePlatformHelper());
        eventBus.addListener(this::registerToRegistries);
    }

    @SubscribeEvent
    public void registerToRegistries(RegisterEvent event) {
        RegistryContext.listen((registry, lazy, id) ->
            event.register(registry.key(), id, lazy)
        );
    }
}