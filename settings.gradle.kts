pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.neoforged.net/releases") {
            name = "NeoForged"
        }
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        maven("https://repo.spongepowered.org/repository/maven-public/") {
            name = "Sponge Snapshots"
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

// This should match the folder name of the project, or else IDEA may complain (see https://youtrack.jetbrains.com/issue/IDEA-317606)
rootProject.name = "Greenhouse-Common"

val platforms = setOf("common", "fabric", "neoforge")
platforms.forEach {
    include(":${it}")
}
val modules = setOf("core", "event", "registry")
modules.forEach {
    platforms.forEach { platform ->
        include(":${it}-${platform}")
        project(":${it}-${platform}").projectDir = file("${it}/${platform}")
    }
}