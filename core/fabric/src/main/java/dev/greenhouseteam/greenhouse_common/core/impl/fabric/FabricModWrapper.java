package dev.greenhouseteam.greenhouse_common.core.impl.fabric;

import dev.greenhouseteam.greenhouse_common.core.api.ModWrapper;
import dev.greenhouseteam.greenhouse_common.core.api.config.ModConfig;
import net.fabricmc.loader.api.ModContainer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class FabricModWrapper implements ModWrapper {
    private final ModContainer modContainer;
    private final Optional<ModConfig> config;

    public FabricModWrapper(ModContainer modContainer) {
        this.modContainer = modContainer;
        try {
            this.config = ModConfig.load(resolve(ModConfig.CONFIG_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String modid() {
        return modContainer.getMetadata().getId();
    }

    @Override
    public Optional<Path> resolve(String... paths) {
        return modContainer.findPath(StringUtils.join(paths, '/'));
    }

    @Override
    public Optional<ModConfig> getConfig() {
        return config;
    }
}
