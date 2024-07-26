import dev.greenhouseteam.greenhouse_common.gradle.Properties
import dev.greenhouseteam.greenhouse_common.gradle.Versions
import org.apache.tools.ant.filters.LineContains
import org.gradle.jvm.tasks.Jar

plugins {
    id("conventions.loader")
    id("net.neoforged.moddev")
    id("me.modmuss50.mod-publish-plugin")
}

dependencies {
    compileOnly(project(":core-common")) {
        capabilities {
            requireCapability("${Properties.GROUP}:${Properties.MOD_ID}-core-common")
        }
    }
    compileOnly(project(":core-neoforge")) {
        capabilities {
            requireCapability("${Properties.GROUP}:${Properties.MOD_ID}-core-neoforge")
        }
    }
}

neoForge {
    version = Versions.NEOFORGE
    parchment {
        minecraftVersion = Versions.INTERNAL_MINECRAFT
        mappingsVersion = Versions.PARCHMENT
    }
    addModdingDependenciesTo(sourceSets["test"])

    val at = project(":core-common").file("src/main/resources/${Properties.MOD_ID}-event.cfg")
    if (at.exists())
        setAccessTransformers(at)
    validateAccessTransformers = true
}

tasks {
    named<ProcessResources>("processResources").configure {
        filesMatching("*.mixins.json") {
            filter<LineContains>("negate" to true, "contains" to setOf("refmap"))
        }
    }
}

publishMods {
    file.set(tasks.named<Jar>("jar").get().archiveFile)
    modLoaders.add("neoforge")
    changelog = rootProject.file("CHANGELOG.md").readText()
    version = "${Versions.MOD}+${Versions.MINECRAFT}-neoforge"
    type = STABLE

    github {
        accessToken = providers.environmentVariable("GITHUB_TOKEN")
        parent(project(":core-common").tasks.named("publishGithub"))
    }
}