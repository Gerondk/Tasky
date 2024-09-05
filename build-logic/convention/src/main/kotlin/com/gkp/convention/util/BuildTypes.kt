package com.gkp.convention.util

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    extensionType: ExtensionType,
) {
    with(commonExtension) {
        buildFeatures {
            buildConfig = true
        }
        val apiKey = gradleLocalProperties(rootDir, rootProject.providers).getProperty("API_KEY")
        when (extensionType) {
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureBuildType(
                                apiKey = "",
                                commonExtension = commonExtension,
                            )
                        }
                        release {
                            configureBuildType(
                                apiKey = "",
                                commonExtension = commonExtension,
                                isBuildTypeRelease =  true
                            )

                        }
                    }
                }

            }

            ExtensionType.LIBRARY -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureBuildType(
                                apiKey = "",
                                commonExtension = commonExtension,
                            )
                        }
                        release {
                            configureBuildType(
                                apiKey = "",
                                commonExtension = commonExtension,
                                isBuildTypeRelease = true
                            )

                        }
                    }
                }
            }
        }
    }

}

private fun BuildType.configureBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    apiKey: String,
    isBuildTypeRelease: Boolean = false,
) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://runique.pl-coding.com:8080\"")

    if (isBuildTypeRelease) {
        isMinifyEnabled = true
        proguardFiles(
            commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
    }
}

//private fun BuildType.configureDebugBuildType(apiKey: String) {
//    buildConfigField("String", "API_KEY", "\"$apiKey\"")
//    buildConfigField("String", "BASE_URL", "\"https://runique.pl-coding.com:8080\"")
//}
