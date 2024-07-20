package dev.greenhouseteam.greenhouse_common;

import dev.greenhouseteam.greenhouse_common.platform.PlatformHelper;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreenhouseCommon {
    public static final String MOD_ID = "greenhouse_common";
    public static final String MOD_NAME = "Greenhouse Common";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    private static PlatformHelper helper;

    public static void init(PlatformHelper helper) {
        GreenhouseCommon.helper = helper;
    }

    public static PlatformHelper getHelper() {
        return helper;
    }

    public static void info(String message) {
        LOG.info(message);
    }

    public static void warn(String message) {
        LOG.warn(message);
    }

    public static void error(String message) {
        LOG.error(message);
    }

    public static void debug(String message) {
        LOG.debug(message);
    }

    public static void trace(String message) {
        LOG.trace(message);
    }
}
