import dev.greenhouseteam.greenhouse_common.gradle.Properties
import dev.greenhouseteam.greenhouse_common.gradle.Versions
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

dependencies {
    minecraft("com.mojang:minecraft:${Versions.INTERNAL_MINECRAFT}")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:${Versions.FABRIC_LOADER}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${Versions.FABRIC_API}")
    modLocalRuntime("com.terraformersmc:modmenu:${Versions.MOD_MENU}")
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
