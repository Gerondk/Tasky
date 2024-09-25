plugins {
    alias(libs.plugins.tasky.featureui)
}

android {
    namespace = "com.gkp.agenda.presentation"
}

dependencies {
    implementation(projects.agenda.domain)
    implementation(projects.core.presentation.designsystem)
    implementation(projects.auth.domain)
}