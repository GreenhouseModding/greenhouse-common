package dev.greenhouseteam.greenhouse_common.core.impl.neoforge.platform;

import dev.greenhouseteam.greenhouse_common.core.api.ModWrapper;
import dev.greenhouseteam.greenhouse_common.core.api.platform.Platform;
import dev.greenhouseteam.greenhouse_common.core.api.platform.PlatformHelper;
import dev.greenhouseteam.greenhouse_common.core.impl.neoforge.NeoforgeModWrapper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class NeoforgePlatformHelper implements PlatformHelper {
    private static ModWrapper[] mods;

    @Override
    public Platform getPlatform() {
        return Platform.NEOFORGE;
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public ModWrapper[] getLoadedMods() {
        if (mods == null)
            mods = ModList.get()
                    .getMods()
                    .stream()
                    .map(NeoforgeModWrapper::new)
                    .toArray(ModWrapper[]::new);
        return mods;
    }
}
