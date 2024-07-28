package dev.greenhouseteam.greenhouse_common.core.test;

import dev.greenhouseteam.greenhouse_common.core.api.GreenhouseLogging;
import dev.greenhouseteam.greenhouse_common.core.api.entrypoint.CommonEntry;

public class TestCommonEntry implements CommonEntry {
    @Override
    public void init() {
        GreenhouseLogging.info("It worked!");
    }
}
