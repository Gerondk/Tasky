plugins {
    alias(libs.plugins.tasky.featureui)
}

android {
    namespace = "com.gkp.auth.presentation"

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}