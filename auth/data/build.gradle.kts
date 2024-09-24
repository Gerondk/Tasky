plugins {
    alias(libs.plugins.tasky.android.library)
    alias(libs.plugins.kotlin.plugin.serialization)
}

android {
    namespace = "com.gkp.data"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.data)
    api(projects.core.network)
    implementation(libs.kotlinx.coroutines.core)
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
    implementation(libs.androidx.datastore)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.security.crypto.ktx)
}