package com.gkp.convention

import com.gkp.convention.util.configureKotlinJvm
import com.gkp.convention.util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(plugins) {
                apply("org.jetbrains.kotlin.jvm")
                configureKotlinJvm()

                dependencies {

                    "implementation"(libs.findLibrary("kotlinx.coroutines.core").get())
                }
            }
        }
    }
}