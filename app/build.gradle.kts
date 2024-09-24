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

    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    implementation(projects.core.presentation.designsystem)

    implementation(projects.auth.presentation)
    implementation(projects.auth.domain)
    implementation(projects.auth.data)

    implementation(projects.agenda.presentation)
//    implementation(projects.agenda.domain)
    implementation(projects.agenda.data)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}