package com.gkp.convention.util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*,*,*,*,*,*>
) {
    with(commonExtension) {
        buildFeatures {
            compose = true
        }
        dependencies {
            val bom = libs.findLibrary("androidx.compose.bom").get()
            "implementation"(platform(bom))
            "implementation"(platform(libs.findLibrary("koin.bom").get()))
            "implementation"(libs.findLibrary("koin.core").get())
            "implementation"(libs.findLibrary("koin.android").get())
            "androidTestImplementation"(platform(bom))
            "implementation"(libs.findLibrary("androidx.ui.tooling.preview").get())
            "implementation"(libs.findBundle("compose").get())
            "debugImplementation"(libs.findLibrary("androidx.ui.tooling").get())
        }
    }

}