import dev.greenhouseteam.greenhouse_common.gradle.Properties
import dev.greenhouseteam.greenhouse_common.gradle.Versions
import net.fabricmc.loom.task.RemapJarTask
import org.gradle.jvm.tasks.Jar
import dev.greenhouseteam.greenhouse_common.gradle.props

plugins {
    id("conventions.loader")
    id("fabric-loom")
    id("me.modmuss50.mod-publish-plugin")
}

repositories {
    maven {
        name = "TerraformersMC"
        url = uri("https://maven.terraformersmc.com/")
    }
}

val core = project(":core-common")
val event = project(":event-common")

evaluationDependsOn(":event-common")

dependencies {
    minecraft("com.mojang:minecraft:${Versions.INTERNAL_MINECRAFT}")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:${Versions.FABRIC_LOADER}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${Versions.FABRIC_API}")

    compileOnly(project(":core-common")) {
        capabilities {
            requireCapability("${Properties.GROUP}:${core.props.modId}-${core.props.moduleName}-common")
        }
        isTransitive = false
    }
    compileOnly(project(":core-fabric", "namedElements")) {
        isTransitive = false
    }

    compileOnly(project(":event-common")) {
        capabilities {
            requireCapability("${Properties.GROUP}:${event.props.modId}-${event.props.moduleName}-common")
        }
        isTransitive = false
    }
    compileOnly(project(":event-fabric", "namedElements")) {
        isTransitive = false
    }
}

loom {
    val aw = file("src/main/resources/${project.props.modId}.accesswidener");
    if (aw.exists())
        accessWidenerPath.set(aw)
    mixin {
        defaultRefmapName.set("${project.props.modId}.refmap.json")
    }
}

tasks {
    named<ProcessResources>("processResources").configure {
        exclude("${project.props.modId}.cfg")
    }
}

publishMods {
    file.set(tasks.named<Jar>("remapJar").get().archiveFile)
    modLoaders.add("fabric")
    changelog = rootProject.file("CHANGELOG.md").readText()
    version = "${Versions.MOD}+${Versions.MINECRAFT}-fabric"
    type = STABLE

    github {
        accessToken = providers.environmentVariable("GITHUB_TOKEN")
        parent(project(":core-common").tasks.named("publishGithub"))
    }
}