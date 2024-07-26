package dev.greenhouseteam.greenhouse_common.core.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreenhouseLogging {
    public static final Logger LOG = LoggerFactory.getLogger(GreenhouseConstants.MOD_NAME);

    public static void info(String message) {
        LOG.info(message);
    }

    public static void info(String message, Object... args) {
        LOG.info(message, args);
    }

    public static void warn(String message) {
        LOG.warn(message);
    }

    public static void warn(String message, Object... args) {
        LOG.warn(message, args);
    }

    public static void error(String message) {
        LOG.error(message);
    }

    public static void error(String message, Object... args) {
        LOG.error(message, args);
    }

    public static void debug(String message) {
        LOG.debug(message);
    }

    public static void debug(String message, Object... args) {
        LOG.debug(message, args);
    }

    public static void trace(String message) {
        LOG.trace(message);
    }

    public static void trace(String message, Object... args) {
        LOG.trace(message, args);
    }
}
