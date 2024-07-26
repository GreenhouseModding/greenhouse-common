import dev.greenhouseteam.greenhouse_common.gradle.Properties
import dev.greenhouseteam.greenhouse_common.gradle.Versions
import org.apache.tools.ant.filters.LineContains
import org.gradle.jvm.tasks.Jar
import dev.greenhouseteam.greenhouse_common.gradle.props

plugins {
    id("net.neoforged.moddev")
}

dependencies {
    Properties.MODULES.forEach {
        implementation(project(":${it.key}-neoforge")) {
            capabilities {
                requireCapability("${Properties.GROUP}:${it.value.modId}-${it.value.moduleName}-neoforge")
            }
            isTransitive = false
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

    runs {
        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            systemProperty("forge.logging.console.level", "debug")
            systemProperty("neoforge.enabledGameTestNamespaces", "greenhouse_common")
        }
        create("client") {
            client()
            gameDirectory.set(file("runs/client"))
            sourceSet = sourceSets["test"]
            jvmArguments.set(setOf("-Dmixin.debug.verbose=true", "-Dmixin.debug.export=true"))
        }
        create("server") {
            server()
            gameDirectory.set(file("runs/server"))
            programArgument("--nogui")
            sourceSet = sourceSets["test"]
            jvmArguments.set(setOf("-Dmixin.debug.verbose=true", "-Dmixin.debug.export=true"))
        }
    }

    mods {
        Properties.MODULES.filter { it.key != "core" }.forEach {
            register(it.value.modId) {
                dependency(project(":${it.key}-neoforge"))
            }
        }
    }
}