package dev.greenhouseteam.greenhouse_common.core.impl.neoforge;

import dev.greenhouseteam.greenhouse_common.core.api.ModWrapper;
import dev.greenhouseteam.greenhouse_common.core.api.config.ModConfig;
import net.neoforged.neoforgespi.language.IModInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class NeoforgeModWrapper implements ModWrapper {
    private final IModInfo modInfo;
    private final Optional<ModConfig> config;

    public NeoforgeModWrapper(IModInfo modInfo){
        this.modInfo = modInfo;
        try {
            this.config = ModConfig.load(resolve(ModConfig.CONFIG_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String modid() {
        return modInfo.getModId();
    }

    @Override
    public Optional<Path> resolve(String... paths) {
        var file = modInfo.getOwningFile().getFile().findResource(paths);
        if (Files.exists(file))
            return Optional.of(file);
        return Optional.empty();
    }

    @Override
    public Optional<ModConfig> getConfig() {
        return config;
    }
}
