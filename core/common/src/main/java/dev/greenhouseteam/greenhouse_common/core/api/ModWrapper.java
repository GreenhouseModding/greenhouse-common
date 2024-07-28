package dev.greenhouseteam.greenhouse_common.core.api;

import dev.greenhouseteam.greenhouse_common.core.api.config.ModConfig;

import java.nio.file.Path;
import java.util.Optional;

public interface ModWrapper {
    String modid();
    Optional<Path> resolve(String... paths);
    Optional<ModConfig> getConfig();
}
