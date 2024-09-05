package com.gkp.convention

import com.gkp.convention.util.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(plugins) {
                apply("org.jetbrains.kotlin.jvm")
                configureKotlinJvm()
            }
        }
    }
}