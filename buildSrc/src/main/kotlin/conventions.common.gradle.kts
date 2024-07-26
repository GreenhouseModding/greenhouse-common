import dev.greenhouseteam.greenhouse_common.gradle.Properties
import dev.greenhouseteam.greenhouse_common.gradle.Versions

plugins {
    base
    `java-library`
    idea
    `maven-publish`
}

lateinit var props: Properties.ModuleProperties
lateinit var platform: String

Properties.MODULES.forEach { (name, metadata) ->
    Properties.PLATFORMS.forEach { platform ->
        if (project.name == "${name}-${platform}") {
            props = metadata
            this.platform = platform
        }
    }
}

project.ext["props"] = props

base.archivesName.set("${props.modId}-${platform}")
group = Properties.GROUP
version = "${Versions.MOD}+${Versions.MINECRAFT}"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(Versions.JAVA))
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
    // https://docs.gradle.org/current/userguide/declaring_repositories.html#declaring_content_exclusively_found_in_one_repository
    exclusiveContent {
        forRepository {
            maven("https://repo.spongepowered.org/repository/maven-public") {
                name = "Sponge"
            }
        }
        filter { includeGroupAndSubgroups("org.spongepowered") }
    }
    exclusiveContent {
        forRepositories(
            maven("https://maven.parchmentmc.org/") {
                name = "ParchmentMC"
            },
            maven("https://maven.neoforged.net/releases") {
                name = "NeoForge"
            }
        )
        filter { includeGroup("org.parchmentmc.data") }
    }
    maven("https://maven.fabricmc.net/") {
        name = "Fabric"
    }
}

dependencies {
    implementation("org.jetbrains:annotations:24.1.0")
}

// Declare capabilities on the outgoing configurations.
// Read more about capabilities here: https://docs.gradle.org/current/userguide/component_capabilities.html#sec:declaring-additional-capabilities-for-a-local-component
setOf("apiElements", "runtimeElements", "sourcesElements", "javadocElements").forEach { variant ->
    configurations.getByName(variant).outgoing {
        capability("$group:${props.modId}-${project.name}:$version")
    }
    publishing.publications.forEach { publication ->
        if (publication is MavenPublication) {
            publication.suppressPomMetadataWarningsFor(variant)
        }
    }
}

tasks {
    named<Jar>("sourcesJar").configure {
        from(rootProject.file("LICENSE")) {
            rename { "${it}_${props.modName}" }
        }
    }
    named<Jar>("jar").configure {
        from(rootProject.file("LICENSE")) {
            rename { "${it}_${props.modName}" }
        }

        manifest {
            attributes["Specification-Title"] = props.modName
            attributes["Specification-Vendor"] = Properties.MOD_AUTHOR
            attributes["Specification-Version"] = archiveVersion
            attributes["Implementation-Title"] = project.name
            attributes["Implementation-Version"] = archiveVersion
            attributes["Implementation-Vendor"] = Properties.MOD_AUTHOR
            attributes["Built-On-Minecraft"] = Versions.INTERNAL_MINECRAFT
        }
    }

    val expandProps = mapOf(
        "mod_version" to Versions.MOD,
        "group" to project.group, //Else we target the task's group.
        "minecraft_version" to Versions.MINECRAFT,
        "fabric_api_version" to Versions.FABRIC_API,
        "fabric_loader_version" to Versions.FABRIC_LOADER,
        "fabric_minecraft_version_range" to Versions.FABRIC_MINECRAFT_RANGE,
        "fabric_loader_range" to Versions.FABRIC_LOADER_RANGE,
        "mod_name" to props.modName,
        "mod_author" to Properties.MOD_AUTHOR,
        "neoforge_mod_contributors" to Properties.MOD_CONTRIBUTORS.joinToString(prefix = ", "),
        "fabric_mod_contributors" to Properties.MOD_CONTRIBUTORS.joinToString(prefix = "\"\n,\t\t\""),
        "mod_id" to props.modId,
        "mod_license" to Properties.LICENSE,
        "mod_description" to props.description,
        "neoforge_version" to Versions.NEOFORGE,
        "neoforge_minecraft_version_range" to Versions.NEOFORGE_MINECRAFT_RANGE,
        "neoforge_loader_version_range" to Versions.NEOFORGE_LOADER_RANGE,
        "java_version" to Versions.JAVA,
        "homepage" to Properties.HOMEPAGE,
        "sources" to Properties.GITHUB_REPO
    )

    val processResourcesTasks = listOf("processResources", "processTestResources", "processDatagenResources")

    withType<ProcessResources>().matching { processResourcesTasks.contains(it.name) }.configureEach {
        inputs.properties(expandProps)
        filesMatching(setOf("fabric.mod.json", "META-INF/neoforge.mods.toml", "*.mixins.json")) {
            expand(expandProps)
        }
        exclude("\\.cache")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = base.archivesName.get()
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "Greenhouse"
            url = uri("https://maven.greenhouseteam.dev/releases")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}