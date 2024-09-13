plugins {
    alias(libs.plugins.tasky.application.compose)
    alias(libs.plugins.kotlin.plugin.serialization)
}

android {
    namespace = "com.gkp.tasky"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.core.splashscreen)

    implementation(projects.core.presentation.designsystem)
    implementation(projects.auth.presentation)
    implementation(projects.auth.data)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}