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
    implementation(libs.http.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}