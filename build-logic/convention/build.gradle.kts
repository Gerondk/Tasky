plugins {
    `kotlin-dsl`
}

group = "com.gkp.tasky.buildlogic"



dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("AndroidApplication") {
            id = "tasky.android.application"
            implementationClass = "com.gkp.convention.AndroidApplicationConventionPlugin"
        }
        register("AndroidApplicationCompose") {
            id = "tasky.android.application.compose"
            implementationClass = "com.gkp.convention.AndroidApplicationComposeConventionPlugin"
        }
        register("AndroidLibrary") {
            id = "tasky.android.library"
            implementationClass = "com.gkp.convention.AndroidLibraryConventionPlugin"
        }
        register("AndroidLibraryCompose") {
            id = "tasky.android.library.compose"
            implementationClass = "com.gkp.convention.AndroidLibraryComposeConventionPlugin"
        }
        register("AndroidFeatureUiConventionPlugin") {
            id = "tasky.android.featureui"
            implementationClass = "com.gkp.convention.AndroidFeatureUiConventionPlugin"
        }
        register("JvmLibrary") {
            id = "tasky.Jvm.library"
            implementationClass = "com.gkp.convention.JvmLibraryConventionPlugin"
        }
        register("AndroidRoom") {
            id = "tasky.android.room"
            implementationClass = "com.gkp.convention.AndroidRoomConventionPlugin"
        }
    }
}