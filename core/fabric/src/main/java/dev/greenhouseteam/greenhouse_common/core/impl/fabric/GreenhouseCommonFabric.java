package dev.greenhouseteam.greenhouse_common.core.impl.fabric;

import dev.greenhouseteam.greenhouse_common.core.api.GreenhouseCommon;
import dev.greenhouseteam.greenhouse_common.core.impl.fabric.platform.FabricPlatformHelper;
import net.fabricmc.api.ModInitializer;

public class GreenhouseCommonFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        GreenhouseCommon.init(new FabricPlatformHelper());
    }
}
