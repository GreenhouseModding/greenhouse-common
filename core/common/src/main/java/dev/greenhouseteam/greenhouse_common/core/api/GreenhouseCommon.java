package dev.greenhouseteam.greenhouse_common.core.api;

import dev.greenhouseteam.greenhouse_common.core.api.entrypoint.CommonEntry;
import dev.greenhouseteam.greenhouse_common.core.api.entrypoint.EntrypointLoader;
import dev.greenhouseteam.greenhouse_common.core.api.platform.PlatformHelper;

import java.lang.reflect.InvocationTargetException;

public class GreenhouseCommon {
    private static PlatformHelper helper;

    public static void init(PlatformHelper helper) {
        if (wasInit())
            return;

        GreenhouseCommon.helper = helper;

        GreenhouseLogging.info("Initializing {} with platform {}", GreenhouseConstants.MOD_NAME, helper.getPlatform().readableName);

        try {
            EntrypointLoader.loadEntrypoints("common", CommonEntry.class).forEach(CommonEntry::init);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static PlatformHelper getHelper() {
        return helper;
    }

    public static boolean wasInit() {
        return helper != null;
    }
}
