plugins {
    alias(libs.plugins.tasky.featureui)
}

android {
    namespace = "com.gkp.auth.presentation"

}

dependencies {
   implementation(projects.auth.domain)
    implementation(projects.core.domain)
}