package com.gkp.convention

import androidx.room.gradle.RoomExtension
import com.gkp.convention.util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                pluginManager.apply("androidx.room")
                pluginManager.apply("com.google.devtools.ksp")

                extensions.configure<RoomExtension> {
                    schemaDirectory("$projectDir/schemas")
                }

                dependencies {
                    "implementation"(libs.findLibrary("room.runtime").get())
                    "implementation"(libs.findLibrary("room.ktx").get())
                    "ksp"(libs.findLibrary("room.compiler").get())
                    "annotationProcessor"(libs.findLibrary("room.compiler").get())
                }

            }
        }
    }
}