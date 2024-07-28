import dev.greenhouseteam.greenhouse_common.gradle.Properties
import dev.greenhouseteam.greenhouse_common.gradle.Versions
import org.apache.tools.ant.filters.LineContains
import org.gradle.jvm.tasks.Jar
import dev.greenhouseteam.greenhouse_common.gradle.props

plugins {
    id("conventions.loader")
    id("net.neoforged.moddev")
    id("me.modmuss50.mod-publish-plugin")
}

val core = project(":core-common")
val event = project(":event-common")

evaluationDependsOn(":event-common")

dependencies {
    compileOnly(project(":core-common")) {
        capabilities {
            requireCapability("${Properties.GROUP}:${core.props.modId}-${core.props.moduleName}-common")
        }
        isTransitive = false
    }
    compileOnly(project(":core-neoforge")) {
        capabilities {
            requireCapability("${Properties.GROUP}:${core.props.modId}-${core.props.moduleName}-neoforge")
        }
        isTransitive = false
    }

    compileOnly(project(":event-common")) {
        capabilities {
            requireCapability("${Properties.GROUP}:${event.props.modId}-${event.props.moduleName}-common")
        }
        isTransitive = false
    }
    compileOnly(project(":event-neoforge")) {
        capabilities {
            requireCapability("${Properties.GROUP}:${event.props.modId}-${event.props.moduleName}-neoforge")
        }
        isTransitive = false
    }
}

neoForge {
    version = Versions.NEOFORGE
    parchment {
        minecraftVersion = Versions.INTERNAL_MINECRAFT
        mappingsVersion = Versions.PARCHMENT
    }
    addModdingDependenciesTo(sourceSets["test"])

    val at = project(":core-common").file("src/main/resources/${project.props.modId}.cfg")
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