plugins {
    alias(libs.plugins.tasky.android.library)
}

android {
    namespace = "com.gkp.data"
}

dependencies {
    implementation(projects.auth.domain)
    api(projects.core.network)
    implementation(libs.kotlinx.coroutines.core)
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
}