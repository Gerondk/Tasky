plugins {
    alias(libs.plugins.tasky.android.library)
    alias(libs.plugins.kotlin.plugin.serialization)

}

android {
    namespace = "com.gkp.network"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    api(libs.http.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(projects.auth.domain)
    implementation(libs.kotlinx.coroutines.core)
}