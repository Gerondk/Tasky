plugins {
    alias(libs.plugins.tasky.jvm.library)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    api(projects.core.domain)
    implementation(libs.kotlinx.serialization.json)
}