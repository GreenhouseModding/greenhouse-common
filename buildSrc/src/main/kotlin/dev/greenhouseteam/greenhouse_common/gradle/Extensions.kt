package dev.greenhouseteam.greenhouse_common.gradle

import gradle.kotlin.dsl.accessors._8347e3f88f0262002bff02c22a2fad22.ext
import org.gradle.api.Project
import java.io.File

val Project.props: Properties.ModuleProperties
    get() = ext["props"] as Properties.ModuleProperties

val Project.runDir: File
    get() = File("../../run")
