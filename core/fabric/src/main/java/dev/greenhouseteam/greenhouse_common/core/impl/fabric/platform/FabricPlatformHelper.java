package dev.greenhouseteam.greenhouse_common.core.impl.fabric.platform;

import dev.greenhouseteam.greenhouse_common.core.api.ModWrapper;
import dev.greenhouseteam.greenhouse_common.core.api.platform.Platform;
import dev.greenhouseteam.greenhouse_common.core.api.platform.PlatformHelper;
import dev.greenhouseteam.greenhouse_common.core.impl.fabric.FabricModWrapper;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformHelper implements PlatformHelper {
    public static ModWrapper[] mods;

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

    @Override
    public ModWrapper[] getLoadedMods() {
        if (mods == null)
            mods = FabricLoader.getInstance()
                    .getAllMods()
                    .stream()
                    .map(FabricModWrapper::new)
                    .toArray(ModWrapper[]::new);
        return mods;
    }
}
