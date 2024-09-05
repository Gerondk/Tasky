package com.gkp.convention

import com.android.build.api.dsl.LibraryExtension
import com.gkp.convention.util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureUiConventionPlugin : Plugin<Project>  {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("tasky.android.library.compose")
            }
            dependencies {
                "implementation"(project.libs.findBundle("koin.compose").get())
                "implementation"(project.libs.findBundle("compose").get())
                "implementation"(project.libs.findLibrary("androidx.navigation.compose").get())
                "debugImplementation"(project.libs.findBundle("compose.debug").get())
                "androidTestImplementation"(project.libs.findLibrary("androidx.compose.ui.test.junit4").get())
            }
        }
    }
}