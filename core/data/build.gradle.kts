plugins {
    alias(libs.plugins.tasky.android.library)

}

android {
    namespace = "com.gkp.core.data"

}

dependencies {
   api(projects.core.domain)
}