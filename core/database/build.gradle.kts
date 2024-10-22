plugins {
    alias(libs.plugins.tasky.android.library)
    alias(libs.plugins.tasky.android.room)
}

android {
    namespace = "com.gkp.core.database"

}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
    implementation(projects.agenda.domain)
}