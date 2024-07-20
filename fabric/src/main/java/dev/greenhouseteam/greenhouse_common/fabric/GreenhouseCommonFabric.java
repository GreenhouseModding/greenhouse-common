package dev.greenhouseteam.greenhouse_common.fabric;

import dev.greenhouseteam.greenhouse_common.GreenhouseCommon;
import dev.greenhouseteam.greenhouse_common.api.RegistryContext;
import dev.greenhouseteam.greenhouse_common.fabric.platform.FabricPlatformHelper;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;

public class GreenhouseCommonFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        GreenhouseCommon.LOG.info("Hello Fabric world!");
        GreenhouseCommon.init(new FabricPlatformHelper());

        RegistryContext.listen(((registry, lazy, id) -> Registry.register(registry, id, lazy.get())));
    }
}
