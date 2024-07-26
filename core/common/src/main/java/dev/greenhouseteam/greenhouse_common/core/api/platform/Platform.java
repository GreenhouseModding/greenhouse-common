package dev.greenhouseteam.greenhouse_common.core.api.platform;

public enum Platform {
    FABRIC("Fabric"),
    NEOFORGE("NeoForge");

    public final String readableName;

    Platform(String readableName) {
        this.readableName = readableName;
    }
}
