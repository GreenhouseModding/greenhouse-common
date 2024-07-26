package dev.greenhouseteam.greenhouse_common.core.api;

import dev.greenhouseteam.greenhouse_common.core.api.platform.PlatformHelper;

public class GreenhouseCommon {

    private static PlatformHelper helper;

    public static void init(PlatformHelper helper) {
        GreenhouseCommon.helper = helper;

        GreenhouseLogging.info("Initializing {} with platform {}", GreenhouseConstants.MOD_NAME, helper.getPlatform().readableName);
    }

    public static PlatformHelper getHelper() {
        return helper;
    }
}
