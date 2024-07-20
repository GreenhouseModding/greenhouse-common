package dev.greenhouseteam.greenhouse_common.neoforge.platform;

import dev.greenhouseteam.greenhouse_common.platform.Platform;
import dev.greenhouseteam.greenhouse_common.platform.PlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class NeoforgePlatformHelper implements PlatformHelper {

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
}