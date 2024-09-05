import org.jetbrains.kotlin.com.intellij.psi.search.GlobalSearchScope

plugins {
    alias(libs.plugins.tasky.application.compose)
    alias(libs.plugins.kotlin.plugin.serialization)
}

android {
    namespace = "com.gkp.tasky"

//    defaultConfig {
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        vectorDrawables {
//            useSupportLibrary = true
//        }
//    }
//
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.kotlinx.serialization.json)

    implementation(projects.core.presentation.designsystem)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}