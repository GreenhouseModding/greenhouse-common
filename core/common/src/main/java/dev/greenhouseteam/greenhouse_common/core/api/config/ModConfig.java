package dev.greenhouseteam.greenhouse_common.core.api.config;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.greenhouseteam.greenhouse_common.core.api.GreenhouseConstants;
import dev.greenhouseteam.greenhouse_common.core.api.GreenhouseLogging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public record ModConfig(Map<String, List<String>> entrypoints) {
    public static final String CONFIG_PATH = "greenhouse.json";

    public static final Codec<List<String>> LIST_OR_SINGLE = Codec.either(Codec.STRING.listOf(), Codec.STRING).flatComapMap(it -> it.map(Function.identity(), List::of), it -> DataResult.success(Either.left(it)));

    public static final Codec<ModConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                   Codec.unboundedMap(Codec.STRING, LIST_OR_SINGLE).fieldOf("entrypoints").forGetter(ModConfig::entrypoints)
            ).apply(instance, ModConfig::new)
    );

    public static Optional<ModConfig> load(Optional<Path> path) throws IOException {
        if (path.isEmpty())
            return Optional.empty();
        var jsonString = Files.readString(path.get());
        GreenhouseLogging.info(jsonString);
        var json = GreenhouseConstants.GSON.fromJson(jsonString, JsonObject.class);
        return Optional.of(ModConfig.CODEC.decode(JsonOps.INSTANCE, json).getOrThrow().getFirst());
    }

    public List<String> getEntrypoints(String name) {
        if (!entrypoints.containsKey(name))
            return List.of();
        return entrypoints.get(name);
    }
}
