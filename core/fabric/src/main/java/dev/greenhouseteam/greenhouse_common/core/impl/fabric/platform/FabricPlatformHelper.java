package dev.greenhouseteam.greenhouse_common.core.impl.fabric.platform;

import dev.greenhouseteam.greenhouse_common.core.api.platform.Platform;
import dev.greenhouseteam.greenhouse_common.core.api.platform.PlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformHelper implements PlatformHelper {
    @Override
    public Platform getPlatform() {
        return Platform.FABRIC;
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
