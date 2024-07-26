import dev.greenhouseteam.greenhouse_common.gradle.Properties

plugins {
    id("conventions.common")
}

lateinit var props: Properties.ModuleProperties;

Properties.MODULES.forEach { (name, metadata) ->
    Properties.PLATFORMS.forEach { platform ->
        if (project.name == "${name}-${platform}")
            props = metadata
    }
}

fun getCommonProjectName() : String {
    if (!project.name.contains("-"))
        return "common"
    return project.name.split("-")[0] + "-common"
}

configurations {
    register("commonJava") {
        isCanBeResolved = true
    }
    register("commonTestJava") {
        isCanBeResolved = true
    }
    register("commonResources") {
        isCanBeResolved = true
    }
    register("commonTestResources") {
        isCanBeResolved = true
    }
}

dependencies {
    compileOnly(project(":${getCommonProjectName()}")) {
        capabilities {
            requireCapability("${Properties.GROUP}:${props.modId}-${props.moduleName}-common")
        }
    }
    testCompileOnly(project(":${getCommonProjectName()}")) {
        capabilities {
            requireCapability("${Properties.GROUP}:${props.modId}-${props.moduleName}-common")
        }
    }
    "commonJava"(project(":${getCommonProjectName()}", "commonJava"))
    "commonTestJava"(project(":${getCommonProjectName()}", "commonTestJava"))
    "commonResources"(project(":${getCommonProjectName()}", "commonResources"))
    "commonTestResources"(project(":${getCommonProjectName()}", "commonTestResources"))
}

tasks {
    named<JavaCompile>("compileJava").configure {
        dependsOn(configurations.getByName("commonJava"))
        source(configurations.getByName("commonJava"))
    }
    named<ProcessResources>("processResources").configure {
        dependsOn(configurations.getByName("commonResources"))
        from(configurations.getByName("commonResources"))
        from(configurations.getByName("commonResources"))
    }
    named<ProcessResources>("processTestResources").configure {
        dependsOn(configurations.getByName("commonTestResources"))
        from(configurations.getByName("commonTestResources"))
        from(configurations.getByName("commonTestResources"))
    }
    named<Javadoc>("javadoc").configure {
        dependsOn(configurations.getByName("commonJava"))
        source(configurations.getByName("commonJava"))
    }
    named<Jar>("sourcesJar").configure {
        dependsOn(configurations.getByName("commonJava"))
        from(configurations.getByName("commonJava"))
        dependsOn(configurations.getByName("commonResources"))
        from(configurations.getByName("commonResources"))
    }
}
