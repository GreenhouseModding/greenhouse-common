package dev.greenhouseteam.greenhouse_common.gradle

import gradle.kotlin.dsl.accessors._8347e3f88f0262002bff02c22a2fad22.ext
import org.gradle.api.Project

object Properties {
    const val GROUP = "dev.greenhouseteam"
    const val MOD_AUTHOR = "Greenhouse Team"
    val MOD_CONTRIBUTORS = listOf("Oliver-makes-code", "MerchantPug")
    const val LICENSE = "MPL-2.0"

    const val HOMEPAGE = "https://github.com/GreenhouseTeam/Greenhouse-Common"
    const val GITHUB_REPO = "GreenhouseTeam/Greenhouse-Common"
    const val GITHUB_COMMITISH = Versions.MOD

    val MODULES = mapOf(
        "core" to ModuleProperties("core", "Greenhouse Common", "greenhouse_common", "Common code for Greenhouse mods"),
        "event" to ModuleProperties("event", "Greenhouse Event", "greenhouse_event", "Events for Greenhouse Common"),
        "registry" to ModuleProperties("registry", "Greenhouse Registry", "greenhouse_registry", "Registry helpers for Greenhouse Common"),
    )

    val PLATFORMS = setOf(
        "common",
        "fabric",
        "neoforge"
    )

    class ModuleProperties(val moduleName: String, val modName: String, val modId: String, val description: String)
}

val Project.props: Properties.ModuleProperties
    get() = ext["props"] as Properties.ModuleProperties
