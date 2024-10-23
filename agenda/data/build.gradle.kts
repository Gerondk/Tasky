plugins {
    alias(libs.plugins.tasky.android.library)
}

android {
    namespace = "com.gkp.agenda.data"

}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
    implementation(libs.workmager.ktx)
    implementation(projects.core.network)
    implementation(projects.agenda.domain)
    implementation(projects.auth.domain)
    implementation(projects.core.database)
}