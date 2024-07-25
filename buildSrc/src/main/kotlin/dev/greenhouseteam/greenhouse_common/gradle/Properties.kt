package dev.greenhouseteam.greenhouse_common.gradle

object Properties {
    const val GROUP = "dev.greenhouseteam"
    const val MOD_NAME = "Greenhouse Common"
    const val MOD_ID = "greenhouse_common"
    const val MOD_AUTHOR = "Greenhouse Team"
    const val MOD_CONTRIBUTORS = "Oliver-makes-code, MerchantPug"
    const val DESCRIPTION = "Common code for Greenhouse mods"
    const val LICENSE = "MPL-2.0"

    const val HOMEPAGE = "https://github.com/GreenhouseTeam/Greenhouse-Common"
    const val GITHUB_REPO = "GreenhouseTeam/Greenhouse-Common"
    const val GITHUB_COMMITISH = Versions.MOD

    val MODULES = setOf("core", "event", "registry")
}
